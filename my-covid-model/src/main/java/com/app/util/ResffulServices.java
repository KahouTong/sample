package com.app.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class ResffulServices {
	
	 private ResffulServices() {
		// This is intentional
		  }

	public static String getServices(String uRL) throws Exception {

		URL url;
		StringBuilder textBuilder = new StringBuilder();
		try {
			url = new URL(uRL);

			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
			
			checkInput(urlConnection,textBuilder);
			
		} catch (Exception e) {
			throw new Exception(e);

		}

		return textBuilder.toString();

	}
	
	public static void checkInput(HttpURLConnection urlConnection, StringBuilder textBuilder) throws IOException {
		try {
			InputStream in = new BufferedInputStream(urlConnection.getInputStream());
			checkReader(in, textBuilder);
		} finally {
			urlConnection.disconnect();
		}
	}
	
	public static void checkReader(InputStream in, StringBuilder textBuilder) throws IOException {
		try (Reader reader = new BufferedReader(
				new InputStreamReader(in, Charset.forName(StandardCharsets.UTF_8.name())))) {
			int c = 0;
			while ((c = reader.read()) != -1) {
				textBuilder.append((char) c);
			}
		}
	}
}
