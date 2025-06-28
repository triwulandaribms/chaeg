package com.example.aplikasi_sederhana_penjualan.Service;

import com.example.aplikasi_sederhana_penjualan.Entity.User;
import com.example.aplikasi_sederhana_penjualan.Model.Request.loginReq;
import com.example.aplikasi_sederhana_penjualan.Model.Request.registerReq;
import com.example.aplikasi_sederhana_penjualan.Repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    private static final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private static final long EXPIRATION_TIME = 86400000;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public Map<String, Object> registerUser(registerReq req) {
        Map<String, Object> data = new HashMap<>();

        for (User dataUser : userRepository.findAll()) {
            boolean cekEmail = req.email().equals(dataUser.getEmail());
            boolean cekPassword = passwordEncoder.matches(req.password(), dataUser.getPassword());
    
            if (cekEmail && cekPassword) {
                data.put("message", "Kombinasi email dan password sudah digunakan");
                return data;
            }else if (cekPassword) {
                data.put("message", "Password sudah pernah digunakan oleh pengguna lain");
                return data;
            }else if (cekEmail) {
                data.put("message", "Email sudah digunakan");
                return data;
            }
        }

        String hashPassword = passwordEncoder.encode(req.password());
        User dataNewUser = new User(req.username(), hashPassword, req.email());
        userRepository.save(dataNewUser);

        data.put("message", "Registrasi berhasil");
        data.put("user", dataNewUser);
        return data;
    }

    public Map<String, Object> loginUser(loginReq req) {
        Map<String, Object> data = new HashMap<>();

        User user = userRepository.findByUsername(req.username());

        if (user != null && passwordEncoder.matches(req.password(), user.getPassword())) {
            String token = Jwts.builder()
                    .setSubject(user.getUsername())
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                    .signWith(SECRET_KEY)
                    .compact();

            data.put("message", "Login berhasil");
            data.put("token", token);
        } else {
            data.put("message", "Username atau password salah");
        }

        return data;
    }

}
