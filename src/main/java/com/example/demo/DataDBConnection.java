package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataDBConnection {


    public  Connection getConnection() {

        String url = "jdbc:sqlite:Database.sqlite";
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(url);
            System.out.println("Połączono z bazą danych SQLite");

           /* stmt = conn.createStatement();
            String sql = "INSERT INTO Data (weight) VALUES (2000)";
            stmt.executeUpdate(sql);
            System.out.println("Wiersz dodany do tabeli");

            */

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


