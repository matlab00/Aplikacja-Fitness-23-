package com.example.demo;

//region imports
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
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
    public TextField addUserText;
    public ChoiceBox selectUser;
    public Button addUserButton;
    public Label activeUserLabel;
    public DatePicker dataDatePicker;
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

        DataDBConnection dataDBConnection = new DataDBConnection();
        ObservableList dataArray = dataDBConnection.getUser();
        //ObservableList data2Array = FXCollections.observableArrayList(dataDBConnection.getData());
        //System.out.println("Data: " + dataDBConnection.getData());
        //System.out.println("User: " + dataArray.toString());
        selectUser.setItems(dataArray);
        //dataTableView.getItems().addAll(data2Array);


    }

    public void updateDataTable() {
        dataTableView.getItems().clear();
        System.out.println("table cleared");
        DataDBConnection dataDBConnection = new DataDBConnection();
        ObservableList<Data> newDataArray = FXCollections.observableArrayList(dataDBConnection.getData());
        dataTableView.getItems().addAll(newDataArray);
        dataTableView.refresh();

    }

    private void updateUser() {
        selectUser.getItems().clear();
        DataDBConnection dataDBConnection = new DataDBConnection();

        ObservableList data = dataDBConnection.getUser();

        ObservableList oldData = selectUser.getItems();

        Set uniqueSet = new HashSet();
        uniqueSet.addAll(data);
        uniqueSet.addAll(oldData);

        ObservableList newData = FXCollections.observableArrayList();

        newData.addAll(uniqueSet);
        selectUser.setItems(newData);

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

        try {
            DataDBConnection dataDBConnection = new DataDBConnection();

            String sql = "INSERT INTO Data (weight, sex, age, height, date, BMR, BMI, user_name) VALUES (?,?,?,?,?,?,?,?)";
            try (PreparedStatement stmt = dataDBConnection.getConnection().prepareStatement(sql)) {

                stmt.setInt(1, Integer.parseInt(weightTextField.getText()));
                stmt.setString(2,sexChoiceBox.getValue().toString());
                stmt.setString(3,ageChoiceBox.getValue().toString());
                stmt.setInt(4, Integer.parseInt(heightTextField.getText()));
                if (!getDate().isEmpty()) {
                    stmt.setString(5, getDate());
                } else {
                    stmt.setString(5, getCurrentDate());
                }
                stmt.setInt(6, calculateBMR());
                stmt.setInt(7, calculateBMI());
                stmt.setString(8, selectUser.getValue().toString());
                int rowsAffected = stmt.executeUpdate();
                System.out.println(rowsAffected + " wiersz dodany do tabeli");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
            updateDataTable();
    }
    private Integer calculateBMR() {

        if (sexChoiceBox.getValue().toString().equals("Mężczyzna")) {
            Integer bmr = (int)Math.round(88.36 + 13.4 * Integer.parseInt(weightTextField.getText()) + 4.8 * Integer.parseInt(heightTextField.getText()) - 5.7 * (Year.now().getValue() - Integer.parseInt(ageChoiceBox.getValue().toString())));

            return bmr;
        } else {
            Integer bmr = (int)Math.round(447.6 + 9.2 * Integer.parseInt(weightTextField.getText()) + 3.1 * Integer.parseInt(heightTextField.getText()) - 4.3 * (Year.now().getValue() - Integer.parseInt(ageChoiceBox.getValue().toString())));
            return bmr;
        }
    }

    private Integer calculateBMI() {
        Integer bmi = Integer.parseInt(weightTextField.getText())/(Integer.parseInt(heightTextField.getText())/100)^2;
        return bmi;
    }

    String getCurrentDate() {

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

        data.sort(Comparator.comparing(item -> dataDateTableColumn.getCellData(item)));

        for (Data item : data) {
            series.getData().add(new XYChart.Data(dataDateTableColumn.getCellData(item), weightTableColumn.getCellData(item)));
        }

        lineChart.getData().add(series);
        lineChart.setAxisSortingPolicy(LineChart.SortingPolicy.X_AXIS);



        Stage stage = new Stage();
        //Group root = new Group((lineChart));
        //Scene scene = new Scene(root, 600, 400);
        //stage.setScene(scene);
       // stage.setTitle("Statystyki");
       // stage.show();
        start(stage);
    }

    public void addUserButtonOnAction(ActionEvent actionEvent) {
        try {
            DataDBConnection dataDBConnection = new DataDBConnection();

            String sql = "INSERT INTO Users (user_name) VALUES (?)";
            try (PreparedStatement stmt = dataDBConnection.getConnection().prepareStatement(sql)) {

                stmt.setString(1, addUserText.getText());
                int rowsAffected = stmt.executeUpdate();
                System.out.println(rowsAffected + " wiersz dodany do tabeli");

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
        updateUser();
        addUserText.clear();
    }

    public void changeUserButtonOnAction(ActionEvent actionEvent) {

        FXMLConnector.LogInfo.setLogData(selectUser.getValue().toString());
        System.out.println(FXMLConnector.LogInfo.getLogData());
        activeUserLabel.setText(FXMLConnector.LogInfo.getLogData());
        updateDataTable();
    }

    public void deleteUserOnAction(ActionEvent actionEvent) {

        String profile = FXMLConnector.LogInfo.getLogData();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Potwierdzenie");
        alert.setHeaderText("Czy na pewno chcesz usunąć profil?");
        alert.setContentText("Kliknij OK, aby kontynuować.");

        ButtonType buttonTypeOK = new ButtonType("OK");
        ButtonType buttonTypeCancel = new ButtonType("Anuluj", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(buttonTypeOK, buttonTypeCancel);

        alert.showAndWait().ifPresent(buttonType -> {
            if (buttonType == buttonTypeOK) {
                try {
                    DataDBConnection dataDBConnection = new DataDBConnection();
                    String query = "DELETE FROM Data where user_name = '"+profile+"'";
                    String query2 = "DELETE FROM Users where user_name = '"+profile+"'";
                    try (Connection connection = dataDBConnection.getConnection()) {

                        PreparedStatement preparedStatement1 = connection.prepareStatement(query);
                        PreparedStatement preparedStatement2 = connection.prepareStatement(query2);

                        int rowsAffected1 = preparedStatement1.executeUpdate();
                        int rowsAffected2 = preparedStatement2.executeUpdate();
                        System.out.println(profile + "usunięty");

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } catch (NumberFormatException e) {
                    throw new RuntimeException(e);
                }
                updateUser();
                updateDataTable();
            } else {
                System.out.println("no nie");
            }
        });
    }
    public String getDate() {
        LocalDate date = dataDatePicker.getValue();
        String formatedDate = date.format((DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        return formatedDate;
    }
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Statystyki");

        // Create radio buttons
        RadioButton radioButton1 = new RadioButton("BMR");
        RadioButton radioButton2 = new RadioButton("BMI");
        RadioButton radioButton3 = new RadioButton("Waga");

        // Create a toggle group and add radio buttons to it
        ToggleGroup toggleGroup = new ToggleGroup();
        radioButton1.setToggleGroup(toggleGroup);
        radioButton2.setToggleGroup(toggleGroup);
        radioButton3.setToggleGroup(toggleGroup);

        // Create buttons
        Button okButton = new Button("OK");
        Button cancelButton = new Button("Cancel");

        // Create a vertical layout and add components to it
        VBox vbox = new VBox(10); // spacing between components
        vbox.setPadding(new Insets(10)); // padding around the layout
        vbox.getChildren().addAll(radioButton1, radioButton2, radioButton3, okButton, cancelButton);

        // Create a scene and set it on the primary stage
        Scene scene = new Scene(vbox, 200, 200);
        primaryStage.setScene(scene);

        primaryStage.show();
    }
}