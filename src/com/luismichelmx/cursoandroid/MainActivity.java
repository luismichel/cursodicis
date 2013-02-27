package com.luismichelmx.cursoandroid;


import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;

import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {

	private String appTag = "curso Android";
	private Button btn_temp; 
	private Button btn_sms;
	private ImageView im_logo;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Log.i(appTag, "onCreate");
		
		btn_temp = (Button) this.findViewById(R.id.btn_temp);
		btn_sms = (Button)findViewById(R.id.btn_sms);
		im_logo = (ImageView)findViewById(R.id.im_logo);
		
		btn_temp.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.e(appTag, "warning: no le des muchos clicks");
				
				Intent temp_int = new Intent(v.getContext(), TempActivity.class);
				startActivity(temp_int);
				
			}
			
		});
		
		btn_sms.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.e(appTag, "click en sms");
				
				Intent sms_int = new Intent(v.getContext(), SmsActivity.class);
				startActivity(sms_int);
				
			}
			
		});
		
		
		im_logo.setOnLongClickListener(new OnLongClickListener(){

			@Override
			public boolean onLongClick(View arg0) {
				// TODO Auto-generated method stub
				Log.i(appTag, "long click!!");
				
				Intent shareIntent = new Intent();
				shareIntent.setAction(Intent.ACTION_SEND);
				
				
				Uri uri = Uri.parse("android.resource://com.luismichelmx.cursoandroid/drawable/logodicis");
				
				shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
				shareIntent.setType("image/jpeg");
				
				//startActivity(shareIntent);
				startActivity( Intent.createChooser(shareIntent, "Enviar a:") );
				
				
				
				return false;
			}
			
		});
		
		
		
	}
	
	@Override
	public void onPause()
	{
		super.onPause();
		Log.i(appTag, "onPause");
	}
	
	@Override
	public void onStart()
	{
		super.onStart();
		Log.i(appTag, "onStart");
	}
	
	@Override
	public void onStop()
	{
		super.onStop();
		Log.i(appTag, "onStop");
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
