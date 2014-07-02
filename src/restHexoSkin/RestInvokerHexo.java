package restHexoSkin;
import java.util.List;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Date;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

/**
 * Author : Pont Vincent
 * Date : 09.06.2014
 * Last modification : 25.06.2014
 * Travail de bachelor
 */

public class RestInvokerHexo  {
	
	private String url;
	private String id ;
	private JSONObject json;
	private Timestamp ts = new Timestamp(new Date().getTime());
	private String userpass;
	private String basicAuth;
	
	// Transform string of Sha1, not needed
	//private final String signature = DigestUtils.shaHex("zmKunuuIZBYnY79ikyecwrhyPxZxFfARt9ONCbIc");

	public RestInvokerHexo(String username, String password, String url) {
		this.url = url;
		userpass = username + ":" + password;
		basicAuth = "Basic " + new String(new Base64().encode(userpass.getBytes()));
	}
	
	// Get data in JSON
	public JSONObject getJSONObjectData(){
		
	try {
		 
		URL url = new URL(this.url);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept", "application/json");
		conn.setRequestProperty ("Authorization", basicAuth);
		
		// Key, timestamp, signature NOT NEEDED
		//conn.setRequestProperty("X-HEXOAPIKEY", "4QL3vPdCjSE6mJL2");
		//conn.setRequestProperty("X-HEXOTIMESTAMP", ts.toString());
		//conn.setRequestProperty("X-HEXOAPISIGNATURE", signature);



		if (conn.getResponseCode() != 200) {
			throw new RuntimeException("Failed, HTTP error code : "
					+ conn.getResponseCode() +" " +conn.getResponseMessage());
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
	return json;
	
	
	
	}
	
	/** Method that return the json from a jsonArray
	 * 
	 * 
	 * @return JSONArray
	 */
	public JSONArray getJSONArrayData(){
		
		JSONArray jsonArray  = null;
		
	try {
		 
		URL url = new URL(this.url);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept", "application/json");
		conn.setRequestProperty ("Authorization", basicAuth);


		if (conn.getResponseCode() != 200) {
			throw new RuntimeException("Failed, HTTP error code : "
					+ conn.getResponseCode() +" " +conn.getResponseMessage());
		}
 
		BufferedReader br = new BufferedReader(new InputStreamReader(
			(conn.getInputStream())));
		
		String jsonText = readAll(br);
		
		 
		try {
			jsonArray = new JSONArray(jsonText);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			 
		// Disconnect
		conn.disconnect();
 

 
	  } catch (IOException e) {
 
		e.printStackTrace();
 
	  }
	return jsonArray;
	
	}
	
	/**
	 * Method that return the id of the workout passing a date in param
	 * @param date
	 * @return String id 
	 */
	public String returnIdOfWorkout(String date){

		try {
			
			// Decompose the jsonObject jsonArray in a list
			JSONArray jsonMainArr = json.getJSONArray("objects");
			for (int i = 0; i < jsonMainArr.length(); i++) {
				JSONObject childJSONObject = jsonMainArr.getJSONObject(i);
				if (childJSONObject.getString("start_date").equals(date)){
				id = childJSONObject.getString("id");
				}

			}

		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		
		return id ;
	}
	
	/**
	 * Method that return the values of the metrics passed in param
	 * @param date
	 * @return String values of the metrics 
	 */
	public String GetLastValueFromDatatype(String url){

		this.url = url;
		String values = "";
		JSONArray json = new JSONArray();
		json = getJSONArrayData();
		
		try {
			JSONObject jsonObject = json.getJSONObject(0);
			values = returnLastValueFromJson(jsonObject.getString("data")) ;
			System.out.println(jsonObject.getString("data"));

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return values ;
	}
	
	
	// A MODIFIER
    // Method that return all values containing in the json from a datatype
	public List<String> returnAllValueFromJson(String jsonString){

		List<String> list = new ArrayList<String>();
		
		String values = "";
		int j = 0;
		Boolean find = false ;

		for (int i = 0 ; i < jsonString.length()  ; i++) {


			// First position, if we find the first ','
			if(jsonString.charAt(i) == ',' && jsonString.charAt(i+3) == ']'){
				j = i +1 ;
				System.out.println("j : " +j);
				System.out.println("i : " +i);
			}
			
			//  Second position,Position near the values
			if (jsonString.charAt(i) == ',' && jsonString.charAt(i-1) == ']') {
				values = jsonString.substring(j, i-1); 
				list.add(values);
			}

		}
		
		// Add last value
		list.add(returnLastValueFromJson(jsonString));
		
		 return list;
		
	}
	
	
	
	/**
	 * Method that with a string return the last value 
	 * 
	 * @param String jsonString
	 * @return String values
	 */
	public String returnLastValueFromJson(String jsonString){

		String values = "";
		int j = 0;
		Boolean find = false ;

		
		for (int i = jsonString.length()-1; i > 1 ; i--) {

			// -3 because of the 3 characters at the end
			j = jsonString.length()-3;
			
			if (find == false){
			if (jsonString.charAt(i) == ',') {
				values = jsonString.substring(i+1, j); // +1 Because of the ','
				find = true ;
				}
			}

		}
		 return values;
		
	}
	
	
	  private String readAll(Reader rd) throws IOException {
		    StringBuilder sb = new StringBuilder();
		    int cp;
		    while ((cp = rd.read()) != -1) {
		      sb.append((char) cp);
		    }
		    return sb.toString();
		  }
	
}
