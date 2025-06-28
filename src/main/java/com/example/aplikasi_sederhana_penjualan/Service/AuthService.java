package com.example.aplikasi_sederhana_penjualan.Service;

import com.example.aplikasi_sederhana_penjualan.Entity.User;
import com.example.aplikasi_sederhana_penjualan.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepo;

    @Autowired
    public AuthService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public User login(String username, String password) {
        return userRepo.findByUsernameAndPassword(username, password);
    }
}
