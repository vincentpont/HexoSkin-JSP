package restHexoSkin;

import java.util.Iterator;
import java.util.List;

import org.json.JSONException;

import com.google.protobuf.TextFormat.ParseException;



public class testHexoSkin {

	public static void main(String[] args) throws JSONException, ParseException {
		
		// Oauth
		String username = "bruno.alves@hevs.ch";
		String password = "$hes-so2014!";
		
		
		// Voir les séances réalisées
		String s1 = "https://api.hexoskin.com/api/v1/record/?startTimestamp__gte=1404205354";

		RestInvokerHexo rest = new RestInvokerHexo(username, password, s1);
		rest.getJSONObjectData();
		/*
		// return id by date
		String id = rest.returnIdOfWorkout("2014-06-28T11:11:42+00:00");
		
		
		String idMetric = "52";
		String urlWithId = "https://api.hexoskin.com/api/v1/data/?datatype="+idMetric+"&record=" +id;
		
		
		System.out.println("Id : " +rest.returnIdOfWorkout("2014-06-28T11:11:42+00:00"));
		System.out.println("Values : " +rest.GetLastValueFromDatatype(urlWithId));

		
		
		String dateNonFormated = "2014-06-28T11:11:42+00:00";
		String dateFormated = dateNonFormated.substring(0, 10);
		dateFormated = dateFormated.replaceAll("-", ".");
		System.out.println("Date : " + dateFormated);
		 */
		
		
		// Get all datas
		String string = "{'19':[[359412199216,71],[359412199472,70],[359412199728,70],[359412199984,59]]}" ;
		System.out.println(string);
		List list = rest.returnAllValueFromJson(string);

		 Iterator<String> iterator = list.iterator(); 
		 
		 while (iterator.hasNext()) {
		 System.out.println(iterator.next()); 
		 }

	}

}
