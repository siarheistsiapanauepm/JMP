package  com.epam.multithreading.model;

import java.math.BigDecimal;

public class Currency {
    private String code;
    private String name;
    //assumption that each currency has gold equivalence, currencies exchange performs based on this ratios
    //shows current price of 1 gramm of gold in this currency
    private BigDecimal goldEquivalence;

    public BigDecimal convert(BigDecimal amount, Currency target) {
        return amount.multiply(target.getGoldEquivalence().divide(this.goldEquivalence, BigDecimal.ROUND_HALF_DOWN));
    }

	public Currency() {
	}

	public Currency(String code, String name, BigDecimal goldEquivalence) {

        this.code = code;
        this.name = name;
        this.goldEquivalence = goldEquivalence;
    }

    public Currency(String code, String name, double goldEquivalence) {
        this(code, name, new BigDecimal(goldEquivalence));
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getGoldEquivalence() {
        return goldEquivalence;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;

        Currency currency = (Currency) object;

        if (!code.equals(currency.code)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + code.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "{code:" + code + "," + "name:" + name + "}";
    }
}
