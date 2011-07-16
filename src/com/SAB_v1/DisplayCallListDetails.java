package com.SAB_v1;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Set;

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

public class DisplayCallListDetails extends ListActivity implements OnItemClickListener
{	
	TelephonyManager tm;
	DataBaseHelper data;
	String n,n1,m1,ans,name,sdate,oname,on;
	Button call,cancel,back;
	TextView town,tname,pr,date,time;
	int id1,conid,cnt=0,icnt=0;
	private ArrayList<String> results = new ArrayList<String>();
	public void onCreate(Bundle savedInstanceState)
	{
		requestWindowFeature(Window.FEATURE_NO_TITLE);  
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
		data=new DataBaseHelper(this);
		TextView tView = new TextView(this);
		Calendar cal = new GregorianCalendar();
		int month = cal.get(Calendar.MONTH);
		int year = cal.get(Calendar.YEAR);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		sdate=(+ (month + 1) + "-" + day + "-" + year); 
		Bundle bundle = getIntent().getExtras(); 
		String name1=bundle.getString("name");
		tView.setText(name1);
		getListView().addHeaderView(tView);
		System.out.println("Name1 "+name1); 
		getListView().setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		setListAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, results));
		getListView().setTextFilterEnabled(true);
		getListView().setOnItemClickListener(this);

		Cursor c1=data.getDataCall();
		while (c1.moveToNext())
		{	
			id1=c1.getInt(0);
			System.out.println("IDs "+id1);
		}

		Cursor c=data.getDataCallList(id1);
		System.out.println("ID1 "+id1);
		while (c.moveToNext())
		{	

			n=c.getString(0);
			n1=c.getString(1);
			results.add(n+"  "+n1);
			System.out.println("FName"+n);
			System.out.println("LName"+n1);
			oname=n+n1;
		}
		/*Cursor c2=data.gettask(oname,sdate);
		while (c2.moveToNext())
		{	
			on=c.getString(0);
		}
		 Intent i= new Intent(this,Calldetails.class);
		 Bundle bun=new Bundle();
		 bun.putString("name1",ans);
		 System.out.println("Name "+name);
		 i.putExtras(bun);
		 startActivity(i);*/
	}
	public void onItemClick(AdapterView<?> a, View v, int position, long id) 
	{
		
			
		System.out.println("Position..."+position);
		ans= (String) a.getItemAtPosition(position);
		System.out.println("Value is "+ans);
		System.out.println("ID1 "+id1);
		/*setContentView(R.layout.ddetails);
		call=(Button)findViewById(R.id.call);
		cancel=(Button)findViewById(R.id.cancel);
		town=(TextView)findViewById(R.id.town);
		tname=(TextView)findViewById(R.id.tname);
		pr=(TextView)findViewById(R.id.tpriority);
		date=(TextView)findViewById(R.id.due);
		time=(TextView)findViewById(R.id.time);
		 */
		Cursor c3=data.getDataCallList1(id1,n);
		
		System.out.println("Function "+n);
		System.out.println("ID2 "+id1);
		System.out.println("FN: "+n);
		while(c3.moveToNext())
		{
			m1=c3.getString(0);
			System.out.println("My Mob1 "+m1);
		}
		Cursor c4=data.getconid(n,m1);
		while(c4.moveToNext())
		{
			conid=c4.getInt(0);
			System.out.println("conid=="+conid);
		}
		Cursor c6= data.getcnt(conid);
		while(c6.moveToNext())
		{
			cnt=c6.getInt(0);
		}
		if(cnt==0)	
		{	cnt=cnt+1;
			System.out.println("cnt=="+cnt);
			data.insertccnt(conid,cnt);
		}
		else
		{
			data.updatecnt(conid,cnt);
		}
		Intent intent = new Intent(Intent.ACTION_CALL);
		intent.setData(Uri.parse("tel:" +m1));
		startActivity(intent);
		tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
		tm.listen(mPhoneListener, PhoneStateListener.LISTEN_CALL_STATE);
	
			
		



		/*	
		call.setOnClickListener(new OnClickListener() {

			public void onClick(View v)
			{ 


			}

		});

		 cancel.setOnClickListener(new OnClickListener() {

			    public void onClick(View v)
			    {
			    	finish();
			      }

			    });*/
	}

	private PhoneStateListener mPhoneListener = new PhoneStateListener() 
	{
		public void onCallStateChanged(int state, String incomingNumber) 
		{
			try 
			{
				switch (state) 
				{
				case TelephonyManager.CALL_STATE_RINGING:
					Toast.makeText(DisplayCallListDetails.this, "CALL_STATE_RINGING", Toast.LENGTH_SHORT).show();
					break;
				case TelephonyManager.CALL_STATE_OFFHOOK:
					Toast.makeText(DisplayCallListDetails.this, "CALL_STATE_OFFHOOK", Toast.LENGTH_SHORT).show();
					break;
				case TelephonyManager.CALL_STATE_IDLE:
					Toast.makeText(DisplayCallListDetails.this, "CALL_STATE_IDLE", Toast.LENGTH_SHORT).show();
					Intent i = new Intent(DisplayCallListDetails.this,UpdateStatus.class);
					Bundle bun=new Bundle();
					bun.putString("name",ans);
					System.out.println("Name "+name);
					i.putExtras(bun);
					startActivity(i);
					//onRestart(); 
					break;
				default:
					Toast.makeText(DisplayCallListDetails.this, "default", Toast.LENGTH_SHORT).show();
					Log.i("Default", "Unknown phone state=" + state);
				}
			} catch (Exception e) 
			{
				Log.i("Exception", "PhoneStateListener() e = " + e);
			}
		}
	};

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
		break; */
		case R.id.mcall: Intent i4 = new Intent(this, CallList.class);
		startActivity(i4);
		break;

		}
		return true;
	}    

}



