package com.example.demo;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;

import java.util.Comparator;

public class RunStatWindow {
    public Button closeButton;
    public RadioButton caloriesButton;
    public RadioButton distanceButton;
    public LineChart lineChart;

    public void closeButtonOnAction(ActionEvent actionEvent) {
        Stage currentstage = (Stage) lineChart.getScene().getWindow();
        currentstage.close();
    }

    public void caloriesButtonOnAction(ActionEvent actionEvent) {
        if (caloriesButton.isSelected()) {

            distanceButton.setSelected(false);
            lineChart.getData().clear();

            ObservableList<Bieganie> data = FXMLConnector.LogInfo.getRunObservableList();
            TableColumn[] tableColumns;
            tableColumns = FXMLConnector.LogInfo.getTableColumn();

            CategoryAxis xAxis = new CategoryAxis();
            xAxis.setLabel("Data");

            NumberAxis yAxis = new NumberAxis();
            yAxis.setLabel("Kalorie");

            //LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);
            XYChart.Series<String, Number> series = new XYChart.Series<>();
            series.setName("Kalorie/Data");

            //data.sort(Comparator.comparing(item -> tableColumns[1].getCellData(item)));

            for (Bieganie item : data) {
                series.getData().add(new XYChart.Data(tableColumns[0].getCellData(item), tableColumns[2].getCellData(item)));
            }

            series.getData().sort(Comparator.comparing(XYChart.Data::getXValue));
            lineChart.getData().add(series);
        } else {lineChart.getData().clear();}
    }

    public void distanceButtonOnAction(ActionEvent actionEvent) {
        if (caloriesButton.isSelected()) {

            caloriesButton.setSelected(false);
            lineChart.getData().clear();

            ObservableList<Bieganie> data = FXMLConnector.LogInfo.getRunObservableList();
            TableColumn[] tableColumns;
            tableColumns = FXMLConnector.LogInfo.getTableColumn();

            CategoryAxis xAxis = new CategoryAxis();
            xAxis.setLabel("Data");

            NumberAxis yAxis = new NumberAxis();
            yAxis.setLabel("Dystans");

            //LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);
            XYChart.Series<String, Number> series = new XYChart.Series<>();
            series.setName("Dystans/Data");

            //data.sort(Comparator.comparing(item -> tableColumns[1].getCellData(item)));

            for (Bieganie item : data) {
                series.getData().add(new XYChart.Data(tableColumns[0].getCellData(item), tableColumns[1].getCellData(item)));
            }

            series.getData().sort(Comparator.comparing(XYChart.Data::getXValue));
            lineChart.getData().add(series);
        } else {lineChart.getData().clear();}
    }
}
