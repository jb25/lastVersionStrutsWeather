package net.viralpatel.struts2;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import com.opensymphony.xwork2.ActionSupport;


public class LoginAction extends ActionSupport {

	private String email;
	private String password;


	public String execute() {
		URL requestURL;
		HttpURLConnection urlConnection;
		String user;
		String response;
		String works="error";
		

		try {
			requestURL = new URL("http://localhost:8080/zalando.deusto-0.0.1-SNAPSHOT/api/user/user");
			urlConnection = (HttpURLConnection) requestURL.openConnection();
			urlConnection.setRequestProperty("email", email);
			urlConnection.setRequestProperty("password", password);
			InputStream in = new BufferedInputStream(urlConnection.getInputStream());
			response = readStream(in);
			System.out.println("holaaaaaaaaaaaaaa");
			System.out.println(email);
			System.out.println(password);
			user = response;
			
			//user.getString("email");
			urlConnection.disconnect();
		
			if (!response.isEmpty()) {
				works = "success";

			} else {
				addActionError(getText("error.login"));
				works = "error";

			}

		} catch (MalformedURLException e) {
			
			e.printStackTrace();
		} catch (IOException e1) {
		
			e1.printStackTrace();
		} 
		return works;
	}



	public LoginAction() {
		super();
	}

	public LoginAction(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
