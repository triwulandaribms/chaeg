package com.example.aplikasi_sederhana_penjualan.Service;

import com.example.aplikasi_sederhana_penjualan.Entity.Buku;
import com.example.aplikasi_sederhana_penjualan.Entity.ItemTransaksi;
import com.example.aplikasi_sederhana_penjualan.Entity.Transaksi;
import com.example.aplikasi_sederhana_penjualan.Entity.User;
import com.example.aplikasi_sederhana_penjualan.Model.Request.ItemTransaksiReq;
import com.example.aplikasi_sederhana_penjualan.Model.Request.TransaksiReq;
import com.example.aplikasi_sederhana_penjualan.Model.Response.ItemTransaksiDto;
import com.example.aplikasi_sederhana_penjualan.Model.Response.TransaksiDto;
import com.example.aplikasi_sederhana_penjualan.Model.Response.UserDto;
import com.example.aplikasi_sederhana_penjualan.Repository.BukuRepository;
import com.example.aplikasi_sederhana_penjualan.Repository.ItemTransaksiRepository;
import com.example.aplikasi_sederhana_penjualan.Repository.TransaksiRepository;
import com.example.aplikasi_sederhana_penjualan.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import java.util.stream.Collectors;

@Service
public class TransaksiService {

    private final TransaksiRepository transaksiRepository;
    private final ItemTransaksiRepository itemTransaksiRepository;
    private final UserRepository userRepository;
    private final BukuRepository bukuRepository;

    public TransaksiService(
            TransaksiRepository transaksiRepository,
            ItemTransaksiRepository itemTransaksiRepository,
            UserRepository userRepository,
            BukuRepository bukuRepository
    ) {
        this.transaksiRepository = transaksiRepository;
        this.itemTransaksiRepository = itemTransaksiRepository;
        this.userRepository = userRepository;
        this.bukuRepository = bukuRepository;
    }

    public List<TransaksiDto> getAllTransaksi() {
        List<Transaksi> transaksiList = transaksiRepository.findAll();
        return transaksiList.stream().map(this::toDto).collect(Collectors.toList());
    }

    public TransaksiDto getTransaksiById(Long id) {
        Transaksi transaksi = transaksiRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaksi dengan ID " + id + " tidak ditemukan"));
        return toDto(transaksi);
    }

    public TransaksiDto createTransaksi(TransaksiReq req) {
        User user = userRepository.findById(req.userId())
                .orElseThrow(() -> new RuntimeException("User tidak ditemukan"));

        Transaksi transaksi = new Transaksi();
        transaksi.setUser(user);
        transaksi.setTanggalTransaksi(LocalDateTime.now());
        transaksi.setTotalHarga(0);

        transaksi = transaksiRepository.save(transaksi);

        List<ItemTransaksi> savedItems = new ArrayList<>();
        double total = 0;

        for (ItemTransaksiReq itemReq : req.items()) {
            Buku buku = bukuRepository.findById(itemReq.bukuId())
                    .orElseThrow(() -> new RuntimeException("Buku tidak ditemukan"));

            if (buku.getStok() < itemReq.jumlah()) {
                throw new RuntimeException("Stok buku tidak mencukupi untuk buku: " + buku.getJudul());
            }

            buku.setStok(buku.getStok() - itemReq.jumlah());
            bukuRepository.save(buku);

            ItemTransaksi item = new ItemTransaksi();
            item.setTransaksi(transaksi);
            item.setBuku(buku);
            item.setJumlah(itemReq.jumlah());
            item.setHargaSatuan(buku.getHarga());
            item.setSubtotal(buku.getHarga() * itemReq.jumlah());

            itemTransaksiRepository.save(item);
            savedItems.add(item);
            total += item.getSubtotal();
        }

        transaksi.setTotalHarga(total);
        transaksi.setItems(savedItems);
        transaksiRepository.save(transaksi);

        return toDto(transaksi);
    }

    private TransaksiDto toDto(Transaksi transaksi) {
        UserDto userDto = new UserDto(transaksi.getUser().getId(), transaksi.getUser().getUsername());
        List<ItemTransaksiDto> itemsDto = transaksi.getItems().stream().map(this::toItemDto).toList();

        return new TransaksiDto(
                transaksi.getId(),
                userDto,
                transaksi.getTanggalTransaksi(),
                transaksi.getTotalHarga(),
                itemsDto
        );
    }

    private ItemTransaksiDto toItemDto(ItemTransaksi item) {
        return new ItemTransaksiDto(
                item.getId(),
                item.getBuku().getId(),
                item.getBuku().getJudul(),
                item.getJumlah(),
                item.getHargaSatuan(),
                item.getSubtotal()
        );
    }
}
