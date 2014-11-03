package  com.epam.multithreading.model;

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

    public String getPersonId() {
        return owner.getGovermentalId();
    }

    public Account refill(Currency currency, BigDecimal amount){
        final String code = currency.getCode();
        final BigDecimal curValue = currencies.get(code);
        BigDecimal newValue = amount;
        if (curValue != null) {
            newValue = curValue.add(amount);
        }
        currencies.put(code, newValue);
        return this;
    }

    public void exchange(Currency src, BigDecimal amount, Currency dest) {
        BigDecimal curSrcVal = currencies.get(src.getCode());
        if (curSrcVal != null) {

	        if (curSrcVal.compareTo(amount) >= 0) { //check, it is enough money in this currency
		        currencies.put(src.getCode(), curSrcVal.subtract(amount));
		        refill(dest, src.convert(amount, dest));
	        }
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
