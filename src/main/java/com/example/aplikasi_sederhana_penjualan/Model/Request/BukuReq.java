
package com.example.aplikasi_sederhana_penjualan.Model.Request;

public record BukuReq(
    String judul,
    String penulis,
    double harga,
    int stok
) {
}


