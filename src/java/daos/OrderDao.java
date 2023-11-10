package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dtos.Drink;
import dtos.Order;
import dtos.OrderItem;
import utils.Connector;

public class OrderDao {
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

    public Order createNewOrder(Order order) throws Exception {
        try {
            DrinkDAO drinkDao = new DrinkDAO();
            conn = Connector.getConnection();
            String sql = "INSERT INTO tbl_Order(orderDate, tableNumber) VALUES(?,?)";
            preStm = conn.prepareStatement(sql);
            preStm.setDate(1, new java.sql.Date(order.getOrderDate().getTime()));
            preStm.setInt(2, order.getTableNumber());
            preStm.executeUpdate();

            sql = "SELECT TOP 1 * FROM tbl_Order ORDER BY orderId DESC";
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            if (rs.next()) {
                int orderIdSql = rs.getInt("orderId");
                order.setOrderId(orderIdSql);
            }
            System.out.println("Order id: ");
            System.out.println(order.getOrderId());
            // create order item

            for (OrderItem orderItem : order.getItems()) {
                sql = "INSERT INTO tbl_Order_Item(orderId, drinkId, quantity, price) VALUES(?,?,?,?)";
                Drink drink = drinkDao.getOneById(orderItem.getDrink().getDrinkId());
                preStm = conn.prepareStatement(sql);
                preStm.setInt(1, order.getOrderId());
                preStm.setInt(2, drink.getDrinkId());
                preStm.setInt(3, orderItem.getQuantity());
                preStm.setFloat(4, drink.getPrice());

                preStm.executeUpdate();
            }

            return order;
        }

        finally {
            this.closeConnection();
        }
    }

    public Order getOneById(int orderId) throws Exception {
        Order order = null;
        ArrayList<OrderItem> orderItems = new ArrayList<>();
        DrinkDAO drinkDao = new DrinkDAO();
        try {
            conn = Connector.getConnection();
            String sql = "SELECT * FROM tbl_Order WHERE orderId=?";
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, orderId);
            rs = preStm.executeQuery();

            if (rs.next()) {
                int orderIdSql = rs.getInt("orderId");
                int tableNumberSql = rs.getInt("tableNumber");
                Date orderDateSql = rs.getDate("orderDate");

                // get order item
                sql = "SELECT * FROM tbl_Order_Item WHERE orderId=?";
                preStm = conn.prepareStatement(sql);
                preStm.setInt(1, orderId);
                ResultSet rsOrderItem = preStm.executeQuery();
                while (rsOrderItem.next()) {
                    int drinkIdSql = rsOrderItem.getInt("drinkId");
                    int quantitySql = rsOrderItem.getInt("quantity");
                    float priceSql = rsOrderItem.getFloat("price");
                    Drink drink = drinkDao.getOneById(drinkIdSql);
                    OrderItem orderItem = new OrderItem(orderIdSql, drinkIdSql, quantitySql, priceSql);
                    orderItem.setDrink(drink);

                    orderItems.add(orderItem);

                }

                order = new Order(orderIdSql, orderDateSql, tableNumberSql, orderItems);
            }
        } finally {
            this.closeConnection();
        }
        return order;
    }

    public List<Order> getAll() throws Exception {
        List<Order> orders = new ArrayList<>();
        try {
            conn = Connector.getConnection();
            String sql = "SELECT * FROM tbl_Order";
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();

            while (rs.next()) {
                int orderIdSql = rs.getInt("orderId");
                int tableNumberSql = rs.getInt("tableNumber");
                Date orderDateSql = rs.getDate("orderDate");

                // get order item
                sql = "SELECT * FROM tbl_Order_Item WHERE orderId=?";
                preStm = conn.prepareStatement(sql);
                preStm.setInt(1, orderIdSql);
                ResultSet rsOrderItem = preStm.executeQuery();
                ArrayList<OrderItem> orderItems = new ArrayList<>();
                while (rsOrderItem.next()) {
                    int drinkIdSql = rsOrderItem.getInt("drinkId");
                    int quantitySql = rsOrderItem.getInt("quantity");
                    float priceSql = rsOrderItem.getFloat("price");
                    OrderItem orderItem = new OrderItem(orderIdSql, drinkIdSql, quantitySql, priceSql);
                    orderItems.add(orderItem);
                }

                Order order = new Order(orderIdSql, orderDateSql, tableNumberSql, orderItems);
                orders.add(order);
            }
        } finally {
            this.closeConnection();
        }
        return orders;
    }

    public void deleteOneById(int orderId) throws Exception {
        try {
            conn = Connector.getConnection();
            String sql = "DELETE FROM tbl_Order WHERE orderId=?";
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, orderId);
            preStm.executeUpdate();
            // delete order item
            sql = "DELETE FROM tbl_Order_Item WHERE orderId=?";
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, orderId);
            preStm.executeUpdate();

        } finally {
            this.closeConnection();
        }
    }

}
