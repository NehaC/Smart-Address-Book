package com.SAB_v1;

import java.io.InputStream;
import java.util.ArrayList;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TabHost;
import android.widget.TextView;

public class Register extends Activity 
{
	private EditText fname,mname,lname,ph,email,pass,gen,rp;
	private RadioButton m,f;
	private Button reg,cancel;
	private TextView text3 = null;
	private TextView text4 = null;
	private TextView text5 = null;
	private TextView text,text1, text2;
	String ans;
	InputStream is = null;
	@Override
		public void onCreate(Bundle icicle)
		{
			super.onCreate(icicle);
			 setContentView(R.layout.signup);
			 fname = (EditText)findViewById(R.id.fname);
			 mname = (EditText)findViewById(R.id.mname);
			 lname = (EditText)findViewById(R.id.lname);
			 email = (EditText)findViewById(R.id.email);
			 ph = (EditText)findViewById(R.id.ph);
			 pass = (EditText)findViewById(R.id.pass);
			 rp=(EditText)findViewById(R.id.rp);
			 m=(RadioButton)findViewById(R.id.female);
			 f=(RadioButton)findViewById(R.id.male);
			 text1=(TextView)findViewById(R.id.text1);
		        text2=(TextView)findViewById(R.id.text2);
		    	text3 = (TextView)findViewById(R.id.text3);
		    	text4 = (TextView)findViewById(R.id.text4);
		    	text5 = (TextView)findViewById(R.id.text5);
		    	cancel=(Button)findViewById(R.id.cancel);
			 reg=(Button)findViewById(R.id.reg);
			 initc();
}
		private void initc() 
		{

	    	text3.setText("");
	    	text4.setText("");
	    	text5.setText("");
	 	    reg.setOnClickListener(new Button.OnClickListener() 
	    			{ public void onClick (View v)
	    				{ 
	    					
	    					checkValidate();
	    					
	    				}
	    			});
	 	   cancel.setOnClickListener(new Button.OnClickListener() 
			{ public void onClick (View v)
				{ 
					
					Intent i = new Intent(Register.this,Settings.class);
					startActivity(i);
					
				}
			});
	
		}
		protected void checkValidate() {
			
		if(m.isChecked())
			{
				ans="Female";
				System.out.println("ans..."+ans);
				//pr1=rb1.getId();
			}
			if(f.isChecked())
			{
				ans="Male";
				//ans=rb2.getText().toString();
				//rb2.setId(1);
				//pr1=rb1.getId();
			}
		
		if((fname.getText().toString()).equals(""))
	    	{
	    		text1.setText("First name not entered!!");
	    	}
	    	/*else if((mname.getText().toString()).equals(""))
	    	{
	  		text1.setText("Middle name not entered!!");
	    	}*/
			if((lname.getText().toString()).equals(""))
	    	{
	    		text1.setText("Last name not entered!!");
	    	}
	    	else if((ph.getText().toString()).equals(""))
	    	{
	    		text1.setText("Phone not entered!!");
	    	}
			if((email.getText().toString()).equals(""))
	    	{
	    		text1.setText("Email not entered!!");
	    	}
	    	else if((pass.getText().toString()).equals(""))
	    	{
	    		text1.setText("Password not entered!!");
	    	}
	    	else if((rp.getText().toString()).equals(""))
	    	{
	    		text1.setText("Password not entered!!");
	    	}
	    	else if(!((pass.getText().toString()).equals (rp.getText().toString()))) 
	    	{
	    		text1.setText("Sorry your Passwords donot Match!!");
	    	
	    	}else
	    	{
	        
	       
	        //the year data to send
	        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
	        nameValuePairs.add(new BasicNameValuePair("fname",fname.getText().toString()));
	        nameValuePairs.add(new BasicNameValuePair("mname",mname.getText().toString()));
	        nameValuePairs.add(new BasicNameValuePair("lname",lname.getText().toString()));
	        nameValuePairs.add(new BasicNameValuePair("ph",ph.getText().toString()));
	        nameValuePairs.add(new BasicNameValuePair("email",email.getText().toString()));
	        nameValuePairs.add(new BasicNameValuePair("pass",pass.getText().toString()));
	        nameValuePairs.add(new BasicNameValuePair("gen",ans));
	       // nameValuePairs.add(new BasicNameValuePair("pwd",pwd.getText().toString()));
	        //http post
	         try
	         {
	                HttpClient httpclient = new DefaultHttpClient();
	                HttpPost httppost = new HttpPost("http://bpsi.us/blueplanetsolutions/stlist/iuser.php");
	               httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	                HttpResponse response = httpclient.execute(httppost);
	                HttpEntity entity = response.getEntity();
	                is = entity.getContent();
	                text2.setText("Registration Successfull!!");
	         }
	         catch(Exception e)
	         {
	        	 	text2.setText("Error in http connection");
	         }
	         	
    			
		}
		
}
} 