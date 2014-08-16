package com.goeuro.locator.controller;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.goeuro.locator.utils.CSVHelper;
import com.goeuro.locator.utils.Constants;
import com.goeuro.locator.utils.JSONHelper;
import com.goeuro.locator.utils.NetworkHelper;

/**
 * @author Hossam Yahia
 *
 * Locator Project
 * 15-8-2014
 */

public class Main {

	private static Logger logger = Logger.getLogger(Main.class);
	
	/**
	 * The main method
	 * @param args expected one argument as a query.
	 */
	public static void main(String[] args){
		
		try {
			logger.info("=================== LOCATOR STARTED SUCCESSFULLY ====================");

			// Validate that the query argument exists
			if (args == null || args.length == 0) {
				logger.error(Constants.ERROR_NO_ARGS);
				return;
			}
			logger.info("=================== ARGUMENTS VALIDATED SUCCESSFULLY ====================");
			String query = args[0];
			
			// Send query to the API via HTTP GET and read response
			String text = null;
			try {
				text = NetworkHelper.doGet(Constants.API_URL, query);
				logger.info("=================== CALLED API SUCCESSFULLY ====================");

			} catch (Throwable e) {
				logger.error(Constants.ERROR_NETWORK_FAILURE, e);
				return;
			}
			
			// Parse the response from the API
			ArrayList<ArrayList<Object>> result = null;
			try {
				result = JSONHelper.parseJSONObject(text, "result");
				logger.info("=================== PARSED API SUCCESSFULLY ====================");

			} catch (Throwable e) {
				logger.error(Constants.ERROR_PARSING_RESPONSE, e);
				return;

			}
			
			// Prepare Headers
			ArrayList<String> fileHeaders = new ArrayList<String>();
			fileHeaders.add("_type");
			fileHeaders.add("_id");
			fileHeaders.add("name");
			fileHeaders.add("type");
			fileHeaders.add("latitude");
			fileHeaders.add("longitude");

			// Fix file name
			String fileName = query.replace("\\", "_");
			fileName = fileName.replace("/", "_");
			fileName = fileName.replace("\"", "_");
			fileName = fileName.replace(">", "_");
			fileName = fileName.replace("<", "_");
			fileName = fileName.replace("?", "_");
			fileName = fileName.replace("*", "_");
			fileName = fileName.replace(":", "_");
			
			// Write to file
			try {
				CSVHelper.writeToFile("Result-" + fileName + "-" + System.currentTimeMillis() + ".csv", result, fileHeaders);
				logger.info("=================== CREATED RESULT FILE SUCCESSFULLY ====================");

			} catch (Throwable e) {
				logger.error(Constants.ERROR_WRITING_FILE, e);
				return;
			}
			logger.info("=================== LOCATOR COMPLETED SUCCESSFULLY ====================");

		} catch (Throwable e) {
			logger.error(Constants.ERROR_UNKNOWN, e);
		}
	}

}
