package dtos;

public class Drink {
    private int drinkId;
    private String drinkName;
    private float price;
    private String description;
    private String image;

    public Drink(int drinkId, String drinkName, float price, String description, String image) {
        this.drinkId = drinkId;
        this.drinkName = drinkName;
        this.price = price;
        this.description = description;
        this.image = image;
    }

    public Drink() {
    }

    public int getDrinkId() {
        return drinkId;
    }

    public String getDrinkName() {
        return drinkName;
    }

    public float getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public void setDrinkId(int drinkId) {
        this.drinkId = drinkId;
    }

    public void setDrinkName(String drinkName) {
        this.drinkName = drinkName;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
