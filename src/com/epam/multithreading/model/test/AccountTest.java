package com.epam.multithreading.model.test;

import com.epam.multithreading.model.*;
import com.epam.multithreading.model.exception.*;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class AccountTest {

    private static final Person person = Mockito.mock(Person.class);
    private static final String codeByr = "BYR";
    private static final String codeUsd = "USD";
    private static final Currency byr = new Currency(codeByr, "Belarusian ruble", 415_181);
    private static final Currency usd = new Currency(codeUsd, "US dollar", 37.51962);

    @BeforeClass
    public static void init() {

    }

    @Test
    public final void testGrtGovId() {
        final String gId = "gov-gov";
        Mockito.when(person.getGovermentalId()).thenReturn(gId);
        final Account account = new Account(person);
        assertEquals(account.getPersonId(), gId);
    }

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public final void testRefill() throws AccountOperationException {
        expectedEx.expect(AccountOperationException.class);
        expectedEx.expectMessage(AccountOperationException.CURRENCY_NOT_SPECIFIED);

        final Account account = new Account(person);
        account.refill(null, new BigDecimal(30_000));
    }

    @Test
    public final void testRefill2() throws AccountOperationException {
        expectedEx.expect(AccountOperationException.class);
        expectedEx.expectMessage(AccountOperationException.AMOUNT_NOT_SPECIFIED);

        final Account account = new Account(person);
        account.refill(byr, null);
    }

    @Test
    public final void testRefill3() throws AccountOperationException {
        final Account account = new Account(person);
        account.refill(byr, new BigDecimal(100_000));
        account.refill(byr, new BigDecimal(30_000));

        Assert.assertEquals(account.getCurrencyBalance(codeByr), new BigDecimal(130_000));
    }

    @Test
    public final void testExchange() throws ExchangeOperationException, AccountOperationException {
        expectedEx.expect(ExchangeOperationException.class);
        expectedEx.expectMessage(ExchangeOperationException.SOURCE_CURRENCY_DOES_NOT_EXIST_IN_THIS_ACCOUNT);

        final Account account = new Account(person);
        account.exchange(usd, new BigDecimal(100), byr);
    }

    @Test
    public final void testExchange2() throws ExchangeOperationException, AccountOperationException {
        expectedEx.expect(ExchangeOperationException.class);
        expectedEx.expectMessage(ExchangeOperationException.SOURCE_CURRENCY_DOES_NOT_EXIST_IN_THIS_ACCOUNT);

        final Account account = new Account(person);
        account.exchange(null, new BigDecimal(100), byr);
    }

    @Test
    public final void testExchange3() throws ExchangeOperationException, AccountOperationException {
        final Account account = new Account(person);
        final BigDecimal amount = new BigDecimal(100);
        account.refill(usd, amount);
        Assert.assertEquals(account.getCurrencyBalance(codeUsd), amount);

        account.exchange(usd, new BigDecimal(100), byr);
        Assert.assertEquals(account.getCurrencyBalance(codeByr), amount.multiply(new BigDecimal(415_181).divide(new BigDecimal(37.51962), BigDecimal.ROUND_HALF_DOWN)));
    }
}
