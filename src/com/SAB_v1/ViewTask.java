//Search result to display tasks
package com.SAB_v1;
import java.util.Calendar;
import java.util.GregorianCalendar;
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
import android.widget.Button;
public class ViewTask extends Activity
{
	Button today,upcoming,pending;
	String sdate,stime,taskname;
	DataBaseHelper data;
	@Override
	public void onCreate(Bundle icicle)
	{
		requestWindowFeature(Window.FEATURE_NO_TITLE);  
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


		super.onCreate(icicle);
		setContentView(R.layout.viewtask);
		data=new DataBaseHelper(this);
		today = (Button)findViewById(R.id.today);
		today .setOnClickListener(new OnClickListener() 
		{
			public void onClick(View v) 
			{
				
				Intent i=new Intent(ViewTask.this,Task_Today.class);
				startActivity(i);
			}
			
		});
		upcoming = (Button)findViewById(R.id.upcoming);
		upcoming.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View v) 
			{
				Intent i1=new Intent(ViewTask.this,Task_Upcoming.class);
				startActivity(i1);
     		}
		});
		pending = (Button)findViewById(R.id.pending);
		pending.setOnClickListener(new OnClickListener() 
		{
			
			public void onClick(View v) 
			{
				Intent i2=new Intent(ViewTask.this,Task_Pending.class);
				startActivity(i2);
			}
		});
		
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
