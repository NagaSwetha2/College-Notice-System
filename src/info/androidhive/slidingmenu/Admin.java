
package info.androidhive.slidingmenu;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import libraries.UserFunctions;



import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

@SuppressLint("NewApi")
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class Admin extends Fragment {
	private static String KEY_SUCCESS = "success";
    private static String KEY_ERROR = "error";
    private static String KEY_ERROR_MSG = "error_msg";
    Spinner branch;
	String brnch,notice="POST COLLEGE NOTICE";
    ArrayList<RowItem> al;
    ArrayList<String> al1;
    ArrayList<String> regid;
		UserFunctions uf;
		EditText message;
		ArrayAdapter<String> ad;
		Gcmusersadapter gd;
		RowItem ri;
		Button send;ListView lv;
	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	@SuppressLint("NewApi")
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.activity_admin, container, false);
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
	  	  .detectDiskReads().detectDiskWrites().detectNetwork() // StrictMode is most commonly used to catch accidental disk or network access on the application's main thread
	  	  .penaltyLog().build());
		uf=new UserFunctions();
		branch=(Spinner)rootView.findViewById(R.id.spinner1);
		al1=new ArrayList<String>();
		al1.add(AdminMain.name);
		ArrayAdapter<String> ad=new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item,al1);
		ad.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
		branch.setAdapter(ad);
		brnch=AdminMain.name;
		Toast.makeText(getActivity(), "Branch:"+brnch, Toast.LENGTH_LONG);
		branch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				brnch=arg0.getItemAtPosition(arg2).toString();
				al.clear();
				JSONObject json = uf.getallusers(brnch);
				//Toast.makeText(getActivity(),""+json, Toast.LENGTH_LONG).show();
		      Log.v("anu",""+json);
		      ri=new RowItem("Select all", "", "");
		      al.add(ri);
		      // check for login response
		      try {
		          if (json.getString(KEY_SUCCESS) != null) {
		             
		              String res = json.getString(KEY_SUCCESS); 
		              if(Integer.parseInt(res) == 1){
		                  
		                //  DatabaseHandler db = new DatabaseHandler(getActivity());
		                 // JSONObject json_user = json.getJSONObject("user");
		                //  Toast.makeText(getActivity(),""+res, Toast.LENGTH_LONG).show();
									JSONArray jaaray=json.getJSONArray("users");
									String[] s=new String[jaaray.length()];
									 for(int i=0;i<jaaray.length();i++){
					                     JSONObject json_data = jaaray.getJSONObject(i);
					                   //  Toast.makeText(getActivity(),""+json_data.getString("name")+json_data.getString("email")+json_data.getString("gcm_regid"), Toast.LENGTH_LONG).show();
					                     
					                     ri=new RowItem(json_data.getString("name"), json_data.getString("email"), json_data.getString("gcm_regid"));
					                    al.add(ri);
					                    

									 }
		                  
		              }else{
		                  // Error in registration
		              	Toast.makeText(getActivity(),"Error in registration", Toast.LENGTH_LONG).show();
		              }
		          }
		      } catch (JSONException e) {
		          e.printStackTrace();
		      } 
		    gd=new Gcmusersadapter(getActivity(), R.layout.users,al);
			lv.setAdapter(gd);
			lv.setFocusable(false);
			lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
						long arg3) {
					// TODO Auto-generated method stub

				}
			});
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		al=new ArrayList<RowItem>();
		message=(EditText)rootView.findViewById(R.id.editText1);
		lv=(ListView)rootView.findViewById(R.id.listView1);
		send=(Button)rootView.findViewById(R.id.button1);
		
		send.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				regid=new ArrayList<String>();
				regid=gd.getregid();
				
				String msg=message.getText().toString();
			
			for(int i=0;i<gd.getregid().size();i++)
			{
				JSONObject json12=uf.senMessage(regid.get(i), msg);
				
			}
			
			}
		});
		
			JSONObject json = uf.getallusers(brnch);
			//Toast.makeText(getActivity(),""+json, Toast.LENGTH_LONG).show();
			al.clear();
	      Log.v("anu",""+json);
	      ri=new RowItem("Select all", "", "");
	      al.add(ri);
	      // check for login response
	      try {
	          if (json.getString(KEY_SUCCESS) != null) {
	             
	              String res = json.getString(KEY_SUCCESS); 
	              if(Integer.parseInt(res) == 1){
	                  
	                //  DatabaseHandler db = new DatabaseHandler(getActivity());
	                 // JSONObject json_user = json.getJSONObject("user");
	                //  Toast.makeText(getActivity(),""+res, Toast.LENGTH_LONG).show();
								JSONArray jaaray=json.getJSONArray("users");
								String[] s=new String[jaaray.length()];
								 for(int i=0;i<jaaray.length();i++){
				                     JSONObject json_data = jaaray.getJSONObject(i);
				                   //  Toast.makeText(getActivity(),""+json_data.getString("name")+json_data.getString("email")+json_data.getString("gcm_regid"), Toast.LENGTH_LONG).show();
				                     
				                     ri=new RowItem(json_data.getString("name"), json_data.getString("email"), json_data.getString("gcm_regid"));
				                    al.add(ri);
				                    

								 }
	                  
	              }else{
	                  // Error in registration
	              	Toast.makeText(getActivity(),"Error in registration", Toast.LENGTH_LONG).show();
	              }
	          }
	      } catch (JSONException e) {
	          e.printStackTrace();
	      } 
	    gd=new Gcmusersadapter(getActivity(), R.layout.users,al);
		lv.setAdapter(gd);
		lv.setFocusable(false);
		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub

			}
		});

		
        return rootView;
    }

}
