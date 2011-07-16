package com.SAB_v1;


import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
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

public class DeleteList extends Activity implements OnItemClickListener
{	
	int no,i;
	String n,m,m1,n2,n3,oname;
	DataBaseHelper data;
	Button bback; 
	int id1,id2;
	String[] s;
	ListView lv1;
	private ArrayList<String> results = new ArrayList<String>();
	public void onCreate(Bundle savedInstanceState)
	{
		requestWindowFeature(Window.FEATURE_NO_TITLE);  
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.deletelist);
		data=new DataBaseHelper(this);
		lv1=(ListView)findViewById(R.id.List);
		Bundle bundle = getIntent().getExtras(); 
		String name2=bundle.getString("name2");
		Cursor c1=data.getDataCallid(name2);
		while (c1.moveToNext())
		{	
			id1=c1.getInt(0);
			System.out.println("IDs "+id1);
		}
		Cursor c=data.getDataCallList(id1);
		System.out.println("ID1 "+id1);
		while (c.moveToNext())
		{	

			n2=c.getString(0);
			n3=c.getString(1);
			results.add(n2+"  "+n3);
			System.out.println("FName"+n2);
			System.out.println("LName"+n3);
			oname=n2+n3;
		}
		Cursor c2=data.getCallListid(n2,n3);
		while (c2.moveToNext())
		{	
			id2=c2.getInt(0);
			System.out.println("IDs "+id2);
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
		s=ans.split(" ");
		System.out.println("Split Value is: "+s[0]);
		System.out.println("Split Value is: "+s[1]);
		lv1.clearChoices();
		data.deletecallist(id2);
		 /*AlertDialog.Builder adb=new AlertDialog.Builder(DeleteList.this);
	        adb.setTitle("Delete?");
	        adb.setMessage("Are you sure you want to delete " + position);
	        final int positionToRemove = position;
	        adb.setNegativeButton("Cancel", null);
	        adb.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
	            public void onClick(DialogInterface dialog, int which)
	            {
	            	data.deletecallist(s[0]);
	            	lv1.remove(positionToRemove);
	                results.notifyDataSetChanged();
	            }});
	        adb.show();*/
		//results.remove(position);
		Toast.makeText(DeleteList.this, "Contacts Deleted Successfully from CallList",Toast.LENGTH_SHORT).show();
	}


	public void call()
	{
		Intent i=new Intent(DeleteList.this,CallList.class);
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



