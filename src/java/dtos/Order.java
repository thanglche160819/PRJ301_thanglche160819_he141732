package dtos;

import java.util.Date;

import java.util.List;

public class Order {

    private int orderId;
    private Date orderDate;
    private int tableNumber;
    private List<OrderItem> items;

    public Order(int orderId, Date orderDate, int tableNumber, List<OrderItem> items) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.tableNumber = tableNumber;
        this.items = items;
    }

    public Order() {
    }

    public int getOrderId() {
        return orderId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

}
