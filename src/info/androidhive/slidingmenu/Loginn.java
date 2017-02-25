package info.androidhive.slidingmenu;



import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
@SuppressLint("NewApi")
public class Loginn extends Activity {
	
	// UI elements
	EditText txtName; 
	EditText txtEmail;
Spinner branch;
String brnch;
ArrayList<String> al;
	// Register button
	Button btnRegister;
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loginn);
		   getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#000000"))); 
		//Get Global Controller Class object (see application tag in AndroidManifest.xml)
		final Controller aController = (Controller)getApplicationContext();
		branch=(Spinner)findViewById(R.id.spinner1);
		al=new ArrayList<String>();
		al.add("CSE");
		al.add("ECE");
		al.add("EEE");
		// Check if Internet Connection present
		if (!aController.isConnectingToInternet()) {
			
			// Internet Connection is not present
			aController.showAlertDialog(Loginn.this,
					"Internet Connection Error",
					"Please connect to working Internet connection", false);
			
			// stop executing code by return
			return;
		}

		// Check if GCM configuration is set
		if (Config.YOUR_SERVER_URL == null || Config.GOOGLE_SENDER_ID == null || Config.YOUR_SERVER_URL.length() == 0
				|| Config.GOOGLE_SENDER_ID.length() == 0) {
			
			// GCM sernder id / server url is missing
			aController.showAlertDialog(Loginn.this, "Configuration Error!",
					"Please set your Server URL and GCM Sender ID", false);
			
			// stop executing code by return
			 return;
		}
		ArrayAdapter<String> ad=new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item,al);
		ad.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
		branch.setPrompt("select the state");
	    branch.setBackgroundResource(R.drawable.bg);
		branch.setAdapter(ad);

		branch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				brnch=arg0.getItemAtPosition(arg2).toString();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		txtName = (EditText) findViewById(R.id.txtName);
		txtEmail = (EditText) findViewById(R.id.txtEmail);
		btnRegister = (Button) findViewById(R.id.btnRegister);
		
		
		
		// Click event on Register button
		btnRegister.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {  
				// Get data from EditText 
				String name = txtName.getText().toString(); 
				String email = txtEmail.getText().toString();
				Toast.makeText(getApplicationContext(), ""+brnch,Toast.LENGTH_LONG).show();
				// Check if user filled the form
				if(name.trim().length() > 0 && email.trim().length() > 0){
					
					// Launch Main Activity
					Intent i = new Intent(getApplicationContext(), Noti.class);
					
					// Registering user on our server					
					// Sending registraiton details to MainActivity
					i.putExtra("name", name);
					i.putExtra("email", email);
					i.putExtra("branch", brnch);
					startActivity(i);
					finish();
					
				}else{
					
					// user doen't filled that data
					aController.showAlertDialog(Loginn.this, "Registration Error!", "Please enter your details", false);
				}
			}
		});
	}

}
