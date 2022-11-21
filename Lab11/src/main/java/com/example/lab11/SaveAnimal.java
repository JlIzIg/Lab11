package com.example.lab11;

import javafx.scene.control.TableView;

import java.io.IOException;
import java.sql.SQLException;

public class SaveAnimal {
    ExcelWriter excelWriter;
    JSONWriter jsonWriter;
    TxtWriter txtWriter;

    /***конструктор класса*/
    public SaveAnimal() {
        initialize();
    }

    /**
     * иницализатор
     **/
    public void initialize() {
        excelWriter = new ExcelWriter();
        jsonWriter = new JSONWriter();
        txtWriter = new TxtWriter();
    }

    /**
     * сохранение в формате txt
     */
    public void saveToTxt() throws IOException, SQLException {
        txtWriter.saveToFile("Animals.txt");
    }

    /**
     * сохранение в формате json
     */
    public void saveToJson() throws IOException, SQLException {
        jsonWriter.saveToFile("Animals.json");
    }

    /**
     * сохранение в формате xlsx
     */
    public void saveToExcel() {
        excelWriter.saveToFile("Animals.xls");
    }


}
