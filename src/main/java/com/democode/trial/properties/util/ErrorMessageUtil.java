package com.democode.trial.properties.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Logger;

public class ErrorMessageUtil {

	private static Logger LOG = Logger.getLogger("com.democode.trial.properties.util.ErrorMessageUtil");
	
	public static void reportError(Throwable e, String errMsg) {
		
		/**
		 * Log message
		 */
		LOG.severe(errMsg);

		/**
		 * Log stacktrace
		 */
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		LOG.severe(sw.toString());
	}	
}
