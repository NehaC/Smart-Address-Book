//Search contacts by name,tags,company
package com.SAB_v1;
import java.util.ArrayList;

import android.content.Intent;
import android.database.Cursor;
import android.app.Activity;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;
public class Search extends Activity 
{

	private ArrayList<String> results = new ArrayList<String>();
	String tn="";
	String tn1,mno;
	DataBaseHelper data;
	Button bsearch;
	EditText es;
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search);
		es=(EditText)findViewById(R.id.esearch);
		data=new DataBaseHelper(this);
		bsearch= (Button)findViewById(R.id.search);
		bsearch.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View v) 
			{
				tn=es.getText().toString();
				System.out.println("Name "+tn);

				searchtag();
				calltag();

			}

		});
	}

	/*Type :Function
	name:searchtag
	return type:void
	date:29-6-11
	purpose:Bind list with data*/	
	private void searchtag() 
	{
		int i=0;
		Cursor c=data.SearchTag(tn);
		while (c.moveToNext())
		{	
			tn=c.getString(0);
			tn1=c.getString(1);
			mno=c.getString(2);
			System.out.println("Name:"+tn);
			System.out.println("LName:"+tn1);
			results.add(tn+" "+tn1+" "+" "+mno);
		}

	}
	/*Type :Function
	name:calltag 
	return type:void
	date:29-6-11
	purpose:To send data to view result of search*/	
	public void calltag()
	{
		Intent i2 = new Intent(Search.this,ViewByTag.class);
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




