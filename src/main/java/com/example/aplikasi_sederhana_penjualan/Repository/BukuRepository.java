package com.example.aplikasi_sederhana_penjualan.Repository;

import com.example.aplikasi_sederhana_penjualan.Entity.Buku;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BukuRepository extends JpaRepository<Buku, Long> {
    Buku findByJudul(String judul);
}
