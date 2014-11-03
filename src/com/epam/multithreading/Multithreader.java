package  com.epam.multithreading;

import com.epam.multithreading.model.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Multithreader {

	private static final String FILENAME = "model.json";

	private static final ReentrantReadWriteLock LOCK = new ReentrantReadWriteLock(true);


	public static void save(Object obj) throws IOException {
		Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
		LOCK.writeLock().lock();
		LOCK.readLock().lock();
		try {
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(FILENAME)));
			out.write(gson.toJson(obj));
			out.close();
		} finally {
			LOCK.writeLock().unlock();
			LOCK.readLock().unlock();
		}
	}

	public static Bank load() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder().enableComplexMapKeySerialization();
		Gson gson = gsonBuilder.create();
		Bank b;
		LOCK.readLock().lock();
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(FILENAME)));
			b = gson.fromJson(in.readLine(), Bank.class);
			in.close();
		} finally {
			LOCK.readLock().unlock();
		}
		return b;
	}

	public static void sleep(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.out.println(e);
		}
	}
}
