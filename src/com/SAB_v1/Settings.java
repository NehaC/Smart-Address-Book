package com.SAB_v1;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

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

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class Settings extends Activity {
           DataBaseHelper data;
           CheckBox auto,manual;
           Button done,cancel,reset;
           RadioButton min,hour,day;
           String ans;
           int mHour,mMinute;
           private EditText uname = null;
       	private EditText pwd = null;
       	private Button login = null,save,cancel1;
       	private TextView signup1 = null;
       	private TextView name = null,txt1;
       	private TextView text3 = null;
       	private TextView text4 = null;
       	private TextView text5 = null;
       	private TextView text,text1, text2;
       	String u_id;
       	String result = "";
       	String temp="";
       	InputStream is = null;
            @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        auto=(CheckBox)findViewById(R.id.auto);
        manual=(CheckBox)findViewById(R.id.manual);
        auto.setOnCheckedChangeListener(new OnCheckedChangeListener()
        {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
            	if(isChecked)
            	{
            		login();
            	//ch=r1.getText();
    			
            }
            }
        });
        manual.setOnCheckedChangeListener(new OnCheckedChangeListener()
        {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
            	if(isChecked)
            	{
            		manualsync();
            		
    			
            	}
            }
        });
            }
			protected void manualsync() {
				Intent i= new Intent(this,ManualSync.class);
        		startActivity(i);

				
			}
			protected void login() 
			{
				setContentView(R.layout.login);
				signup1 = (TextView) findViewById(R.id.signup1);
				signup1.setMovementMethod(LinkMovementMethod.getInstance());
				signup1.setOnClickListener(new Button.OnClickListener() 
				{ public void onClick (View v)
				{ 
					signup();
				}
				});

				uname = (EditText)findViewById(R.id.uname);
				pwd = (EditText)findViewById(R.id.pwd);
				text=(TextView)findViewById(R.id.text);
				text1=(TextView)findViewById(R.id.text1);
				text2=(TextView)findViewById(R.id.text2);
				text3 = (TextView)findViewById(R.id.text3);
				text4 = (TextView)findViewById(R.id.text4);
				text5 = (TextView)findViewById(R.id.text5);
				reset = (Button)findViewById(R.id.reset);
				initControls();



			}

			protected void signup()
			{
				Intent newActivity = new Intent(this,Register.class);     
				startActivity(newActivity);

			}

			private void initControls()
			{

				text3.setText("");
				text4.setText("");
				text5.setText("");
				login = (Button)findViewById(R.id.login);
				login.setOnClickListener(new Button.OnClickListener() 
				{ public void onClick (View v)
				{ 

					checkValidate();
					

				}
				});
				reset.setOnClickListener(new Button.OnClickListener() 
				{ public void onClick (View v)
				{ 

					reset_func();

				}
				});
			}

			private void reset_func()
			{
				uname.setText("");
				pwd.setText("");
			}

			private void checkValidate()
			{
				String result = "";



				if((uname.getText().toString()).equals(""))
				{
					text1.setText("Uname not entered!!");
				}
				else if((pwd.getText().toString()).equals(""))
				{
					text1.setText("Password not entered!!");
				}
				else
				{

					System.out.println("uname.."+uname.getText().toString()+"Pass"+pwd.getText().toString());
					//the year data to send
					ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
					nameValuePairs.add(new BasicNameValuePair("uname",uname.getText().toString()));
					nameValuePairs.add(new BasicNameValuePair("pwd",pwd.getText().toString()));

					//http post
					try
					{
						HttpClient httpclient = new DefaultHttpClient();
						HttpPost httppost = new HttpPost("http://bpsi.us/blueplanetsolutions/stlist/select_query_server.php");
						httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
						HttpResponse response = httpclient.execute(httppost);
						HttpEntity entity = response.getEntity();
						is = entity.getContent();
					}
					catch(Exception e)
					{
						//text2.setText("Error in http connection");
					}
					try
					{
						BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
						StringBuilder sb = new StringBuilder();
						String line = null;

						while ((line = reader.readLine()) != null) 
						{
							sb.append(line + "\n");
						}
						is.close();
						result=sb.toString();

					}
					catch(Exception e)
					{
						text2.setText("Error converting result");
					}
					//parse data


					try{


						JSONArray jArray = new JSONArray(result);

						for(int i=0;i<jArray.length();i++)
						{
							JSONObject json_data = jArray.getJSONObject(i);

							int temp =json_data.getInt("u_id");
							u_id = ""+temp;
						}
						final Dialog dialog = new Dialog(Settings.this);

		    			dialog.setContentView(R.layout.autodialog);
		    			min =(RadioButton)dialog.findViewById(R.id.emin);
		    			hour =(RadioButton)dialog.findViewById(R.id.ehour);
		    			day =(RadioButton)dialog.findViewById(R.id.eday);
		    			dialog.setTitle("Select Option");
		    			dialog.setCancelable(true);
		    			done=(Button)dialog.findViewById(R.id.done);
		    			done.setOnClickListener(new OnClickListener()
		    			{
		    				public void onClick(View v)
		    				{		
		    					if(min.isChecked())	              		    {
		    						ans="min";
		    						autosync(ans);
		              		    }else if(hour.isChecked())
		              		    {
		    						ans="hour";
		    						autosync(ans);
		              		    }else if(day.isChecked())
		              		    {
		    						ans="day";
		    						autosync(ans);
		              		    }
		    				}
		    				});
		    			dialog.show();
						//name.setText("Welcome "+uname.getText().toString());
						
					}
					catch(JSONException e)
					{
						text1.setText("Invalid Login!!");
					}
				}

			}
				
			
			protected void autosync(String ans2) 
			{
				if(ans=="min")
				{
					Calendar c = new GregorianCalendar();
					mHour = c.get(Calendar.HOUR_OF_DAY);
					mMinute = c.get(Calendar.MINUTE);
					System.out.println("current time="+mHour+":"+mMinute);
					/*for(int i=mMinute;;i++)
					{*/
						Intent alarmIntent = new Intent(Settings.this, MyAlarmService.class);
						Bundle bun=new Bundle();
						 bun.putString("u_id",u_id);
						 alarmIntent.putExtras(bun);
							PendingIntent pendingAlarmIntent = PendingIntent.getBroadcast(Settings.this, 0,
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
					      
					//}
     			}
				else if(ans=="hour")
				{
					Calendar c = new GregorianCalendar();
					mHour = c.get(Calendar.HOUR_OF_DAY);
					mMinute = c.get(Calendar.MINUTE);
					System.out.println("current time="+mHour+":"+mMinute);
					/*for(int i=mMinute;;i++)
					{*/
						Intent alarmIntent = new Intent(Settings.this, MyAlarmServiceHour.class);
						Bundle bun=new Bundle();
						 bun.putString("u_id",u_id);
						 alarmIntent.putExtras(bun);
							PendingIntent pendingAlarmIntent = PendingIntent.getBroadcast(Settings.this, 0,
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
					      
					//}
     			}
				if(ans=="day")
				{
					Calendar c = new GregorianCalendar();
					mHour = c.get(Calendar.HOUR_OF_DAY);
					mMinute = c.get(Calendar.MINUTE);
					System.out.println("current time="+mHour+":"+mMinute);
					/*for(int i=mMinute;;i++)
					{*/
						Intent alarmIntent = new Intent(Settings.this, MyAlarmServiceDay.class);
						Bundle bun=new Bundle();
						 bun.putString("u_id",u_id);
						 alarmIntent.putExtras(bun);
							PendingIntent pendingAlarmIntent = PendingIntent.getBroadcast(Settings.this, 0,
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
					      
					//}
     			}
			}
}
