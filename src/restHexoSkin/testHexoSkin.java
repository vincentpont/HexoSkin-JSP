package restHexoSkin;

import java.util.Iterator;
import java.util.List;



public class testHexoSkin {

	public static void main(String[] args) {
				
		// Voir les s�ances r�alis�es
		String s1 = "https://api.hexoskin.com/api/v1/record/?startTimestamp__gte=1404205354";

		RestInvokerHexo rest = new RestInvokerHexo(s1);
		
		 	
		 List list = rest.returnAllValueFromJson("2014-06-28", "19" );

		 Iterator<String> iterator = list.iterator(); 
		 
		 while (iterator.hasNext()) {
		 System.out.println(iterator.next()); 
		 }
		 


	}

}
