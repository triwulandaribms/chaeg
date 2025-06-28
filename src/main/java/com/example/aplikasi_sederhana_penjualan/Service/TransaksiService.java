// package com.example.aplikasi_sederhana_penjualan.Service;

// import java.util.ArrayList;
// import java.util.List;
// import java.util.Map;

// import org.springframework.beans.factory.annotation.Autowired;

// import com.example.aplikasi_sederhana_penjualan.Entity.Buku;
// import com.example.aplikasi_sederhana_penjualan.Entity.ItemTransaksi;
// import com.example.aplikasi_sederhana_penjualan.Entity.Transaksi;
// import com.example.aplikasi_sederhana_penjualan.Repository.BukuRepository;
// import com.example.aplikasi_sederhana_penjualan.Repository.TransaksiRepository;

// public class TransaksiService {
    
//     @Autowired private BukuRepository bukuRepo;
//     @Autowired private TransaksiRepository transaksiRepo;

//     public Transaksi simpan(Map<String, Integer> pembelian) {
//         List<ItemTransaksi> items = new ArrayList<>();
//         Transaksi t = new Transaksi("Transaksi buku");

//         for (Map.Entry<String, Integer> entry : pembelian.entrySet()) {
//             Long idBuku = Long.parseLong(entry.getKey());
//             int jumlah = entry.getValue();
//             Buku b = bukuRepo.findById(idBuku).orElse(null);
//             if (b != null && b.stok() >= jumlah) {
//                 Buku updated = new Buku(b.id(), b.judul(), b.penulis(), b.harga(), b.stok() - jumlah);
//                 bukuRepo.save(updated);
//                 ItemTransaksi item = new ItemTransaksi(null, jumlah, updated, t);
//                 items.add(item);
//             }
//         }
//         Transaksi saved = new Transaksi(null, t.tanggal(), t.keterangan(), items);
//         return transaksiRepo.save(saved);
//     }
    
// }