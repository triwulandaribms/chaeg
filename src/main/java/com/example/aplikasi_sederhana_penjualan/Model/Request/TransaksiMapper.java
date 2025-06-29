package com.example.aplikasi_sederhana_penjualan.Model.Request;

import com.example.aplikasi_sederhana_penjualan.Entity.Transaksi;
import com.example.aplikasi_sederhana_penjualan.Model.Response.*;

import java.util.List;

public class TransaksiMapper {

    public static TransaksiDto toDto(Transaksi transaksi) {
        UserDto userDto = new UserDto(
            transaksi.getUser().getId(),
            transaksi.getUser().getUsername()
        );

        List<ItemTransaksiDto> itemsDto = transaksi.getItems().stream().map(item -> new ItemTransaksiDto(
            item.getId(),
            item.getBuku().getId(),
            item.getBuku().getJudul(),
            item.getJumlah(),
            item.getHargaSatuan(),
            item.getSubtotal()
        )).toList();

        return new TransaksiDto(
            transaksi.getId(),
            userDto,
            transaksi.getTanggalTransaksi(),
            transaksi.getTotalHarga(),
            itemsDto
        );
    }
}
