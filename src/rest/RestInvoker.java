package rest;
import java.io.IOException;
import java.io.InputStream;










import net.smartam.leeloo.client.request.OAuthClientRequest;

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

/*
 * Author : Pont Vincent
 * Date : 09.06.2014
 * Last modification : 09.06.2014
 * Travail de bachelor
 */

public class RestInvoker  {
	
	private String username ;
	private String password ;
	private String url ;

	
	public RestInvoker(String username, String password, String url) {
		this.username = username;
		this.password = password;
		this.url = url;
	}


	public  String getASCIIContentFromEntity(HttpEntity entity)
			throws IllegalStateException, IOException {

		InputStream in = entity.getContent();

		StringBuffer out = new StringBuffer();
		int n = 1;
		while (n > 0) {
			byte[] b = new byte[4096];
			n = in.read(b);

			if (n > 0)
				out.append(new String(b, 0, n));
		}

		return out.toString();
	}
	
	// GET = on reçoit un string
	public String getData() {
		
		CredentialsProvider provider = new BasicCredentialsProvider();
		UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(this.username, this.password);
		provider.setCredentials(AuthScope.ANY, credentials);
		HttpClient client = HttpClientBuilder.create().setDefaultCredentialsProvider(provider).build();
		
		HttpContext localContext = new BasicHttpContext();
		String result = "";
		HttpGet request = new HttpGet(this.url);

		try {
			HttpResponse response = client.execute(request, localContext);

			
			HttpEntity entity = response.getEntity();

			result = getASCIIContentFromEntity(entity);

		} catch (Exception e) {
			return  e.getLocalizedMessage();
		}
		
		return result;
		
	}
	
	

}
