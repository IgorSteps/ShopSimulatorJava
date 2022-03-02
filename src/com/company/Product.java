/******************************************************************************
 File : Product.java
 Date : 4.04.2020
 Author : Igor Stepanenko
 Description : An abstract base class for products and subclasses for food and drink.
 ******************************************************************************/

package com.company;

public abstract class Product implements Comparable<Product> {
    protected String productId;
    protected String name;
    protected int basePrice;

    public Product(String productID, String productName, int productPrice ) {
       this.productId = productID;
       this.name = productName;
       this.basePrice = productPrice;
    }


    public String getProductID(){
        return this.productId ;
    }
    public String getProductName(){
        return this.name;
    }
    public int getProductPrice(){
        return this.basePrice;
    }

    public abstract int calculatePrice();

    public abstract int compareTo(Product o);
}

