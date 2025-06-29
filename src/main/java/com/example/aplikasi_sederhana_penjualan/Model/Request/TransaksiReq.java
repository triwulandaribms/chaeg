package com.example.aplikasi_sederhana_penjualan.Model.Request;

import java.util.List;

public record TransaksiReq(
    Long userId,
    List<ItemTransaksiReq> items
) {}
