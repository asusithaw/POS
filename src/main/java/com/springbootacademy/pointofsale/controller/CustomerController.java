package com.springbootacademy.pointofsale.controller;

import com.springbootacademy.pointofsale.dto.CustomerDTO;
import com.springbootacademy.pointofsale.dto.request.CustomerUpdateByDTO;
import com.springbootacademy.pointofsale.dto.request.RequestCustomerUpdateQueryDTO;
import com.springbootacademy.pointofsale.dto.request.RequestUpdateCustomerDTO;
import com.springbootacademy.pointofsale.dto.response.ResponseActiveCustomerDTO;
import com.springbootacademy.pointofsale.dto.response.ResponseCustomerFilterDTO;
import com.springbootacademy.pointofsale.service.CustomerService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customer")
@CrossOrigin
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping(path = "/save")
    public String saveCustomer(@RequestBody CustomerDTO customerDTO) {
        /*
        __Past Method___
        CustomerServiceIMPL customerServiceIMPL = new CustomerServiceIMPL();
        customerServiceIMPL.saveCustomer(customerDTO);
        ----USE DEPENDANCY INJECTION------
        */
        customerService.saveCustomer(customerDTO);
        return "save";
    }

    @PutMapping(path = "/update")
    public String updateCustomer(@RequestBody RequestUpdateCustomerDTO customerDTO) {
        String updated = customerService.updateCustomer(customerDTO);
        return updated;
    }

    @GetMapping(
            path = "/get-by-id",
            params = "Id"
    )
    public CustomerDTO getCustomerById(@RequestParam(value = "Id") int customerId) {
        //System.out.println("Customer Id is"+customerId);
        CustomerDTO customerDTO = customerService.getCustomerById(customerId);
        return customerDTO;
    }

    @GetMapping(path = "get-all-customers")
    public List<CustomerDTO> getAllCustomers() {
        List<CustomerDTO> allCustomers = customerService.getAllCustomers();
        return allCustomers;
    }

    @DeleteMapping(
            path = {"/delete-customer/{id}"}
    )
    public String deleteCustomer(@PathVariable(value = "id") int id) throws NotFoundException {
        boolean deleteCustomer = customerService.deleteCustomer(id);
        return "Deleted";
    }

    @GetMapping(
            path = {"/get-by-name"},
            params = {"name"}
    )
    public List<CustomerDTO> getCustomerByName(@RequestParam(value = "name") String customerName) throws NotFoundException {
        List<CustomerDTO> getCustomer = customerService.getByName(customerName);
        return getCustomer;
    }

    @GetMapping(
            path = {"/get-by-active-state"}
    )
    public List<CustomerDTO> getCustomerByActiveState() throws NotFoundException {
        List<CustomerDTO> getCustomer = customerService.getAllCustomersByActiveState();
        return getCustomer;
    }

    @GetMapping(
            path = {"/get-by-active-state-only-name"}
    )
    public List<ResponseActiveCustomerDTO> getCustomerByActiveStateOnlyName() throws NotFoundException {
        List<ResponseActiveCustomerDTO> getCustomer = customerService.getAllCustomersByActiveStateOnlyName();
        return getCustomer;
    }

    @PutMapping(path = "/update-query/{id}")
    public String updateCustomerByQuery(
            @RequestBody RequestCustomerUpdateQueryDTO customerDTO,
            @PathVariable(value = "id") int id) {
        String updated = customerService.updateCustomerByQuery(customerDTO, id);
        return updated;
    }

    @GetMapping(
            path = {"/get-by-nic/{nic}"}
    )
    public CustomerDTO getCustomerByNic(@PathVariable(value = "nic") String nic) {
        CustomerDTO getCustomer = customerService.getCustomersByNic(nic);
        return getCustomer;
    }

    @GetMapping(
            path = {"/get-by-id-filter"},
            params = {"id"}
    )

    public ResponseCustomerFilterDTO getCustomerByIdFilter(@RequestParam(value = "id") int id) {
        ResponseCustomerFilterDTO responseCustomerFilterDTO = customerService.getCustomerByIdByFilter(id);
        return responseCustomerFilterDTO;
    }

    @PutMapping(
            path = {"/update-by-request/{id}"}
    )
    public String updateCustomerByRequest(@RequestBody CustomerUpdateByDTO customerUpdateByDTO,
                                          @PathVariable(value = "id") int id) {
        String updated = customerService.updateCustomerByRequest(customerUpdateByDTO, id);
        return updated;
    }

    @GetMapping(
            path = "/get-by-id-is-active",
            params = "Id"
    )
    public CustomerDTO getCustomerByIdIsActive(@RequestParam(value = "Id") int customerId) {
        //System.out.println("Customer Id is"+customerId);
        CustomerDTO customerDTO = customerService.getCustomerByIdIsActive(customerId);
        return customerDTO;
    }

}
