package restHexoSkin;

import java.io.UnsupportedEncodingException;

import org.apache.http.entity.StringEntity;
import org.json.JSONArray;
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
		String username = "bruno.alves@hevs.ch";
		String password = "$hes-so2014!";
		String urlAllData = "https://api.hexoskin.com/api/v1/datatype/";
		String url = "https://api.hexoskin.com/api/v1/data/?datatype=19&record=35610";

		// Voir les séances réalisées
		String s1 = "https://api.hexoskin.com/api/v1/record/?startTimestamp__gte=1404205354";
		
		// Interroger la table pour les données
		String s2 = "https://api.hexoskin.com/api/v1/data/?datatype=52&record=35610";

		RestInvokerHexo rest = new RestInvokerHexo(username, password, s1);
		JSONObject json = new JSONObject();
		json = rest.getJSONData();

		try {

			System.out.println(json.toString());

			// Decompose the jsonObject jsonArray in a list
			JSONArray jsonMainArr = json.getJSONArray("objects");
			for (int i = 0; i < jsonMainArr.length(); i++) {
				JSONObject childJSONObject = jsonMainArr.getJSONObject(i);
				System.out.println("Séance id : "
						+ childJSONObject.getString("id") + ", Date : "
						+ childJSONObject.getString("start_date"));

			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		


	}

}
