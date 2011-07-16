package com.SAB_v1;


import java.util.ArrayList;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class CreateList extends Activity implements OnItemClickListener
{	
	int no,i;
	String n,m,m1;
	DataBaseHelper data;
	Button bback; 
	ListView lv1;
	int id1;
	String fname,lname;
	private ArrayList<String> results = new ArrayList<String>();
	public void onCreate(Bundle savedInstanceState)
	{
		requestWindowFeature(Window.FEATURE_NO_TITLE);  
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
		Bundle bundle = getIntent().getExtras(); 
		results  = bundle.getStringArrayList("results");
		setContentView(R.layout.createlist);
		data=new DataBaseHelper(this);
		lv1=(ListView)findViewById(R.id.List);
		 /* bback=(Button)findViewById(R.id.back);
	   	 bback.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View v) 
			{
				call();

			}
		});*/
       		
		
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
		String[] s=ans.split(" ");
		 fname =s[0];
		lname=s[1];
		System.out.println("Split Value is: "+fname);
		System.out.println("Split Value is: "+lname);
		Cursor c1=data.getDataCall();
		while(c1.moveToNext())
		{
			i=c1.getInt(0);
		}
		Cursor c2=data.getContactId(fname, lname);
		while(c2.moveToNext())
		{
			id1=c2.getInt(0);
			System.out.println("ConID "+id1);
		}
		Toast.makeText(CreateList.this, "Contacts Added Successfully",Toast.LENGTH_SHORT).show();
		data.InsertCallList(i,id1,fname, lname);
	}


	public void call()
	{
		Intent i=new Intent(CreateList.this,CallList.class);
		startActivity(i);
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
		case R.id.mcall: Intent i4 = new Intent(this, CallList.class);
		startActivity(i4);
		break;
		case R.id.settings: Intent i5 = new Intent(this, Settings.class);
		startActivity(i5);
		break;
		}
		return true;
	}    
}



