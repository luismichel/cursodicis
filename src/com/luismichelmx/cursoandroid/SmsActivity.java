package com.luismichelmx.cursoandroid;

import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SmsActivity extends Activity {
	
	private EditText edit_phone;
	private EditText edit_text;
	private Button button_send;
	private String appTag = "curso android";
	private Context appContext;
	private Activity smsActivity;
	private static final int CONTACT_PICKER_RESULT = 123; 

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sms);
		
		appContext = this.getApplicationContext();
		smsActivity = this;
		
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
				
				if( num.length() != 10 )
				{
					Toast.makeText(appContext, "Número de teléfono tiene que ser de 10 dígitos", Toast.LENGTH_LONG).show();
					
				}
				else
				{
					sendSMS(num, msg);
				}
				
				//finish() termina la activity en la que fue llamada
				finish();
			}
			
		});
		
		
		edit_phone.setOnLongClickListener( new OnLongClickListener(){

			@Override
			public boolean onLongClick(View arg0) {
				// TODO Auto-generated method stub
				Log.i(appTag, "Long click en telefono");
				
				Intent contactPickerIntent = new Intent( Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
				startActivityForResult( contactPickerIntent, CONTACT_PICKER_RESULT);
				
				
				Log.i(appTag, ContactsContract.Contacts.CONTENT_URI.toString());
				
				
				 
				return false;
			}
			
		});
	
		
		
		
	}
	
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		
		switch( item.getItemId() ){
		
			case R.id.menu_share:
				Log.i(appTag, "click en menu->share");
				
				
				Intent sendIntent = new Intent();
				sendIntent.setAction(Intent.ACTION_SEND);
				sendIntent.putExtra(Intent.EXTRA_TEXT, edit_text.getText().toString());
				sendIntent.setType("text/plain");
				startActivity(sendIntent);
				
				
				
				return true;
			case R.id.menu_settings:
				Log.i(appTag, "click menu->settings");
				return true;
			default:
				return super.onOptionsItemSelected(item);
		
		}
		

		
	}
	
	@Override
	public void onActivityResult(int reqCode, int resultCode, Intent data){
		super.onActivityResult(reqCode, resultCode, data);
		
		if( resultCode == Activity.RESULT_OK){
			
			Uri contactData = data.getData();
			
			Log.i(appTag, "URI ="+contactData.toString());
			
			Cursor cursor = getContentResolver().query(contactData, null, null, null, null);
			
			while( cursor.moveToNext() )
			{
				String contactId = cursor.getString( cursor.getColumnIndex(ContactsContract.Contacts._ID));
				
			} 
			
		}
		
	}
	
	//Función para mandar un mensaje SMS
	private void sendSMS(String phoneNumber, String message)
	{

		String SENT = "SMS_SENT";
		
		PendingIntent sentPI = PendingIntent.getBroadcast(this, 0, new Intent(SENT), 0);
		
		this.registerReceiver(new BroadcastReceiver(){

			@Override
			public void onReceive(Context arg0, Intent arg1) {
				// TODO Auto-generated method stub
				
				NotificationManager notifManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
				int icon = R.drawable.ic_stat_sms;
				String ticker;
				String contentTitle;
				String contentText;
				long when = System.currentTimeMillis();
				Notification notification;
				
				switch( getResultCode() )
				{
				case Activity.RESULT_OK:
					Toast.makeText(appContext, "Se envió correctamente el mensaje", Toast.LENGTH_LONG).show();
					
					contentTitle = "Curso Android";
					contentText = "Se envió correctamente el mensaje";
					ticker = "SMS: Éxito";
					
					//Usamos las funciones absoletas para compatiblidad con versiones menores a 3.0 de Android
					notification = new Notification(icon,ticker,when);
					
					Intent notificationIntent = new Intent(smsActivity, MainActivity.class);
					PendingIntent contentIntent = PendingIntent.getActivity(smsActivity, 0, notificationIntent, 0);
					notification.setLatestEventInfo(appContext, contentTitle, contentText, contentIntent);
					
					notifManager.notify(1, notification);
					break;
					
				//Si quieren probar estos casos, intenten mandar el mensaje a un número que no existe, o pongan el modo avión (Esto si tienen un dispositivo físico)
				case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
					Toast.makeText(appContext, "Error: falla genérica", Toast.LENGTH_LONG).show();
					break;
				case SmsManager.RESULT_ERROR_NO_SERVICE:
				Toast.makeText(appContext, "Error: No hay servicio", Toast.LENGTH_LONG).show();
					break;
				case SmsManager.RESULT_ERROR_NULL_PDU:
					Toast.makeText(appContext, "Error: null pdu", Toast.LENGTH_LONG).show();
					break;
				case SmsManager.RESULT_ERROR_RADIO_OFF:
					Toast.makeText(appContext, "Error: Radio apagado", Toast.LENGTH_LONG).show();
					break;
				}
			}
			
		}, new IntentFilter(SENT));
			
		
		SmsManager sms = SmsManager.getDefault();
		sms.sendTextMessage(phoneNumber, null, message, sentPI, null);
		
			
		
	} 
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_sms, menu);
		return true;
	}
	
	
	
	
	
	
	
	

}
