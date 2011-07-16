package com.SAB_v1;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
public class Call extends Activity
{
	DataBaseHelper data;
	String tn,tn1;
	private ArrayList<String> results = new ArrayList<String>();
	@Override
	public void onCreate(Bundle icicle)
	{
		data=new DataBaseHelper(this);
		requestWindowFeature(Window.FEATURE_NO_TITLE);  
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(icicle);
		Cursor c=data.getData();
		search(c);
		call();
	}
	private void search(Cursor c) 
	{
		while (c.moveToNext())
		{	
			tn=c.getString(0);
			tn1=c.getString(1);
			results.add(tn+" "+tn1);
		}

	}
	public void call()
	{
		Intent i2 = new Intent(Call.this,CreateList.class);
		Bundle bundle = new Bundle();
		bundle.putStringArrayList("results", results);
		i2.putExtras(bundle);
		startActivity(i2);
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
		/*case R.id.mimport: Intent i3 = new Intent(this,Import.class);
		startActivity(i3);
		break; */
		case R.id.mcall: Intent i4 = new Intent(this, CallList.class);
		startActivity(i4);
		break;

		}
		return true;
	}    
}