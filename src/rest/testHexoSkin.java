package rest;

import java.io.UnsupportedEncodingException;

import org.apache.http.entity.StringEntity;
import org.json.JSONException;
import org.json.JSONObject;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;




public class testHexoSkin {

	public static void main(String[] args) {

		 String username = "athlete@hexoskin.com";
		 String password = "hexoskin";
		 //String urlAllData = "https://api.hexoskin.com/api/v1/datatype/";
		 String url = "https://api.hexoskin.com/api/v1/datatype/37/";
		 
		 
		RestInvoker rest =  new RestInvoker(username,password,url);
		JSONObject json = new JSONObject();
		json = rest.getJSONData();
		
		try {
			System.out.println(json.getString("dataid"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
		
	}
	

}
