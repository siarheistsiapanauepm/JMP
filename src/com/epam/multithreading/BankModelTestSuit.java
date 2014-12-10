package com.epam.multithreading;

import com.epam.multithreading.model.test.AccountTest;
import com.epam.multithreading.model.test.CurrencyTest;
import com.epam.multithreading.model.test.PersonTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)
@SuiteClasses({ AccountTest.class, PersonTest.class, CurrencyTest.class })

public class BankModelTestSuit {

} 