package com.SAB_v1;


import java.util.ArrayList;
import android.app.ListActivity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class Task_UpcomingDetails extends ListActivity implements OnItemClickListener
{	
	TelephonyManager tm;
	DataBaseHelper data;
	String desp,owner,ans,taskname,sdate,oname,on,ddate,dtime;
	Button call,cancel,back;
	TextView town,tname,pr,date,time;
	int id1;
	private ArrayList<String> results = new ArrayList<String>();
	public void onCreate(Bundle savedInstanceState)
	{
		requestWindowFeature(Window.FEATURE_NO_TITLE);  
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
		data=new DataBaseHelper(this);
		TextView tView = new TextView(this);
		Bundle bundle = getIntent().getExtras(); 
		String taskname=bundle.getString("taskname");
		getListView().addHeaderView(tView);
		tView.setText("Upcoming Task Details!!! ");
		System.out.println("Name1 "+taskname); 
		getListView().setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		setListAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, results));
		getListView().setTextFilterEnabled(true);
		getListView().setOnItemClickListener(this);

		Cursor c1=data.get_task_upcomingDetails(taskname);
		while (c1.moveToNext())
		{	
			owner=c1.getString(0);
			desp=c1.getString(1);
			ddate=c1.getString(2);
			dtime=c1.getString(3);
			System.out.println("Owner::"+owner);
			System.out.println("Desp::"+desp);
			System.out.println("ddate"+ddate);
			System.out.println("time::"+dtime);
			results.add(owner+"\n"+desp+"\n"+ddate+","+dtime); 
		}
		
	}
	public void onItemClick(AdapterView<?> a, View v, int position, long id) 
	{

		System.out.println("Position..."+position);
		ans= (String) a.getItemAtPosition(position);
		System.out.println("Value is "+ans);
		
	}
		
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) 
	{
		switch (item.getItemId()) 
		{
		case R.id.msearch:Intent i = new Intent(this, Search.class);
		startActivity(i);
		break;
		case R.id.mallconct:Intent i6 = new Intent(this, AllContacts.class);
		startActivity(i6);
		break;
		case R.id.madd: Intent i1 = new Intent(this, AddContact.class);
		startActivity(i1);
		break;
		case R.id.medit: Intent i2 = new Intent(this, EditContact.class);
		startActivity(i2);
		break;
		/*case R.id.mimport: Intent i3 = new Intent(this, Import.class);
		startActivity(i3);
		break;*/ 
		case R.id.mcall: Intent i4 = new Intent(this, CallList.class);
		startActivity(i4);
		break;

		}
		return true;
	}    

}



