package com.luismichelmx.cursoandroid;

import android.os.Bundle;
import android.app.Activity;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class SmsActivity extends Activity {
	
	private EditText edit_phone;
	private EditText edit_text;
	private Button button_send;
	private String appTag = "curso android";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sms);
		
		edit_phone = (EditText)findViewById(R.id.et_phone);
		edit_text = (EditText)findViewById(R.id.et_text);
		button_send = (Button)findViewById(R.id.btn_send);
		
		button_send.setOnClickListener( new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Log.i(appTag, "click en enviar sms");
				
				String num = edit_phone.getText().toString();
				String msg = edit_text.getText().toString();
				
				SmsManager sms = SmsManager.getDefault();
				sms.sendTextMessage(num, null, msg, null, null);
				
			}
			
		});
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_sms, menu);
		return true;
	}

}
