// package com.example.aplikasi_sederhana_penjualan.Controller;

// import java.io.IOException;
// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;

// import com.example.aplikasi_sederhana_penjualan.Entity.Transaksi;
// import com.example.aplikasi_sederhana_penjualan.Service.TransaksiService;

// import jakarta.servlet.ServletOutputStream;
// import jakarta.servlet.http.HttpServletResponse;

// public class ExportService {
//     @Autowired private TransaksiService transaksiService;

//     public void exportExcel(HttpServletResponse response) throws IOException {
//         response.setContentType("application/octet-stream");
//         String headerKey = "Content-Disposition";
//         String headerValue = "attachment; filename=transaksi.xlsx";
//         response.setHeader(headerKey, headerValue);

//         List<Transaksi> listTransaksi = transaksiService.getAll();

//         Workbook workbook = new XSSFWorkbook();
//         Sheet sheet = workbook.createSheet("Transaksi");

//         Row headerRow = sheet.createRow(0);
//         headerRow.createCell(0).setCellValue("Tanggal");
//         headerRow.createCell(1).setCellValue("Keterangan");
//         headerRow.createCell(2).setCellValue("Jumlah Item");

//         int rowIdx = 1;
//         for (Transaksi trx : listTransaksi) {
//             Row row = sheet.createRow(rowIdx++);
//             row.createCell(0).setCellValue(trx.tanggal().toString());
//             row.createCell(1).setCellValue(trx.keterangan());
//             row.createCell(2).setCellValue(trx.items().size());
//         }

//         ServletOutputStream outputStream = response.getOutputStream();
//         workbook.write(outputStream);
//         workbook.close();
//         outputStream.close();
//     }
// }