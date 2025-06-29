
package com.example.aplikasi_sederhana_penjualan.Service;

import com.example.aplikasi_sederhana_penjualan.Entity.ItemTransaksi;
import com.example.aplikasi_sederhana_penjualan.Repository.ItemTransaksiRepository;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.IOException;
import java.util.List;

@Service
public class ExportPdf {

    private final ItemTransaksiRepository itemRepo;

    public ExportPdf(ItemTransaksiRepository itemRepo) {
        this.itemRepo = itemRepo;
    }

    public void exportItemTransaksiToPdf(HttpServletResponse response) throws IOException {
        List<ItemTransaksi> items = itemRepo.findAll();

        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();

        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontTitle.setSize(18);
        fontTitle.setColor(Color.BLUE);

        Paragraph title = new Paragraph("Daftar Item Transaksi", fontTitle);
        title.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(title);
        document.add(new Paragraph(" "));

        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100f);
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table, items);

        document.add(table);
        document.close();
    }

    private void writeTableHeader(PdfPTable table) {
        String[] headers = { "ID", "Judul Buku", "Jumlah", "Harga Satuan", "Subtotal", "Transaksi ID" };
        for (String header : headers) {
            PdfPCell cell = new PdfPCell();
            cell.setBackgroundColor(Color.LIGHT_GRAY);
            cell.setPadding(5);
            cell.setPhrase(new Phrase(header));
            table.addCell(cell);
        }
    }

    private void writeTableData(PdfPTable table, List<ItemTransaksi> items) {
        for (ItemTransaksi item : items) {
            table.addCell(String.valueOf(item.getId()));
            table.addCell(item.getBuku().getJudul());
            table.addCell(String.valueOf(item.getJumlah()));
            table.addCell(String.valueOf(item.getHargaSatuan()));
            table.addCell(String.valueOf(item.getSubtotal()));
            table.addCell(String.valueOf(item.getTransaksi().getId()));
        }
    }
}

