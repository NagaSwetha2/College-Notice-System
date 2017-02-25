package info.androidhive.slidingmenu;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class Home extends Activity {
Button reg,login,admin;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		reg=(Button)findViewById(R.id.button1);
		  login=(Button)findViewById(R.id.button2);
		  admin=(Button)findViewById(R.id.button3);
		  reg.setOnClickListener(new View.OnClickListener() {
		   
		   @Override
		   public void onClick(View arg0) {
		    // TODO Auto-generated method stub
		    Intent i=new Intent(getApplicationContext(),Loginn.class);
		    startActivity(i);
		   }
		  });
		  login.setOnClickListener(new View.OnClickListener() {
		   
		   @Override
		   public void onClick(View v) {
		    // TODO Auto-generated method stub
		    Intent i1=new Intent(getApplicationContext(),MainActivity.class);
		    startActivity(i1);
		   }
		  });
		  admin.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i1=new Intent(getApplicationContext(),Adminlogin.class);
			    startActivity(i1);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_home, menu);
		return true;
	}

}
