package com.example.demo;

//region imports
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.time.Year;
import java.util.ResourceBundle;
//endregion

public class MainController implements Initializable {

    //region Variables
    @FXML
    public ChoiceBox foodChoiceBox;

    public String[] food = {"Śniadanie","Obiad", "Kolacja"};

    public String[] sex = {"Mężczyzna", "Kobieta"};
    public TextField runDataTextField;
    public Button runAddButton;
    public TextField runCaloriesTextField;
    public TextField runDistanceTextField;
    public TableView<Bieganie> runTableView;
    public TableColumn<Bieganie, String> runDistanceColumn;
    public TableColumn<Bieganie, String> runCaloriesColumn;
    public TableColumn<Bieganie, String> runDataColumn;
    public Button runStatButton;
    public TableColumn runTimeColumn;
    public TextField runTimeTextField;
    public ChoiceBox sexChoiceBox;
    public TextField weightTextField;
    public TextField heightTextField;
    public TextField ageTextField;
    public TableColumn<Data, Integer> weightTableColumn;
    public TableColumn<Data, String> dataDateTableColumn;
    public Button dataAddButton;
    public TableColumn<Data, Integer> heightTableColumn;
    public TableColumn<Data, Integer> bmrTableColumn;
    public TableColumn<Data, Integer> bmiTableColumn;
    public TableView<Data> dataTableView;
    public ChoiceBox ageChoiceBox;
    public Button dataStatButton;
    public TextField dataDateTextField;
    //endregion


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        foodChoiceBox.getItems().addAll(food);
        sexChoiceBox.getItems().addAll(sex);
        for (int i = 1900; i < 2023; i++) {
            ageChoiceBox.getItems().add(i);
        }

        runDistanceColumn.setCellValueFactory(new PropertyValueFactory<>("Dystans"));
        runCaloriesColumn.setCellValueFactory(new PropertyValueFactory<>("Kalorie"));
        runDataColumn.setCellValueFactory(new PropertyValueFactory<>("Data"));
        runTimeColumn.setCellValueFactory(new PropertyValueFactory<>("Czas"));

        weightTableColumn.setCellValueFactory(new PropertyValueFactory<>("Masa"));
        heightTableColumn.setCellValueFactory(new PropertyValueFactory<>("Wzrost"));
        bmrTableColumn.setCellValueFactory(new PropertyValueFactory<>("BMR"));
        bmiTableColumn.setCellValueFactory(new PropertyValueFactory<>("BMI"));
        dataDateTableColumn.setCellValueFactory(new PropertyValueFactory<>("Data"));


    }


    public void runButtonOnAction(ActionEvent actionEvent) {

        Bieganie bieganie = new Bieganie(
                Integer.parseInt(runDistanceTextField.getText()),
                Integer.parseInt(runCaloriesTextField.getText()),
                Integer.parseInt(runTimeTextField.getText()),
                runDataTextField.getText());

        runTableView.getItems().add(bieganie);
    }
    public void runStatButtonOnAction(ActionEvent actionEvent) {

        ObservableList<Bieganie> data = runTableView.getItems();

        NumberAxis xAxis = new NumberAxis(0,100,10);
        xAxis.setLabel("Dystans");

        NumberAxis yAxis = new NumberAxis(0,100,10);
        yAxis.setLabel("Czas");

        LineChart lineChart = new LineChart(yAxis,xAxis);

        XYChart.Series series = new XYChart.Series();

        for (Bieganie item : data) {
            series.getData().add(new XYChart.Data(runDistanceColumn.getCellData(item), runDistanceColumn.getCellData(item)));
        }

        lineChart.getData().add(series);

        Stage stage = new Stage();
        Group root = new Group((lineChart));
        Scene scene = new Scene(root, 600, 400);
        stage.setScene(scene);
        stage.show();
    }

    public void dataAddButtonOnAction(ActionEvent actionEvent) {

        if (weightTextField.getText().isEmpty() || heightTextField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Brak danych");
            alert.setHeaderText("Błąd");
            alert.setContentText("Brak wymaganych danych");
            alert.showAndWait();
        }

        Data data = new Data(
                Integer.parseInt(weightTextField.getText()),
                Integer.parseInt(heightTextField.getText()),
                getDate(),
                calculateBMR()
        );

        dataTableView.getItems().add(data);
    }
    Double calculateBMR() {

        if (sexChoiceBox.getValue().toString() == "Mężczyzna") {
            double bmr = 88.36 + 13.4 * Integer.parseInt(weightTextField.getText()) + 4.8 * Integer.parseInt(heightTextField.getText()) - 5.7 * (Year.now().getValue() - Integer.parseInt(ageChoiceBox.getValue().toString()));
            return bmr;
        } else {
            double bmr = 447.6 + 9.2 * Integer.parseInt(weightTextField.getText()) + 3.1 * Integer.parseInt(heightTextField.getText()) - 4.3 * (Year.now().getValue() - Integer.parseInt(ageChoiceBox.getValue().toString()));
            return bmr;
        }
    }

    String getDate() {

        if (dataDateTextField.getText().isEmpty()) {
            String date = LocalDate.now().toString();
            return date;
        } else {
            String date = dataDateTextField.getText();
            return date;
        }
    }

    public void dataStatButtonOnAction(ActionEvent actionEvent) {

        ObservableList<Data> data = dataTableView.getItems();


        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Data");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Waga");

        LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Waga/Data");

        for (Data item : data) {
            series.getData().add(new XYChart.Data(dataDateTableColumn.getCellData(item), weightTableColumn.getCellData(item)));
        }

        lineChart.getData().add(series);

        Stage stage = new Stage();
        Group root = new Group((lineChart));
        Scene scene = new Scene(root, 600, 400);
        stage.setScene(scene);
        stage.setTitle("Statystyki");
        stage.show();
    }
}