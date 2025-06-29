package com.example.aplikasi_sederhana_penjualan.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "buku")
public class Buku {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String judul;
    private String penulis;
    private double harga;
    private int stok;

    public Buku() {}

    public Buku(String judul, String penulis, double harga, int stok) {
        this.judul = judul;
        this.penulis = penulis;
        this.harga = harga;
        this.stok = stok;
    }

    public Long getId() { 
        return id; 
    }

    public void setId(Long id) { 
        this.id = id; 
    }

    public String getJudul() { 
        return judul; 
    }

    public void setJudul(String judul) { 
        this.judul = judul; 
    }

    public String getPenulis() { 
        return penulis; 
    }

    public void setPenulis(String penulis) { 
        this.penulis = penulis; 
    }

    public double getHarga() { 
        return harga; 
    }

    public void setHarga(double harga) { 
        this.harga = harga; 
    }

    public int getStok() { 
        return stok; 
    }

    public void setStok(int stok) { 
        this.stok = stok; 
    }
}
