//Sync contacts,tasks,call list with server manually
package com.SAB_v1;
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
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TabHost;
import android.widget.TextView;

public class ManualSync extends Activity 
{

	Button yes,no;
	//DatabaseHelper db;
	DataBaseHelper db1;
	String tname,town,tdesp,tprio,tsdate,tddate,ttime,con,loc,ttype,tcat;
	String tname1,town1,tdesp1,tprio1,tsdate1,tddate1,ttime1;
	String listname,listdate,listid,callcdate,callcfn,callcln,callcmob;
	int con1,loc1,ttype1,tcat1,c_id,t_id;
	String elog_id,econ_id,ecnt,slog_id,scon_id,scnt;
	String fname,lname,mobno,email,image,tags;
	String fname1,lname1,mobno1,email1,image1,tags1,log_id,con_id,cnt;
	private RadioButton t,f;
	private Button reset = null;
	private TextView ls1;
	private TextView txt;
	private ListView ls2;	
	private TabHost myTabHost;
	private EditText uname = null;
	private EditText pwd = null;
	private Button login = null,done,cancel;
	private TextView signup1 = null;
	private TextView name = null,txt1;
	private TextView text3 = null;
	private TextView text4 = null;
	private TextView text5 = null;
	private TextView text,text1, text2;
	String u_id;
	String mobnoh,mobnow,mobnoo,mobnocust,emailw,emailo,emailcust;
	String result = "";
	String temp="";
	InputStream is = null;
	@Override
	public void onCreate(Bundle icicle)
	{	
		//db=new DatabaseHelper(this);
		db1=new DataBaseHelper(this);
		requestWindowFeature(Window.FEATURE_NO_TITLE);  
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(icicle);
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
	/*Type :Function
	name:signup
	return type:void
	date:29-6-11
	purpose:To call Register class*/
	protected void signup()
	{
		Intent newActivity = new Intent(this,Register.class);     
		startActivity(newActivity);

	}
	/*Type :Function
	name:initControls
	return type:void
	date:29-6-11
	purpose:To initialize controls*/
	private void initControls()
	{

		text3.setText("");
		text4.setText("");
		text5.setText("");
		login = (Button)findViewById(R.id.login);
		login.setOnClickListener(new Button.OnClickListener() 
		{ 
			public void onClick (View v)
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
	/*Type :Function
	name:reset_func
	return type:void
	date:29-6-11
	purpose:To reset controls*/
	private void reset_func()
	{
		uname.setText("");
		pwd.setText("");
	}
	/*Type :Function
	name:checkValidate
	return type:void
	date:29-6-11
	purpose:validation*/
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
				text1.setText("Sync Successful!!");
				//name.setText("Welcome "+uname.getText().toString());
				sync();
			}
			catch(JSONException e)
			{
				text1.setText("Invalid Login!!");
			}
		}

	}
	/*Type :Function
	name:sync
	return type:void
	date:29-6-11
	purpose:To call sync xml file*/
	private void sync()
	{
		setContentView(R.layout.sync);
		t=(RadioButton)findViewById(R.id.tserver);
		f=(RadioButton)findViewById(R.id.fserver);
		done=(Button)findViewById(R.id.done);
		cancel=(Button)findViewById(R.id.cancel);
		txt1=(TextView)findViewById(R.id.txt);
		done.setOnClickListener(new Button.OnClickListener() 
		{ 
			public void onClick (View v)
			{ 
				sync1();

			}


		});
 
	}
	/*Type :Function
	name:sync1
	return type:void
	date:29-6-11
	purpose:To sync contacts with and from server*/
	protected void sync1() 
	{
 
		if(t.isChecked()==true)
		{
			/*Cursor c2=db.getFlag();
			while(c2.moveToNext())
			{	
				int flag=c2.getInt(0);
				int t_id=c2.getInt(1);
				if(flag==0)
				{*/
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
				}
			*/
		/*	Cursor c3=db1.getFlagC();
			while(c3.moveToNext())
			{	
				int flag=c3.getInt(0);
				int c_id=c3.getInt(1);
				if(flag==0)
				{*/
					Cursor c1= db1.getcontact();
					while(c1.moveToNext())
					{	

						fname =	c1.getString(0);
						lname=	c1.getString(1);
						mobno =	c1.getString(2);
						mobnoh = c1.getString(3);
						mobnow = c1.getString(4);
						mobnoo = c1.getString(5);
						mobnocust = c1.getString(6);
						email =	c1.getString(7);
						emailw = c1.getString(8);
						emailo = c1.getString(9);
						emailcust = c1.getString(10);
						tags =	c1.getString(11);
						c_id=c1.getInt(6);
						System.out.println("contacts are== --"+fname+lname+mobno+mobnoh+mobnow+mobnoo+mobnocust+email+emailw+emailo+emailcust+tags);
						ArrayList<NameValuePair> nv = new ArrayList<NameValuePair>();
						nv.add(new BasicNameValuePair("fname",fname));
						nv.add(new BasicNameValuePair("lname",lname));
						nv.add(new BasicNameValuePair("mobno",mobno));
						nv.add(new BasicNameValuePair("mobnoh",mobnoh));
						nv.add(new BasicNameValuePair("mobnow",mobnow));
						nv.add(new BasicNameValuePair("mobnoo",mobnoo));
						nv.add(new BasicNameValuePair("mobnocust",mobnocust));
						nv.add(new BasicNameValuePair("email",email));
						nv.add(new BasicNameValuePair("emailw",emailw));
						nv.add(new BasicNameValuePair("emailo",emailo));
						nv.add(new BasicNameValuePair("emailcust",emailcust));
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
			
			/*Cursor c4=db1.getFlaglist();
			while(c4.moveToNext())
			{	
				int flag=c4.getInt(0);
				int callid=c4.getInt(1);
				if(flag==0)
				{*/
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
								callcfn=c_1.getString(0);
								callcln=c_1.getString(1);
								
							}
						ArrayList<NameValuePair> nv = new ArrayList<NameValuePair>();
						nv.add(new BasicNameValuePair("listid",listid));
						nv.add(new BasicNameValuePair("listname",listname));
						//nv.add(new BasicNameValuePair("listdate",listdate));
						nv.add(new BasicNameValuePair("callcfn",callcfn));
						nv.add(new BasicNameValuePair("callcln",callcln));
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
		}
		else if(f.isChecked()==true)
		{
			try
			{	ArrayList<NameValuePair> nv = new ArrayList<NameValuePair>();
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
				text2.setText("Error converting result");
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
				text1.setText("Login Successful!!");
				//name.setText("Welcome "+uname.getText().toString());

			}
			catch(JSONException e)
			{
				text1.setText("Invalid Login!!");
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
				text2.setText("Error converting result");
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
					mobnoh = json_data.getString("mobhome");
					mobnow = json_data.getString("mobwork");
					mobnoo = json_data.getString("mobother");
					mobnocust = json_data.getString("mobcust");
					email1=json_data.getString("email");
					emailw=json_data.getString("emailwork");
					emailo=json_data.getString("emailother");
					emailcust=json_data.getString("emailcust");
					image1=json_data.getString("image");
					tags1=json_data.getString("tags"); 
				System.out.println(fname1+lname1+mobno1+email1+image1+tags1);
					db1.Insert(fname1,lname1,mobno1,mobnoh,mobnow,mobnoo,mobnocust,email1,emailw,emailo,emailcust,image1,tags1);
				}
				text1.setText("Login Successful!!");
				//name.setText("Welcome "+uname.getText().toString());

			}
			catch(JSONException e)
			{
				text1.setText("Invalid Login!!");
			}
			
			
		}txt1.setText("Sync Successfull!!!");
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
		
	







