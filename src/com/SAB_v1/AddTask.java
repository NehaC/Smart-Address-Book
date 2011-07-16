//To add task
package com.SAB_v1;
import java.util.Calendar;
import java.util.GregorianCalendar;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class AddTask extends Activity implements OnClickListener{
	DataBaseHelper data;
	EditText edescp;
	ArrayAdapter adapter,adapter2,adapter3,adapter4;
	Spinner s,sp;
	String name2,ans,name3,sn,taskname;
	private TextView ename;
	private String array_spinner[];
	private String array_spinner1[];
	private Button ddone,dcancel;
	String sdate,ddate,stime1,descp,stime;
	StringBuilder sdate1;
	String[] s1;
	@Override
	public void onCreate(Bundle icicle)
	{

		requestWindowFeature(Window.FEATURE_NO_TITLE);  
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(icicle);
		data=new DataBaseHelper(this);
		setContentView(R.layout.add_task);
		Bundle bundle = getIntent().getExtras(); 
		name3=bundle.getString("name");
		System.out.println("Namessssss: "+name3);
		s1=name3.split(" ");
		sn=s1[0];
		ename=(TextView)findViewById(R.id.name);
		ename.setText(name3);
		edescp=(EditText)findViewById(R.id.descp);
		Calendar cal = new GregorianCalendar();
		int month = cal.get(Calendar.MONTH);
		int year = cal.get(Calendar.YEAR);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int minute = cal.get(Calendar.MINUTE);

		sdate=(+ year + "-" + (month+1)+ "-" + day); 
		stime=(+(hour)+":"+minute);


		array_spinner=new String[2];
		array_spinner[0]="Self";
		array_spinner[1]=sn;

		array_spinner1=new String[5];
		array_spinner1[0]="Select Option";
		array_spinner1[1]="Schedule Call";
		array_spinner1[2]="SMS";
		array_spinner1[3]="Schedule Meeting";
		array_spinner1[4]="Send E-mail";
		ddone=(Button)findViewById(R.id.ddone);
		dcancel=(Button)findViewById(R.id.dcancel);
		s= (Spinner) findViewById(R.id.actionby);
		adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, array_spinner);
		s.setAdapter(adapter);
		sp= (Spinner) findViewById(R.id.action);
		adapter2 = new ArrayAdapter(this,android.R.layout.simple_spinner_item, array_spinner1);
		sp.setAdapter(adapter2);
		int spi=sp.getSelectedItemPosition();

		sp.setOnItemSelectedListener(
				new AdapterView.OnItemSelectedListener() {
					public void onItemSelected(AdapterView<?> a,View view,int position,long id)
					{
						if(position>0)
						{
							taskname= (String) sp.getItemAtPosition(position);
							showDateTimeDialog();
						}
					}

					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub

					}
				});

		ddone.setOnClickListener(new OnClickListener() {
 
			public void onClick(View v)
			{
				descp=edescp.getText().toString();
				System.out.println("stime..."+stime1);
				data.Inserttask(name3, taskname, descp, 1, "low", 0, sdate, ddate, stime1, 0, 0);
				Toast.makeText(AddTask.this, "Task Added Successfully",Toast.LENGTH_SHORT).show();
				Cursor c=data.getDetails1();
				while(c.moveToNext())
				{	System.out.println("c"+c);
				String na=c.getString(1);
				String tna=c.getString(2);
				String tde=c.getString(3);
				String p=c.getString(5);
				String da=c.getString(7);
				String dda=c.getString(8);
				String ti=c.getString(9);
				System.out.println("OName:" +na);
				System.out.println("TName:" +tna);
				System.out.println("TDescp:" +tde);
				System.out.println("Pri:" +p);
				System.out.println("CDate:" +da);
				System.out.println("Due Date:" +dda);
				System.out.println("Time:" +ti);
				}
			} 

		});
		dcancel.setOnClickListener(new OnClickListener() {

			public void onClick(View v)
			{

				finish();
			} 

		});   

	}
	/*Type :Function
	return type:void
	date:29-6-11
	purpose:To show date time dialog*/
	private void showDateTimeDialog() {
		// Create the dialog
		final Dialog mDateTimeDialog = new Dialog(this);
		// Inflate the root layout
		final RelativeLayout mDateTimeDialogView = (RelativeLayout) getLayoutInflater().inflate(R.layout.date_time_dialog, null);
		// Grab widget instance
		final DateTimePicker mDateTimePicker = (DateTimePicker) mDateTimeDialogView.findViewById(R.id.DateTimePicker);
		// Check is system is set to use 24h time (this doesn't seem to work as expected though)
		final String timeS = android.provider.Settings.System.getString(getContentResolver(), android.provider.Settings.System.TIME_12_24);
		final boolean is24h = !(timeS == null || timeS.equals("12"));

		// Update demo TextViews when the "OK" button is clicked 
		((Button) mDateTimeDialogView.findViewById(R.id.SetDateTime)).setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				((TextView) findViewById(R.id.Date)).setText(mDateTimePicker.get(Calendar.YEAR) + "/" + (mDateTimePicker.get(Calendar.MONTH)+1) + "/"
						+ mDateTimePicker.get(Calendar.DAY_OF_MONTH));
				ddate=mDateTimePicker.get(Calendar.YEAR)+"-"+(mDateTimePicker.get(Calendar.MONTH)+1)+"-"+mDateTimePicker.get(Calendar.DAY_OF_MONTH);
				System.out.println("Due Date "+ddate);
				if (mDateTimePicker.is24HourView()) {
					((TextView) findViewById(R.id.Time)).setText(mDateTimePicker.get(Calendar.HOUR_OF_DAY) + ":" + mDateTimePicker.get(Calendar.MINUTE));
					//stime1=mDateTimePicker.get(Calendar.HOUR_OF_DAY) + ":" + mDateTimePicker.get(Calendar.MINUTE);
					//System.out.println("Due time "+stime1);

				} else {
					((TextView) findViewById(R.id.Time)).setText(mDateTimePicker.get(Calendar.HOUR) + ":" + mDateTimePicker.get(Calendar.MINUTE) + " "
							+ (mDateTimePicker.get(Calendar.AM_PM) == Calendar.AM ? "AM" : "PM"));
					stime1=mDateTimePicker.get(Calendar.HOUR)+":" + mDateTimePicker.get(Calendar.MINUTE);
					System.out.println("Due time1 "+stime1);
				}
				//stime1=mDateTimePicker.get(Calendar.HOUR)+":" + mDateTimePicker.get(Calendar.MINUTE);
				mDateTimeDialog.dismiss();
			}
		});

		// Cancel the dialog when the "Cancel" button is clicked
		((Button) mDateTimeDialogView.findViewById(R.id.CancelDialog)).setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				mDateTimeDialog.cancel();
			}
		});

		// Reset Date and Time pickers when the "Reset" button is clicked
		((Button) mDateTimeDialogView.findViewById(R.id.ResetDateTime)).setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				mDateTimePicker.reset();
			}
		});

		// Setup TimePicker
		mDateTimePicker.setIs24HourView(is24h);
		// No title on the dialog window
		mDateTimeDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		// Set the dialog content view
		mDateTimeDialog.setContentView(mDateTimeDialogView);
		// Display the dialog
		mDateTimeDialog.show();
	}
	public void onClick(View v) {
		// TODO Auto-generated method stub

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
