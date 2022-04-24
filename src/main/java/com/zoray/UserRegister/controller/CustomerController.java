package com.zoray.UserRegister.controller;

import com.zoray.UserRegister.dto.customerDto.CustomerCreateDTO;
import com.zoray.UserRegister.dto.customerDto.CustomerUpdateDTO;
import com.zoray.UserRegister.dto.customerDto.CustomerViewDTO;
import com.zoray.UserRegister.service.Impl.CustomerServiceImpl;
import com.zoray.UserRegister.shared.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerController {

    @Autowired
    CustomerServiceImpl customerService;

    @PostMapping("/createCustomer")
    public ResponseEntity<?> createCustomer(@Valid @RequestBody CustomerCreateDTO customerCreateDTO) {

        try {
            customerService.createCustomer(customerCreateDTO);
            return ResponseEntity.ok(new GenericResponse("Customer Created."));
        } catch (Exception e) {
            return ResponseEntity.ok(new GenericResponse("Customer Not Created."));
        }

    }

    @PutMapping("/updateCustomer/{id}")
    public ResponseEntity<CustomerViewDTO> updateCustomer(@Valid @PathVariable("id") Long id,
                                                          @RequestBody CustomerUpdateDTO customerUpdateDTO) {
        final CustomerViewDTO customerViewDTO = customerService.updateCustomer(id, customerUpdateDTO);
        return ResponseEntity.ok(customerViewDTO);
    }

    @GetMapping("/getCustomers")
    public ResponseEntity<List<CustomerViewDTO>> getCustomers() {
        final List<CustomerViewDTO> customersList = customerService.getCustomers();
        return ResponseEntity.ok(customersList);
    }

    @DeleteMapping("/deleteCustomer/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable("id") Long id) {
        try {
            customerService.deleteCustomer(id);
            return ResponseEntity.ok(new GenericResponse("Customer Deleted !"));
        } catch (Exception e) {
            return ResponseEntity.ok(new GenericResponse("Customer Not Deleted."));
        }
    }

}
