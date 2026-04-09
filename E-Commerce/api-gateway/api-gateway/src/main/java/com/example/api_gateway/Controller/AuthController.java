package com.example.api_gateway.Controller;

import com.example.api_gateway.Util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> credentials) {
        String user = credentials.get("username");
        String pass = credentials.get("password");
        if ("admin".equals(user) && "password".equals(pass)) {
            String token = jwtUtil.generateToken(user);
            return Map.of("accessToken", token);
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
    }
}
