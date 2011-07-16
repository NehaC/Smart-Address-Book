package com.SAB_v1;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import android.content.Intent;
import android.database.Cursor;
import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;
public class Task_Upcoming extends ListActivity implements OnItemClickListener
{	


	String desp,owner,taskname,sdate,stime;
	ListView list;
	Button ok,delete;
	DataBaseHelper data;
	private ArrayList<String> results = new ArrayList<String>();
	public void onCreate(Bundle icicle)
	{
		data=new DataBaseHelper(this);
		requestWindowFeature(Window.FEATURE_NO_TITLE);  
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(icicle);
		Calendar cal = new GregorianCalendar();
		int month = cal.get(Calendar.MONTH);
		int year = cal.get(Calendar.YEAR);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int minute = cal.get(Calendar.MINUTE);
		sdate=(+ year + "-" + (month+1)+ "-" + day);
		stime=(+(hour)+":"+minute);
		System.out.println("Sdate "+sdate);
		TextView tView = new TextView(this);
		tView.setText("Upcoming Task!!! ");
		getListView().addHeaderView(tView);
		getListView().setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		setListAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, results));
		getListView().setTextFilterEnabled(true);
		getListView().setOnItemClickListener(this);;
		Cursor c1=data.get_task_upcoming(sdate);
		while(c1.moveToNext())
		{
			taskname=c1.getString(0);
			System.out.println("Task Name1...."+taskname);
			results.add(taskname);
		}

		Cursor c2=data.get_task_todaydetails(taskname);
		while (c2.moveToNext())
		{	
			owner=c2.getString(0);
			desp=c2.getString(1);
			System.out.println("Owner::"+owner);
			System.out.println("Desp::"+desp);

		}

	}



	public void onItemClick(AdapterView<?> a, View v, int position, long id) 
	{

		System.out.println("Position..."+position);
		String ans= (String) a.getItemAtPosition(position);
		System.out.println("value isss"+ans);

		Intent i= new Intent(this,Task_UpcomingDetails.class);
		Bundle bun=new Bundle();
		bun.putString("taskname",ans);
		System.out.println("Name "+taskname);
		i.putExtras(bun);
		startActivity(i);
	}

}



