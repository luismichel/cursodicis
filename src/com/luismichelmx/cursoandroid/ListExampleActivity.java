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

	private ArrayList<String> months = new ArrayList<String>();
	private ListView lv;
	private ArrayAdapter<String> monthAdapter;
	private String appTag = "curso android";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);
		
		lv = (ListView) findViewById(R.id.listView1);
		
		months.add("enero");
		months.add("febrero");
		months.add("marzo");
		months.add("abril");
		months.add("mayo");
		months.add("junio");
		months.add("julio");
		months.add("agosto");
		months.add("septiembre");
		months.add("octubre");
		months.add("noviembre");
		months.add("diciembre");
		
		monthAdapter = new ArrayAdapter<String>( this, android.R.layout.simple_list_item_1, months);
		
		lv.setAdapter(monthAdapter);
		
		lv.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Log.i(appTag, "Click en posicion "+arg2+" dato: "+months.get(arg2));
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
