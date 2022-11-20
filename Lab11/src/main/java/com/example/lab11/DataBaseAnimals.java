package com.example.lab11;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.Properties;

import static java.sql.DriverManager.getConnection;

public class DataBaseAnimals {

    private Connection connection;
    private ObservableList<Animal> data;

    /**
     * сеттер для Connection
     */
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public ObservableList<Animal> getData() {
        return data;
    }

    /**
     * сеттер для Data
     */
    public void setData(ObservableList<Animal> data) {
        this.data = data;
    }

    /**
     * метод соединения с БД
     */

    public boolean dbConnection(String conn, String login, String password) {
        boolean result = false;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace(); //NOPMD - suppressed AvoidPrintStackTrace - TODO explain reason for suppression
        }
        try {
            String url = "jdbc:mysql://" + conn;
            Properties properties = new Properties();
            properties.setProperty("user", login);
            properties.setProperty("password", password);
            properties.setProperty("serverTimezone", "UTC");
            properties.setProperty("useSSL", "false");
            properties.setProperty("autoReconnect", "true");
            connection = getConnection(url, properties);
            result = true;
            setConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace(); //NOPMD - suppressed AvoidPrintStackTrace - TODO explain reason for suppression
        }
        return result;
    }

    /**
     * метод агрузки данных
     **/
    public void loadData() {
        try {
            data = FXCollections.observableArrayList();
            Statement statement = connection.createStatement();
            ResultSet resultSet;
            resultSet = statement.executeQuery("select * from amimals;");
            while (resultSet.next()) {
                data.add(new Animal(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("latinName"), resultSet.getString("animalType"), resultSet.getString("activeTime"), resultSet.getDouble("lenMin"), resultSet.getDouble("lenMax"), resultSet.getDouble("wgMin"), resultSet.getDouble("wgMax"), resultSet.getDouble("lifespan"), resultSet.getString("habitat"), resultSet.getString("diet"), resultSet.getString("geoRange"), resultSet.getString("imageLink")));
            }
            setData(data);
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace(); //NOPMD - suppressed AvoidPrintStackTrace - TODO explain reason for suppression
        } //NOPMD - suppressed AvoidPrintStackTrace - TODO explain reason for suppression

    }

    /**
     * метод удаления последней записи
     */
    public void delete() throws SQLException {
        String deleteString = "delete from amimals where id = (select x.id from (select max(t.id) as id  from amimals t) x);";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from amimals;");
            PreparedStatement preparedStatement = connection.prepareStatement(deleteString);
            preparedStatement.executeUpdate(); //for delete in DB
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace(); //NOPMD - suppressed AvoidPrintStackTrace - TODO explain reason for suppression
        } //NOPMD - suppressed AvoidPrintStackTrace - TODO explain reason for suppression
    }

    /**
     * метод добавления в БД
     */
    public void addData() {

        String name;
        String latinName;
        String animalType;
        String activeTime;
        Double lenMin;
        Double lenMax;
        Double wgMin;
        Double wgMax;
        Double lifespan;
        String habitat;
        String diet;
        String geoRange;
        String imageLink;
        try {
            Animals animals = new Animals();
            Animal animal = animals.getRandomAnimal();
            name = animal.getName();
            latinName = animal.getLatinName();
            animalType = animal.getAnimalType();
            activeTime = animal.getActiveTime();
            lenMin = animal.getLenMin();
            lenMax = animal.getLenMax();
            wgMin = animal.getWgMin();
            wgMax = animal.getWgMax();
            lifespan = animal.getLifespan();
            habitat = animal.getHabitat();
            diet = animal.getDiet();
            geoRange = animal.getGeoRange();
            imageLink = animal.getImageLink();
        } catch (Exception e) {
            e.printStackTrace(); //NOPMD - suppressed AvoidPrintStackTrace - TODO explain reason for suppression
            return; //NOPMD - suppressed AvoidPrintStackTrace - TODO explain reason for suppression
        }
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from amimals;");
            PreparedStatement preparedStatement = connection.prepareStatement("insert into amimals(name, latinName, animalType, activeTime, lenMin, lenMax, wgMin, wgMax, lifespan, habitat, diet, geoRange, imageLink) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, latinName);
            preparedStatement.setString(3, animalType);
            preparedStatement.setString(4, activeTime);
            preparedStatement.setDouble(5, lenMin);
            preparedStatement.setDouble(6, lenMax);
            preparedStatement.setDouble(7, wgMin);
            preparedStatement.setDouble(8, wgMax);
            preparedStatement.setDouble(9, lifespan);
            preparedStatement.setString(10, habitat);
            preparedStatement.setString(11, diet);
            preparedStatement.setString(12, geoRange);
            preparedStatement.setString(13, imageLink);
            preparedStatement.executeUpdate(); //for add in DB
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace(); //NOPMD - suppressed AvoidPrintStackTrace - TODO explain reason for suppression
        } //NOPMD - suppressed AvoidPrintStackTrace - TODO explain reason for suppression
    }

    /**
     * закрытие подключения к бд
     **/
    public void close() throws SQLException {
        this.connection.close();
    }
}
