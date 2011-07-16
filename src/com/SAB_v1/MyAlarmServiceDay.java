package com.SAB_v1;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

//import com.Smart_task1.DatabaseHelper;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.sax.StartElementListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

public final class MyAlarmServiceDay extends BroadcastReceiver
{	

	Button yes,no;
	//DatabaseHelper db;
	DataBaseHelper db1;
	String tname,town,tdesp,tprio,tsdate,tddate,ttime,con,loc,ttype,tcat;
	String tname1,town1,tdesp1,tprio1,tsdate1,tddate1,ttime1;
	String listname,listdate,listid,callcdate,callcfn,callcln,callcmob;
	int con1,loc1,ttype1,tcat1,c_id,t_id;
	String elog_id,econ_id,ecnt,slog_id,scon_id,scnt;
	String fname,lname,mobno,email,image,tags,u_id;
	String fname1,lname1,mobno1,email1,image1,tags1,log_id,con_id,cnt;
	private RadioButton t,f;
	private Button reset = null;
	private TextView ls1;
	private TextView txt;
	private ListView ls2;	
	private TabHost myTabHost;
	String result="";
	
	InputStream is = null;
	
	@Override
    
    public void onReceive(Context context, Intent intent) 
	{
		
		 Bundle bun=intent.getExtras();
		 u_id=bun.getString("u_id");
		 System.out.println("UID service.."+u_id);
		Intent myIntent = new Intent( context, Sync.class );
		myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		Bundle bun1=new Bundle();
		 bun1.putString("u_id",u_id);
		 myIntent.putExtras(bun1);
		 context.startActivity(myIntent); 
		 int SECS = 1800;
		    int MINS = 60 * SECS;
		    Calendar cal = Calendar.getInstance();
		    Intent in = new Intent(context, Sync.class);
		    PendingIntent pi = PendingIntent.getService(context, 0, in, PendingIntent.FLAG_UPDATE_CURRENT);

		    AlarmManager alarms = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
			    alarms.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),10 * MINS, pi);
			    Toast.makeText(context, "Auto Sync Activated For Every Day..", Toast.LENGTH_SHORT).show();

	}
		
	}

	

