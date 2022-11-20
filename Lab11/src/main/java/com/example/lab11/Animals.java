package com.example.lab11;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class Animals implements Serializable { //NOPMD - suppressed RedundantFieldInitializer - TODO explain reason for suppression
   private static final long serialVersionUID = 1L;
    private List<Animal> animals;


    public List<Animal> getAnimals() {
        return animals;
    }

    public void setResults(List<Animal> animals) {
        this.animals = animals;
    }

    /**
     * Реализация загрузки данных отдельным потоком из JSON
     **/
    public Animal loadByURL(String url) throws IOException {
        StringBuilder jsonIn = new StringBuilder();
        final InputStream inputStream = new URL(url).openStream();
        try { //NOPMD - suppressed UseTryWithResources - TODO explain reason for suppression
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));

            int i;
            while ((i = bufferedReader.read()) != -1) {
                jsonIn.append((char) i);
            }
        } finally {

            inputStream.close();
        }
        ObjectMapper om = new ObjectMapper();
        Animal animal = om.readValue(jsonIn.toString(), Animal.class);
        return animal;
    }


    /**
     * Получение одного случайного животного
     **/
    public Animal getRandomAnimal() throws IOException {
        Animal animal = this.loadByURL("https://zoo-animal-api.herokuapp.com/animals/rand/");
        return animal;
    }

}
