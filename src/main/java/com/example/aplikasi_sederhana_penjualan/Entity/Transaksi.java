// package com.example.aplikasi_sederhana_penjualan.Entity;

// import java.time.LocalDateTime;
// import java.util.ArrayList;
// import java.util.List;

// public record Transaksi(
//     Long id,
//     LocalDateTime tanggal,
//     String keterangan,
//     List<ItemTransaksi> items
// ) {
//     public static final String TABLE_NAME = "transaksi";

//     public Transaksi(String keterangan) {
//         this(null, LocalDateTime.now(), keterangan, new ArrayList<>());
//     }

//     public Transaksi(Long id, LocalDateTime tanggal, String keterangan) {
//         this(id, tanggal, keterangan, new ArrayList<>());
//     }
// }
