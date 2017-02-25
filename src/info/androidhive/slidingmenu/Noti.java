package info.androidhive.slidingmenu;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gcm.GCMRegistrar;


@TargetApi(Build.VERSION_CODES.HONEYCOMB)
@SuppressLint("NewApi")
public class Noti extends Activity {
	// label to display gcm messages
	TextView lblMessage;
	Controller aController;
	Notificationdb ndb;
	// Asyntask
	AsyncTask<Void, Void, Void> mRegisterTask;
	public static String name;
	public static String email,branch;;
	ListView lv;
	String notice="";
	ArrayList<String> al;
	@Override
	public void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_noti);
		 getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#000000"))); 
		lv=(ListView)findViewById(R.id.listView1);
		ndb=new Notificationdb(getApplicationContext());
		getMessage();
		
		ArrayAdapter<String> ad=new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,al);
		lv.setAdapter(ad); 
		//Get Global Controller Class object (see application tag in AndroidManifest.xml)
		aController = (Controller) getApplicationContext();
		
		
		// Check if Internet present
		if (!aController.isConnectingToInternet()) {
			
			// Internet Connection is not present
			aController.showAlertDialog(Noti.this,
					"Internet Connection Error",
					"Please connect to Internet connection", false);
			// stop executing code by return
			return;
		}
		
		// Getting name, email from intent
		Intent i = getIntent();
		
		name = i.getStringExtra("name");
		email = i.getStringExtra("email");		
		branch=i.getStringExtra("branch");
		// Make sure the device has the proper dependencies.
		GCMRegistrar.checkDevice(this);

		// Make sure the manifest permissions was properly set 
		GCMRegistrar.checkManifest(this);

		lblMessage = (TextView) findViewById(R.id.lblMessage);
		
		// Register custom Broadcast receiver to show messages on activity
		registerReceiver(mHandleMessageReceiver, new IntentFilter(
				Config.DISPLAY_MESSAGE_ACTION));
		
		// Get GCM registration id
		final String regId = GCMRegistrar.getRegistrationId(this);

		// Check if regid already presents
		if (regId.equals("")) {
			
			// Register with GCM			
			GCMRegistrar.register(this, Config.GOOGLE_SENDER_ID);
			
		} else {
			
			// Device is already registered on GCM Server
			if (GCMRegistrar.isRegisteredOnServer(this)) {
				
				// Skips registration.				
				Toast.makeText(getApplicationContext(), "Already registered with GCM Server", Toast.LENGTH_LONG).show();
			
			} else {
				
				// Try to register again, but not in the UI thread.
				// It's also necessary to cancel the thread onDestroy(),
				// hence the use of AsyncTask instead of a raw thread.
				
				final Context context = this;
				mRegisterTask = new AsyncTask<Void, Void, Void>() {

					@Override
					protected Void doInBackground(Void... params) {
						
						// Register on our server
						// On server creates a new user
						aController.register(context, name, email, regId,branch);
						
						return null;
					}

					@Override
					protected void onPostExecute(Void result) {
						mRegisterTask = null;
					}

				};
				
				// execute AsyncTask
				mRegisterTask.execute(null, null, null);
			}
		}
	}		
	public void getMessage()
	{
		al=new ArrayList<String>();
		al.clear();
		ndb=new Notificationdb(this);
		ndb.open();
		Cursor c=ndb.getMessage();
		
		if(c.moveToFirst())
        {
        	do
        	{
        		
        	//Toast.makeText(getApplicationContext(), ""+c.getString(0)+c.getString(1),Toast.LENGTH_LONG).show();
        	notice=notice+c.getString(0)+" "+c.getString(1)+",";
        	al.add(c.getString(1));
        	}while(c.moveToNext());
        	
        }
		
		ndb.close();
	}
	// Create a broadcast receiver to get message and show on screen 
	private final BroadcastReceiver mHandleMessageReceiver = new BroadcastReceiver() {
		
		@Override
		public void onReceive(Context context, Intent intent) {
			
			String newMessage = intent.getExtras().getString(Config.EXTRA_MESSAGE);
			
			// Waking up mobile if it is sleeping
			aController.acquireWakeLock(getApplicationContext());
			
			// Display message on the screen
			lblMessage.append(newMessage + "\n");			
			
			Toast.makeText(getApplicationContext(), "Got Message: " + newMessage, Toast.LENGTH_LONG).show();
			
			// Releasing wake lock
			aController.releaseWakeLock();
		}
	};
	
	@Override
	protected void onDestroy() {
		// Cancel AsyncTask
		if (mRegisterTask != null) {
			mRegisterTask.cancel(true);
		}
		try {
			// Unregister Broadcast Receiver
			unregisterReceiver(mHandleMessageReceiver);
			
			//Clear internal resources.
			GCMRegistrar.onDestroy(getApplicationContext());
			
		} catch (Exception e) {
			Log.e("UnRegister Receiver Error", "> " + e.getMessage());
		}
		super.onDestroy();
	}

}
