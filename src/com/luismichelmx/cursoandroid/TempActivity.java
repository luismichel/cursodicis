package com.luismichelmx.cursoandroid;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TempActivity extends Activity {
	
	private EditText et_celsius;
	private EditText et_far;
	private Button btn_calc;
	private String appTag = "curso android";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_temp);
		
		Log.i(appTag, "Entro a cálculo de temperatura");
		
		
		et_celsius = (EditText)findViewById(R.id.et_celsius);
		et_far = (EditText)findViewById(R.id.et_faren);
		btn_calc = (Button) findViewById(R.id.btn_calc);
		
		et_celsius.setClickable(false);
		
		btn_calc.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Log.i(appTag, "click en boton calcular");
				
				float gradosFarenheit;
				float gradosCelsius;
				
				Log.i(appTag, et_far.getText().toString()+" editText far" );
				try{
					gradosFarenheit = Float.parseFloat( et_far.getText().toString());
					gradosCelsius = (float)((gradosFarenheit - 32)*(5.0/9.0));
					
					et_celsius.setText(Float.toString(gradosCelsius));
				}catch(Exception e){
					Toast msg = Toast.makeText(getApplicationContext(), "Debes de meter un número", Toast.LENGTH_SHORT);
					msg.show();
					Log.e(appTag, "Error: "+e.getMessage());
				}
			}
			
		});
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_temp, menu);
		return true;
	}

}

