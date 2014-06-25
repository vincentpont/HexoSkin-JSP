package restDatastore;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.json.JSONException;
import org.json.JSONObject;

import restHexoSkin.RestInvokerHexo;

public class testDatastore {

	public static void main(String[] args) throws UnsupportedEncodingException {

		 String username = "vincentpont@gmail.com";
		 String password = "volcom888";
		 String email = "vincentpont@gmail.com";
	     String date = "2014.06.25.17:39" ;
		 
	     // Create url and encode
		 String url = "https://logical-light-564.appspot.com/_ah/api/helloworld/v1/jsonobject/"
		 + URLEncoder.encode(email, "UTF-8") +"/"+URLEncoder.encode(date, "UTF-8");
		 
		 
		RestInvokerHexo rest =  new RestInvokerHexo(username,password,url);
		JSONObject json = new JSONObject();
		json = rest.getJSONData();
		
		System.out.println("Data from entity newSeance :");
		
		try {
			
			System.out.println(json.getString("Time"));
			System.out.println(json.getString("Calories"));
			System.out.println(json.getString("Email"));
			System.out.println(json.getString("Date"));
			System.out.println(json.getString("Distance"));
			System.out.println(json.getString("MeterMin"));
			
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
	}

}
