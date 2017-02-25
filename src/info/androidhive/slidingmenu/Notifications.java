package info.androidhive.slidingmenu;

import java.util.ArrayList;



import info.androidhive.slidingmenu.*;

import android.os.Bundle;
import android.app.Activity;
import android.app.Fragment;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Notifications extends Fragment{
Notificationdb ndb;
ArrayAdapter<String> ad;
	ListView lv;
	String notice="";
	ArrayList<String> al;
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.activity_notifications, container, false);
        lv=(ListView)rootView.findViewById(R.id.listView1);
		ndb=new Notificationdb(getActivity());
		
		getMessage();
		ad=new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,al);
		lv.setAdapter(ad); 
		
        return rootView;
    }
	public void getMessage()
	{
		al=new ArrayList<String>();
		al.clear();
		ndb=new Notificationdb(getActivity());
		ndb.open();
		Cursor c=ndb.getMessage();
		
		if(c.moveToFirst())
        {
        	do
        	{
        		
        	//Toast.makeText(getActivity, ""+c.getString(0)+c.getString(1),Toast.LENGTH_LONG).show();
        	notice=notice+c.getString(0)+" "+c.getString(1)+",";
        	al.add(c.getString(1));
        	}while(c.moveToNext());
        	
        }
		
		ndb.close();
	}
}
