package com.SAB_v1;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SendMail extends Activity {
    /**
     * Called with the activity is first created.
     */
	DataBaseHelper data;
	int conid,count;
	String fname,mail,name;
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.sendmail);
        data=new DataBaseHelper(this);
		Bundle bundle=getIntent().getExtras();
		mail=bundle.getString("semail");
		System.out.println("Nameeeeeeeeee1"+mail);
        final Button send = (Button) this.findViewById(R.id.send);
		final EditText userid = (EditText) this.findViewById(R.id.userid);
        final EditText password = (EditText) this.findViewById(R.id.password);
        final EditText from = (EditText) this.findViewById(R.id.from);
        final EditText to = (EditText) this.findViewById(R.id.to);
        final EditText subject = (EditText) this.findViewById(R.id.subject);
        final EditText body = (EditText) this.findViewById(R.id.body);
        to.setText(mail);
        send.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                GMailSender sender = new GMailSender(userid.getText().toString(), password.getText().toString());
                try 
                {
                    sender.sendMail(subject.getText().toString(),
                            body.getText().toString(),
                            from.getText().toString(),
                            to.getText().toString());
                    Toast.makeText(SendMail.this,"Your Mail has been send successfully.",Toast.LENGTH_SHORT).show();
                    Cursor c1= data.getName(mail);
            		while(c1.moveToNext())
            		{
            			conid=c1.getInt(0);
            			fname=c1.getString(1);
            			System.out.println("conid "+conid);
            			System.out.println("Fname "+fname);
            		}	
                    Cursor c3= data.getcount(conid);
            		while(c3.moveToNext())
            		{
            			count=c3.getInt(0);
            			System.out.println("Count "+count);
            		}	
            		if(count==0)
            		{
            			count=count+1;
            			System.out.println("Count "+count);
            			data.insertmaillog(conid,count);
            			
            		}
            		else if(count>0)
            		{	count=count+1;
               			data.updateemailcnt(conid, count);
            		}
            		Cursor c5= data.getmaildata(conid);
            		while(c5.moveToNext())
            		{
            			int eid=c5.getInt(0);
            			int count1=c5.getInt(1);
            			System.out.println("eid........ "+eid);
            			System.out.println("Count........ "+count1);
            		}	

                } catch (Exception e) 
                {
                    Log.e("SendMail", e.getMessage(), e);
                }
            }
        });
    }
}
