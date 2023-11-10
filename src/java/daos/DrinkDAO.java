package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dtos.Drink;
import utils.Connector;

public class DrinkDAO {
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

    public Drink getOneById(int drinkId) throws Exception {
        Drink drink = null;
        try {
            conn = Connector.getConnection();
            String sql = "SELECT * FROM tbl_Drink WHERE drinkId=?";
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, drinkId);
            rs = preStm.executeQuery();

            if (rs.next()) {
                int drinkIdSql = rs.getInt("drinkId");
                String drinkNameSql = rs.getString("drinkName");
                float priceSql = rs.getFloat("price");
                String descriptionSql = rs.getString("description");
                String imageSql = rs.getString("image");
                drink = new Drink(drinkIdSql, drinkNameSql, priceSql, descriptionSql, imageSql);
            }
        } finally {
            this.closeConnection();
        }
        return drink;
    }

    public ArrayList<Drink> getAll() throws Exception {
        ArrayList<Drink> drinks = new ArrayList<>();
        try {
            conn = Connector.getConnection();
            String sql = "SELECT * FROM tbl_Drink";
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();

            while (rs.next()) {
                int drinkIdSql = rs.getInt("drinkId");
                String drinkNameSql = rs.getString("drinkName");
                float priceSql = rs.getFloat("price");
                String descriptionSql = rs.getString("description");
                String imageSql = rs.getString("image");
                Drink drink = new Drink(drinkIdSql, drinkNameSql, priceSql, descriptionSql, imageSql);
                drinks.add(drink);
            }
        } finally {
            this.closeConnection();
        }
        return drinks;
    }

    public Drink createNewDrink(Drink drink) throws Exception {
        try {
            conn = Connector.getConnection();
            String sql = "INSERT INTO tbl_Drink(drinkName, price, description, image) VALUES(?,?,?,?)";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, drink.getDrinkName());
            preStm.setFloat(2, drink.getPrice());
            preStm.setString(3, drink.getDescription());
            preStm.setString(4, drink.getImage());
            preStm.executeUpdate();
        } finally {
            this.closeConnection();
        }
        return drink;
    }

    public Drink updateDrink(Drink drink) throws Exception {
        try {
            conn = Connector.getConnection();
            String sql = "UPDATE tbl_Drink SET drinkName=?, price=?, description=?, image=? WHERE drinkId=?";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, drink.getDrinkName());
            preStm.setFloat(2, drink.getPrice());
            preStm.setString(3, drink.getDescription());
            preStm.setString(4, drink.getImage());
            preStm.setInt(5, drink.getDrinkId());
            preStm.executeUpdate();
        } finally {
            this.closeConnection();
        }
        return drink;
    }

    public void deleteDrink(int drinkId) throws Exception {
        try {
            conn = Connector.getConnection();
            String sql = "DELETE FROM tbl_Drink WHERE drinkId=?";
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, drinkId);
            preStm.executeUpdate();
        } finally {
            this.closeConnection();
        }
    }

    public boolean isDrinkExistInOrderItem(int drinkId) throws Exception {
        boolean isExist = false;
        try {
            conn = Connector.getConnection();
            String sql = "SELECT * FROM tbl_Order_Item WHERE drinkId=?";
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, drinkId);
            rs = preStm.executeQuery();

            if (rs.next()) {
                isExist = true;
            }
        } finally {
            this.closeConnection();
        }
        return isExist;
    }

}
