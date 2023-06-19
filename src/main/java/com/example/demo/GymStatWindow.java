package com.example.demo;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Comparator;
import java.util.ResourceBundle;

public class GymStatWindow implements Initializable {
    public Button closeButton;
    public LineChart lineChart;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Silownia> data = FXMLConnector.LogInfo.getGymObservableList();
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

        for (Silownia item : data) {
            series.getData().add(new XYChart.Data(tableColumns[0].getCellData(item), tableColumns[1].getCellData(item)));
        }

        series.getData().sort(Comparator.comparing(XYChart.Data::getXValue));
        lineChart.getData().add(series);
    }



    public void closeButtonOnAction(ActionEvent actionEvent) {
        Stage currentstage = (Stage) lineChart.getScene().getWindow();
        currentstage.close();
    }
}
