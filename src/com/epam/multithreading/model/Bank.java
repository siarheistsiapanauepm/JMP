package  com.epam.multithreading.model;

import java.util.*;

public class Bank {

    private Map<String, Currency> currencies = new HashMap<String, Currency>();
    private List<Account> accounts = new ArrayList<>();

    public Currency addCurrency(Currency Currency) {
        String currencyCode = Currency.getCode();
        if (currencies.get(currencyCode) == null ) {
            currencies.put(currencyCode, Currency);
            return Currency;
        } else {
            return null;
        }
    }

    public Currency getCurrency(String code) {
        return currencies.get(code);
    }

    public Account getAccount(String govermentalId) {
        Account result = null;
        if (govermentalId != null){
            for (Account account : accounts) {
                if (govermentalId.equalsIgnoreCase(account.getPersonId())) {
                    result = account;
                }
            }
        }
        return result;
    }

    public void addAccount(Account acc) {
        if (getAccount(acc.getPersonId()) == null) { //if this person hasn't account yet
            accounts.add(acc);
        }
    }
}
