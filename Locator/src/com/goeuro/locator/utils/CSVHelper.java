package com.goeuro.locator.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.apache.log4j.Logger;

public class CSVHelper {
	private static Logger logger = Logger.getLogger(CSVHelper.class);

	/**
	 * 
	 * @param fileName the output file name
	 * @param fileData the data to file the file with
	 * @param fileHeaders the header names
	 * @throws IOException
	 */
	public static void writeToFile(String fileName, ArrayList<ArrayList<Object>> fileData, ArrayList<String> fileHeaders) throws IOException {
		logger.debug("File Name:" + fileName);
		logger.debug("File Data:" + fileData);
		logger.debug("File Headers:" + fileHeaders);
		
		PrintWriter writer = null;
		writer = new PrintWriter(new File(fileName));
		
		StringBuilder headers = new StringBuilder();
		for (String header: fileHeaders) {
			headers.append(header).append(",");
		}
		// remove trailing ','
		String headersString = headers.toString();
		headersString = headersString.substring(0,headersString.length()-1);
		// write headers
		writer.println(headersString);
		
		StringBuilder recordSB = new StringBuilder();
		for (ArrayList<Object> record : fileData) {
			for (Object recordItem : record) {
				recordSB.append(recordItem).append(",");
			}
			String recordString = recordSB.toString();
			recordString = recordString.substring(0,recordString.length()-1);
			writer.println(recordString);
			
			recordSB = new StringBuilder();
		}
		
		writer.close();
	}
	
	/**
	 * Unit testing main method
	 * @param args
	 */
	public static void main(String[] args) throws FileNotFoundException {
		PrintWriter writer = null;
		writer = new PrintWriter(new File("Result-" + System.currentTimeMillis() + ".csv"));
		writer.println("Name,Id");
		writer.println("Hossam,123");
		writer.close();
	}

}
