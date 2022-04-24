package com.zoray.UserRegister.shared;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GenericResponse extends Throwable {

    private String message;
}
