package restDatastore;

import helper.DatesComparator;
import helper.withComparator;

import java.io.BufferedReader;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.json.JSONException;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

/**
 * Author : Pont Vincent
 * Class : This class do REST requests to the datastore to get data saved from android client.
 * Date : 09.06.2014
 * Last modification : 25.06.2014
 * Travail de bachelor 2014
 */

public class RestInvokerDatastore {

	private final String username = "vincentpont@gmail.com";
	private final String password = "volcom888";
	private String userpass;
	private String basicAuth;
	private String url;
	private JSONObject json;
	public int countRows = 0;
	private String StringLatitudes ;
	private String StringLongitudes ;
	private String StringVitesses  ;
	private String StringAltitudes ;

	// Constructor
	public RestInvokerDatastore() {

		// Construct the Oauth
		userpass = username + ":" + password;
		basicAuth = "Basic "
				+ new String(new Base64().encode(userpass.getBytes()));
	}

	/**
	 * Method to get in JSON one workout with the date and email
	 * 
	 * @Param: String date, String email
	 * 
	 * @Return: JSONObject containing the workout
	 */
	public List getDataWorkoutByEmailAndDate(String date, String email)
			throws UnsupportedEncodingException {

		// Create url request and encode email and date
		String urlRequest = "https://logical-light-564.appspot.com/_ah/api/helloworld/v1/jsonobject/getDataWorkoutByEmailAndDate?Date="
				+ URLEncoder.encode(date, "UTF-8")
				+ "&Email="
				+ URLEncoder.encode(email, "UTF-8");

		List list = new ArrayList();

		try {

			URL url = new URL(urlRequest);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("Authorization", basicAuth);

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed, HTTP error code : "
						+ conn.getResponseCode() + " "
						+ conn.getResponseMessage());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));

			String jsonText = readAll(br);

			try {
				json = new JSONObject(jsonText);

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// Disconnect
			conn.disconnect();

		} catch (IOException e) {

			e.printStackTrace();

		}

		// Pass values to the list
		try {
			list.add(json.getString("Date"));
			list.add(json.getString("Time"));
			list.add(json.getString("Distance"));
			list.add(json.getString("Calories"));
			list.add(json.getString("MeterMin"));

		} catch (JSONException e) {
			e.printStackTrace();
		}

		return list;

	}

	/**
	 * Method to get all workouts from the specified email
	 * 
	 * @Param: String email
	 * 
	 * @Return: JSONObject containing a JSONArray with all the workouts
	 */
	public List<String> getAllWorkoutByEmail(String email) {

		// Create url request and encode email and date
		String urlRequest = "";
		try {
			urlRequest = "https://logical-light-564.appspot.com/_ah/api/helloworld/v1/jsonobject/getDatasWorkoutByEmail?Email="
					+ URLEncoder.encode(email, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		List<String> list = new ArrayList<String>();

		try {

			URL url = new URL(urlRequest);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("Authorization", basicAuth);

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed, HTTP error code : "
						+ conn.getResponseCode() + " "
						+ conn.getResponseMessage());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));

			String jsonText = readAll(br);

			try {
				json = new JSONObject(jsonText);


			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// Disconnect
			conn.disconnect();

		} catch (IOException e) {

			e.printStackTrace();

		}

		try {

			// Decompose the jsonObject jsonArray in a list
			JSONArray jsonMainArr = json.getJSONArray("Workouts");
			for (int i = 0; i < jsonMainArr.length(); i++) {
				JSONObject childJSONObject = jsonMainArr.getJSONObject(i);
				list.add(childJSONObject.getString("Date"));
				list.add(childJSONObject.getString("Time"));
				list.add(childJSONObject.getString("Distance"));
				list.add(childJSONObject.getString("Calories"));
				list.add(childJSONObject.getString("MeterMin"));
				countRows++;
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return list;

	}
	
	/**
	 * Method to return all data of the map by email and date
	 * 
	 * @Param: String email, String date
	 * 
	 * @Return: JSONObject containing a JSONArray with all the data : 
	 * List of latitudes, list of longitudes, list of speed and altitudes
	 */
	public List getDataMap(String email)
			throws UnsupportedEncodingException {

		// Create url request and encode email and date
		String urlRequest = "https://logical-light-564.appspot.com/_ah/api/helloworld/v1/jsonobject/getDataMap?Email="
				+ URLEncoder.encode(email, "UTF-8");

		List list = new ArrayList();

		try {

			URL url = new URL(urlRequest);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("Authorization", basicAuth);

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed, HTTP error code : "
						+ conn.getResponseCode() + " "
						+ conn.getResponseMessage());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));

			String jsonText = readAll(br);

			try {
				json = new JSONObject(jsonText);

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// Disconnect
			conn.disconnect();

		} catch (IOException e) {

			e.printStackTrace();

		}

		JSONArray jsonMainArr;
		try {
			jsonMainArr = json.getJSONArray("DataMap");

			for (int i = 0; i < jsonMainArr.length(); i++) {
				JSONObject childJSONObject = jsonMainArr.getJSONObject(i);
				StringLatitudes = childJSONObject.getString("Latitudes");
				StringLongitudes = childJSONObject.getString("Longitudes");
				StringVitesses = childJSONObject.getString("Speed");
				StringAltitudes = childJSONObject.getString("Altitudes");
			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;

	}
	

	/**
	 * Method to get all dates from the specified email
	 * 
	 * @Param: String email
	 * 
	 * @Return: JSONObject containing a JSONArray with all the dates
	 */
	public List getAllWorkoutDates(String email)
			throws UnsupportedEncodingException {

		// Create url request and encode email and date
		String urlRequest = "https://logical-light-564.appspot.com/_ah/api/helloworld/v1/jsonobject/getAllWorkoutDates?Email="
				+ URLEncoder.encode(email, "UTF-8");

		List list = new ArrayList();

		try {

			URL url = new URL(urlRequest);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("Authorization", basicAuth);

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed, HTTP error code : "
						+ conn.getResponseCode() + " "
						+ conn.getResponseMessage());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));

			String jsonText = readAll(br);

			try {
				json = new JSONObject(jsonText);

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// Disconnect
			conn.disconnect();

		} catch (IOException e) {

			e.printStackTrace();

		}

		JSONArray jsonMainArr;
		try {
			jsonMainArr = json.getJSONArray("Dates");

			for (int i = 0; i < jsonMainArr.length(); i++) {
				JSONObject childJSONObject = jsonMainArr.getJSONObject(i);
				list.add(childJSONObject.getString("Date"));
			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;

	}
	
	

	/**
	 * Method to return all the workout dates sorted date (last workout)
	 * 
	 * @Param : String email
	 * 
	 * @Return : List of dates sorted
	 */
	public List getAllDatesWorkoutSorted(String email)
			throws UnsupportedEncodingException {

		List listDates = getAllWorkoutDates(email);

		List<DatesComparator> list = new ArrayList<DatesComparator>();

		for (int i = 0; i < listDates.size(); i++) {
			list.add(new DatesComparator((String) listDates.get(i)));
		}

		// Sort the list
		Collections.sort(list, new withComparator());

		return list;
	}
	
	
	
	

	/**
	 * Method to sort the date returned by datastore and return the more current
	 * date (last workout)
	 * 
	 * @Param : String email
	 * 
	 * @Return : String date
	 */
	public String getLastDateWorkout(String email)
			throws UnsupportedEncodingException {

		RestInvokerDatastore rest = new RestInvokerDatastore();
		List listDates = rest.getAllWorkoutDates(email);

		List<DatesComparator> list = new ArrayList<DatesComparator>();
		Iterator<DatesComparator> it = list.iterator();

		for (int i = 0; i < listDates.size(); i++) {
			list.add(new DatesComparator((String) listDates.get(i)));
		}

		// Sort
		Collections.sort(list, new withComparator());

		DatesComparator date = list.get(list.size() - 1);
		String lastDate = date.toString();

		return lastDate;
	}

	/**
	 * Method to read all from reader
	 * 
	 * @Param : Reader
	 * 
	 * @Return : String
	 */
	private String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}
	
	/**
	 * Method who substring a string (list) and replace all characters non desired and parse to double list
	 * 
	 * @Param : String list
	 * 
	 * @Return : List of Double
	 */
	private List<Double> substringLists(String list) {

		List<Double> listDouble = new ArrayList<Double>();
		int j = 0;
		int temp = 0;

		// Replace all none double characters
		list = list.replaceAll("\\[", "");
		list = list.replaceAll("\\]", "");
		list = list.replace('"', ' ');
		list = list.trim();

		for (int i = 0; i < list.length(); i++) {

			j = temp;

			if (list.charAt(i) == ',') {
				System.out.println("i :" + i + " j :" + j);
				listDouble.add(Double.parseDouble(list.substring(j, i)));
				System.out.println("Ajout :" + list.substring(j, i));
				temp = i + 1;
			}
		}

		// Add last values
		listDouble.add(Double.parseDouble(list.substring(j, list.length())));

		return listDouble;

	}
	
	
	/**
	 *  Getters and Setters
	 */

	public String getStringLatitudes() {
		return StringLatitudes;
	}

	public String getStringLongitudes() {
		return StringLongitudes;
	}

	public String getStringVitesses() {
		return StringVitesses;
	}

	public String getStringAltitudes() {
		return StringAltitudes;
	}



}
