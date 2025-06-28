package com.example.aplikasi_sederhana_penjualan.Controller;

import com.example.aplikasi_sederhana_penjualan.Model.Request.loginReq;
import com.example.aplikasi_sederhana_penjualan.Model.Request.registerReq;
import com.example.aplikasi_sederhana_penjualan.Service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody registerReq req) {
        Map<String, Object> response = authService.registerUser(req);

        if ("Registrasi berhasil".equals(response.get("message"))) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody loginReq req) {
        Map<String, Object> response = authService.loginUser(req);
        
        if (response.containsKey("token")) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(401).body(response);
        }
    }
}
