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

        Buku buku = optional.get();
        buku.setJudul(req.judul());
        buku.setPenulis(req.penulis());
        buku.setHarga(req.harga());
        buku.setStok(req.stok());
        return bukuRepository.save(buku);
    }

    public void deletedBuku(Long id) {
        bukuRepository.deleteById(id);
    }

}
