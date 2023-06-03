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

        Integer profile = FXMLConnector.LogInfo.getUserID();
        System.out.println("Profile: " + profile);
        try {

            String query = "SELECT * FROM DATA where UserID = '" + profile + "'";
            System.out.println("Query: "+query);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);


            while (resultSet.next()) {
                Data data = new Data(
                       resultSet.getInt("weight"),
                        resultSet.getInt("height"),
                        resultSet.getString("date"),
                        resultSet.getInt("BMR"),
                        resultSet.getInt("BMI"),
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

    public ObservableList<Bieganie> getBieganie() {
        ObservableList<Bieganie> dataList = FXCollections.observableArrayList();

        Integer profile = FXMLConnector.LogInfo.getUserID();
        System.out.println("Profile: " + profile);
        try {

            String query = "SELECT * FROM Bieganie where UserID = '" + profile + "'";
            System.out.println("Query: "+query);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);


            while (resultSet.next()) {
                Bieganie data = new Bieganie(
                        resultSet.getDouble("dystans"),
                        resultSet.getInt("kalorie"),
                        resultSet.getDouble("czas"),
                        resultSet.getString("data"),
                        resultSet.getDouble("met"),
                        resultSet.getInt("id")
                );
                System.out.println("IMPORT Z BAZY DANYCH:");
                System.out.println("Dystans: " + data.getDystans());
                System.out.println("Kalorie: " + data.getKalorie());
                System.out.println("Date: " + data.getData());
                System.out.println("Met: " + data.getMet());
                System.out.println("Czas: " + data.getCzas());
                System.out.println("ID: " + data.getId());
                System.out.println("----------------------------");
                dataList.add(data);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        Bieganie[] dataArray = new Bieganie[dataList.size()];
        dataArray = dataList.toArray(dataArray);
        return dataList;
    }

    public ObservableList<Silownia> getSilownia() {
        ObservableList<Silownia> dataList = FXCollections.observableArrayList();

        Integer profile = FXMLConnector.LogInfo.getUserID();
        System.out.println("Profile: " + profile);
        try {

            String query = "SELECT * FROM Silownia where UserID = '" + profile + "'";
            System.out.println("Query: "+query);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);


            while (resultSet.next()) {
                Silownia data = new Silownia(
                        resultSet.getString("cwiczenie"),
                        resultSet.getInt("kalorie"),
                        resultSet.getDouble("czas"),
                        resultSet.getString("data"),
                        resultSet.getDouble("met"),
                        resultSet.getInt("id")
                );
                System.out.println("IMPORT Z BAZY DANYCH:");
                System.out.println("Cwiczenie: " + data.getCwiczenie());
                System.out.println("Kalorie: " + data.getKalorie());
                System.out.println("Date: " + data.getData());
                System.out.println("Met: " + data.getMet());
                System.out.println("Czas: " + data.getCzas());
                System.out.println("ID: " + data.getId());
                System.out.println("----------------------------");
                dataList.add(data);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        Silownia[] dataArray = new Silownia[dataList.size()];
        dataArray = dataList.toArray(dataArray);
        return dataList;
    }

    public ObservableList<Jedzenie> getJedzenie() {
        ObservableList<Jedzenie> dataList = FXCollections.observableArrayList();

        Integer profile = FXMLConnector.LogInfo.getUserID();
        System.out.println("Profile: " + profile);
        try {

            String query = "SELECT * FROM Jedzenie where UserID = '" + profile + "'";
            System.out.println("Query: "+query);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);


            while (resultSet.next()) {
                Jedzenie data = new Jedzenie(
                        resultSet.getString("posilek"),
                        resultSet.getString("rodzaj"),
                        resultSet.getInt("kalorie"),
                        resultSet.getString("data"),
                        resultSet.getInt("id")
                );

                dataList.add(data);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        Jedzenie[] dataArray = new Jedzenie[dataList.size()];
        dataArray = dataList.toArray(dataArray);
        return dataList;
    }

    public ObservableList getUser() {
        //ArrayList<User> dataList = new ArrayList<>();

        ObservableList<User> dataList = FXCollections.observableArrayList();
        try {

            String query = "SELECT * FROM USERS";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);


            while (resultSet.next()) {
                User data = new User(
                        resultSet.getString("user_name"),
                        resultSet.getInt("UserID")
                );
                System.out.println("username: " + data.getUser_name());
                System.out.println("userid: " + data.getUserID());
                dataList.add(data);
                //data.add((resultSet.getString("user_name")));
                System.out.println(resultSet.getString("user_name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        User[] dataArray = new User[dataList.size()];
        dataArray = dataList.toArray(dataArray);
        return dataList;
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


