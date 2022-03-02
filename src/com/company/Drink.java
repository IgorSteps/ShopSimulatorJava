/******************************************************************************
 File : Food.java
 Date : 4.04.2020
 Author : Igor Stepanenko
 Description : A subclass of Product base class, represents a drink item
 ******************************************************************************/
package com.company;

public class Drink extends Product{
    private final String sugarContent;

    // constructor
    public Drink(String sugarLevel, String productID, String productName, int productPrice) throws InvalidProductException{
        super(productID, productName, productPrice);
        // check id format
        if (!productID.matches("[D]-[0-9]{7}"))
            throw new InvalidProductException("Invalid Drink ID", 2222);

        this.sugarContent = sugarLevel;
    }

    // accessor
    public String getSugarLevel(){
        return this.sugarContent;
    }

    @Override
    public int calculatePrice() {
            if (sugarContent.equals("HIGH")){
            int sugarHigh = 24;
            return basePrice + sugarHigh;
        }else if (sugarContent.equals("LOW")){
            int sugarLow = 18;
            return basePrice + sugarLow;
        }else
            return basePrice;
    }

    public String toString(){
        return "Sugar Level: " + this.sugarContent + ", " + "Drink ID: " + this.productId + ", " + "Drink's Price: " + this.basePrice;
    }
    @Override
    public int compareTo(Product o) {
        return basePrice - o.basePrice;
    }

    // testing drink class
    public static void main(String[] args) {
        try {
            Drink firstDrink = new Drink("HIGH", "F-123456", "Tea", 150);
            Drink secondDrink = new Drink("LOW", "R-123456", "Coffee", 200);

            System.out.println(firstDrink.getProductName());
            System.out.println(secondDrink.getSugarLevel());
            System.out.println(firstDrink.calculatePrice());


        } catch (InvalidProductException e) {
            e.printStackTrace();
        }
    }
}
