package app;


import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import android.os.AsyncTask;
import android.util.Log;

public class JSONParser extends AsyncTask<String, Void, JSONObject> {
	static InputStream is=null;
	static JSONObject jobj=null;
	static String json="";	
	
	public JSONParser(){	}		
	
	@Override
	protected JSONObject doInBackground(String... stringarray) {
		String url=stringarray[0];
		String identityNumber=stringarray[1];
				
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("idNumber",identityNumber));
		try{
		
			
		DefaultHttpClient httpClient=new DefaultHttpClient();
		String pat=URLEncodedUtils.format(params,"utf-8");
		url +=pat; 
		HttpGet httpget= new HttpGet(url);
		//httpget.setHeader("Accept", "JSON");
		httpget.setHeader("Content-Type","application/json");
		System.out.println(url);
		HttpResponse httpResponse=httpClient.execute(httpget);
		System.out.println(  "what suusuusussuccess gott");
		HttpEntity httpEntity=httpResponse.getEntity();
		
		is=httpEntity.getContent();
		
		Log.e("bufferError","frankkkkk in passing data"+is.toString());
		
		BufferedReader reader=new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);//
		StringBuilder sb=new StringBuilder();
		String line=null;
		//Log.e("bufferError","Error inn passing data"+line);
		while((line=reader.readLine())!=null){
			sb.append(line+"\n");
			
		}
		is.close();
		json=sb.toString(); 
		jobj =new JSONObject(json);
	
	}catch(Exception e){
		e.getMessage();
	}
		
		return jobj;
	}

	
}
