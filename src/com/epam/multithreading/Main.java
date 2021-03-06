package  com.epam.multithreading;

import com.epam.multithreading.Multithreader;
import com.epam.multithreading.model.*;

import java.io.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Random;

public class Main {

    public static void main(String[] args) throws IOException {
	    Generator.start();
	    Application.start();
    }

    private static void test() throws IOException {
	    Bank bank = new Bank();

	    Currency byr = new Currency("BYR", "Belarusian ruble", 415_181);
	    Currency usd = new Currency("USD", "US dollar", 37.51962);
	    bank.addCurrency(byr);
	    bank.addCurrency(usd);

	    Person vasya = new Person("Vasilij", "Pupkin", "A12051970HDI039H7");
	    final Account acc = new Account(vasya).refill(byr, new BigDecimal(500_000));
	    bank.addAccount(acc);

	    acc.exchange(byr, new BigDecimal(100_000), usd);

	    Multithreader.save(bank);
    }
}
