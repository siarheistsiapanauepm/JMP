package com.epam.multithreading.model.exception;

public class ExchangeOperationException extends Exception {

    public static final String TARGET_CURRENCY_NOT_SPECIFIED = "Target currency not specified.";
    public static final String NOT_ENOUGH_MONEY_TO_EXCHANGE = "Not enough money to exchange.";
    public static final String SOURCE_CURRENCY_DOES_NOT_EXIST_IN_THIS_ACCOUNT = "Source currency does not exist in this account.";

    public ExchangeOperationException(String message) {
        super(message);
    }
}
