package com.Asg2;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import static com.Asg2.ReadExcelFile.readExcelWord;
import static com.Asg2.ReadExcelFile.readExcelTable;

public class ConvertPDF {
    // Set PDF file location
    private static String PDF_FILE = "C:\\Users\\MyAsus\\Desktop\\RT source\\Asg2\\Asg2.pdf";
    public static void writePDF(){
        try {
            System.out.println("Creating empty PDF file...");
            // Create a PDF file
            FileOutputStream fos = new FileOutputStream(PDF_FILE);
            Document doc = new Document(PageSize.A4);
            PdfWriter write = PdfWriter.getInstance(doc,fos);
            doc.open();
            PdfPTable table = new PdfPTable(6); // 6 column table
            table.setHorizontalAlignment(Element.ALIGN_CENTER); // Set table to align center
            table.setWidthPercentage(100); // Set table width
            float[]colWidth = {1.5f,10f,2f,1.5f,1.2f,5f}; // Determine cell width
            table.setWidths(colWidth); // Set cell width
            System.out.println("Starting to retrieve data from Excel File...");
            int count = 0;
            // Transfer word into PDF file (Before Table)
            for(ChessWords addInfo : readExcelWord()){
                if(count>=0 && count<4) {
                    Phrase wordBeforeTable = new Phrase(new Paragraph(String.valueOf(ReadExcelFile.addInfo.get(count).getWord())));
                    doc.add(wordBeforeTable);
                    doc.add(Chunk.NEWLINE);
                }
                count++;
            }
            System.out.println(" - Words before Excel Table added into PDF file.");
            count = 0; // Reset count to 0, build Excel Table into PDF file
            // Make all collected data into a table in PDF, according to Excel Table
            for (ChessList info : readExcelTable()) {
                // Align all data based on alignment from Excel Table
                PdfPCell col1 = new PdfPCell(new Paragraph(String.valueOf(ReadExcelFile.info.get(count).getNo())));
                col1.setHorizontalAlignment(Element.ALIGN_CENTER);
                PdfPCell col2 = new PdfPCell(new Paragraph(String.valueOf(ReadExcelFile.info.get(count).getName())));
                col2.setHorizontalAlignment(Element.ALIGN_LEFT);
                PdfPCell col3 = new PdfPCell(new Paragraph(String.valueOf(ReadExcelFile.info.get(count).getFideID())));
                col3.setHorizontalAlignment(Element.ALIGN_RIGHT);
                PdfPCell col4 = new PdfPCell(new Paragraph(String.valueOf(ReadExcelFile.info.get(count).getFed())));
                col4.setHorizontalAlignment(Element.ALIGN_LEFT);
                PdfPCell col5 = new PdfPCell(new Paragraph(String.valueOf(ReadExcelFile.info.get(count).getRtg())));
                col5.setHorizontalAlignment(Element.ALIGN_RIGHT);
                PdfPCell col6 = new PdfPCell(new Paragraph(String.valueOf(ReadExcelFile.info.get(count).getCc())));
                col6.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(col1);
                table.addCell(col2);
                table.addCell(col3);
                table.addCell(col4);
                table.addCell(col5);
                table.addCell(col6);
                count++;
            }
            doc.add(table);
            System.out.println(" - Data from table was added into PDF file.");
            count = 0; // Reset count again, need to insert after table words
            // Build words after Table in PDF was build
            for(ChessWords addInfo : readExcelWord()){
                if(count>=4 && count<6) {
                    Phrase wordAfterTable = new Phrase(new Paragraph(String.valueOf(ReadExcelFile.addInfo.get(count).getWord())));
                    doc.add(wordAfterTable);
                }
                count++;
            }
            System.out.println(" - Words after Excel Table added into PDF file.");
            doc.close();
            write.close();
            System.out.println("PDF file was created successfully!");
            System.out.println("Location: "+PDF_FILE);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
