/******************************************************************************
 File : StaffCustomer.java
 Date : 05.04.2020
 Author : Igor Stepanenko
 Description : The StaffCustomer class extends the Customer class. It includes an additional field
 to store the school that the staff member works in (which could either be CMP, BIO,
 MTH, or other) to apply a discount.
 ******************************************************************************/
package com.company;

public class StaffCustomer extends Customer{

    private final String schoolName;

    // constructors
    public StaffCustomer(String customerAccountId, String customerName, int customerAccountBalance)
            throws InvalidCustomerException {
        this(customerAccountId, customerName, customerAccountBalance, "");
    }
    public StaffCustomer(String customerAccountId, String customerName, int customerAccountBalance, String schoolName) throws InvalidCustomerException {
        super(customerAccountId, customerName, customerAccountBalance);
        this.schoolName = schoolName;
    }

    // accessors
    public String getSchoolName() {
        return this.schoolName;
    }
    // toString
    public String toString(){
        return "Staff AccountID: " + this.accountId + ", " + "Staff Name: "+ this.name + ", " + "Staff Balance: "+ this.accountBalance + ", " + "School: " + this.schoolName;
    }

    @Override
    public int chargeAccount(Product product) throws InsufficientBalanceException{
        double sumWithDisc;
        switch (schoolName) {
            case "CMP":
                sumWithDisc = product.basePrice * 0.1;
                break;
            case "MTH":
            case "BIO":
                sumWithDisc = product.basePrice * 0.025;
                break;
            default:
                sumWithDisc = 0;
                break;
        }
        int productPrice = (int)Math.ceil((product.calculatePrice() - sumWithDisc));
        if (accountBalance - productPrice > 0) {
            accountBalance = accountBalance - productPrice;
            return productPrice;
        } else {
            throw new InsufficientBalanceException("Customer doesn't have enough credit to complete transaction!");
        }
    }

    // testing staff customer class
    public static void main(String[] args) throws InvalidCustomerException {
        // can create a product to test chargeAccount()
        // but need to add exceptions
        // Product pr = new Food("HOT", "1234", "Sausage roll", 100);
        Customer staffCustomer = new Customer("12345G", "Bob", 500);
        //System.out.println(staffCustomer.chargeAccount());
        System.out.println(staffCustomer.getCustomerName());
        System.out.println(staffCustomer.getAccountBalance());
        System.out.println(staffCustomer.getAccountId());
   }
}
