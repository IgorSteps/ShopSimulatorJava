/******************************************************************************
 File : Customer.java
 Date : 05.04.2020
 Author : Igor Stepanenko
 Description : The Customer class stores information about a customer‘s account such as their account ID,
 their name and their account balance.
 ******************************************************************************/
package com.company;

import java.util.*;

public class Customer implements  Comparable<Customer>{
    protected String accountId;
    protected String name;
    protected int accountBalance;

    // constructor
    public Customer(String customerAccountId, String customerName, int customerAccountBalance) throws InvalidCustomerException {
        if (!customerAccountId.matches("[A-Z0-9]{6}"))
            throw new InvalidCustomerException("Invalid Customer Id");
        if (this.accountBalance < 0)
            throw new InvalidCustomerException("You can't have negative balance");
        this.accountId = customerAccountId;
        this.name = customerName;
        this.accountBalance = customerAccountBalance;
    }

    // accessors
    public String getAccountId(){
        return this.accountId;
    }
    public String getCustomerName(){
        return this.name;
    }
    public int getAccountBalance(){
        return this.accountBalance;
    }
    public void setAccountBalance(int newAccountBalance){
        this.accountBalance = newAccountBalance;
    }
    public String toString(){
        return "Customer AccountID: " + this.accountId + ", " + "Customer Name: " + this.name + ", "
                + "Customer Balance: " + this.accountBalance;
    }


    public void addFunds(int amount){
        if (amount > 0){
            setAccountBalance(accountBalance + amount);
        }
    }
    public int chargeAccount(Product product) throws InsufficientBalanceException{
        if (accountBalance - product.calculatePrice() > 0) {
            accountBalance = accountBalance - product.calculatePrice();
            return product.calculatePrice();
        } else {
            throw new InsufficientBalanceException("Customer doesn't have enough credit to complete transaction!");
        }
    }


    // map's methods overriding
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(accountId, customer.accountId) && Objects.equals(name, customer.name);
    }
    @Override
    public int hashCode() {
        return Objects.hash(accountId, name);
    }
    @Override
    public int compareTo(Customer o) {
        return accountBalance - o.accountBalance;
    }

    // test customer class
    public static void main(String[] args) throws InvalidCustomerException {
        Customer firstCustomer = new Customer("12345G", "Bob", 500);
        //System.out.println(firstCustomer.chargeAccount());
        System.out.println(firstCustomer.getCustomerName());
        System.out.println(firstCustomer.getAccountBalance());
        System.out.println(firstCustomer.getAccountId());

    }

}
