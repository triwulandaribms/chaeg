// package com.example.aplikasi_sederhana_penjualan.Controller;


// import com.example.aplikasi_sederhana_penjualan.Entity.Transaksi;
// import com.example.aplikasi_sederhana_penjualan.Model.Request.TransaksiReq;
// import com.example.aplikasi_sederhana_penjualan.Service.TransaksiService;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;
// import java.util.Map;


// @RestController
// @RequestMapping("/transaksi")
// public class TransaksiController {
    
//     private final TransaksiService transaksiService;

    
//     @PostMapping("/create")
//     public Transaksi simpanTransaksi(@RequestBody Map<String, Integer> pembelian) {
//         return transaksiService.simpan(pembelian);
//     }

//     // @GetMapping("/excel")
//     // public void exportToExcel(HttpServletResponse response) throws IOException {
//     //     Object exportService;
//     //             ((Object) exportService).exportExcel(response);
//     // }

// }
