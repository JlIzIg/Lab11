package com.example.lab11;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class TxtWriter {

    /**
     * метод сохранения List<Animal> в файл TXT
     */
    public void saveToFile(String filename) throws IOException, SQLException {
        DataBaseAnimals dataBaseAnimals = new DataBaseAnimals();
        dataBaseAnimals.dbConnection(Constants.SERVER + "/mydb", "root", "NoFear@Dinar2021");
        dataBaseAnimals.loadData();
        List<Animal> animalArrayList = dataBaseAnimals.getData();
        FileWriter fileWriter = new FileWriter(filename);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        for (Animal animal : animalArrayList) {
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
        dataBaseAnimals.close();
    }

}
