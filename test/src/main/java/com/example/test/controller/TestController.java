package com.example.test.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;

@RestController
@RequestMapping("/hi")
public class TestController {

    @Value("${user}")
    private String user;
    @Value("${pass}")
    private String pass;

    @PostMapping("/hello")
    public String heelo() {
        return "hi";
    }


    @GetMapping("/hello")
    public String hello() {
        return "hi";
    }

    @GetMapping("/api")
    public String apiEndpoint(@RequestBody String s, HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Basic ")) {
            String base64Credentials = authorizationHeader.substring("Basic ".length());
            byte[] decodedBytes = Base64.getDecoder().decode(base64Credentials);
            String credentials = new String(decodedBytes);
            String[] parts = credentials.split(":");
            String username = parts[0];
            String password = parts[1];

            if (user.equals(username) && pass.equals(password)) {
                return "Success " + "Username: " + username + ", Password: " + password + ", Body:" + s;
            } else {
                return "Sai tai khoan hoac mat khau. Username: " + username + ", Password: " + password + ", Body:" + s;
            }
        } else {
            // Xử lý logic khi không có header Authorization hoặc không phải là Basic Authentication

            return "Invalid authentication";
        }
    }
}
