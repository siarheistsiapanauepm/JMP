package com.epam.multithreading.model.test;

import com.epam.multithreading.model.Currency;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;

@RunWith(MockitoJUnitRunner.class)
public class CurrencyTest {

    private static Currency currency;

    @BeforeClass
    public static void init() {
        currency = Mockito.mock(Currency.class);
    }

    @Test
    public final void checkConvertion() {
        final BigDecimal blrGE = new BigDecimal(415_181);
        Currency byr = new Currency("BYR", "Belarusian ruble", blrGE);
        final BigDecimal usdGE = new BigDecimal(37.51962);
        Currency usd = new Currency("USD", "US dollar", usdGE);
        final BigDecimal val = new BigDecimal(100_000);
        Assert.assertEquals(byr.convert(val, usd), val.multiply(usdGE.divide(blrGE, BigDecimal.ROUND_HALF_DOWN)));
    }
}
