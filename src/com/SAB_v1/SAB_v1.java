package com.SAB_v1;


import java.util.ArrayList;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.AdapterView.OnItemClickListener;
public class SAB_v1 extends Activity implements OnItemClickListener
{
	Button bsearch,bbs,badd,bcall,btask;
	EditText esearch;
	DataBaseHelper data;
	private ArrayList<String> results = new ArrayList<String>();
	String tn="";
	String tn1,mno,fname,lname;
	String []name;
	int count,j;
	String name1,name2;
	@Override
	public void onCreate(Bundle icicle)
	{
		requestWindowFeature(Window.FEATURE_NO_TITLE);  
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


		super.onCreate(icicle);
		setContentView(R.layout.main);
		data=new DataBaseHelper(this);
		
		badd = (Button)findViewById(R.id.add);
		badd.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View v) 
			{
				Intent i=new Intent(SAB_v1.this,AllContacts.class);
				startActivity(i);
			//call();
				
			}
		   
		});
		
		bcall = (Button)findViewById(R.id.call);
		bcall.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View v) 
			{
				Intent newActivity = new Intent(SAB_v1.this,CallList.class);     
				startActivity(newActivity);
			}
		});
		btask = (Button)findViewById(R.id.task);
		btask.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View v) 
			{
				Intent newActivity1 = new Intent(SAB_v1.this,ViewTask.class);     
				startActivity(newActivity1);
			}
		});
		bbs = (Button)findViewById(R.id.bs);
	 bbs.setOnClickListener(new OnClickListener() 
	 {
	   public void onClick(View v) 
	   {
		   Intent newActivity1 = new Intent(SAB_v1.this,Bscontacts.class);     
			startActivity(newActivity1);
	   }
	 });
	/* bsearch = (Button)findViewById(R.id.bsearch);
		bsearch.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View v) 
			{
				
				esearch=(EditText)findViewById(R.id.esearch);
				
						tn=esearch.getText().toString();
						System.out.println("Name "+tn);

						searchtag();
						calltag();

					}

				});*/

	}
	
	private void searchtag() 
	{
		int i=0;
		Cursor c2=data.SearchTag(tn);
		while (c2.moveToNext())
		{	
			tn=c2.getString(0);
			tn1=c2.getString(1);
			mno=c2.getString(2);
			System.out.println("Name:"+tn);
			System.out.println("LName:"+tn1);
			results.add(tn+" "+tn1+" "+" "+mno);
		}

	}
	
	public void onItemClick(AdapterView<?> a, View v, int position, long id) 
	{
		System.out.println("Position..."+position);
		String ans= (String) a.getItemAtPosition(position);
		System.out.println("Value is "+ans);
		Intent i= new Intent(this,Details.class);
		Bundle bun=new Bundle();
		bun.putString("name2",ans);
		i.putExtras(bun);
		startActivity(i);


	}
	public void calltag()
	{
		Intent i2 = new Intent(SAB_v1.this,ViewByTag.class);
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
		case R.id.settings: Intent i5 = new Intent(this, Settings.class);
		startActivity(i5);
		break;
		}
		return true;
	}     


}

