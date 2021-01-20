package com.jwt.validator.service.impl;

import com.jwt.validator.model.entity.User;
import com.jwt.validator.model.objects.CustomResponse;
import com.jwt.validator.model.objects.LoginRequest;
import com.jwt.validator.model.objects.UserDetails;
import com.jwt.validator.model.repository.UserRepo;
import com.jwt.validator.service.UserService;
import com.jwt.validator.service.ValidateTokenService;
import com.jwt.validator.utils.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements UserService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    ValidateTokenService tokenService;

    @Override
    public CustomResponse loginUser(LoginRequest request) {
        CustomResponse response = new CustomResponse();
        User user = userRepo.findByUsername(request.getUser());
        if(user != null) {
            boolean passwordMatch = (request.getPassword().equals(user.getPassword()));
            if(passwordMatch) {
                UserDetails userDetails = new UserDetails();
                userDetails.setKey(user.getIdPlatform().getKeyValue());
                String token = tokenService.generateToken(userDetails);
                response.setCode(Utils.CODE_OK);
                response.setResponse("Logueo correcto");
                response.setObjectResponse(token);
                return response;
            } else {
                response.setCode(Utils.CODE_UNAUTORIZED);
                response.setResponse("Contraseña incorrecta");
            }
        } else {
            response.setCode(Utils.CODE_NOT_FOUND);
            response.setResponse("No existe usuario con el correo ingresado");
        }
        return response;
    }
    
}
