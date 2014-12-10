package com.epam.multithreading.model.exception;

public class AccountOperationException extends Exception {

    public static final String CURRENCY_NOT_SPECIFIED = "Currency to refill not specified.";
    public static final String AMOUNT_NOT_SPECIFIED = "Amount of money not specified.";

    public AccountOperationException(String message) {
        super(message);
    }
}
