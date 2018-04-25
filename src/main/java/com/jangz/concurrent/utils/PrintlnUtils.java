package com.jangz.concurrent.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class PrintlnUtils {

	private static final String BASE = System.getProperty("user.dir");
	private static final String PATH = "src/main/resources/_result";
	
	public static void println(Object o) {
		try {
			OutputStream out = new FileOutputStream(new File(BASE + "/" + PATH), true);
			out.write((o.toString() + "\n").getBytes());
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
