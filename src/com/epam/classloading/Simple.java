package com.epam.classloading;

public class Simple implements AnInterface {

	public void execute() {
		System.out.println("Hello world 3");
	}

	public static void main(String[] args) {
		new Simple().execute();
	}
}
