package com.company;


public class InvalidProductException extends Exception{

    private Integer excId;

    public InvalidProductException(String message, int excId) {
        super(message);
        this.excId = excId;
    }

    public InvalidProductException(String message) {
        super(message);
    }

    public Integer getExcId() {
        return excId;
    }
}
