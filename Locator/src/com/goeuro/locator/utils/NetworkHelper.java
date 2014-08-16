package com.goeuro.locator.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.log4j.Logger;

public class NetworkHelper {
	private static Logger logger = Logger.getLogger(NetworkHelper.class);

	/**
	 * 
	 * @param url the API URL
	 * @param query the query to search for
	 * @return the API result
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public static String doGet(String url, String query) throws MalformedURLException, IOException{
		
		URLConnection connection = new URL(url + query).openConnection();
		InputStream response = connection.getInputStream();

		String output = convertStreamToString(response);
		logger.debug(output);
		return output;
	}
	
	/**
	 * Unit testing main method
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			doGet("http://api.goeuro.com/api/v2/position/suggest/en/", "berlin");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	static String convertStreamToString(java.io.InputStream is) {
	    java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
	    return s.hasNext() ? s.next() : "";
	}
}
