package com.example.demo;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;
import javafx.scene.chart.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class StatWindow {
    public LineChart lineChart;
    public RadioButton bmrButton;
    public RadioButton weightButton;
    public RadioButton bmiButton;
    public Button closeButton;

    public void closeButtonOnAction(ActionEvent actionEvent) {
        Stage currentstage = (Stage) lineChart.getScene().getWindow();
        currentstage.close();
    }

    public void bmiButtonOnAction(ActionEvent actionEvent) {
        if (bmiButton.isSelected()) {

            weightButton.setSelected(false);
            bmrButton.setSelected(false);
            lineChart.getData().clear();

            ObservableList<Data> data = FXMLConnector.LogInfo.getObservableList();
            TableColumn[] tableColumns;
            tableColumns = FXMLConnector.LogInfo.getTableColumn();

            CategoryAxis xAxis = new CategoryAxis();
            xAxis.setLabel("Data");

            NumberAxis yAxis = new NumberAxis();
            yAxis.setLabel("BMI");

            //LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);
            XYChart.Series<String, Number> series = new XYChart.Series<>();
            series.setName("BMI/Data");

            //data.sort(Comparator.comparing(item -> tableColumns[1].getCellData(item)));

            for (Data item : data) {
                series.getData().add(new XYChart.Data(tableColumns[0].getCellData(item), tableColumns[2].getCellData(item)));
            }
            series.getData().sort(Comparator.comparing(XYChart.Data::getXValue));
            lineChart.getData().add(series);
        } else {lineChart.getData().clear();}
    }

    public void bmrButtonOnAction(ActionEvent actionEvent) {
        if (bmrButton.isSelected()) {

            weightButton.setSelected(false);
            bmiButton.setSelected(false);
            lineChart.getData().clear();

            ObservableList<Data> data = FXMLConnector.LogInfo.getObservableList();
            TableColumn[] tableColumns;
            tableColumns = FXMLConnector.LogInfo.getTableColumn();

            CategoryAxis xAxis = new CategoryAxis();
            xAxis.setLabel("Data");

            NumberAxis yAxis = new NumberAxis();
            yAxis.setLabel("BMR");

            //LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);
            XYChart.Series<String, Number> series = new XYChart.Series<>();
            series.setName("BMR/Data");

            //data.sort(Comparator.comparing(item -> tableColumns[1].getCellData(item)));

            for (Data item : data) {
                series.getData().add(new XYChart.Data(tableColumns[0].getCellData(item), tableColumns[1].getCellData(item)));
            }

            lineChart.getData().add(series);
        } else {lineChart.getData().clear();}
    }

    public void weightButtonOnAction(ActionEvent actionEvent) {
        if (weightButton.isSelected()) {

            bmrButton.setSelected(false);
            bmiButton.setSelected(false);
            lineChart.getData().clear();

            ObservableList<Data> data = FXMLConnector.LogInfo.getObservableList();
            TableColumn[] tableColumns = FXMLConnector.LogInfo.getTableColumn();

            CategoryAxis xAxis = new CategoryAxis();
            xAxis.setLabel("Data");

            NumberAxis yAxis = new NumberAxis();
            yAxis.setLabel("Waga");

            //LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);
            XYChart.Series<String, Number> series = new XYChart.Series<>();
            series.setName("Waga/Data");

            //data.sort(Comparator.comparing(item -> tableColumns[1].getCellData(item)));

            for (Data item : data) {
                series.getData().add(new XYChart.Data(tableColumns[0].getCellData(item), tableColumns[3].getCellData(item)));
            }

            lineChart.getData().add(series);
        } else {lineChart.getData().clear();}

    }

    private Date parseDate(String dateString) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
