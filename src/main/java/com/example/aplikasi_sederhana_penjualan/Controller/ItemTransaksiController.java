package com.example.aplikasi_sederhana_penjualan.Controller;

import com.example.aplikasi_sederhana_penjualan.Service.ExportPdf;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/transaksi")
public class ItemTransaksiController {

    private final ExportPdf exportPdf;

    public ItemTransaksiController(ExportPdf exportPdf) {
        this.exportPdf = exportPdf;
    }

    @GetMapping("/item-transaksi/export/pdf")
    public void exportToPdf(HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=item_transaksi.pdf");
        exportPdf.exportItemTransaksiToPdf(response);
    }
}
