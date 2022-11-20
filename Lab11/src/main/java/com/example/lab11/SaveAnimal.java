package com.example.lab11;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.scene.control.TableView;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.*;
import java.util.ArrayList;

public class SaveAnimal {
    /**
     * поле таблицы
     */
    TableView<Animal> tableView = new TableView<>();

    /**
     * геттер для таблицы
     */
    public TableView<Animal> getTableView() {
        return tableView;
    }

    /**
     * сеттер для таблицы
     */
    public void setTableView(TableView<Animal> tableView) {
        this.tableView = tableView;
    }

    /**
     * сохранение в формате txt
     */
    public void saveToTxt() throws IOException {

        FileWriter fileWriter = new FileWriter("Animals.txt");
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        for (Animal animal : new ArrayList<>(getTableView().getItems())) {
            try {
                bufferedWriter.write(String.valueOf(animal.getId()));
                bufferedWriter.write(System.lineSeparator());
                bufferedWriter.write(String.valueOf(animal.getName()));
                bufferedWriter.write(System.lineSeparator());
                bufferedWriter.write(String.valueOf(animal.getLatinName()));
                bufferedWriter.write(System.lineSeparator());
                bufferedWriter.write(String.valueOf(animal.getAnimalType()));
                bufferedWriter.write(System.lineSeparator());
                bufferedWriter.write(String.valueOf(animal.getActiveTime()));
                bufferedWriter.write(System.lineSeparator());
                bufferedWriter.write(String.valueOf(animal.getLenMin()));
                bufferedWriter.write(System.lineSeparator());
                bufferedWriter.write(String.valueOf(animal.getLenMax()));
                bufferedWriter.write(System.lineSeparator());
                bufferedWriter.write(String.valueOf(animal.getWgMin()));
                bufferedWriter.write(System.lineSeparator());
                bufferedWriter.write(String.valueOf(animal.getWgMax()));
                bufferedWriter.write(System.lineSeparator());
                bufferedWriter.write(String.valueOf(animal.getLifespan()));
                bufferedWriter.write(System.lineSeparator());
                bufferedWriter.write(String.valueOf(animal.getHabitat()));
                bufferedWriter.write(System.lineSeparator());
                bufferedWriter.write(String.valueOf(animal.getDiet()));
                bufferedWriter.write(System.lineSeparator());
                bufferedWriter.write(String.valueOf(animal.getGeoRange()));
                bufferedWriter.write(System.lineSeparator());
                bufferedWriter.write(String.valueOf(animal.getImageLink()));
                bufferedWriter.write(System.lineSeparator());
            } catch (IOException e) {
            }
        }
        bufferedWriter.close();
        fileWriter.close();
    }

    /**
     * сохранение в формате json
     */
    public void saveToJson() throws IOException {
        new ObjectMapper().writeValue(new File("Animals.json"), new ArrayList<>(getTableView().getItems()));


    }

    /**
     * сохранение в формате xml
     */
    public void saveToExcel() {

        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        HSSFSheet hssfSheet = hssfWorkbook.createSheet("Sheet1");
        HSSFRow firstRow = hssfSheet.createRow(0);
        for (int i = 0; i < getTableView().getColumns().size(); i++) {
            firstRow.createCell((short) i).setCellValue(getTableView().getColumns().get(i).getText());
        }
        for (int row = 0; row < getTableView().getItems().size(); row++) {
            HSSFRow hssfRow = hssfSheet.createRow(row + 1);
            for (int col = 0; col < getTableView().getColumns().size(); col++) {
                Object celValue = getTableView().getColumns().get(col).getCellObservableValue(row).getValue();
                try {
                    if (celValue != null && Double.parseDouble(celValue.toString()) != 0.0) {
                        hssfRow.createCell((short) col).setCellValue(Double.parseDouble(celValue.toString()));
                    }
                } catch (NumberFormatException e) {
                    hssfRow.createCell((short) col).setCellValue(celValue.toString());
                }
            }
        }
        try {
            hssfWorkbook.write(new FileOutputStream("Animals.xls"));
            hssfWorkbook.close();
        } catch (IOException e) {
        }
    }
}
