package com.epam.multithreading;

import com.epam.multithreading.model.Account;
import com.epam.multithreading.model.Bank;
import com.epam.multithreading.model.exception.AccountOperationException;
import com.epam.multithreading.model.exception.ExchangeOperationException;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Random;

public class Application implements Runnable{

	@Override
	public void run() {
		Random rand = new Random();

		while (true) {
			try {
				Bank bank = Multithreader.load();

				Account acc = bank.getAccount("" + (1_000_000_000 + rand.nextInt(Generator.MAX_ID)));
				if (acc != null) {
					acc.exchange(
							bank.getCurrency("code" + rand.nextInt(Generator.NUM_OF_CURRENCIES)),
							new BigDecimal(Generator.NUM_OF_CURRENCIES),
							bank.getCurrency("code100"));
				}

				Multithreader.save(bank);
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println(e);
			} catch (AccountOperationException e) {
				e.printStackTrace();
			} catch (ExchangeOperationException e) {
				e.printStackTrace();
			}
		}
	}

	public static void start() {
		new Thread(new Application()).start();
	}
}