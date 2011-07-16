package com.SAB_v1;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Call1 extends ListActivity implements OnItemClickListener
{
	TelephonyManager tm;
	String fname,lname;
	DataBaseHelper data;
	String no;
	int sno,conid1,cnt=0,conid;
	public void onCreate(Bundle savedInstanceState)
	{
		requestWindowFeature(Window.FEATURE_NO_TITLE);  
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
		data=new DataBaseHelper(this);
		Bundle bundle=getIntent().getExtras();
		no=bundle.getString("smno");
		sno=Integer.parseInt(no);
		System.out.println("Sms N"+sno);
        Cursor c1= data.getMNo(sno);
        System.out.println("Sms Nooo"+sno);
		while(c1.moveToNext())
		{
			conid1=c1.getInt(0);
			fname=c1.getString(1);
			System.out.println("conid "+conid1);
			System.out.println("Fname "+fname);
		}
		Cursor c4=data.getconid(fname,no);
		while(c4.moveToNext())
		{
			conid=c4.getInt(0);
			System.out.println("conid=="+conid);
		}
		Cursor c6= data.getcnt(conid);
		while(c6.moveToNext())
		{
			cnt=c6.getInt(0);
		}
		if(cnt==0)	
		{	cnt=cnt+1;
			System.out.println("cnt=="+cnt);
			data.insertccnt(conid,cnt);
		}
		else
		{
			cnt=cnt+1;
			data.updatecnt(conid,cnt);
			
		}
		Intent intent = new Intent(Intent.ACTION_CALL);
		intent.setData(Uri.parse("tel:" +sno));
		startActivity(intent);
		tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
		tm.listen(mPhoneListener, PhoneStateListener.LISTEN_CALL_STATE);

	}
	private PhoneStateListener mPhoneListener = new PhoneStateListener() 
	{
		public void onCallStateChanged(int state, String incomingNumber) 
		{
			try 
			{
				switch (state) 
				{
				case TelephonyManager.CALL_STATE_RINGING:
					Toast.makeText(Call1.this, "CALL_STATE_RINGING", Toast.LENGTH_SHORT).show();
					break;
				case TelephonyManager.CALL_STATE_OFFHOOK:
					Toast.makeText(Call1.this, "CALL_STATE_OFFHOOK", Toast.LENGTH_SHORT).show();
					break;
				case TelephonyManager.CALL_STATE_IDLE:
					Toast.makeText(Call1.this, "CALL_STATE_IDLE", Toast.LENGTH_SHORT).show();
					
					//onRestart(); 
					break;
				default:
					Toast.makeText(Call1.this, "default", Toast.LENGTH_SHORT).show();
					Log.i("Default", "Unknown phone state=" + state);
				}
			} catch (Exception e) 
			{
				Log.i("Exception", "PhoneStateListener() e = " + e);
			}
		}
	};
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		
	}

}
