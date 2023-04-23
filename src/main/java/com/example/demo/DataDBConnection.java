package com.example.demo;

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

    public Data[] getData() {
        ArrayList<Data> dataList = new ArrayList<>();

        try {

            String query = "SELECT * FROM DATA";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);


            while (resultSet.next()) {
                Data data = new Data(
                       resultSet.getInt("weight"),
                        resultSet.getInt("height"),
                        resultSet.getString("date"),
                        resultSet.getDouble("BMR"),
                        resultSet.getInt("id")
                );
                dataList.add(data);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        Data[] dataArray = new Data[dataList.size()];
        dataArray = dataList.toArray(dataArray);
        return dataArray;
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


