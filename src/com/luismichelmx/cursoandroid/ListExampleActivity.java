package com.luismichelmx.cursoandroid;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;

public class ListExampleActivity extends Activity {

	private ArrayList<Locations> locations = new ArrayList<Locations>();
	private ListView lv;
	private ArrayAdapter<Locations> locationsAdapter;
	private String appTag = "curso android";
	private Activity listActivity;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);
		listActivity = this;
		
		lv = (ListView) findViewById(R.id.listView1);
		
		
		
		
		
		/*locations.add( new Locations( android.R.drawable.ic_btn_speak_now, "Palo Blanco", "Salamanca") );
		locations.add( new Locations( android.R.drawable.ic_btn_speak_now, "Las Reinas", "Salamanca") );
		locations.add( new Locations( android.R.drawable.ic_btn_speak_now, "Centro", "Salamanca") );
		locations.add( new Locations( android.R.drawable.ic_btn_speak_now, "Infonavit 1", "Salamanca") );
		locations.add( new Locations( android.R.drawable.ic_btn_speak_now, "Bellavista", "Salamanca") );
		locations.add( new Locations( android.R.drawable.ic_btn_speak_now, "Humanista", "Salamanca") );*/
		
		new BackTask().execute("hola");
		
		
		
		lv.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Log.i(appTag, "Click en posicion "+arg2+" dato: "+locations.get(arg2).getLocation());
			}
			
		});
		
		
		

		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_list, menu);
		
		return true;
	}
	
	
	private class BackTask extends AsyncTask<String, Integer, String>{

		private ProgressBar progressBar;
		
		@Override
		protected void onPreExecute(){
			super.onPreExecute();
			
			progressBar = (ProgressBar)findViewById(R.id.progressBar1);
			
		}
		
		@Override
		protected String doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			publishProgress(10);
			getData();
			publishProgress(90);
			return null;
		}
		
		@Override
		protected void onProgressUpdate(Integer... values){
			super.onProgressUpdate(values);
			
			progressBar.setProgress(values[0]);
		}
		
		@Override
		protected void onPostExecute(String result){
			super.onPostExecute(result);
			publishProgress(100);
			progressBar.setVisibility(View.GONE);
			locationsAdapter = new LocationsAdapter( listActivity, R.layout.list_item, locations);
			
			lv.setAdapter(locationsAdapter);
		}
		
		
		private void getData()
		{
			
			String res = null;
			publishProgress(20);
			HTTPRequest hr = new HTTPRequest("http://api.geonames.org/findNearbyPostalCodesJSON?postalcode=36700&country=MX&radius=3&username=cursoandroiddicis");
			
			
				try {
					res = hr.execute("get");
				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			if( res != null) Log.i(appTag, res);
			publishProgress(50);
			String ciudad = null;
			String colonia = null;
			
			try {
				JSONObject loc = new JSONObject(res);
				
				JSONArray jsonArray = loc.getJSONArray("postalCodes");
				JSONObject obj = null;
				
				for( int i=0; i < jsonArray.length(); i++)
				{
					obj = jsonArray.getJSONObject(i);
					
					ciudad = obj.getString("adminName2");
					colonia = obj.getString("placeName");
					
					Log.i(appTag, "ciudad = "+ciudad+" colonia = "+colonia);
					locations.add( new Locations( android.R.drawable.ic_btn_speak_now, colonia, ciudad) );
					publishProgress(50+i);
				}
				
				
				
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
		
	}
	
	
	

	
	
	
	
}
