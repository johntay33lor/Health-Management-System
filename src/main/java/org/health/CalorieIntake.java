package org.health;

public class CalorieIntake {
    private String foodItem;
    private int caloricValue;

    public CalorieIntake(String foodItem, int caloricValue) {
        this.foodItem = foodItem;
        this.caloricValue = caloricValue;
    }

    public String getFoodItem() {
        return foodItem;
    }

    public int getNumOfCalories() {
        return caloricValue;
    }
}
