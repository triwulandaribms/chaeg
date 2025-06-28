package com.example.aplikasi_sederhana_penjualan.Controller;

import com.example.aplikasi_sederhana_penjualan.Entity.Buku;
import com.example.aplikasi_sederhana_penjualan.Model.Request.BukuReq;
import com.example.aplikasi_sederhana_penjualan.Service.BukuService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/buku")
public class BukuController {

    private final BukuService bukuService;

    public BukuController(BukuService bukuService) {
        this.bukuService = bukuService;
    }

    @GetMapping("/list")
    public List<Buku> listBuku() {
        return bukuService.listAll();
    }

    @PostMapping("/create")
    public ResponseEntity<?> tambahBuku(@RequestBody BukuReq req) {
        try {
            Buku buku = bukuService.addBuku(req);
            return ResponseEntity.ok(buku);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateBuku(@PathVariable Long id, @RequestBody BukuReq req) {
        try {
            Buku updated = bukuService.updateBuku(id, req);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @DeleteMapping("/delete/{id}")
    public void hapusBuku(@PathVariable Long id) {
        bukuService.deletedBuku(id);
    }
}
