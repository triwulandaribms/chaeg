package com.example.aplikasi_sederhana_penjualan.Model.Response;

public record ItemTransaksiDto(
    Long id,
    Long bukuId,
    String judulBuku,
    int jumlah,
    double hargaSatuan,
    double subtotal
) {}
