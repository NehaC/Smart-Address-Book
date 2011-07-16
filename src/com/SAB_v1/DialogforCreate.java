package com.SAB_v1;

import java.util.Calendar;
import java.util.GregorianCalendar;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class DialogforCreate extends Activity
{
	Button b1,b2, mpickDate,mpickTime;
	TextView text1;
	EditText ename,mDate,mTime;
	String sdate,stime1;
	DataBaseHelper data;
	private int mYear;
	private int mMonth;
	private int mDay;
	static final int DATE_DIALOG_ID = 1;
	static final int TIME_DIALOG_ID = 0;
	private int mHour;
	private int mMinute;
	String n;
	@Override
	public void onCreate(Bundle icicle)
	{


		super.onCreate(icicle);
		data=new DataBaseHelper(this);
		Calendar cal = new GregorianCalendar();
		int month = cal.get(Calendar.MONTH);
		int year = cal.get(Calendar.YEAR);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		sdate=(+ year + "-" + (month+1)+ "-" + day);  


		setContentView(R.layout.maindialog);
		setTitle("Create Call List");
		text1=(TextView)findViewById(R.id.text1);
		ename=(EditText)findViewById(R.id.name);
		mDate=(EditText)findViewById(R.id.edate);
		mTime=(EditText)findViewById(R.id.etime);
		mpickDate = (Button)findViewById(R.id.Date);
		mpickDate.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				showDialog(DATE_DIALOG_ID);
			}
		});
		mpickTime = (Button)findViewById(R.id.Time);
		mpickTime.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				showDialog(TIME_DIALOG_ID);
			}
		});

		// get the current date and time
		final Calendar c = Calendar.getInstance();
		mYear = c.get(Calendar.YEAR);
		mMonth = c.get(Calendar.MONTH);
		mDay = c.get(Calendar.DAY_OF_MONTH);
		mHour = c.get(Calendar.HOUR_OF_DAY);
		mMinute = c.get(Calendar.MINUTE);
		updateDisplay();
		updateDisplay1();
		
		b1 = (Button)findViewById(R.id.ok);
        
		b1.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				checkvalidate();
			}

		});
		b1 = (Button)findViewById(R.id.cancel);

   b1.setOnClickListener(new OnClickListener() {

    public void onClick(View v) {

 	 finish();
        }

    });
		 

		//now that the dialog is set up, it's time to show it    



	}
	public void checkvalidate()
	{
		if((ename.getText().toString()).equals(""))
    	{
    		text1.setText("Call List Name not entered!!");
    	}
    	else if((mDate.getText().toString()).equals(""))
    	{
    		text1.setText("Date not selected!!");
    	}	
    	else
    	{
    		n=ename.getText().toString();
			data.InsertCall(n,sdate);
			call();
    	}
		Intent alarmIntent = new Intent(DialogforCreate.this, MyAlarmServiceCalllist.class);
		Bundle bun=new Bundle();
		bun.putString("listname",n);
		alarmIntent.putExtras(bun);
		//setting task alert notification on time and date selected
		PendingIntent pendingAlarmIntent = PendingIntent.getBroadcast(DialogforCreate.this, 0,
	                                   alarmIntent, 0);
	     
	      Calendar AlarmCal = Calendar.getInstance();
	      AlarmCal.setTimeInMillis(System.currentTimeMillis());
	      AlarmCal.set(Calendar.HOUR_OF_DAY,mHour);  // set user selection
	      AlarmCal.set(Calendar.MINUTE,mMinute);        // set user selection
	      AlarmCal.set(Calendar.SECOND, 0);
	      AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
	      alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,
	              AlarmCal.getTimeInMillis(), AlarmManager.INTERVAL_FIFTEEN_MINUTES,
	              pendingAlarmIntent);

	}
	public void call()
	{
		Intent newActivity = new Intent(DialogforCreate.this,Call.class);     
		startActivity(newActivity); 
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

	private void updateDisplay() 
	{
		// updates the date in the TextView

		mDate.setText(
				new StringBuilder()
				// Month is 0 based so add 1
				.append(mMonth + 1).append("-")
				.append(mDay).append("-")
				.append(mYear).append(" "));


	}
	private void updateDisplay1() {
		mTime.setText(new StringBuilder()
		.append(pad(mHour)).append(":")
		.append(pad(mMinute)));
		stime1=mTime.getText().toString();
	}
	// the callback received when the user "sets" the date in the dialog
	private DatePickerDialog.OnDateSetListener mDateSetListener =
		new DatePickerDialog.OnDateSetListener() {

		public void onDateSet(DatePicker view, int year, 
				int monthOfYear, int dayOfMonth) {
			mYear = year;
			mMonth = monthOfYear;
			mDay = dayOfMonth;
			updateDisplay();
		}

	};

	private static String pad(int c) {
		if (c >= 10)
			return String.valueOf(c);
		else
			return "0" + String.valueOf(c);


	}
	protected Dialog onCreateDialog(int id)
	{
		switch (id) 
		{
		case DATE_DIALOG_ID:
			return new DatePickerDialog(this,
					mDateSetListener,
					mYear, mMonth, mDay);

		case TIME_DIALOG_ID: return new TimePickerDialog(this,
				mTimeSetListener, mHour, mMinute, false);


		}
		return null;
	}
	private TimePickerDialog.OnTimeSetListener mTimeSetListener =
		new TimePickerDialog.OnTimeSetListener() {
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			mHour = hourOfDay;
			mMinute = minute;
			updateDisplay1();
		}
	};

}    



