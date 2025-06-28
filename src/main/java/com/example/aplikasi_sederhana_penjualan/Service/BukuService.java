package com.example.aplikasi_sederhana_penjualan.Service;

import com.example.aplikasi_sederhana_penjualan.Entity.Buku;
import com.example.aplikasi_sederhana_penjualan.Model.Request.BukuReq;
import com.example.aplikasi_sederhana_penjualan.Repository.BukuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BukuService {

    @Autowired
    private BukuRepository bukuRepository;

    public List<Buku> listAll() {
        return bukuRepository.findAll();
    }

    public Buku addBuku(BukuReq req) {
        if (bukuRepository.findByJudul(req.judul()) != null) {
            throw new RuntimeException("Judul buku \"" + req.judul() + "\" sudah ada.");
        }

        Buku buku = new Buku();
        buku.setJudul(req.judul());
        buku.setPenulis(req.penulis());
        buku.setHarga(req.harga());
        buku.setStok(req.stok());
        return bukuRepository.save(buku);
    }

    public Buku updateBuku(Long id, BukuReq req) {
        Optional<Buku> optional = bukuRepository.findById(id);
        if (optional.isEmpty()) {
            throw new RuntimeException("Buku dengan ID " + id + " tidak ditemukan.");
        }

        Buku existing = optional.get();
        Buku bukuDenganJudulBaru = bukuRepository.findByJudul(req.judul());
        if (bukuDenganJudulBaru != null && !bukuDenganJudulBaru.getId().equals(existing.getId())) {
            throw new RuntimeException("Judul buku \"" + req.judul() + "\" sudah digunakan oleh buku lain.");
        }

        existing.setJudul(req.judul());
        existing.setPenulis(req.penulis());
        existing.setHarga(req.harga());
        existing.setStok(req.stok());
        return bukuRepository.save(existing);
    }

    public void deletedBuku(Long id) {
        bukuRepository.deleteById(id);
    }
}

