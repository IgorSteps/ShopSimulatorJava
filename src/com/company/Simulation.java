/******************************************************************************
 File : Simulation.java
 Date : 07.04.2020
 Author : Igor Stepanenko
 Description : Main method class will simulate the creation and use of a snack shop.
 ******************************************************************************/
package com.company;

import java.io.*;
import java.util.Map;


public class Simulation {

    public static void main (String[]args) {
        SnackShop shop = initialiseShop() ;
        simulateShopping(shop);
        System.out.println();
        System.out.println("Shop turnover: " + shop.getTurnoverMoney());
        System.out.println();
        shop.findLargestBasePrice();
        System.out.println();
        int count = shop.countNegativeAccounts();
        System.out.println("Number of negative balances: " +count);
        System.out.println();
        int medianCustomerBalance = shop.calculateMedianCustomerBalance();
        System.out.println("Median Balance: " + medianCustomerBalance);
    }

    public static SnackShop initialiseShop() {
        // create shop
        SnackShop shop = new SnackShop("Ziggy's");
        // add each customer and product from the respective files to the SnackShop
        Map<String, Product> productMap = shop.productDatabase();
        Map<String, Customer> customerMap = shop.customerDatabase();
        shop.setCustomerMap(customerMap);
        shop.setProductMap(productMap);

        return shop;
    }

    public static void simulateShopping(SnackShop shop){
        BufferedReader tranBr = null;
        String line = null;
        try {
            File transactionsFile = new File("./transactions.txt");
            tranBr = new BufferedReader(new FileReader(transactionsFile));
            while ((line = tranBr.readLine()) != null) {
                String[] parts = line.split(",");

                //An algorithm to determine which action to take when reading a file
                String action = parts[0].trim();

                switch (action){
                    case "PURCHASE" : {
                        String customerID = parts[1].trim();
                        String productID = parts[2].trim();
                        // check if a customer/product from transaction file are in products/customer files
                        if (!shop.getProductMap().containsKey(productID)) {
                            System.out.println("Such product doesn't exist in this shop!");
                            break;
                        }
                        if (!shop.getCustomerMap().containsKey(customerID)) {
                            System.out.println("Such customer doesn't exist in the shop!");
                            break;
                        }

                        try {
                            // get customer/product id and instantiate them
                            Customer customer = shop.getCustomerMap().get(customerID);
                            Product product = shop.getProductMap().get(productID);

                            // parse into processPurchase method
                            shop.processPurchase(customer.chargeAccount(product));
                            System.out.println("Purchased by: " + customer.getCustomerName() + ", is a product: "
                                    + product.getProductName() + ", which costs: " + product.calculatePrice());
                        } catch (InsufficientBalanceException e){
                            System.out.println(e.getMessage());
                        }
                        break;
                    }

                    case "ADD_FUNDS" : {
                        String customerID = parts[1].trim();
                        String amount = parts[2].trim();
                        // check if customer from transaction file is in customer files
                        if (!shop.getCustomerMap().containsKey(customerID)) {
                            System.out.println("Such customer doesn't exist in the shop!");
                            break;
                        }
                        // add funds
                        Customer customer = shop.getCustomerMap().get(customerID);
                        customer.addFunds(Integer.parseInt(amount));
                        System.out.println("Increase funds of: " + customer.getCustomerName() + ", by amount: "
                                + amount);
                        break;
                    }

                    case "NEW_CUSTOMER" : {
                        // create instance of a new customer
                        Customer customer = null;
                        String customerType = "";
                        customerType = parts[3].trim();
                        try {
                            switch (customerType){
                                case "STUDENT" : {
                                    customer = new StudentCustomer(parts[1], parts[2], Integer.parseInt(parts[4]));
                                    break;
                                }
                                case "STAFF" : {
                                    // check length of a line to determine which staff it is
                                    if (parts.length > 5) {
                                        customer = new StaffCustomer(parts[1], parts[2], Integer.parseInt(parts[5]),
                                                parts[4].trim());
                                    } else {
                                        customer = new StaffCustomer(parts[1], parts[2], Integer.parseInt(parts[4]));
                                    }
                                    break;
                                }
                                default: {
                                    customer = new Customer(parts[1], parts[2], Integer.parseInt(parts[3]));
                                    break;
                                }
                            }
                            shop.getCustomerMap().put(parts[1], customer);
                            System.out.println("New customer: " + customer);
                        } catch (InvalidCustomerException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    }
                    default: break;
                }
            }
        } catch (IOException e ) {
            e.printStackTrace();
        } finally {
            if (tranBr != null) {
                try {
                    tranBr.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}



