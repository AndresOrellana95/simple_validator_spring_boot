package com.jwt.validator.service;

import com.jwt.validator.model.objects.CustomResponse;
import com.jwt.validator.model.objects.LoginRequest;

public interface UserService {
    public CustomResponse loginUser(LoginRequest request);
}
