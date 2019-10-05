package com.accessibility.common;

import java.io.PrintWriter;

public class Logger {
	
	public static org.apache.log4j.Logger APPLICATION_LOGS = null;
	public static String sAppLogs = GenericUtils.getProperty("appliacion_Logs");
	public static boolean bAppLogs1, bAppsLogs2 = true;
	static PrintWriter out = null;
	
	public static void LogMessage(String strMessage) {
		try {
//			if (null!=TestCase.TestName) {
//				strMessage = GenericUtils.getDateTime()
//			}
				
				
		}catch(Exception e) {
			
		}
	}
	
	
}
