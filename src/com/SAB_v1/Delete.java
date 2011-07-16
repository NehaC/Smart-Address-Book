//Delete Contacts
package com.SAB_v1;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

public class Delete extends Activity
{
	DataBaseHelper data;
	int inid;
	public void onCreate(Bundle savedInstanceState)
	{
		requestWindowFeature(Window.FEATURE_NO_TITLE);  
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
		data=new DataBaseHelper(this);
		Bundle bundle=getIntent().getExtras();
		inid=bundle.getInt("inid");
		deletecont(inid);
	}
	/*Type :Function
	name:deletecont
	return type:void
	date:29-6-11
	purpose:To delete contact from database*/
	public void deletecont(int contid)
	{
    	data.deletecontact(contid);
		Toast t=Toast.makeText(getBaseContext(),"Contact Deleted",Toast.LENGTH_LONG);
		t.show();

	}

}
