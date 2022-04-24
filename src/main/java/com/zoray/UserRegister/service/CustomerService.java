package com.zoray.UserRegister.service;

import com.zoray.UserRegister.dto.customerDto.CustomerCreateDTO;
import com.zoray.UserRegister.dto.customerDto.CustomerUpdateDTO;
import com.zoray.UserRegister.dto.customerDto.CustomerViewDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {

    List<CustomerViewDTO> getCustomers();

    CustomerViewDTO createCustomer(CustomerCreateDTO employeeCreateDTO);

    CustomerViewDTO updateCustomer(Long id, CustomerUpdateDTO employeeUpdateDTO);

    void deleteCustomer(Long id);
}
