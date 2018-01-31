package net.viralpatel.struts2;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringBufferInputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

import com.opensymphony.xwork2.ActionSupport;

public class WelcomeAction  extends ActionSupport{

	private String city;
	URL requestURL;
	HttpURLConnection urlConnection;
	String data;
	String response; 
	
	
	  private InputStream inputStream;
	  public InputStream getInputStream() {
	    return inputStream;
	   }
	 
	@SuppressWarnings("deprecation")
	public String execute(){


		try {
			requestURL = new URL("http://localhost:8080/weather.deusto-0.0.1-SNAPSHOT/api/weather/findWeatherByCity");
			urlConnection = (HttpURLConnection) requestURL.openConnection();
			urlConnection.setRequestProperty("city", city);

			InputStream in = new BufferedInputStream(urlConnection.getInputStream());
			response = readStream(in);		

			data = response;


			//user.getString("email");

			urlConnection.disconnect();
System.out.println("AAAAAAAAAAA" + data.toString());
inputStream = new StringBufferInputStream(data.toString());

		} catch (MalformedURLException e) {

			e.printStackTrace();
		} catch (IOException e1) {

			e1.printStackTrace();
		}

		if (!response.isEmpty()) {
			return "yes";

		} else {
			addActionError(getText("error.city"));
			return "error";
		}



	}

	public WelcomeAction() {
		super();
	}

	public WelcomeAction(String city) {
		super();
		this.city = city;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	/*
	 * Input stream Reader
	 * @param is
	 * @return
	 * @throws IOException
	 */
	private String readStream(InputStream is) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader r = new BufferedReader(new InputStreamReader(is), 1000);
		for (String line = r.readLine(); line != null; line = r.readLine()) {
			sb.append(line);
		}
		is.close();	
		return sb.toString();
	}
}
