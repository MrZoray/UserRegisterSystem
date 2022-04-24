package com.zoray.UserRegister.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "customer_fullname", length = 100)
    @NotNull
    private String customerFullName;

    @Column(name = "customer_email", length = 100)
    @NotNull
    private String customerEmail;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<File> files;


    public Customer(String customerFullName, String customerEmail) {
        this.customerFullName = customerFullName;
        this.customerEmail = customerEmail;
    }


}
