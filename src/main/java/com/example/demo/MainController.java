package com.example.demo;

//region imports
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
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
    public TableColumn runMetColumn;
    public DatePicker gymDatePicker;
    public ChoiceBox gymChoiceBox;
    public TextField gymTimeTextField;
    public TableColumn gymDateTableColumn;
    public TableColumn gymCalorieTableColumn;
    public TableColumn gymMetTableColumn;
    public TableColumn gymTimeTableColumn;
    public TableColumn gymExTableColumn;
    public TableView gymTableView;
    public DatePicker foodDatePicker;
    public Button foodAddButton;
    public TextField foodCaloriesTextField;
    public TextField foodMealTextField;
    public TableColumn foodDateTableColumn;
    public TableColumn foodCategoryTableColumn;
    public TableColumn foodCaloriesTableColumn;
    public TableColumn foodMealTableColumn;
    public TableView foodTableView;
    public TextField foodMealKcalTextFieldArea;

    private String[] food = {"Śniadanie","Obiad", "Kolacja"};

    private String[] sex = {"Mężczyzna", "Kobieta"};
    private String[] metString = {"Niskie", "Średnie", "Szybkie", "Sprint" };
    private String[] excercises = {"Pompki", "Brzuszki", "Przysiady", "Podciąganie", "Wyciskanie sztangi", "Martwy ciąg", "Przysiady ze sztangą"};
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
    public DatePicker runDatePicker;
    public ChoiceBox runMetChoiceBox;
    //endregion


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        foodChoiceBox.getItems().addAll(food);
        sexChoiceBox.getItems().addAll(sex);
        for (int i = 1900; i < 2023; i++) {
            ageChoiceBox.getItems().add(i);
        }

        runMetChoiceBox.setValue("Tempo");
        runMetChoiceBox.getItems().addAll(metString);

        gymChoiceBox.setValue("Ćwiczenie");
        gymChoiceBox.getItems().addAll(excercises);


        runDistanceColumn.setCellValueFactory(new PropertyValueFactory<>("Dystans"));
        runCaloriesColumn.setCellValueFactory(new PropertyValueFactory<>("Kalorie"));
        runDataColumn.setCellValueFactory(new PropertyValueFactory<>("Data"));
        runTimeColumn.setCellValueFactory(new PropertyValueFactory<>("Czas"));
        runMetColumn.setCellValueFactory(new PropertyValueFactory<>("Met"));

        weightTableColumn.setCellValueFactory(new PropertyValueFactory<>("Masa"));
        heightTableColumn.setCellValueFactory(new PropertyValueFactory<>("Wzrost"));
        bmrTableColumn.setCellValueFactory(new PropertyValueFactory<>("BMR"));
        bmiTableColumn.setCellValueFactory(new PropertyValueFactory<>("BMI"));
        dataDateTableColumn.setCellValueFactory(new PropertyValueFactory<>("Data"));

        gymExTableColumn.setCellValueFactory(new PropertyValueFactory<>("Cwiczenie"));
        gymTimeTableColumn.setCellValueFactory(new PropertyValueFactory<>("Czas"));
        gymMetTableColumn.setCellValueFactory(new PropertyValueFactory<>("Met"));
        gymCalorieTableColumn.setCellValueFactory(new PropertyValueFactory<>("Kalorie"));
        gymDateTableColumn.setCellValueFactory(new PropertyValueFactory<>("Data"));

        foodMealTableColumn.setCellValueFactory((new PropertyValueFactory<>("Posilek")));
        foodCaloriesTableColumn.setCellValueFactory((new PropertyValueFactory<>("Kalorie")));
        foodCategoryTableColumn.setCellValueFactory((new PropertyValueFactory<>("Rodzaj")));
        foodDateTableColumn.setCellValueFactory((new PropertyValueFactory<>("Data")));

        DataDBConnection dataDBConnection = new DataDBConnection();
        ObservableList<User> userArray = dataDBConnection.getUser();

        for (User user : userArray) {
            selectUser.getItems().add(user.getUser_name());
        }

    }

    public void updateDataTable() {
        dataTableView.getItems().clear();
        DataDBConnection dataDBConnection = new DataDBConnection();
        ObservableList<Data> newDataArray = FXCollections.observableArrayList(dataDBConnection.getData());
        dataTableView.getItems().addAll(newDataArray);
        dataTableView.refresh();

        runTableView.getItems().clear();
        ObservableList<Bieganie> newDataArray1 = FXCollections.observableArrayList(dataDBConnection.getBieganie());
        runTableView.getItems().addAll(newDataArray1);
        runTableView.refresh();

        gymTableView.getItems().clear();
        ObservableList<Silownia> newDataArray2 = FXCollections.observableArrayList(dataDBConnection.getSilownia());
        gymTableView.getItems().addAll(newDataArray2);
        gymTableView.refresh();

        foodTableView.getItems().clear();
        ObservableList<Jedzenie> newDataArray3 = FXCollections.observableArrayList(dataDBConnection.getJedzenie());
        foodTableView.getItems().addAll(newDataArray3);
        foodTableView.refresh();
    }

    private void updateUser() {
        selectUser.getItems().clear();
        DataDBConnection dataDBConnection = new DataDBConnection();
        ObservableList<User> userArray = dataDBConnection.getUser();

        for (User user : userArray) {
            selectUser.getItems().add(user.getUser_name());
        }

    }

    public void runStatButtonOnAction(ActionEvent actionEvent) {

        runTableView.getSortOrder().add(runDataColumn);
        runDataColumn.setSortType(TableColumn.SortType.ASCENDING);
        runTableView.sort();

        ObservableList<Bieganie> data = runTableView.getItems();
        FXMLConnector.LogInfo.setRunObservableList(data);
        TableColumn[] tableColumns = new TableColumn[3];
        tableColumns[0] = runDataColumn;
        tableColumns[1] = runDistanceColumn;
        tableColumns[2] = runCaloriesColumn;
        FXMLConnector.LogInfo.setTableColumn(tableColumns);

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("RunStatWindow.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Statystyki");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {

        }
    }


    public void dataAddButtonOnAction(ActionEvent actionEvent) {


        System.out.println("user is: "+FXMLConnector.LogInfo.getLogData());

            if (weightTextField.getText().isEmpty() || heightTextField.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Brak danych");
                alert.setHeaderText("Błąd");
                alert.setContentText("Brak wymaganych danych");
                alert.showAndWait();
            }

            try {
                DataDBConnection dataDBConnection = new DataDBConnection();

                String sql = "INSERT INTO Data (weight, sex, age, height, date, BMR, BMI, UserID) VALUES (?,?,?,?,?,?,?,?)";
                try (PreparedStatement stmt = dataDBConnection.getConnection().prepareStatement(sql)) {

                    stmt.setInt(1, Integer.parseInt(weightTextField.getText()));
                    stmt.setString(2, sexChoiceBox.getValue().toString());
                    stmt.setString(3, ageChoiceBox.getValue().toString());
                    stmt.setInt(4, Integer.parseInt(heightTextField.getText()));
                    //if (!getDate().toString().isEmpty()) {
                        stmt.setString(5, getDate(dataDatePicker));
                   // } else {
                      //  stmt.setString(5, getCurrentDate());
                  //  }
                    stmt.setInt(6, calculateBMR());
                    stmt.setInt(7, calculateBMI());
                    stmt.setInt(8, FXMLConnector.LogInfo.getUserID());
                    int rowsAffected = stmt.executeUpdate();
                    System.out.println(rowsAffected + " wiersz dodany do tabeli");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } catch (NumberFormatException | NullPointerException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Brak danych");
                alert.setHeaderText("Błąd");
                alert.setContentText("Brak wymaganych danych");
                alert.showAndWait();
            }
            updateDataTable();

    }

    private Integer getMet() {
        Integer met;
        String tempo = runMetChoiceBox.getValue().toString();
        switch (tempo) {
            case "Niskie":
                met = 7;
                return met;
            case "Średnie":
                met = 10;
                return met;
            case "Szybkie":
                met = 14;
                return met;
            case "Sprint":
                met = 20;
                return met;
        }
        return 0;
    }

    private Integer getGymMet() {
        Integer met;
        String cwiczenie = gymChoiceBox.getValue().toString();
        switch (cwiczenie) {
            case "Pompki":
                met = 12;
                return met;
            case "Brzuszki":
                met = 8;
                return met;
            case "Przysiady":
                met = 10;
                return met;
            case "Podciąganie":
                met = 15;
                return met;
            case "Wyciskanie sztangi":
                met = 20;
                return met;
            case "Martwy ciąg":
                met = 18;
                return met;
            case "Przysiady ze sztangą":
                met = 19;
                return met;
            default:
                return -1;
        }

    }

    public void runButtonOnAction(ActionEvent actionEvent) {


        System.out.println("user is: "+FXMLConnector.LogInfo.getLogData());

        /*if (weightTextField.getText().isEmpty() || heightTextField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Brak danych");
            alert.setHeaderText("Błąd");
            alert.setContentText("Brak wymaganych danych");
            alert.showAndWait();
        }*/

        try {
            DataDBConnection dataDBConnection = new DataDBConnection();

            String sql = "INSERT INTO Bieganie (UserID, dystans, kalorie, czas, data, met ) VALUES (?,?,?,?,?,?)";
            try (PreparedStatement stmt = dataDBConnection.getConnection().prepareStatement(sql)) {

                stmt.setInt(1, FXMLConnector.LogInfo.getUserID());
                stmt.setDouble(2, Double.parseDouble(runDistanceTextField.getText()));
                stmt.setDouble(3,calculateRunCalories());
                stmt.setDouble(4,Double.parseDouble(runTimeTextField.getText().toString()));
                stmt.setString(5,getDate(runDatePicker));
                stmt.setInt(6,getMet());

                int rowsAffected = stmt.executeUpdate();
                System.out.println(rowsAffected + " wiersz dodany do tabeli");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (NumberFormatException | NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Brak danych");
            alert.setHeaderText("Błąd");
            alert.setContentText("Brak wymaganych danych");
            alert.showAndWait();
        }
        updateDataTable();

    }

    public void gymAddButtonOnAction(ActionEvent actionEvent) {


        System.out.println("user is: "+FXMLConnector.LogInfo.getLogData());

        /*if (weightTextField.getText().isEmpty() || heightTextField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Brak danych");
            alert.setHeaderText("Błąd");
            alert.setContentText("Brak wymaganych danych");
            alert.showAndWait();
        }*/

        try {
            DataDBConnection dataDBConnection = new DataDBConnection();

            String sql = "INSERT INTO Silownia (UserID, cwiczenie, MET, czas, data, kalorie ) VALUES (?,?,?,?,?,?)";
            try (PreparedStatement stmt = dataDBConnection.getConnection().prepareStatement(sql)) {

                stmt.setInt(1, FXMLConnector.LogInfo.getUserID());
                stmt.setString(2, gymChoiceBox.getValue().toString());
                stmt.setDouble(3,getGymMet());
                stmt.setDouble(4,Double.parseDouble(gymTimeTextField.getText().toString()));
                stmt.setString(5,getDate(gymDatePicker));
                stmt.setDouble(6,calculateGymCalories());

                int rowsAffected = stmt.executeUpdate();
                System.out.println(rowsAffected + " wiersz dodany do tabeli");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (NumberFormatException | NullPointerException e) {
            /*Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Brak danych");
            alert.setHeaderText("Błąd");
            alert.setContentText("Brak wymaganych danych");
            alert.showAndWait();*/
        }
        updateDataTable();

    }

    private Double calculateGymCalories() {
        ObservableList<Data> dataList = dataTableView.getItems();
        int size = dataList.size();
        Data lastItem = dataList.get(size-1);
        Integer lastValue = weightTableColumn.getCellData(lastItem);

        Double calories;
        String cwiczenie = gymChoiceBox.getValue().toString();
        System.out.println(cwiczenie);
        Double time = Double.parseDouble(gymTimeTextField.getText().toString());
        switch (cwiczenie) {
            case "Pompki":
                calories = time * (12*3.5*lastValue)/200;
                System.out.println("kalorie:"+calories);
                return calories;
            case "Brzuszki":
                calories = time * (8*3.5*lastValue)/200;;
                System.out.println("kalorie:"+calories);
                return calories;
            case "Przysiady":
                calories = time * (10*3.5*lastValue)/200;;
                System.out.println("kalorie:"+calories);
                return calories;
            case "Podciąganie":
                calories = time * (15*3.5*lastValue)/200;;
                System.out.println("kalorie:"+calories);
                return calories;
            case "Wyciskanie sztangi":
                calories = time * (20*3.5*lastValue)/200;;
                System.out.println("kalorie:"+calories);
                return calories;
            case "Martwy ciąg":
                calories = time * (8*3.5*lastValue)/200;;
                System.out.println("kalorie:"+calories);
                return calories;
            case "Przysiady ze sztangą":
                calories = time * (19*3.5*lastValue)/200;;
                System.out.println("kalorie:"+calories);
                return calories;

            default:
                return 1.0;
        }
    }

    private Double calculateRunCalories() {
        ObservableList<Data> dataList = dataTableView.getItems();
        int size = dataList.size();
        Data lastItem = dataList.get(size-1);
        Integer lastValue = weightTableColumn.getCellData(lastItem);
        //System.out.println("czas biegu:"+((Integer.parseInt(runTimeTextField.getText().toString()))/60));
        Double calories;
        String tempo = runMetChoiceBox.getValue().toString();
        System.out.println(tempo);
        Double time = Double.parseDouble(runTimeTextField.getText().toString())/60;
        switch (tempo) {
            case "Niskie":
                calories = 7.0 * lastValue * time ;
                System.out.println("kalorie:"+calories);
                return calories;
            case "Średnie":
                calories = 10.0 * lastValue * time;
                System.out.println("kalorie:"+calories);
                return calories;
            case "Szybkie":
                calories = 14.0 * lastValue * time;
                System.out.println("kalorie:"+calories);
                return calories;
            case "Sprint":
                calories = 20.0 * lastValue * time;
                System.out.println("kalorie:"+calories);
                return calories;

            default:
                return 1.0;
        }
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
        Integer bmi = Integer.parseInt(weightTextField.getText())/((Integer.parseInt(heightTextField.getText())/100)^2);
        return bmi;
    }

    public void dataStatButtonOnAction(ActionEvent actionEvent) {

        dataTableView.getSortOrder().add(dataDateTableColumn);
        dataDateTableColumn.setSortType(TableColumn.SortType.ASCENDING);
        dataTableView.sort();

        ObservableList<Data> data = dataTableView.getItems();
        FXMLConnector.LogInfo.setObservableList(data);
        TableColumn[] tableColumns = new TableColumn[5];
        tableColumns[0] = dataDateTableColumn;
        tableColumns[1] = bmrTableColumn;
        tableColumns[2] = bmiTableColumn;
        tableColumns[3] = weightTableColumn;
        FXMLConnector.LogInfo.setTableColumn(tableColumns);

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("StatWindow.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Statystyki");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {

        }
    }

    public void addUserButtonOnAction(ActionEvent actionEvent) {
        if (!(addUserText.getText().toString().length() < 6)) {
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
        } else {alert("Nazwa użytkownika musi mieć conajmniej 6 znaków ");}
    }

    public void changeUserButtonOnAction(ActionEvent actionEvent) {

        DataDBConnection dataDBConnection = new DataDBConnection();
        ObservableList<User> userArray = dataDBConnection.getUser();

        FXMLConnector.LogInfo.setLogData(selectUser.getValue().toString());
        System.out.println(FXMLConnector.LogInfo.getLogData());

        for (User item : userArray) {
            if (item.getUser_name().toString().equals(selectUser.getValue().toString())) {
                Integer activeUserId = item.getUserID();
                System.out.println("active user id: " + activeUserId);
                FXMLConnector.LogInfo.setUserID(activeUserId);
            }
        }
        System.out.println("ActiveUserID: "+FXMLConnector.LogInfo.getUserID());
        activeUserLabel.setText(FXMLConnector.LogInfo.getLogData());
        updateDataTable();
    }

    public void deleteUserOnAction(ActionEvent actionEvent) {

        Integer profile = FXMLConnector.LogInfo.getUserID();
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
                    String query = "DELETE FROM Data where UserID = '"+profile+"'";
                    String query2 = "DELETE FROM Users where UserID = '"+profile+"'";
                    String query3 = "DELETE FROM Silownia where UserID = '"+profile+"'";
                    String query4 = "DELETE FROM Jedzenie where UserID = '"+profile+"'";
                    String query5 = "DELETE FROM Bieganie where UserID = '"+profile+"'";
                    try (Connection connection = dataDBConnection.getConnection()) {

                        PreparedStatement preparedStatement1 = connection.prepareStatement(query);
                        PreparedStatement preparedStatement2 = connection.prepareStatement(query2);
                        PreparedStatement preparedStatement3 = connection.prepareStatement(query3);
                        PreparedStatement preparedStatement4 = connection.prepareStatement(query4);
                        PreparedStatement preparedStatement5 = connection.prepareStatement(query5);

                        int rowsAffected1 = preparedStatement1.executeUpdate();
                        int rowsAffected2 = preparedStatement2.executeUpdate();
                        int rowsAffected3 = preparedStatement3.executeUpdate();
                        int rowsAffected4 = preparedStatement4.executeUpdate();
                        int rowsAffected5 = preparedStatement5.executeUpdate();
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
            activeUserLabel.setText("Brak");

        });
    }

    public String getDate(DatePicker datePicker) {
        LocalDate date = datePicker.getValue();
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

    public void alert(String text){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Błąd");
        alert.setHeaderText("Błąd");
        alert.setContentText(text);
        alert.showAndWait();
    }

    public void foodAddButtonOnAction(ActionEvent actionEvent) {
        System.out.println("user is: "+FXMLConnector.LogInfo.getLogData());

        /*if (weightTextField.getText().isEmpty() || heightTextField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Brak danych");
            alert.setHeaderText("Błąd");
            alert.setContentText("Brak wymaganych danych");
            alert.showAndWait();
        }*/

        try {
            DataDBConnection dataDBConnection = new DataDBConnection();

            String sql = "INSERT INTO Jedzenie (UserID, posilek, rodzaj, kalorie, data) VALUES (?,?,?,?,?)";
            try (PreparedStatement stmt = dataDBConnection.getConnection().prepareStatement(sql)) {

                stmt.setInt(1, FXMLConnector.LogInfo.getUserID());
                stmt.setString(2, foodMealTextField.getText().toString());
                stmt.setString(3,foodChoiceBox.getValue().toString());
                stmt.setInt(4, Integer.parseInt(foodCaloriesTextField.getText().toString()));
                stmt.setString(5,getDate(foodDatePicker));


                int rowsAffected = stmt.executeUpdate();
                System.out.println(rowsAffected + " wiersz dodany do tabeli");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (NumberFormatException | NullPointerException e) {
            /*Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Brak danych");
            alert.setHeaderText("Błąd");
            alert.setContentText("Brak wymaganych danych");
            alert.showAndWait();*/
        }
        updateDataTable();
    }

    public void gymStatButtonOnAction(ActionEvent actionEvent) {
        gymTableView.getSortOrder().add(gymDateTableColumn);
        gymDateTableColumn.setSortType(TableColumn.SortType.ASCENDING);
        gymTableView.sort();

        ObservableList<Silownia> data = gymTableView.getItems();
        FXMLConnector.LogInfo.setGymObservableList(data);
        TableColumn[] tableColumns = new TableColumn[5];
        tableColumns[0] = dataDateTableColumn;
        tableColumns[1] = gymCalorieTableColumn;
        FXMLConnector.LogInfo.setTableColumn(tableColumns);

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("GymStatWindow.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Statystyki");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {

        }
    }


}