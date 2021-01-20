package com.jwt.validator.controller;

import com.jwt.validator.model.objects.CustomResponse;
import com.jwt.validator.model.objects.LoginRequest;
import com.jwt.validator.service.UserService;
import com.jwt.validator.service.ValidateTokenService;
import com.jwt.validator.utils.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class DefaultController {
    @Autowired
    UserService userService;

    @Autowired
    ValidateTokenService validateToken;

    @PostMapping("/login")
    public ResponseEntity<CustomResponse> login(@RequestBody LoginRequest loginRequest) {
        CustomResponse response = null;
        try {
            response = userService.loginUser(loginRequest);
        } catch(Exception e) {
            response = new CustomResponse();
            response.setCode(Utils.CODE_ERROR);
            response.response(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/validateToken")
    public ResponseEntity<CustomResponse> validate( @RequestHeader("token") String token) {
        CustomResponse response = new CustomResponse();
        try {
            response = validateToken.validateToken(token);
        } catch(Exception e) {
            response = new CustomResponse();
            response.setCode(Utils.CODE_ERROR);
            response.response(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
