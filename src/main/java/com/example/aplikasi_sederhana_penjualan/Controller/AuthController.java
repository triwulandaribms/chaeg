package com.example.aplikasi_sederhana_penjualan.Controller;

import com.example.aplikasi_sederhana_penjualan.Entity.User;
import com.example.aplikasi_sederhana_penjualan.Model.Request.UserReq;
import com.example.aplikasi_sederhana_penjualan.Service.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserReq req) {
        System.out.println("Username: " + req.username());
        System.out.println("Password: " + req.password());
        User u = authService.login(req.username(), req.password());
        if (u != null)
            return ResponseEntity.ok(u);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login gagal");
    }

}
