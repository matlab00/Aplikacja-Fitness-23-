package com.example.demo;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class FXMLConnector {
    public final class LogInfo {
        private static StringProperty logData = new SimpleStringProperty();
        public static StringProperty logDataProperty() { return logData; }
        public static void setLogData(String data) { logData.set(data); }
        public static String getLogData() { return logData.get(); }
    }
}
