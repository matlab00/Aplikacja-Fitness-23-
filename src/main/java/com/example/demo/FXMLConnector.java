package com.example.demo;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;

public class FXMLConnector {
    public final class LogInfo {
        private static StringProperty logData = new SimpleStringProperty();
        private static ObservableList<Data> dataList;
        private static TableColumn[] tableColumn;
        private static Integer UserID;
        public static StringProperty logDataProperty() { return logData; }
        public static void setLogData(String data) { logData.set(data); }
        public static String getLogData() { return logData.get(); }

        public static Integer getUserID() {
            return UserID;
        }

        public static void setUserID(Integer userID) {
            UserID = userID;
        }

        public static void setObservableList(ObservableList<Data> list) {
            dataList = list;
        }
        public static ObservableList<Data> getObservableList() {
            return dataList;
        }

        public static void setTableColumn(TableColumn[] column) {
            tableColumn = column;
        }

        public static TableColumn[] getTableColumn() {
            return tableColumn;
        }

    }
}
