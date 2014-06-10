
public class testHexoSkin {

	public static void main(String[] args) {

		 String username = "athlete@hexoskin.com";
		 String password = "hexoskin";
		 String urlAllData = "https://api.hexoskin.com/api/v1/datatype/";
		 String urlDataFiltered = "https://api.hexoskin.com/api/v1/datatype/36/";
		 
		 
		 // Get Data from api 
		 RestInvoker rest1 = new RestInvoker(username, password, urlDataFiltered);
		 System.out.println("Données :" + rest1.getData());
		

		 

	}

}
