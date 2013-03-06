package com.luismichelmx.cursoandroid;

import java.util.ArrayList;

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
import android.widget.SimpleAdapter;

public class ListExampleActivity extends Activity {

	private ArrayList<Locations> locations = new ArrayList<Locations>();
	private ListView lv;
	private ArrayAdapter<Locations> locationsAdapter;
	private String appTag = "curso android";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);
		
		lv = (ListView) findViewById(R.id.listView1);
		
		locations.add( new Locations( android.R.drawable.ic_btn_speak_now, "Palo Blanco", "Salamanca") );
		locations.add( new Locations( android.R.drawable.ic_btn_speak_now, "Las Reinas", "Salamanca") );
		locations.add( new Locations( android.R.drawable.ic_btn_speak_now, "Centro", "Salamanca") );
		locations.add( new Locations( android.R.drawable.ic_btn_speak_now, "Infonavit 1", "Salamanca") );
		locations.add( new Locations( android.R.drawable.ic_btn_speak_now, "Bellavista", "Salamanca") );
		locations.add( new Locations( android.R.drawable.ic_btn_speak_now, "Humanista", "Salamanca") );
		
		
		locationsAdapter = new LocationsAdapter( this, R.layout.list_item, locations);
		
		lv.setAdapter(locationsAdapter);
		
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

	
	
	
	
}
