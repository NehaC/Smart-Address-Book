package com.SAB_v1;

import java.util.ArrayList;
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
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class UpdateStatus extends Activity implements View.OnClickListener {
	protected static RadioGroup radiogroup; 

	private ArrayList<String> results = new ArrayList<String>();
	Button but,but1,dcancel;
	CharSequence ch;
	Button b1,b2,b3;
	TextView ename,valueTextView,tstatus;
	String name2,ans,name1,sn,notes;
	String[] s1;
	RadioButton r1,r2,r3,r4;

	EditText mDate,mTime,eNote,note1;    
	String sdate,stime1,stask,note;

	DataBaseHelper data;
	@Override
	public void onCreate(Bundle icicle)
	{


		super.onCreate(icicle);
		data=new DataBaseHelper(this);
		setContentView(R.layout.updatedialog);

		Bundle bundle = getIntent().getExtras(); 
		name2=bundle.getString("name");
		System.out.println("My Names: "+name2);
		s1=name2.split(" ");
		sn=s1[0];

		ename=(TextView)findViewById(R.id.name);
		ename.setText(name2);
		tstatus=(TextView)findViewById(R.id.cstatus);
		//eNote=(EditText)findViewById(R.id.note);
		radiogroup = (RadioGroup) findViewById(R.id.Group1);

		r1=(RadioButton)findViewById(R.id.task);
		r2=(RadioButton)findViewById(R.id.notes);

		//radiogroup.setOnCheckedChangeListener(this);


		b1 = (Button)findViewById(R.id.ok);
        b2=(Button)findViewById(R.id.cancel1);
        b2.setOnClickListener(new OnClickListener() 
 		{
 			public void onClick(View v) 
 			{
 				call();

 			}
 		});
		b1.setOnClickListener(new OnClickListener() {

			public void onClick(View v)
			{

				if(r1.isChecked())
				{	  
					tstatus.setText("Schedule Task");
					ch=r1.getText();
					stask=(String)ch;
					Intent i = new Intent(UpdateStatus.this,AddTask.class);
					Bundle bun=new Bundle();
					bun.putString("name",name2);
					System.out.println("Name2: "+name2);
					i.putExtras(bun);
					startActivity(i);
				}
				if(r2.isChecked())
				{	  
					tstatus.setText("Call Completed");
					ch=r1.getText();
					final Dialog dialog = new Dialog(UpdateStatus.this);

					dialog.setContentView(R.layout.note);
					note1 =(EditText)dialog.findViewById(R.id.unotes);
					dialog.setTitle("Enter Notes");
					dialog.setCancelable(true);
					Button btn=(Button)dialog.findViewById(R.id.bok);
					Button btn1=(Button)dialog.findViewById(R.id.bcancel);
					btn1.setOnClickListener(new OnClickListener() 
			 		{
			 			public void onClick(View v) 
			 			{
			 				finish();

			 			}
			 		});
					btn.setOnClickListener(new OnClickListener()
					{
						public void onClick(View v)
						{		

							notes=note1.getText().toString();
							System.out.println("your notes:-"+notes);
							data.Insertnotes(name2,notes);
							Cursor c3 =data.getnotes();
							while(c3.moveToNext())
							{
								String n1=c3.getString(1);
								String n2=c3.getString(2);
								System.out.println("N1 "+n1);
								System.out.println("N2 "+n2);
							}
			  				dialog.dismiss();
			  			Toast.makeText(UpdateStatus.this, "Note Added Sucessfully",Toast.LENGTH_SHORT).show();

						}

					});
					dialog.show();
					/*stask=(String)ch;
		String status=tstatus.getText().toString();*/
					//data.Insertnotes(name2,notes);
					//Toast.makeText(UpdateStatus.this, "Call Completed",Toast.LENGTH_SHORT).show();
				} 
			}
		});
		/*dcancel = (Button)findViewById(R.id.cancel);
    dcancel.setOnClickListener(new OnClickListener() {

	    public void onClick(View v)
	    {

	    	finish();
		  } 

	    });   
		 */
	}
	/*public void onCheckedChanged(RadioGroup arg0, int arg1) {
		// TODO Auto-generated method stub
		  if(r1.isChecked())
		  {	  
			  ch=r1.getText();
			stask=(String)ch;
	//Intent i2=new Intent(UpdateStatus.this,AddTask.class);
	//startActivity(i2);
		  }
  	   	if(r2.isChecked())
  	  {	  
		  ch=r1.getText();
		stask=(String)ch;
		//Intent i3=new Intent(UpdateStatus.this,AddTask.class);
		//startActivity(i3);
	  } 
  	if(r3.isChecked())
  	{	  
		  ch=r1.getText();
		stask=(String)ch;
	  }
  	if(r4.isChecked())
  	{	  
		  ch=r1.getText();
		stask=(String)ch;
	  }
	}*/
	public void onClick(View arg0) {
		// TODO Auto-generated method stub

	}
 public void call()
 {
	 Intent i=new Intent(UpdateStatus.this,AllContacts.class);
	 startActivity(i);
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



