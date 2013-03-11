package com.luismichelmx.cursoandroid;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import android.util.Log;

public class HTTPRequest {

	private String requestURL;
	
	public HTTPRequest(String request){
		
		this.requestURL = request;
	}
	
	public String execute(String method) throws ClientProtocolException, IOException{
	
		String responseBody = null;
		
		if( method.equalsIgnoreCase("get") )
		{
			HttpClient httpclient = new DefaultHttpClient();
			HttpGet httpget = new HttpGet(requestURL);
			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			//HttpResponse response = httpclient.execute(httpget, responseHandler);
			
            responseBody = httpclient.execute(httpget, responseHandler);
			
			
			//response.getEntity().toString();
			Log.i("HTTPRequest", responseBody);
			
		}
		
		return responseBody;
	}
	
}
