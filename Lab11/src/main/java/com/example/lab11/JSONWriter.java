package com.example.lab11;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class JSONWriter {

    /**
     * метод сохранения List<Animal> в файл JSON
     */
    public void saveToFile(String filename) throws IOException, SQLException {
        DataBaseAnimals dataBaseAnimals = new DataBaseAnimals();
        dataBaseAnimals.dbConnection(Constants.SERVER + "/mydb", "root", "NoFear@Dinar2021");
        dataBaseAnimals.loadData();
        List<Animal> animalArrayList = dataBaseAnimals.getData();
        new ObjectMapper().writeValue(new File(filename), animalArrayList);
        dataBaseAnimals.close();
    }
}
