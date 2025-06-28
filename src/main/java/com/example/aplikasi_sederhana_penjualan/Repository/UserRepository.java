package com.example.aplikasi_sederhana_penjualan.Repository;

import com.example.aplikasi_sederhana_penjualan.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsernameAndPassword(String username, String password);
}
