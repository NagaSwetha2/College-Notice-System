package info.androidhive.slidingmenu;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Adminlogin extends Activity {
	EditText txtName,txtEmail;
	String name,email;
	Button login;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.admin_login);
		txtName = (EditText) findViewById(R.id.UserID);
		txtEmail = (EditText) findViewById(R.id.txtPassword);
		login=(Button)findViewById(R.id.button1);
		login.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View arg0) {
				if(txtName.getText().toString().equals("admin") && txtEmail.getText().toString().equals("admin")){
					Intent i=new  Intent(getApplicationContext(),AdminMain.class);
					i.putExtra("priority", "");
					startActivity(i);					
				}else if(txtName.getText().toString().equals("cse") && txtEmail.getText().toString().equals("cse")){
					Intent i=new  Intent(getApplicationContext(),AdminMain.class);
					i.putExtra("priority", "CSE");
					startActivity(i);					
				}else if(txtName.getText().toString().equals("ece") && txtEmail.getText().toString().equals("ece")){
					Intent i=new  Intent(getApplicationContext(),AdminMain.class);
					i.putExtra("priority", "ECE");
					startActivity(i);					
				}else if(txtName.getText().toString().equals("eee") && txtEmail.getText().toString().equals("eee")){
					Intent i=new  Intent(getApplicationContext(),AdminMain.class);
					i.putExtra("priority", "EEE");
					startActivity(i);					
				}
			}
		});
	}

}
