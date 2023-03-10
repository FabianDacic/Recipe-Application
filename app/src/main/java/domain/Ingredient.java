package domain;

public class Ingredient {
    private int id;
    private Unit unit;
    private int price;
    private String name;
    private int amountOfUnit;

    public Ingredient(int id, String name, Unit unit, int amountOfUnit, int price) {
        this.id = id;
        this.price = price;
        this.unit = unit;
        this.name = name;
        this.amountOfUnit = amountOfUnit;
    }

    public Ingredient() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public Unit getUnit() {
        return unit;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmountOfUnit() {
        return amountOfUnit;
    }

    public void setAmountOfUnit(int amountOfUnit) {
        this.amountOfUnit = amountOfUnit;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "id=" + id +
                ", unit=" + unit +
                ", price=" + price +
                ", name='" + name + '\'' +
                ", amountOfUnit=" + amountOfUnit +
                '}';
    }
}