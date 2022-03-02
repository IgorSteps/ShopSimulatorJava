/******************************************************************************
 File : SnackShop.java
 Date : 07.04.2020
 Author : Igor Stepanenko
 Description : A class to model a snack shop. This class has fields for the
 shop’s name, a field for the turnover of the shop, a collection of the products that this shop
 sells, and a collection of the customers that have accounts at this shop.
 ******************************************************************************/
package com.company;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class SnackShop {
    private final String shopName;
    private int turnoverMoney = 0;
    private Map<String, Product> productMap = new HashMap<>();
    private Map<String, Customer> customerMap = new HashMap<>();

    // constructor
    public SnackShop(String shopName) {
        this.shopName = shopName;
    }

    // accessors
    public int getTurnoverMoney() {
        return turnoverMoney;
    }
    public Map<String, Product> getProductMap() {
        return productMap;
    }
    public Map<String, Customer> getCustomerMap() {
        return customerMap;
    }
    public void setProductMap(Map<String, Product> productMap) {
        this.productMap = productMap;
    }
    public void setCustomerMap(Map<String, Customer> customerMap) {
        this.customerMap = customerMap;
    }

    // create databases with products and customers
    public Map<String, Product> productDatabase() {
        Map<String, Product> res = new HashMap<>();
        BufferedReader prBr = null;
        try {
            File productFile = new File("./products.txt");
            prBr = new BufferedReader(new FileReader(productFile));
            String line;
            while ((line = prBr.readLine()) != null) {
                String[] parts = line.split("@"); // split on @
                String productID = parts[0].trim();
                try {
                    Product food = new Food(parts[2], parts[0], parts[1], Integer.parseInt(parts[3]));
                    res.put(productID, food);
                } catch (InvalidProductException ex){
                    if (ex.getExcId() != null && ex.getExcId().equals(1111)) {
                        // This way I can put a product into a correct constructor , because when BufferReader reads
                        // a line my constructors in Food and Drink will catch InvalidProductException and by using
                        // exception ID which I implemented in the exception class
                        Product drink = new Drink(parts[2], parts[0], parts[1], Integer.parseInt(parts[3]));
                        res.put(productID, drink);
                    }
                }
            }
        } catch (IOException | InvalidProductException e) {
            e.printStackTrace();
        } finally {
            if (prBr != null) {
                try {
                    prBr.close(); // close BufferReader
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return res;
    }
    public Map<String, Customer> customerDatabase() {
        Map<String, Customer> res = new HashMap<>();
        BufferedReader cusBr = null;
        try {
            File customerFile = new File("./customer.txt");
            cusBr = new BufferedReader(new FileReader(customerFile));
            String line;
            while ((line = cusBr.readLine()) != null) {
                String[] parts = line.split("#");
                String customerAccountID = parts[0].trim();
                Customer customer = null;       // create an instance of a customer and cusotmer type
                String customerType = "";
                if (parts.length > 3) {         // check length
                    customerType = parts[3].trim(); // create customer type
                }
                try {
                    switch (customerType){
                        case "STUDENT" : {
                            customer = new StudentCustomer(parts[0], parts[1], Integer.parseInt(parts[2]));
                            break;
                        }
                        case "STAFF" : {
                            if (parts.length > 4) { // check length
                                customer = new StaffCustomer(parts[0], parts[1], Integer.parseInt(parts[2]), parts[4].trim());
                            } else {
                                customer = new StaffCustomer(parts[0], parts[1], Integer.parseInt(parts[2]));
                            }
                            break;
                        }
                        default: { // if a customer without school
                            customer = new Customer(parts[0], parts[1], Integer.parseInt(parts[2]));
                            break;
                        }
                    }
                    res.put(customerAccountID, customer);

                } catch (InvalidCustomerException e) {
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (cusBr != null) {
                try {
                    cusBr.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return res;
    }

    // change turnover
    public void processPurchase(int amount){
        turnoverMoney += amount;
    }


    // extra methods
    public void findLargestBasePrice() {
        List<Product> products = productMap.values()
                .stream()
                .sorted()
                .collect(Collectors.toList());
        System.out.println("Product with the largest price: " + products.get(products.size() - 1));
    }
    public int countNegativeAccounts() {
        int count = 0;
        for (Customer customer : customerMap.values()) {
            if (customer.getAccountBalance() < 0) {
                count++;
            }
        }
        return count;
    }
    public int calculateMedianCustomerBalance() {
        List<Customer> sortCustomers = customerMap.values()
                .stream()
                .sorted()
                .collect(Collectors.toList());
        int medianCustomerBalance;
        if (sortCustomers.size() % 2 == 0) {
            // if a list has even number of customers get the middle value
            // add the value next to it and find average by dividing by 2
            medianCustomerBalance = (sortCustomers.get(sortCustomers.size()/2).getAccountBalance()
                    + sortCustomers.get(sortCustomers.size()/2 + 1).getAccountBalance())/2;
        } else {
            //get the middle value
            medianCustomerBalance = sortCustomers.get(sortCustomers.size()/2).getAccountBalance();
        }
        return medianCustomerBalance;
    }

    //Test
    // Uncomment and make maps static to test
    /*public static void main(String[] args) {
       productMap = productDatabase();
        for (Map.Entry<String, Product> entry : productMap.entrySet()) {
           System.out.println(entry.getKey() + " : " + entry.getValue());
       }
        customerMap = customerDatabase();
        for (Map.Entry<String, Customer> newentry : customerMap.entrySet()) {
            System.out.println(newentry.getKey() + ":" + newentry.getValue());
       }
    }*/
}



