package com.stg.insurance.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommonUtilities {
	
	private static Logger LOGGER = LoggerFactory.getLogger(CommonUtilities.class);

	private static HashMap<String, String> delimiterMapper;

	static {
		delimiterMapper = new HashMap<>();
		delimiterMapper.put("CRLF", "\r\n");
		delimiterMapper.put("LF", "\n");
		delimiterMapper.put("CR", "\r");
	}

	public static String getEscapeSequence(String key) {
		return delimiterMapper.get(key);
	}

	public static String getStringFromInputStream(InputStream is) {
		BufferedReader br = null;

		StringBuilder sb = new StringBuilder();
		int line;

		try {
			br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

			while ((line = br.read()) != -1) {

				sb.append((char)line);

			}
		} catch (IOException e) {

			LOGGER.error("IO Exception occured: {}", e);

		} finally {

			if (br != null) {

				try {

					br.close();

				} catch (IOException e) {

					LOGGER.error("IO Exception occured: {}", e);

				}

			}

		}
		return sb.toString();
	}

	private CommonUtilities() {
		super();
	}
}
