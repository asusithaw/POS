package com.springbootacademy.pointofsale.service;

import com.springbootacademy.pointofsale.dto.CustomerDTO;
import com.springbootacademy.pointofsale.dto.request.CustomerUpdateByDTO;
import com.springbootacademy.pointofsale.dto.request.RequestCustomerUpdateQueryDTO;
import com.springbootacademy.pointofsale.dto.request.RequestUpdateCustomerDTO;
import com.springbootacademy.pointofsale.dto.response.ResponseActiveCustomerDTO;
import com.springbootacademy.pointofsale.dto.response.ResponseCustomerFilterDTO;
import javassist.NotFoundException;

import java.util.List;
public interface CustomerService {

    String saveCustomer(CustomerDTO customerDTO);

    String updateCustomer(RequestUpdateCustomerDTO customerDTO);

    CustomerDTO getCustomerById(int customerId);

    List<CustomerDTO> getAllCustomers();

    boolean deleteCustomer(int id) throws NotFoundException;

    List<CustomerDTO> getByName(String customerName) throws NotFoundException;

    List<CustomerDTO> getAllCustomersByActiveState() throws NotFoundException;

    List<ResponseActiveCustomerDTO> getAllCustomersByActiveStateOnlyName() throws NotFoundException;

    String updateCustomerByQuery(RequestCustomerUpdateQueryDTO customerDTO, int id);

    CustomerDTO getCustomersByNic(String nic);

    ResponseCustomerFilterDTO getCustomerByIdByFilter(int id);

    String updateCustomerByRequest(CustomerUpdateByDTO customerUpdateByDTO, int id);

    CustomerDTO getCustomerByIdIsActive(int customerId);
}
