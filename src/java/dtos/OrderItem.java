package dtos;

public class OrderItem {

    private int orderId;
    private int drinkId;
    private int quantity;
    private float price;
    private Drink drink;

    public OrderItem(int orderId, int drinkId, int quantity, float price) {
        this.orderId = orderId;
        this.drinkId = drinkId;
        this.quantity = quantity;
        this.price = price;
    }

    public OrderItem() {
    }

    public int getOrderId() {
        return orderId;
    }

    public int getDrinkId() {
        return drinkId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setDrinkId(int drinkId) {
        this.drinkId = drinkId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        if (price > 0) {
            this.price = price;
        }
    }

    public Drink getDrink() {
        return drink;
    }

    public void setDrink(Drink drink) {
        if (drink != null) {
            this.drink = drink;
        }
    }

}
