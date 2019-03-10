package com.atulsharma.dwelloassignment;

import com.atulsharma.dwelloassignment.models.Result;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class DwelloAssignmentQuestion1 {

	private static String MOVIE_ENDPOINT = "https://jsonmock.hackerrank.com/api/movies/search/";

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String subStr = br.readLine();
		DwelloAssignmentQuestion1 application = new DwelloAssignmentQuestion1();
		String[] titles = application.getMovieTitles(subStr);
		System.out.println(titles.length);
	}

	public String[] getMovieTitles(String subStr) {
		String resultStr = getQueryResult(subStr);
		Result resultObj = unmarshalResult(resultStr);
		System.out.println(String.format("Total Pages: %d",resultObj.getTotalPages()));
		if(resultObj.getPage() > 0) {
			String[] titles = getMovieTitles(subStr, resultObj.getPage());
			return titles;
		}
		return new String[0];
	}

	public String[] getMovieTitles(String subStr, int page) {
		System.out.println(String.format("Fetching Page: %d",page));
		String resultStr = getQueryResult(subStr,page);
		Result resultObj = unmarshalResult(resultStr);
		String[] titlesOnPage = new String[resultObj.getData().length];
		for(int i=0;i < resultObj.getData().length;i++) {
			titlesOnPage[i] = resultObj.getData()[i].getTitle();
		}
		if(resultObj.getPage() < resultObj.getTotalPages()) {
			List titlesList = new ArrayList(Arrays.asList(titlesOnPage));
			titlesList.addAll(Arrays.asList(getMovieTitles(subStr,resultObj.getPage()+1)));
			Object[] titles = titlesList.toArray();
			String[] stringArray = Arrays.copyOf(titles, titles.length, String[].class);
			return stringArray;
		} else {
			return titlesOnPage;
		}
	}

	public String getQueryResult(String subStr) {
		try {
			String query = String.format("Title=%s",
					URLEncoder.encode(subStr, "UTF-8"));
			URL url = new URL(MOVIE_ENDPOINT + "?" + query);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept-Charset", "UTF-8");
			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			return rd.readLine();
		} catch (ProtocolException protocolException) {
			return "";
		} catch (IOException ioException) {
			return "";
		}
	}

	public String getQueryResult(String subStr, int page) {
		try {
			String query = String.format("Title=%s&page=%d",
					URLEncoder.encode(subStr, "UTF-8"),page);
			URL url = new URL(MOVIE_ENDPOINT + "?" + query);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept-Charset", "UTF-8");
			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			return rd.readLine();
		} catch (ProtocolException protocolException) {
			return "";
		} catch (IOException ioException) {
			return "";
		}
	}

	public Result unmarshalResult(String resultStr) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.fromJson(resultStr,Result.class);
	}
}