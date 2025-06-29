package com.example.aplikasi_sederhana_penjualan.Model.Response;

import java.time.LocalDateTime;
import java.util.List;

public record TransaksiDto(
    Long id,
    UserDto user,
    LocalDateTime tanggalTransaksi,
    double totalHarga,
    List<ItemTransaksiDto> items
) {}
