package restDatastore;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;










import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Date;

import net.smartam.leeloo.client.request.OAuthClientRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.json.JSONException;
import org.json.JSONObject;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

/*
 * Author : Pont Vincent
 * Date : 09.06.2014
 * Last modification : 25.06.2014
 * Travail de bachelor
 */

public class RestInvokerDatastore  {
	
	private String username ;
	private String password ;
	private String url;
	private JSONObject json;


	public RestInvokerDatastore(String username, String password, String url) {
		this.username = username;
		this.password = password;
		this.url = url;
	}
	
	// Get data in JSON
	public JSONObject getJSONData(){
		
		String userpass = username + ":" + password;
		String basicAuth = "Basic " + new String(new Base64().encode(userpass.getBytes()));
			
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
			this.json = new JSONObject(jsonText);
			
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
	
	  private String readAll(Reader rd) throws IOException {
		    StringBuilder sb = new StringBuilder();
		    int cp;
		    while ((cp = rd.read()) != -1) {
		      sb.append((char) cp);
		    }
		    return sb.toString();
		  }
	
}
