/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {

    public static Connection getConnection() throws ClassNotFoundException {
        try {

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=PRJ301_CAFE_SHOP", "sa", "123");

            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }
}
