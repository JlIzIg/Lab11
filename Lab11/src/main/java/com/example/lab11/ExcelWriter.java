package com.example.lab11;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ExcelWriter {
    /**
     * поля класса
     **/
    HSSFWorkbook hssfWorkbook;
    HSSFSheet hssfSheet;
    DataBaseAnimals dataBaseAnimals;
    List<Animal> animalList;

    /**
     * конструктор класса
     **/

    public ExcelWriter() {
        initialize();
    }

    /**
     * метод для заполнения HSSFSheet данными из List
     */

    private void createSheetHeader(HSSFSheet sheet, int rowNum, Animal animal) {

        Row row = sheet.createRow(rowNum);
        row.createCell(0).setCellValue(animal.getId());
        row.createCell(1).setCellValue(animal.getName());
        row.createCell(2).setCellValue(animal.getLatinName());
        row.createCell(3).setCellValue(animal.getAnimalType());
        row.createCell(4).setCellValue(animal.getActiveTime());
        row.createCell(5).setCellValue(animal.getLenMin());
        row.createCell(6).setCellValue(animal.getLenMax());
        row.createCell(7).setCellValue(animal.getWgMin());
        row.createCell(8).setCellValue(animal.getWgMax());
        row.createCell(9).setCellValue(animal.getLifespan());
        row.createCell(10).setCellValue(animal.getHabitat());
        row.createCell(11).setCellValue(animal.getDiet());
        row.createCell(12).setCellValue(animal.getGeoRange());
        row.createCell(13).setCellValue(animal.getImageLink());
    }

    /**
     * инициализатор
     **/
    public void initialize() {
        hssfWorkbook = new HSSFWorkbook();
        hssfSheet = hssfWorkbook.createSheet("Sheet1");
        dataBaseAnimals = new DataBaseAnimals();
        dataBaseAnimals.dbConnection(Constants.SERVER + "/mydb", "root", "NoFear@Dinar2021");
        dataBaseAnimals.loadData();
        animalList = dataBaseAnimals.getData();
        try {
            dataBaseAnimals.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * метод сохранения данных в файл
     */
    public void saveToFile(String filename) {
        int rowNum = 0;
        HSSFRow firstRow = hssfSheet.createRow(rowNum);
        firstRow.createCell(0).setCellValue("id");
        firstRow.createCell(1).setCellValue("name");
        firstRow.createCell(2).setCellValue("latinName");
        firstRow.createCell(3).setCellValue("animalType");
        firstRow.createCell(4).setCellValue("activeTime");
        firstRow.createCell(5).setCellValue("lenMin");
        firstRow.createCell(6).setCellValue("lenMax");
        firstRow.createCell(7).setCellValue("wgMin");
        firstRow.createCell(8).setCellValue("wgMax");
        firstRow.createCell(9).setCellValue("lifespan");
        firstRow.createCell(10).setCellValue("habitat");
        firstRow.createCell(11).setCellValue("diet");
        firstRow.createCell(12).setCellValue("geoRange");
        firstRow.createCell(13).setCellValue("imageLink");
        for (Animal animal : animalList) {
            createSheetHeader(hssfSheet, rowNum++, animal);
        }
        try (FileOutputStream out = new FileOutputStream(filename)) {
            hssfWorkbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}