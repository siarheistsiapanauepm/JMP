package com.epam.multithreading;

import com.epam.multithreading.model.*;
import com.epam.multithreading.model.exception.AccountOperationException;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Random;

public class Generator implements Runnable{

	public static final int MAX_ID = 10000;
	public static final int NUM_OF_CURRENCIES = 100;

	public static void generate() throws IOException, AccountOperationException {

		Bank bank = new Bank();
		Random rand = new Random();

		for (int i = 0; i <= NUM_OF_CURRENCIES; i++) {
			bank.addCurrency(new Currency("code"+i, "name"+i, 10+rand.nextInt(500_000)));
		}
		addSeveralAccounts(bank, rand);
		Multithreader.save(bank);

		while (true) {
			bank = Multithreader.load();

			addSeveralAccounts(bank, rand);

			Multithreader.save(bank);

		}

	}

	private static void addSeveralAccounts(Bank bank, Random rand) throws AccountOperationException {
		final int numOfAccounts = 1 + rand.nextInt(3);
		for (int i = 1; i <= numOfAccounts; i++) {
			Account account = new Account(new Person("first"+i, "last"+i, ""+(1_000_000_000 + rand.nextInt(MAX_ID)) ));
			int numOfCursForThisAcc = 2 + rand.nextInt(3);
			for (int j = 0; j < numOfCursForThisAcc; j++) {
				account.refill(bank.getCurrency("code"+rand.nextInt(NUM_OF_CURRENCIES)), new BigDecimal(rand.nextInt(100)+100));
			}
			bank.addAccount(account);
		}
	}

	@Override
	public void run() {

		try {
			generate();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(e);
		} catch (AccountOperationException e) {
			e.printStackTrace();
		}
	}

	public static void start() {
		new Thread(new Generator()).start();
	}
}
