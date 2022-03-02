/******************************************************************************
 File : Food.java
 Date : 4.04.2020
 Author : Igor Stepanenko
 Description : A subclass of Product base class, represents a food item
 ******************************************************************************/
package com.company;

public class Food extends Product{
    private final String itemType;

    // constructor
    public Food(String foodType, String productID, String productName, int productPrice) throws InvalidProductException{
        super(productID, productName, productPrice);
        // check id format
        if (!productID.matches("[F]-[0-9]{7}"))
            throw new InvalidProductException("Invalid Food ID", 1111);

        this.itemType = foodType;
    }

    // accessor
    public String getFoodType(){
        return this.itemType;
    }


    @Override
    public int calculatePrice(){
        double SURCHARGE_PERCENTAGE = 0.1; // surcharge percentage for hot food items
        double calculatePrice = basePrice + (basePrice * SURCHARGE_PERCENTAGE);
        return (int)Math.ceil(calculatePrice);
    }

    public String toString(){
        return "Food Type: " + this.itemType + "," + "Food ID: " + this.productId + "," + "Food Price: " + this.basePrice;
    }
    @Override
    public int compareTo(Product o) {
        return basePrice - o.basePrice;
    }

    // testing food class
    public static void main(String[] args) {
        try {
            Food firstFood = new Food("HOT", "F-123456", "Tea", 150);
            Food secondFood = new Food("COLD", "R-123456", "Nutella", 200);

            System.out.println("The names of our products");
            System.out.println(firstFood.getProductName());
            System.out.println(secondFood.getFoodType());
            System.out.println(firstFood.calculatePrice());
            System.out.println(secondFood.getProductID());

        } catch (InvalidProductException e) {
            e.printStackTrace();
        }
    }

}
