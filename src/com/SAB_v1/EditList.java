package com.SAB_v1;

import java.util.ArrayList;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
public class EditList extends Activity implements OnItemClickListener
{
	DataBaseHelper data;
	Button bback,badd;
	String name,name1,name2,sp;
	TextView txt1,txt2,txt3;
	int cid;
	String mob;
	private ListView lv1;
	private ArrayList<String> results = new ArrayList<String>();
	ArrayAdapter<String> adapter;
	@Override
	public void onCreate(Bundle icicle)
	{
		super.onCreate(icicle);
		data=new DataBaseHelper(this);
		requestWindowFeature(Window.FEATURE_NO_TITLE);  
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.editlist);
		data=new DataBaseHelper(this);
		 lv1=(ListView)findViewById(R.id.List);
		  bback=(Button)findViewById(R.id.back);
	   	 Cursor c=data.getData();
	   	  showtask(c);
	   	  displayResultList();  
        bback.setOnClickListener(new OnClickListener() 
 		{
 			public void onClick(View v) 
 			{
 				call();

 			}
 		});
        
 }
	private void displayResultList() {
		CheckBox cb=new CheckBox(this); 
		lv1.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		lv1.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_multiple_choice,results));
		lv1.setTextFilterEnabled(true);
		lv1.setOnItemClickListener(this);
	}
	private void showtask(Cursor c)
	{
		while (c.moveToNext())
		{	
			name=c.getString(0);
			name1=c.getString(1);
			results.add(name+"  "+name1);
			System.out.println("My Name :"+name);
			
		}
		Cursor c1=data.getidmob(name);
		
		while (c1.moveToNext())
		{	
			cid=c1.getInt(0);
			mob=c1.getString(1);
			System.out.println("Id And No is :"+cid+mob);
			
		}
	}
	public void call()
	{
		Intent i=new Intent(EditList.this,CallList.class);
		startActivity(i);
	}
		public void onItemClick(AdapterView<?> a, View v, int position, long id) 
	   {
		System.out.println("Position..."+position);
		String ans= (String) a.getItemAtPosition(position);
		System.out.println("Value is "+ans);
		data.updatecalllist(cid, name, name1);
		Toast.makeText(EditList.this, "Contacts Added Successfully",Toast.LENGTH_SHORT).show();
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
		case R.id.mallconct:Intent i6 = new Intent(this, EditList.class);
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
