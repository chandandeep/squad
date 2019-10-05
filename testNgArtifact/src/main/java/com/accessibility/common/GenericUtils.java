package com.accessibility.common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

public class GenericUtils {

	public static String getProperty(String strProp) {
		Properties prop = new Properties();
		try {
			prop.loadFromXML(new FileInputStream("./config/properties.xml"));
		}catch(InvalidPropertiesFormatException e) {
			e.printStackTrace();
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}

		return prop.getProperty(strProp);		

	}

	public static void updatePropertiesFiles(String key, String value) {
		java.util.Properties prop = new Properties();
		try {
			prop.loadFromXML(new FileInputStream(".config/Properties.xml"));
			prop.setProperty(key, value);
			prop.storeToXML(new FileOutputStream(".config/Properties.xml"),"");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
