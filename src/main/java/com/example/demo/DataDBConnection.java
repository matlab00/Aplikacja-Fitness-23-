package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;

import java.sql.*;
import java.util.ArrayList;


public class DataDBConnection {

    public Connection connection;
    public DataDBConnection() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:Database.sqlite");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Data> getData() {
        ObservableList<Data> dataList = FXCollections.observableArrayList();

        String profile = FXMLConnector.LogInfo.getLogData();
        System.out.println("Profile: " + profile);
        try {

            String query = "SELECT * FROM DATA where user_name = '" + profile + "'";
            System.out.println("Query: "+query);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);


            while (resultSet.next()) {
                Data data = new Data(
                       resultSet.getInt("weight"),
                        resultSet.getInt("height"),
                        resultSet.getString("date"),
                        resultSet.getInt("BMR"),
                        resultSet.getInt("id")
                );
                System.out.println("IMPORT Z BAZY DANYCH:");
                System.out.println("Weight: " + data.getMasa());
                System.out.println("Height: " + data.getWzrost());
                System.out.println("Date: " + data.getData());
                System.out.println("BMR: " + data.getBMR());
                System.out.println("ID: " + data.getId());
                System.out.println("----------------------------");
                dataList.add(data);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        Data[] dataArray = new Data[dataList.size()];
        dataArray = dataList.toArray(dataArray);
        return dataList;
    }

    public ObservableList getUser() {
        ArrayList<User> dataList = new ArrayList<>();

        ObservableList data = FXCollections.observableArrayList();
        try {

            String query = "SELECT * FROM USERS";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);


            while (resultSet.next()) {
                /*User data = new User(
                        resultSet.getString("user_name")
                );
                System.out.println("Weight: " + data.getUser_name());
                dataList.add(data);*/
                data.add((resultSet.getString("user_name")));
                System.out.println(resultSet.getString("user_name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        User[] dataArray = new User[dataList.size()];
        dataArray = dataList.toArray(dataArray);
        return data;
    }


    public  Connection getConnection() {

        String url = "jdbc:sqlite:Database.sqlite";
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(url);
            System.out.println("Połączono z bazą danych SQLite");

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return conn;
    }
}


