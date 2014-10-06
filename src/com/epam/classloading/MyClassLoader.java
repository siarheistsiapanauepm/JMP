package com.epam.classloading;

import org.apache.log4j.Logger;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.jar.JarFile;

public class MyClassLoader extends ClassLoader{

	private static final Logger LOGGER = Logger.getLogger(MyClassLoader.class);

	private static final String PATH_TO_JAR = "C:/lib.jar";
	private static final String[] loadableClasses = {"com.epam.classloading.Simple"}; //list names of classes that should be loaded by this classloader (package.Classname)

	public MyClassLoader(ClassLoader parent) {
		super(parent);
	}

	public Class loadClass(String name) throws ClassNotFoundException {
		if (! Arrays.asList(loadableClasses).contains(name))   {
			LOGGER.info("!!! parent classloader is using !!!");
			return super.loadClass(name);
		}

		try {
//			InputStream input = getClassFromJar(PATH_TO_JAR, loadableClasses[0]);
			InputStream input = getClassAsStream(loadableClasses[0]);
			ByteArrayOutputStream buffer = new ByteArrayOutputStream();

			int data = input.read();
			while(data != -1){
				buffer.write(data);
				data = input.read();
			}
			input.close();

			byte[] classData = buffer.toByteArray();

			return defineClass(name, classData, 0, classData.length);

		} catch (MalformedURLException e) {
			LOGGER.error(e);
//			e.printStackTrace();
		} catch (IOException e) {
			LOGGER.error(e);
//			e.printStackTrace();
		}

		return null;
	}

	private InputStream getClassAsStream(String name) throws IOException {
		String url = "file:C:\\" + name.replace(".", File.separator) + ".class";

		URL myUrl = new URL(url);
		URLConnection connection = myUrl.openConnection();
		InputStream input = connection.getInputStream();
		return input;
	}

	private InputStream getClassFromJar(String pathToJar, String className) throws IOException {
		JarFile jar = new JarFile(pathToJar);
		return jar.getInputStream(jar.getEntry(className));
	}


	public static void main(String[] args) throws
			ClassNotFoundException,
			IllegalAccessException,
			InstantiationException, IOException {

		ClassLoader parentClassLoader = MyClassLoader.class.getClassLoader();
		MyClassLoader classLoader;
		Class myObjectClass;
		AnInterface obj;

		while (true) {
			classLoader = new MyClassLoader(parentClassLoader);
			myObjectClass = classLoader.loadClass(loadableClasses[0]);

			obj = (AnInterface) myObjectClass.newInstance();
			obj.execute();

			new BufferedReader(new InputStreamReader(System.in)).readLine();
		}
	}
}