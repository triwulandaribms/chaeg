package com.example.aplikasi_sederhana_penjualan.Repository;

import com.example.aplikasi_sederhana_penjualan.Entity.Transaksi;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransaksiRepository extends JpaRepository<Transaksi, Long> {
    List<Transaksi> findByUserId(Long userId);
}
