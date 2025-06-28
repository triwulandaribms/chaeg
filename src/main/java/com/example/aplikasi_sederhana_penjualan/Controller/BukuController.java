package com.example.aplikasi_sederhana_penjualan.Controller;

import com.example.aplikasi_sederhana_penjualan.Entity.Buku;
import com.example.aplikasi_sederhana_penjualan.Model.Request.BukuReq;
import com.example.aplikasi_sederhana_penjualan.Service.BukuService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public Buku tambahBuku(@RequestBody BukuReq req) {
        return bukuService.addBuku(req);
    }

    @PutMapping("/update/{id}")
    public Buku editBuku(@PathVariable Long id, @RequestBody BukuReq req) {
        return bukuService.updateBuku(id, req);
    }

    @DeleteMapping("/delete/{id}")
    public void hapusBuku(@PathVariable Long id) {
        bukuService.deletedBuku(id);
    }
}
