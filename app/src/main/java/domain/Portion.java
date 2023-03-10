package domain;

public class Portion {
    private int amountOfPeople;
    private String unit;

    public Portion(int amountOfPeople, String unit) {
        this.amountOfPeople = amountOfPeople;
        this.unit = unit;
    }

    public int getAmountOfPeople() {
        return amountOfPeople;
    }

    public void setAmountOfPeople(int amountOfPeople) {
        this.amountOfPeople = amountOfPeople;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
