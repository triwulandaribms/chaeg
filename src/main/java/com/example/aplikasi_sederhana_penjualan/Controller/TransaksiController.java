package com.example.aplikasi_sederhana_penjualan.Controller;

import com.example.aplikasi_sederhana_penjualan.Model.Request.TransaksiReq;
import com.example.aplikasi_sederhana_penjualan.Model.Response.TransaksiDto;
import com.example.aplikasi_sederhana_penjualan.Service.TransaksiService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/transaksi")
public class TransaksiController {

    private final TransaksiService transaksiService;

    public TransaksiController(TransaksiService transaksiService) {
        this.transaksiService = transaksiService;
    }

    @GetMapping("/listAll")
    public ResponseEntity<List<TransaksiDto>> getAllTransaksi() {
        try {
            return ResponseEntity.ok(transaksiService.getAllTransaksi());
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<?> getTransaksiById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(transaksiService.getTransaksiById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/addTransaksi")
    public ResponseEntity<?> buatTransaksi(@RequestBody TransaksiReq req) {
        try {
            return ResponseEntity.ok(transaksiService.createTransaksi(req));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
}
