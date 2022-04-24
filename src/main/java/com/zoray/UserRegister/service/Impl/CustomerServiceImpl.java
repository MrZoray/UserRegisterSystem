package com.zoray.UserRegister.service.Impl;

import com.zoray.UserRegister.dto.customerDto.CustomerCreateDTO;
import com.zoray.UserRegister.dto.customerDto.CustomerUpdateDTO;
import com.zoray.UserRegister.dto.customerDto.CustomerViewDTO;
import com.zoray.UserRegister.execption.ResourceNotFoundException;
import com.zoray.UserRegister.model.Customer;
import com.zoray.UserRegister.repository.CustomerRepository;
import com.zoray.UserRegister.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<CustomerViewDTO> getCustomers() {
        return customerRepository.findAll().stream().map(CustomerViewDTO::of).collect(Collectors.toList());
    }

    @Transactional
    public CustomerViewDTO createCustomer(CustomerCreateDTO customerCreateDTO) {
        final Customer customer = customerRepository
                .save(new Customer(customerCreateDTO.getCustomerFullName(), customerCreateDTO.getCustomerEmail()));
        return CustomerViewDTO.of(customer);
    }

    @Transactional
    public CustomerViewDTO updateCustomer(Long id, CustomerUpdateDTO customerUpdateDTO) {
        final Customer customer = customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not Found Exception"));

        customer.setCustomerFullName(customerUpdateDTO.getCustomerFullName());
        customer.setCustomerEmail(customerUpdateDTO.getCustomerEmail());

        final Customer updatedCustomer = customerRepository.save(customer);

        return CustomerViewDTO.of(updatedCustomer);
    }

    @Transactional
    public void deleteCustomer(Long id) {
        final Customer customer = customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not Found Exception"));
        customerRepository.deleteById(customer.getId());
    }


}
