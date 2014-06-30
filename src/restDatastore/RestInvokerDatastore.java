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

/*
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

	// Constructor
	public RestInvokerDatastore() {

		// Construct the Oauth
		userpass = username + ":" + password;
		basicAuth = "Basic "
				+ new String(new Base64().encode(userpass.getBytes()));
	}

	/*
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

	/*
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

	/*
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
		        System.out.println(json.toString());

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
				countRows++;
			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;

	}

	/*
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

	/*
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

	/*
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

}
