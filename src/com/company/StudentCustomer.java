/******************************************************************************
 File : StudentCustomer.java
 Date : 05.04.2020
 Author : Igor Stepanenko
 Description : The StudentCustomer class extends the Customer class. All students are given a 5% discount
 and are allowed to have a negative balance of up to £5 (i.e. -500).
 ******************************************************************************/
package com.company;

public class StudentCustomer extends Customer{

    // constructor
    public StudentCustomer(String customerAccountId, String customerName, int customerAccountBalance) throws InvalidCustomerException {
        super(customerAccountId,customerName, customerAccountBalance);
    }
    public String toString(){
        return "Student accountId: " + this.accountId + ", " + "Student Name: " + this.name + ", " + "Student Balance: " + this.accountBalance;
    }

    @Override
    public int chargeAccount(Product product) throws InsufficientBalanceException {
        double sumWithDisc = product.basePrice * 0.05;
        int productPrice = (int)Math.ceil((product.calculatePrice() - sumWithDisc));
        if (accountBalance - productPrice > -500) {
            accountBalance = accountBalance - productPrice;
            return productPrice;
        } else {
            throw new InsufficientBalanceException("Customer doesn't have enough credit to complete transaction!");
        }
    }

    // testing student customer class
    public static void main(String[] args) throws InvalidCustomerException {

        Customer studentCustomer = new Customer("12345G", "Bob", 500);
        //System.out.println(studentCustomer.chargeAccount());
        System.out.println(studentCustomer.getCustomerName());
        System.out.println(studentCustomer.getAccountBalance());
        System.out.println(studentCustomer.getAccountId());
    }
}
