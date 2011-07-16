package com.SAB_v1;

import java.util.ArrayList;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class DeleteCallList extends Activity implements OnItemClickListener
{
	ListView lv1;
	private ArrayList<String> results = new ArrayList<String>();
	DataBaseHelper data;
	String s;
	int id1,id2;
	int callid;
	String name;
	Cursor c;
	public void onCreate(Bundle savedInstanceState)
	{
		requestWindowFeature(Window.FEATURE_NO_TITLE);  
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.deletecalllist);
		data=new DataBaseHelper(this);
		lv1=(ListView)findViewById(R.id.List);
		c=data.getCallListName();
		while (c.moveToNext())
		{	
			name=c.getString(0);
			System.out.println("ListName "+name); 
			results.add(name);
			}
		Cursor c1=data.getCallListid1(name);
		while (c1.moveToNext())
		{	
			id2=c1.getInt(0);
			System.out.println("Call List ID "+id2);
		}
		CheckBox cb=new CheckBox(this); 
		lv1.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		lv1.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_multiple_choice,results));
		lv1.setTextFilterEnabled(true);
		lv1.setOnItemClickListener(this);
		
	}
	public void onItemClick(AdapterView<?> a, View v, int position, long id) 
	{
		System.out.println("Position..."+position);
		String ans= (String) a.getItemAtPosition(position);
		System.out.println("Value is "+ans);
		lv1.clearChoices();
		data.deleteCallListName(id2);
		results.remove(position);
		Toast.makeText(DeleteCallList.this, "CallList Deleted Successfully",Toast.LENGTH_SHORT).show();
	}

	
}
