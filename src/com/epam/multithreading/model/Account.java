package com.epam.multithreading.model;

import com.epam.multithreading.model.exception.AccountOperationException;
import com.epam.multithreading.model.exception.ExchangeOperationException;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Account {
    private Person owner;
    private Map<String, BigDecimal> currencies;

    public Account(Person owner) {
        this.owner = owner;
        currencies = new HashMap<>();
    }

    public BigDecimal getCurrencyBalance(String code) {
        return currencies.get(code);
    }

    public String getPersonId() {
        return owner.getGovermentalId();
    }

    public Account refill(Currency currency, BigDecimal amount) throws AccountOperationException {
        if (currency == null) {
            throw new AccountOperationException(AccountOperationException.CURRENCY_NOT_SPECIFIED);
        }
        if (amount == null) {
            throw new AccountOperationException(AccountOperationException.AMOUNT_NOT_SPECIFIED);
        }
        final String code = currency.getCode();
        final BigDecimal curValue = currencies.get(code);
        BigDecimal newValue = amount;
        if (curValue != null) {
            newValue = curValue.add(amount);
        }
        currencies.put(code, newValue);
        return this;
    }

    public void exchange(Currency src, BigDecimal amount, Currency dest) throws ExchangeOperationException, AccountOperationException {
        if (src == null) {
            throw new ExchangeOperationException(ExchangeOperationException.SOURCE_CURRENCY_DOES_NOT_EXIST_IN_THIS_ACCOUNT);
        }
        BigDecimal curSrcVal = currencies.get(src.getCode());
        if (curSrcVal != null) {

	        if (curSrcVal.compareTo(amount) >= 0) { //check, it is enough money in this currency
		        currencies.put(src.getCode(), curSrcVal.subtract(amount));
                if (dest == null) {
                    throw new ExchangeOperationException(ExchangeOperationException.TARGET_CURRENCY_NOT_SPECIFIED);
                }
                refill(dest, src.convert(amount, dest));
            } else {
                throw new ExchangeOperationException(ExchangeOperationException.NOT_ENOUGH_MONEY_TO_EXCHANGE);
            }
        } else {
            throw new ExchangeOperationException(ExchangeOperationException.SOURCE_CURRENCY_DOES_NOT_EXIST_IN_THIS_ACCOUNT);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        if (owner != null ? !owner.equals(account.owner) : account.owner != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return owner != null ? owner.hashCode() : 0;
    }
}
