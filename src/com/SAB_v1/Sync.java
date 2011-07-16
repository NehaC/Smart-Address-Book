package com.SAB_v1;
//import com.Smart_task1.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
import android.database.Cursor;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;


public class Sync extends Activity 
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
	String mobnoh,mobnow,mobnoo,mobnocust,emailw,emailo,emailcust;
	
	String result="";
	
	InputStream is = null;
	@Override
	public void onCreate(Bundle icicle)
	{	
		
		//db=new DatabaseHelper(this);
		db1=new DataBaseHelper(this);
		requestWindowFeature(Window.FEATURE_NO_TITLE);  
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(icicle);
		Bundle bun = getIntent().getExtras(); 
		u_id=bun.getString("u_id");
		System.out.println("UID.."+u_id);
		String result = "";
		System.out.println("After 10 Mins...............");
			//the year data to send
					/*Cursor c= db.gettask();
					while(c.moveToNext())
					{	

					tname = c.getString(1);
						town =	c.getString(0);
						tdesp =	c.getString(2);
						ttype =	c.getString(3);
						tprio =	c.getString(4);
						tcat  =	c.getString(5);
						tsdate =c.getString(6);
						tddate = c.getString(7);
						ttime =	c.getString(8);
						con = c.getString(9);
						loc = c.getString(10);
						t_id =c.getInt(11);
						System.out.println("task are:--"+tname+town+tdesp+ttype+tprio+tcat+tsdate+tddate+ttime+con+loc);
						ArrayList<NameValuePair> nv = new ArrayList<NameValuePair>();
						nv.add(new BasicNameValuePair("town",town));
						nv.add(new BasicNameValuePair("tname",tname));
						nv.add(new BasicNameValuePair("tdesp",tdesp));
						nv.add(new BasicNameValuePair("ttype",ttype));
						nv.add(new BasicNameValuePair("tprio",tprio));
						nv.add(new BasicNameValuePair("tcat",tcat));
						nv.add(new BasicNameValuePair("tsdate",tsdate));
						nv.add(new BasicNameValuePair("tddate",tddate));
						nv.add(new BasicNameValuePair("ttime",ttime));
						nv.add(new BasicNameValuePair("con",con));
				nv.add(new BasicNameValuePair("u_id",u_id));
						//http post
						try
						{
							HttpClient httpclient = new DefaultHttpClient();
							HttpPost httppost = new HttpPost("http://bpsi.us/blueplanetsolutions/stlist/insert.php");
							httppost.setEntity(new UrlEncodedFormEntity(nv));
							HttpResponse response = httpclient.execute(httppost);
							HttpEntity entity = response.getEntity();
							is = entity.getContent();
						}
						catch(Exception e)
						{
							//text2.setText("Error in http connection");
						}

					
					db.updateflag(t_id);
				}*/
			
	
					Cursor c1= db1.getcontact();
					while(c1.moveToNext())
					{	

						fname =	c1.getString(0);
						lname=	c1.getString(1);
						mobno =	c1.getString(2);
						email =	c1.getString(3);
						tags =	c1.getString(5);
						c_id=c1.getInt(6);
						System.out.println("contacts are== --"+fname+lname+mobno+email+tags);
						ArrayList<NameValuePair> nv = new ArrayList<NameValuePair>();
						nv.add(new BasicNameValuePair("fname",fname));
						nv.add(new BasicNameValuePair("lname",lname));
						nv.add(new BasicNameValuePair("mobno",mobno));
						nv.add(new BasicNameValuePair("email",email));
						nv.add(new BasicNameValuePair("tags",tags));
						nv.add(new BasicNameValuePair("u_id",u_id));
						//http post
						try
						{
							HttpClient httpclient = new DefaultHttpClient();
							HttpPost httppost = new HttpPost("http://bpsi.us/blueplanetsolutions/stlist/insertc.php");
							httppost.setEntity(new UrlEncodedFormEntity(nv));
							HttpResponse response = httpclient.execute(httppost);
							HttpEntity entity = response.getEntity();
							is = entity.getContent();
						}
						catch(Exception e)
						{
							//text2.setText("Error in http connection");
						}

					
					db1.updateflagc(c_id);
					}
		
					Cursor c3= db1.getlistname();
					while(c3.moveToNext())
					{	

							listid  =	c3.getString(0);
							listname=	c3.getString(1);
							listdate =	c3.getString(2);
							System.out.println(listid+listname+listdate);
							Cursor c_1=db1.getlistdetails(listid,listdate);
							while(c_1.moveToNext())
							{
								callcdate=c_1.getString(0);
								callcfn=c_1.getString(1);
								callcln=c_1.getString(2);
								callcmob=c_1.getString(3);
							}
						ArrayList<NameValuePair> nv = new ArrayList<NameValuePair>();
						nv.add(new BasicNameValuePair("listid",listid));
						nv.add(new BasicNameValuePair("listname",listname));
						nv.add(new BasicNameValuePair("listdate",listdate));
						nv.add(new BasicNameValuePair("callcfn",callcfn));
						nv.add(new BasicNameValuePair("callcln",callcln));
						nv.add(new BasicNameValuePair("callcmob",callcmob));
						
						//http post
						try
						{
							HttpClient httpclient = new DefaultHttpClient();
							HttpPost httppost = new HttpPost("http://bpsi.us/blueplanetsolutions/stlist/insertlist.php");
							httppost.setEntity(new UrlEncodedFormEntity(nv));
							HttpResponse response = httpclient.execute(httppost);
							HttpEntity entity = response.getEntity();
							is = entity.getContent();
						}
						catch(Exception e)
						{
							//text2.setText("Error in http connection");
						}

					
					db1.updateflagl(listid);
				}
					Cursor c4= db1.getcal_log();
					while(c4.moveToNext())
					{	

							log_id  =	c4.getString(0);
							con_id=	c4.getString(1);
							cnt =	c4.getString(2);
							System.out.println(log_id+con_id+cnt);
						
							
						ArrayList<NameValuePair> nv = new ArrayList<NameValuePair>();
						nv.add(new BasicNameValuePair("log_id",log_id));
						nv.add(new BasicNameValuePair("con_id",con_id));
						nv.add(new BasicNameValuePair("cnt",cnt));
						nv.add(new BasicNameValuePair("u_id",u_id));
						System.out.println(u_id+log_id+con_id+cnt);
						//http post
						try
						{
							HttpClient httpclient = new DefaultHttpClient();
							HttpPost httppost = new HttpPost("http://bpsi.us/blueplanetsolutions/stlist/insertclog.php");
							httppost.setEntity(new UrlEncodedFormEntity(nv));
							HttpResponse response = httpclient.execute(httppost);
							HttpEntity entity = response.getEntity();
							is = entity.getContent();
						}
						catch(Exception e)
						{
							//text2.setText("Error in http connection");
						}

					
					db1.updateflagclog(log_id);
				}		
				Cursor c5= db1.getemail_log();
					while(c5.moveToNext())
					{	

							elog_id  =	c5.getString(0);
							econ_id=	c5.getString(1);
							ecnt =	c5.getString(2);
							System.out.println(elog_id+econ_id+ecnt);
				
							
						ArrayList<NameValuePair> nv = new ArrayList<NameValuePair>();
						nv.add(new BasicNameValuePair("elog_id",elog_id));
						nv.add(new BasicNameValuePair("econ_id",econ_id));
						nv.add(new BasicNameValuePair("ecnt",ecnt));
						nv.add(new BasicNameValuePair("u_id",u_id));
						System.out.println(u_id+elog_id+econ_id+ecnt);
						//http post
						try
						{
							HttpClient httpclient = new DefaultHttpClient();
							HttpPost httppost = new HttpPost("http://bpsi.us/blueplanetsolutions/stlist/insertelog.php");
							httppost.setEntity(new UrlEncodedFormEntity(nv));
							HttpResponse response = httpclient.execute(httppost);
							HttpEntity entity = response.getEntity();
							is = entity.getContent();
						}
						catch(Exception e)
						{
							//text2.setText("Error in http connection");
						}

					
					db1.updateflagelog(elog_id);
				}	
					Cursor c6= db1.getsms_log();
					while(c6.moveToNext())
					{	

							slog_id  =	c6.getString(0);
							scon_id=	c6.getString(1);
							scnt =	c6.getString(2);
							System.out.println(slog_id+scon_id+scnt);
						
							
						ArrayList<NameValuePair> nv = new ArrayList<NameValuePair>();
						nv.add(new BasicNameValuePair("slog_id",slog_id));
						nv.add(new BasicNameValuePair("scon_id",scon_id));
						nv.add(new BasicNameValuePair("scnt",scnt));
						nv.add(new BasicNameValuePair("u_id",u_id));
						System.out.println(u_id+slog_id+scon_id+scnt);
						//http post
						try
						{
							HttpClient httpclient = new DefaultHttpClient();
							HttpPost httppost = new HttpPost("http://bpsi.us/blueplanetsolutions/stlist/insertslog.php");
							httppost.setEntity(new UrlEncodedFormEntity(nv));
							HttpResponse response = httpclient.execute(httppost);
							HttpEntity entity = response.getEntity();
							is = entity.getContent();
						}
						catch(Exception e)
						{
							//text2.setText("Error in http connection");
						}

					
					db1.updateflagslog(slog_id);
				}		
		
			try
			{	
				ArrayList<NameValuePair> nv = new ArrayList<NameValuePair>();
				nv.add(new BasicNameValuePair("u_id",u_id));
				HttpClient httpclient = new DefaultHttpClient();
				HttpPost httppost = new HttpPost("http://bpsi.us/blueplanetsolutions/stlist/gettask.php");
			httppost.setEntity(new UrlEncodedFormEntity(nv));
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			is = entity.getContent();
			}
			catch(Exception e)
			{
				//text2.setText("Error in http connection");
			}
			//convert response to string
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
				//text2.setText("Error converting result");
			}
			//parse data


			try{


				JSONArray jArray = new JSONArray(result);

				for(int i=0;i<jArray.length();i++)
				{
					JSONObject json_data = jArray.getJSONObject(i);
					town1 = json_data.getString("t_owner");
					tname1 = json_data.getString("t_name");
					tdesp1 = json_data.getString("t_desp");
					ttype1=json_data.getInt("t_type");
					tprio1=json_data.getString("t_priority");
					tcat1=json_data.getInt("t_cat");
					tsdate1=json_data.getString("t_sdate");
					tddate1=json_data.getString("t_ddate");
					ttime1=json_data.getString("t_time");
					con1=json_data.getInt("con");
					loc1=json_data.getInt("loc");
					//System.out.println(town+tname+tdesp+t_type+t_priority+t_cat+t_sdate+t_ddate+t_time+con+loc);
				}
				//text1.setText("Login Successful!!");
				//name.setText("Welcome "+uname.getText().toString());

			}
			catch(JSONException e)
			{
				//text1.setText("Invalid Login!!");
			}
			//db.Inserttask(town1, tname1, tdesp1,ttype1,tprio1,tcat1,tsdate1,tddate1,ttime1,con1,loc1);
			try
			{	ArrayList<NameValuePair> nv = new ArrayList<NameValuePair>();
				System.out.println("uidd"+u_id);
			nv.add(new BasicNameValuePair("u_id",u_id));
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost("http://bpsi.us/blueplanetsolutions/stlist/getcontacts.php");
			httppost.setEntity(new UrlEncodedFormEntity(nv));
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			is = entity.getContent();
			}
			catch(Exception e)
			{
				//text2.setText("Error in http connection");
			}
			//convert response to string
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
				//.setText("Error converting result");
			}
			//parse data


			try{


				JSONArray jArray = new JSONArray(result);

				for(int i=0;i<jArray.length();i++)
				{
					JSONObject json_data = jArray.getJSONObject(i);
					fname1= json_data.getString("fname");
					lname1 = json_data.getString("lname");
					mobno1 = json_data.getString("mobno");
					mobnoh = json_data.getString("mobnoh");
					mobnow = json_data.getString("mobnow");
					mobnoo = json_data.getString("mobnoo");
					mobnocust = json_data.getString("mobnocust");
					email1=json_data.getString("email");
					emailw=json_data.getString("emailw");
					emailo=json_data.getString("emailo");
					emailcust=json_data.getString("emailcust");
					image1=json_data.getString("image");
					tags1=json_data.getString("tags");
				//System.out.println(fname1+lname1+mobno1+email1+image1+tags1);
				db1.Insert(fname1,lname1,mobno1,mobnoh,mobnow,mobnoo,mobnocust,email1,emailw,emailo,emailcust,image1,tags1);
				}
				//.setText("Login Successful!!");
				//name.setText("Welcome "+uname.getText().toString());

			}
			catch(JSONException e)
			{
				//text1.setText("Invalid Login!!");
			}
			
						
		}//txt1.setText("Sync Successfull!!!");
			}
		
		
	







