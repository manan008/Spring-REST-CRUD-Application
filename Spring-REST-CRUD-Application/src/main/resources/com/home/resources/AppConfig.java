package com.home.resources;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppConfig {
	public static final Properties PROPERTIES;
	public static InputStream inputStream1 = null;
	public static InputStream inputStream2 = null;
	
	static {
		try {
			inputStream1 = AppConfig.class.getResourceAsStream("configuration.properties");
			inputStream2 = AppConfig.class.getResourceAsStream("database.properties");
		}
		catch (Exception e) {
			LogConfig.getLogger(AppConfig.class).error(e.getMessage(), e);
		}
		
		PROPERTIES = new Properties();
		
		try {
			PROPERTIES.load(inputStream1);
			PROPERTIES.load(inputStream2);
		}
		catch (IOException e) {
			LogConfig.getLogger(AppConfig.class).error(e.getMessage(), e);
		}
	}
}
