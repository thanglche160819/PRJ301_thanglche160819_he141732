/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dtos.User;
import utils.Connector;

public class UserDAO {
    private Connection conn;

    private PreparedStatement preStm;

    private ResultSet rs;

    // This function handle to close connection of database
    private void closeConnection() throws Exception {
        if (rs != null) {
            rs.close();
        }

        if (preStm != null) {
            preStm.close();
        }

        if (conn != null) {

            conn.close();
        }
    }

    public User getOneByUsername(String username) throws Exception {
        User user = null;
        try {
            conn = Connector.getConnection();
            String sql = "SELECT * FROM tbl_User WHERE username=?";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, username);
            rs = preStm.executeQuery();

            if (rs.next()) {
                int userIdSql = rs.getInt("userId");
                String passwordSql = rs.getString("password");
                String fullNameSql = rs.getString("fullName");
                String usernameSql = rs.getString("username");
                user = new User(userIdSql, usernameSql, passwordSql, fullNameSql);
            }
        } finally {
            this.closeConnection();
        }
        return user;
    }

    public User createNewUser(User user) throws Exception {
        try {
            conn = Connector.getConnection();
            String sql = "INSERT INTO tbl_User(username, password, fullName) VALUES(?,?,?,?)";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, user.getUsername());
            preStm.setString(2, user.getPassword());
            preStm.setString(3, user.getFullName());

            preStm.executeUpdate();
        } finally {
            this.closeConnection();
        }
        return user;
    }

    public User getOneUserByUserId(int userId) throws Exception {
        User user = null;
        try {
            conn = Connector.getConnection();
            String sql = "SELECT * FROM tbl_User WHERE userId=?";
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, userId);
            rs = preStm.executeQuery();

            if (rs.next()) {
                int userIdSql = rs.getInt("userId");
                String passwordSql = rs.getString("password");
                String fullNameSql = rs.getString("fullName");
                String usernameSql = rs.getString("username");
                user = new User(userIdSql, usernameSql, passwordSql, fullNameSql);

            }
        } finally {
            this.closeConnection();
        }
        return user;
    }

}
