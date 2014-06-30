package restDatastore;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class testDatastore {

	public static void main(String[] args) throws UnsupportedEncodingException, JSONException {
		 	 
		RestInvokerDatastore rest =  new RestInvokerDatastore();

		System.out.println("Data from entity newSeance :");
		System.out.println("");
		
		/*
		JSONObject json = rest.getDataWorkoutByEmailAndDate("2014.06.25.17:39","vincentpont@gmail.com");
		System.out.println(json.toString());
		*/
		
		/*
		List list = rest.getAllWorkoutByEmail("vincentpont@gmail.com");
	    //System.out.println(json.toString());

		Iterator<String> iterator = list.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
		*/

		/*
		List list = rest.getAllWorkoutDates("vincentpont@gmail.com");
		
		Iterator<String> iterator = list.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
		*/
										// 18               //36			// 55
		String string = "46.298736572265625,46.29862976074219,46.298519134521484,46.29844665527344";
		List<Double> listDouble = new ArrayList<Double>();
		int j = 0;
		int temp = 0 ;

		for(int i  =  0 ; i < string.length() ; i++){
			
			j = temp;
			
			if(string.charAt(i) == ','){
				
				System.out.println("i :"+i +" j :"+j);
				listDouble.add(Double.parseDouble(string.substring(j, i)));
				System.out.println("Ajout :" + string.substring(j, i) );

				temp  = i +1; 
			}

		}
		// Add last latitude 
		listDouble.add(Double.parseDouble(string.substring(j, string.length())));
		
		
		// Affichage
		System.out.println("liste:");
		Iterator<Double> iterator = listDouble.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
		
	}

}
