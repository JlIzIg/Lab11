package com.example.lab11;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AnimalsController implements Initializable { //NOPMD - suppressed AtLeastOneConstructor - TODO explain reason for suppression
    /**
     * инициализация элемента таблица
     **/
    @FXML
    public TableView<Animal> table1;
    @FXML
    public ChoiceBox<String> chooseFormat;
    @FXML
    public ChoiceBox<String> chooseAction;
    /***инициализация колонки id**/
    @FXML
    TableColumn<Animal, Integer> id;
    /***инициализация колонки name**/
    @FXML
    TableColumn<Animal, String> name;
    /***инициализация колонки latinName**/
    @FXML
    TableColumn<Animal, String> latinName;
    /***инициализация колонки animalType**/

    @FXML
    TableColumn<Animal, String> animalType;
    /***инициализация колонки activeTime**/
    @FXML
    TableColumn<Animal, String> activeTime;
    /***инициализация колонки lenMin**/
    @FXML
    TableColumn<Animal, Double> lenMin;
    /***инициализация колонки lenMax**/
    @FXML
    TableColumn<Animal, Double> lenMax;
    /***инициализация колонки wgMin**/
    @FXML
    TableColumn<Animal, Double> wgMin;
    /***инициализация колонки wgMax**/
    @FXML
    TableColumn<Animal, Double> wgMax;
    /***инициализация колонки lifespan**/
    @FXML
    TableColumn<Animal, Double> lifespan;
    /***инициализация колонки habitat**/
    @FXML
    TableColumn<Animal, String> habitat;
    /***инициализация колонки diet**/
    @FXML
    TableColumn<Animal, String> diet;
    /***инициализация колонки geoRange**/
    @FXML
    TableColumn<Animal, String> geoRange;
    /***инициализация колонки imageLink**/
    @FXML
    TableColumn<Animal, String> imageLink;
    /**
     * экземпляр класса DataBaseAnimals
     **/
    DataBaseAnimals dataBaseAnimals;
    /**
     * кземпляр класса SaveAnimal
     **/
    SaveAnimal saveAnimal;
    /***поле connection*/
    private Connection connection;

    /**
     * заполнение таблицы данными
     */
    public void setTable1(ObservableList data) {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        latinName.setCellValueFactory(new PropertyValueFactory<>("latinName"));
        animalType.setCellValueFactory(new PropertyValueFactory<>("animalType"));
        activeTime.setCellValueFactory(new PropertyValueFactory<>("activeTime"));
        lenMin.setCellValueFactory(new PropertyValueFactory<>("lenMin"));
        lenMax.setCellValueFactory(new PropertyValueFactory<>("lenMax"));
        wgMin.setCellValueFactory(new PropertyValueFactory<>("wgMin"));
        wgMax.setCellValueFactory(new PropertyValueFactory<>("wgMax"));
        lifespan.setCellValueFactory(new PropertyValueFactory<>("lifespan"));
        habitat.setCellValueFactory(new PropertyValueFactory<>("habitat"));
        diet.setCellValueFactory(new PropertyValueFactory<>("diet"));
        geoRange.setCellValueFactory(new PropertyValueFactory<>("geoRange"));
        imageLink.setCellValueFactory(new PropertyValueFactory<>("imageLink"));
        table1.setItems(data);
    }

    /**
     * инициализация
     **/
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        connection = null;
        dataBaseAnimals = new DataBaseAnimals();
        saveAnimal = new SaveAnimal();
        chooseFormat.getItems().addAll( ".txt", ".json",".xls");
        chooseFormat.setOnAction(this::getFormat);
        chooseAction.getItems().addAll("Connect to DB", "Load Data", "Add One Animal", "Exit");
        chooseAction.setOnAction(this::getAction);
    }

    /**
     * выбор действия с БД
     **/
    public void getAction(ActionEvent event) {

        String actionValue = chooseAction.getValue();
        switch (actionValue) {
            case ("Connect to DB"):
                dataBaseAnimals.dbConnection(Constants.SERVER + "/mydb", "root", "NoFear@Dinar2021");
                break;
            case ("Load Data"):
                dataBaseAnimals.loadData();
                setTable1(dataBaseAnimals.getData());
                break;
            case ("Add One Animal"):
                dataBaseAnimals.addData();
                break;
            case ("Exit"):
                try {
                    dataBaseAnimals.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;

        }
    }

    /**
     * выбор формата в котором сохраняется таблица в файл
     */
    public void getFormat(ActionEvent event) {
        saveAnimal.setTableView(table1);
        String formatValue = chooseFormat.getValue();
        switch (formatValue) {
            case (".txt"):
                try {
                    saveAnimal.saveToTxt();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case (".json"):
                try {
                    saveAnimal.saveToJson();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case (".xls"):
                try {
                    saveAnimal.saveToExcel();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    /**
     * удаление последней записи
     */
    public void delete(ActionEvent actionEvent) throws SQLException {
        dataBaseAnimals.delete();
    }
}