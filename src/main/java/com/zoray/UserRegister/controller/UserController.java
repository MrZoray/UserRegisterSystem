package com.zoray.UserRegister.controller;

import com.zoray.UserRegister.dto.userDto.UserRegisterDTO;
import com.zoray.UserRegister.service.Impl.UserServiceImpl;
import com.zoray.UserRegister.shared.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
public class UserController {

    @Autowired
    UserServiceImpl userService;


    @PostMapping("/login")
    public ResponseEntity login(@Valid @RequestBody UserRegisterDTO userRegisterDTO) {

        try {
            userService.login(userRegisterDTO.getUserName(), userRegisterDTO.getUserPassword());
            return ResponseEntity.ok(new GenericResponse("redirect:/"));
        } catch (Exception e) {
            return ResponseEntity.ok(new GenericResponse("redirect:/login"));
        }

    }

    @RequestMapping(value = {"/logout"}, method = RequestMethod.POST)
    public String logoutDo(HttpServletRequest request, HttpServletResponse response) {
        return "redirect:/login";
    }

}
