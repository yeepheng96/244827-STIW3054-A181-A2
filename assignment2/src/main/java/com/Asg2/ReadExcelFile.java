package com.Asg2;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadExcelFile{
    // Determine file that want to retrieve data from it
    private static final String TARGET = "C:\\Users\\MyAsus\\Desktop\\RT source\\Asg2\\chessResultsList.xlsx";
    static ArrayList<ChessWords> addInfo = new ArrayList<ChessWords>();
    static ArrayList<ChessList>info = new ArrayList<ChessList>();
    // Get Excel data NOT in Table
    public static List<ChessWords>readExcelWord(){
        try{
            System.out.println("Getting words data...");
            FileInputStream fis = new FileInputStream(TARGET);
            DataFormatter dataFormatter = new DataFormatter();
            XSSFWorkbook wb = new XSSFWorkbook(fis);
            XSSFSheet sheet = wb.getSheetAt(0);
            String cellWord ="";
            int count = 0;
            for(Row row: sheet){
                for(Cell cell: row){
                    if(count>=0 && count<4){
                        // Collect information before Excel Table
                        cellWord = dataFormatter.formatCellValue(cell);
                        addInfo.add(new ChessWords(cellWord));
                    }
                    else if(count>=155){
                        // Collect information after Excel Table
                        cellWord = dataFormatter.formatCellValue(cell);
                        addInfo.add(new ChessWords(cellWord));
                    }
                }
                // Adding count to proceed next row
                count++;
            }
            wb.close();
            return addInfo;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    // Get Excel data from Table
    public static List<ChessList>readExcelTable(){
        try {
            System.out.println("Retrieving Excel Table data...");
            FileInputStream fis = new FileInputStream(TARGET);
            XSSFWorkbook wb = new XSSFWorkbook(fis);
            XSSFSheet sheet = wb.getSheetAt(0);
            String no ="", name="", fideID="", fed="", rtg="", cc="";
            int countCol = 0;
            int count = 0;
            for(Row row: sheet){
                for(Cell cell: row){
                    if(count>3 && count<155){
                        // Collect data from Excel Table ONLY
                        if(countCol==0) {
                            Cell col1 = row.getCell(0); // Excel Table A
                            no = String.valueOf(col1);
                        }
                        else if(countCol==2){
                            Cell col2 = row.getCell(2); // Excel Table B are empty, so retrieve data start from C
                            name = String.valueOf(col2);
                        }
                        else if(countCol==3){
                            Cell col3 = row.getCell(3); // Excel Table D
                            fideID = String.valueOf(col3);
                        }
                        else if(countCol==4){
                            Cell col4 = row.getCell(4); // Excel Table E
                            fed = String.valueOf(col4);
                        }
                        else if(countCol==5){
                            Cell col5 = row.getCell(5); // Excel Table F
                            rtg = String.valueOf(col5);
                        }
                        else if(countCol==6){
                            Cell col6 = row.getCell(6); // Excel Table G
                            cc = String.valueOf(col6);
                            // Add all information from Excel Table into ChessList.java
                            info.add(new ChessList(no,name,fideID,fed,rtg,cc));
                        }
                    }
                    countCol++;
                }
                // Adding count to proceed next row, reset countCol to start from new row 1st column
                count++;
                countCol = 0;
            }
            wb.close();
            return info;
        }catch(IOException e){
            e.printStackTrace();
            return null;
        }
    }
}
