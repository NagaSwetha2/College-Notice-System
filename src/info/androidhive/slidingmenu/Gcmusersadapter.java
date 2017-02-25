package info.androidhive.slidingmenu;


import java.util.ArrayList;
import java.util.List;

import libraries.UserFunctions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class Gcmusersadapter extends ArrayAdapter<RowItem> {
	private static String KEY_SUCCESS = "success";
    private static String KEY_ERROR = "error";
    private static String KEY_ERROR_MSG = "error_msg";
	String[] result;
	String party;SparseBooleanArray mCheckStates; 
	List<RowItem> objects;
	private int mSelectedVariation;
	UserFunctions uf;
	ArrayList<String> regids,rr;
	int votes;int i=0;
	 boolean[] checkBoxState;
	private CheckBox mCurrentlyCheckedRB;
	  public Gcmusersadapter(Context context, int resource,
			List<RowItem> objects) {
		
		super(context, resource, objects);
		  regids=new ArrayList<String>();
        this.context = context;
        this.objects=objects;
        mSelectedVariation=resource;
        checkBoxState=new boolean[objects.size()];
	}
	  
	Context context;
   
	  private boolean userSelected = false;
	  
	     /*private view holder class*/
	     private class ViewHolder {
	    	 
	         TextView name;
	         TextView mailid;
	         TextView regid;
	         CheckBox cb;
	         
	        
	     }
	
	    
		public View getView(final int position, View convertView, ViewGroup parent) {
	          ViewHolder holder = null;
	         RowItem rowItem = getItem(position);
	       
	        
	         LayoutInflater mInflater = (LayoutInflater) context
	                 .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
	         if (convertView == null) {
	             convertView = mInflater.inflate(R.layout.users, null);
	             holder = new ViewHolder();
	             holder.name= (TextView) convertView.findViewById(R.id.textView1);
	             holder.mailid = (TextView) convertView.findViewById(R.id.textView2);
	             holder.regid= (TextView) convertView.findViewById(R.id.textView3);
	             holder.cb= (CheckBox) convertView.findViewById(R.id.checkBox1);
	             convertView.setTag(holder);
	             
               
	             
	         } else
	         {

	             holder = (ViewHolder) convertView.getTag();
	         }
	         
		   
	         //Toast.makeText(getContext(),""+rowItem.getname(), Toast.LENGTH_LONG).show();

		         holder.name.setText(rowItem.getname()); 
		         holder.mailid.setText(rowItem.getmailid()); 
		         holder.regid.setText(rowItem.getregid()); 
		         regids.add(rowItem.getregid());
		         holder.cb.setChecked(checkBoxState[position]);
	             
		          
		           //for managing the state of the boolean
		           //array according to the state of the
		           //CheckBox
		           
		           holder.cb.setOnClickListener(new View.OnClickListener() {
		     
		   public void onClick(View v) {
			   
		    if(((CheckBox)v).isChecked())
		    {
		     checkBoxState[position]=true;
		     View containerView = (View)v.getParent();
             TextView textView2 = (TextView)containerView.findViewById(R.id.textView2);
             TextView textView3 = (TextView)containerView.findViewById(R.id.textView3);
             TextView textView1 = (TextView)containerView.findViewById(R.id.textView1);
            // Toast.makeText(getContext(), "hiii"+textView3.getText().toString(), Toast.LENGTH_LONG).show();
		    regids.add(textView3.getText().toString());
		    
		     if(position==0)
		     {
		    	
		    	 Toast.makeText(getContext(), ""+objects.get(0)+objects.get(1), Toast.LENGTH_LONG).show();
		    	
//							UserFunctions uf=new UserFunctions();
//							 JSONObject json = uf.getallusers();
//							 
//				regids.clear();
//					        try {
//					            if (json.getString(KEY_SUCCESS) != null) {
//					               
//					                String res = json.getString(KEY_SUCCESS); 
//					                if(Integer.parseInt(res) == 1){
//					                    
//					                 
//												JSONArray jaaray=json.getJSONArray("users");
//												
//												String[] s=new String[jaaray.length()];
//												 for(int i=0;i<jaaray.length();i++){
//								                     JSONObject json_data = jaaray.getJSONObject(i);
//								                  
//								                    regids.add(json_data.getString("gcm_regid"));
//												 
//								                    
//												 }
//												
//					                }else{
//					                    // Error in registration
//					                	Toast.makeText(getContext(),"Error in registration", Toast.LENGTH_LONG).show();
//					                }
//					            }
//					        } catch (JSONException e) {
//					            e.printStackTrace();
//					        } 
//							
					       
					        selectedAll();
	              
	           
		     }
		    
		    }
		    else
		    {
		     
		    if(position==0)
		     {
		    	 unselectedAll();
		     }
		    checkBoxState[position]=false;
		    }
		    }
		   });
		 
	         return convertView;
	         
	        

	        
		
	     }
	     
		
	 	@Override
		public RowItem getItem(int position) {
			// TODO Auto-generated method stub
			return super.getItem(position);
		}
	 	public void selectedAll() {
	 	    for(int i = 0; i< checkBoxState.length; i++){
	 	       checkBoxState[i] = true;
	 	    }
	 	       
	 	    
	 	    notifyDataSetChanged();
	 	}
	 	public void unselectedAll() {
	 	    for(int i = 0; i< checkBoxState.length; i++){
	 	       checkBoxState[i] = false;
	 	       regids.clear();
	 	    }
	 	    notifyDataSetChanged();
	 	}
		@Override
	 	public long getItemId(int position) {
	 		// TODO Auto-generated method stub
	 		return super.getItemId(position);
	 	}
		public ArrayList<String> getregid()
		{
			return regids;
		}
		
}
