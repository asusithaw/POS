package com.springbootacademy.pointofsale.util.mappers;

import com.springbootacademy.pointofsale.dto.CustomerDTO;
import com.springbootacademy.pointofsale.dto.response.ResponseActiveCustomerDTO;
import com.springbootacademy.pointofsale.dto.response.ResponseCustomerFilterDTO;
import com.springbootacademy.pointofsale.entity.Customer;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-14T15:51:37+0530",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 11.0.14 (Oracle Corporation)"
)
@Component
public class CustomerMapperImpl implements CustomerMapper {

    @Override
    public CustomerDTO entityToDto(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        CustomerDTO customerDTO = new CustomerDTO();

        customerDTO.setCustomerId( customer.getCustomerId() );
        customerDTO.setCustomerName( customer.getCustomerName() );
        customerDTO.setCustomerAddress( customer.getCustomerAddress() );
        customerDTO.setCustomerSalary( customer.getCustomerSalary() );
        customerDTO.setNic( customer.getNic() );
        ArrayList arrayList = customer.getContactNumber();
        if ( arrayList != null ) {
            customerDTO.setContactNumber( new ArrayList( arrayList ) );
        }
        customerDTO.setActiveState( customer.isActiveState() );

        return customerDTO;
    }

    @Override
    public List<CustomerDTO> entityListToDtoList(List<Customer> customerList) {
        if ( customerList == null ) {
            return null;
        }

        List<CustomerDTO> list = new ArrayList<CustomerDTO>( customerList.size() );
        for ( Customer customer : customerList ) {
            list.add( entityToDto( customer ) );
        }

        return list;
    }

    @Override
    public List<ResponseActiveCustomerDTO> entityListToResponseDtoListOnlyName(List<Customer> customers) {
        if ( customers == null ) {
            return null;
        }

        List<ResponseActiveCustomerDTO> list = new ArrayList<ResponseActiveCustomerDTO>( customers.size() );
        for ( Customer customer : customers ) {
            list.add( customerToResponseActiveCustomerDTO( customer ) );
        }

        return list;
    }

    @Override
    public ResponseCustomerFilterDTO entityToResponseDto(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        ResponseCustomerFilterDTO responseCustomerFilterDTO = new ResponseCustomerFilterDTO();

        responseCustomerFilterDTO.setCustomerId( customer.getCustomerId() );
        responseCustomerFilterDTO.setCustomerAddress( customer.getCustomerAddress() );
        responseCustomerFilterDTO.setCustomerSalary( customer.getCustomerSalary() );

        return responseCustomerFilterDTO;
    }

    protected ResponseActiveCustomerDTO customerToResponseActiveCustomerDTO(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        ResponseActiveCustomerDTO responseActiveCustomerDTO = new ResponseActiveCustomerDTO();

        responseActiveCustomerDTO.setCustomerName( customer.getCustomerName() );
        ArrayList arrayList = customer.getContactNumber();
        if ( arrayList != null ) {
            responseActiveCustomerDTO.setContactNumber( new ArrayList( arrayList ) );
        }

        return responseActiveCustomerDTO;
    }
}
