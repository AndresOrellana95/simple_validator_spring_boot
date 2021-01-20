package com.jwt.validator.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import com.jwt.validator.model.entity.Platform;
import com.jwt.validator.model.objects.CustomResponse;
import com.jwt.validator.model.objects.UserDetails;
import com.jwt.validator.model.repository.PlatformRepo;
import com.jwt.validator.service.ValidateTokenService;
import com.jwt.validator.utils.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class ValidateTokenServiceImpl implements ValidateTokenService {

    @Autowired
    PlatformRepo platformRepo;

    @Value("${app.config.secretKey}")
    private String SECRET_KEY;

    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<String, Object>();
        claims.put("key", userDetails.getKey());
        return createToken(claims, userDetails.getKey());
    }

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)).signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    public CustomResponse validateToken(String token) {
        CustomResponse response = new CustomResponse();
        if(!isTokenExpired(token)) {
            String keyValue = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().get("key", String.class);
            Platform platform = platformRepo.findByKeyValue(keyValue);
            if(platform != null) {
                Map map = new HashMap<String, String>();
                map.put("value1", platform.getValue1());
                map.put("value2", platform.getValue2());
                response.setCode(Utils.CODE_OK);
                response.setResponse("Operación exitosa");
                response.setObjectResponse(map);
            } else {
                response.setCode(Utils.CODE_ERROR);
                response.response("Token inválido");
            }
        } else {
            response.setCode(Utils.CODE_UNAUTORIZED);
            response.response("Token expirado");
        }
        return response;
    }
}
