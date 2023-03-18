package com.springbootacademy.pointofsale.service.impl;

import com.springbootacademy.pointofsale.dto.CustomerDTO;
import com.springbootacademy.pointofsale.dto.request.CustomerUpdateByDTO;
import com.springbootacademy.pointofsale.dto.request.RequestCustomerUpdateQueryDTO;
import com.springbootacademy.pointofsale.dto.request.RequestUpdateCustomerDTO;
import com.springbootacademy.pointofsale.dto.response.ResponseActiveCustomerDTO;
import com.springbootacademy.pointofsale.dto.response.ResponseCustomerFilterDTO;
import com.springbootacademy.pointofsale.entity.Customer;
import com.springbootacademy.pointofsale.exception.EntryNotFoundException;
import com.springbootacademy.pointofsale.repo.CustomerRepo;
import com.springbootacademy.pointofsale.service.CustomerService;
import com.springbootacademy.pointofsale.util.mappers.CustomerMapper;
import javassist.NotFoundException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceIMPL implements CustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public String saveCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer(
                customerDTO.getCustomerId(),
                customerDTO.getCustomerName(),
                customerDTO.getCustomerAddress(),
                customerDTO.getCustomerSalary(),
                customerDTO.getNic(),
                customerDTO.getContactNumber(),
                customerDTO.isActiveState()
        );

        if (customerRepo.existsById(customer.getCustomerId())) {
            throw new DuplicateKeyException("Customer Already exists");
        } else {
            customerRepo.save(customer);
            return customer.getCustomerName() + "Saved";
        }

    }

    @Override
    public String updateCustomer(RequestUpdateCustomerDTO customerDTO) {
        if (customerRepo.existsById(customerDTO.getCustomerId())) {
            Customer customer = customerRepo.getById(customerDTO.getCustomerId());
            customer.setCustomerName(customerDTO.getCustomerName());
            customer.setCustomerAddress(customerDTO.getCustomerAddress());
            customer.setCustomerSalary(customerDTO.getCustomerSalary());
            customerRepo.save(customer);
            return "Saved" + customer.getCustomerId() + " " + customer.getCustomerName();
        } else {
            throw new EntryNotFoundException("Customer Not in Database");
        }
    }

    @Override
    public CustomerDTO getCustomerById(int customerId) {
        /*Customer customer = customerRepo.getById(customerId);
        //Next we have to convert the Entity to DTO type to return to the front-end.(Understand vice-versa)
        if (customer!=null) {
            CustomerDTO customerDTO = new CustomerDTO(
                    customer.getCustomerId(),
                    customer.getCustomerName(),
                    customer.getCustomerAddress(),
                    customer.getCustomerSalary(),
                    customer.getNic(),
                    customer.getContactNumber(),
                    customer.isActiveState()
            );
            return customerDTO;
        }else{
            throw new RuntimeException("No Customer Found In THat Id" +customerId);

        }


    }*/
        Optional<Customer> customer = customerRepo.findById(customerId);
        if (customer.isPresent()) {
//            CustomerDTO customerDTO;
//            customerDTO = new CustomerDTO(
//
//                    customer.get().getCustomerId(),
//                    customer.get().getCustomerName(),
//                    customer.get().getCustomerAddress(),
//                    customer.get().getCustomerSalary(),
//                    customer.get().getNic(),
//                    customer.get().getContactNumber(),
//                    customer.get().isActiveState());
//            return customerDTO;
//            CustomerDTO customerDTO = modelMapper.map(customer.get(), CustomerDTO.class);
//            return customerDTO;
            CustomerDTO customerDTO = customerMapper.entityToDto(customer.get());
            return customerDTO;

        } else
            throw new RuntimeException("Customer Not Found");
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> getCustomers = customerRepo.findAll();//------55 records have
        List<CustomerDTO> customerDTOList = new ArrayList<>();

        //Customer c1 = getCustomers.get(0);

//        for (Customer customer : getCustomers) {
//            CustomerDTO customerDTO = new CustomerDTO(
//                    customer.getCustomerId(),
//                    customer.getCustomerName(),
//                    customer.getCustomerAddress(),
//                    customer.getCustomerSalary(),
//                    customer.getNic(),
//                    customer.getContactNumber(),
//                    customer.isActiveState()
//            );
//            customerDTOList.add(customerDTO);
//        }
//        List<CustomerDTO> customerDTOS = modelMapper
//                .map(getCustomers, new TypeToken<List<CustomerDTO>>() {
//                }
//                        .getType());
        List<CustomerDTO> customerDTOS = customerMapper.entityListToDtoList(getCustomers);

        return customerDTOS;
    }

    @Override
    public boolean deleteCustomer(int id) throws NotFoundException {

        if (customerRepo.existsById(id)) {
            customerRepo.deleteById(id);
        } else {
            throw new NotFoundException("not found customer for this id");
        }
        return true;
    }

    @Override
    public List<CustomerDTO> getByName(String customerName) throws NotFoundException {

        List<Customer> customers = customerRepo.findAllByCustomerNameEquals(customerName);
        if (customers.size() != 0) {
            List<CustomerDTO> customerDTOS = modelMapper
                    .map(customers, new TypeToken<List<CustomerDTO>>() {
                    }.getType());
            return customerDTOS;
        } else {
            throw new NotFoundException("No Results for the name");
        }

    }

    @Override
    public List<CustomerDTO> getAllCustomersByActiveState() throws NotFoundException {
//        boolean b = true;
        List<Customer> customers = customerRepo.findAllByActiveStateEquals(true);
        if (customers.size() != 0) {
            List<CustomerDTO> customerDTOS = customerMapper.entityListToDtoList(customers);
            return customerDTOS;
        } else {
            throw new NotFoundException("No Customers in true active state");
        }

    }

    @Override
    public List<ResponseActiveCustomerDTO> getAllCustomersByActiveStateOnlyName() throws NotFoundException {
        List<Customer> customers = customerRepo.findAllByActiveStateEquals(true);
        if (customers.size() != 0) {
            List<ResponseActiveCustomerDTO> customerDTOS = customerMapper.entityListToResponseDtoListOnlyName(customers);
            return customerDTOS;
        } else {
            throw new NotFoundException("No any active customer");
        }
    }

    @Override
    public String updateCustomerByQuery(RequestCustomerUpdateQueryDTO customerDTO, int id) {
        if (customerRepo.existsById(id)) {
//           Customer customer = customerRepo.getById(id);
//           customer.setCustomerName(customerDTO.getCustomerName());
            customerRepo.updateCustomerByQuery(customerDTO.getCustomerName(), customerDTO.getNic(), id);
            return "updated Successful for id" + id;
        } else
            return "No customer found for this id" + id;
    }

    @Override
    public CustomerDTO getCustomersByNic(String nic) {
        Optional<Customer> customer = customerRepo.findByNic(nic);
        if (customer.isPresent()) {
//            CustomerDTO customerDTO = customerMapper.entityToDto(customer.get());
            CustomerDTO customerDTO = modelMapper.map(customer.get(), CustomerDTO.class);
            return customerDTO;

        } else
            throw new com.springbootacademy.pointofsale.exception.NotFoundException("Not Found");
    }

    @Override
    public ResponseCustomerFilterDTO getCustomerByIdByFilter(int id) {
        Optional<Customer> customer = customerRepo.findById(id);
        if (customer.isPresent()) {
            ResponseCustomerFilterDTO responseCustomerFilterDTO = customerMapper.entityToResponseDto(customer.get());

            return responseCustomerFilterDTO;

        } else
            throw new com.springbootacademy.pointofsale.exception.NotFoundException("Not Found");
    }

    @Override
    public String updateCustomerByRequest(CustomerUpdateByDTO customerUpdateByDTO, int id) {
        if (customerRepo.existsById(id)) {
            Customer customer = customerRepo.getById(id);
            customer.setCustomerName(customerUpdateByDTO.getCustomerName());
            customer.setCustomerSalary(customerUpdateByDTO.getCustomerSalary());
            customer.setNic(customerUpdateByDTO.getNic());
            customerRepo.save(customer);
            return "Saved" + customer.getCustomerId() + " " + customer.getCustomerName();
        } else {
            throw new EntryNotFoundException("Customer Not in Database");
        }
    }

    @Override
    public CustomerDTO getCustomerByIdIsActive(int customerId) {
        Optional<Customer> customer = customerRepo.findById(customerId);
        if (customer.isPresent()) {
            if (customer.get().isActiveState()==true) {
                CustomerDTO customerDTO = modelMapper.map(customer.get(),CustomerDTO.class);
                return customerDTO;
            }else {
                System.out.println("This is inactive Customer");
            }
        }else {
            throw new com.springbootacademy.pointofsale.exception.NotFoundException("Not Found");
        }
      // return null;
            return new CustomerDTO(
                    customer.get().getCustomerName()+"is not active"+customerId
        );
    }

}

