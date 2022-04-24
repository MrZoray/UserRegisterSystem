package com.zoray.UserRegister.dto.customerDto;

import com.zoray.UserRegister.model.Customer;
import lombok.Data;

@Data
public class CustomerViewDTO {

    private String customerFullName;
    private String customerEmail;

    public CustomerViewDTO(String customerFullName, String customerEmail) {
        this.customerFullName = customerFullName;
        this.customerEmail = customerEmail;
    }

    public static CustomerViewDTO of(Customer customer) {
        return new CustomerViewDTO(customer.getCustomerFullName(), customer.getCustomerEmail());
    }

}
