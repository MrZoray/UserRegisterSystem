package com.zoray.UserRegister.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username", length = 50)
    @NotNull
    private String userName;

    @Column(name = "password", length = 50)
    @NotNull
    private String password;


    public User(@NotNull String userName, @NotNull String password) {
        this.userName = userName;
        this.password = password;
    }
}
