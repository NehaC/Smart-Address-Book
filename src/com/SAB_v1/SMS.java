//SMS
package com.SAB_v1;


import java.util.ArrayList;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SMS extends Activity {
    private static final int MAX_SMS_MESSAGE_LENGTH = 160; 
        private static final int SMS_PORT = 8091;
        private static final String SMS_DELIVERED = "SMS_DELIVERED";
        private static final String SMS_SENT = "SMS_SENT";
        DataBaseHelper data;
        int conid,count,sno;
        EditText phno;
        String no,fname;
        /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sms);
        data=new DataBaseHelper(this);
        Bundle bundle=getIntent().getExtras();
		no=bundle.getString("smno");
		sno=Integer.parseInt(no);
		System.out.println("Sms N"+sno);
        Cursor c1= data.getMNo(sno);
        System.out.println("Sms Nooo"+sno);
		while(c1.moveToNext())
		{
			conid=c1.getInt(0); 
			fname=c1.getString(1);
			System.out.println("conid "+conid);
			System.out.println("Fname "+fname);
		}	
		phno=(EditText)findViewById(R.id.phonenumber);
		phno.setText(no);
        ((Button)findViewById(R.id.send)).setOnClickListener(onButtonClick);
        
        registerReceiver(sendreceiver, new IntentFilter(SMS_SENT));
        registerReceiver(deliveredreceiver, new IntentFilter(SMS_DELIVERED));
        
        registerReceiver(smsreceiver, new IntentFilter("android.provider.Telephony.SMS_RECEIVED"));
    }
    
    @Override
        protected void onDestroy()
        {
        unregisterReceiver(sendreceiver);
        unregisterReceiver(deliveredreceiver);
        unregisterReceiver(smsreceiver);
                
                super.onDestroy();
        }
    /*Type :Function
	name:sendSms
	return type:void
	date:29-6-11
	purpose:To send sms*/	
        private void sendSms(String phonenumber,String message, boolean isBinary)
    {
        SmsManager manager = SmsManager.getDefault();
        
        PendingIntent piSend = PendingIntent.getBroadcast(this, 0, new Intent(SMS_SENT), 0);
        PendingIntent piDelivered = PendingIntent.getBroadcast(this, 0, new Intent(SMS_DELIVERED), 0);
        
        if(isBinary)
        {
                byte[] data = new byte[message.length()];
                
                for(int index=0; index<message.length() && index < MAX_SMS_MESSAGE_LENGTH; ++index)
                {
                        data[index] = (byte)message.charAt(index);
                }
                
                manager.sendDataMessage(phonenumber, null, (short) SMS_PORT, data,piSend, piDelivered);
        }
        else
        {
                int length = message.length();
                
                if(length > MAX_SMS_MESSAGE_LENGTH)
                {
                        ArrayList<String> messagelist = manager.divideMessage(message);
                        
                        manager.sendMultipartTextMessage(phonenumber, null, messagelist, null, null);
                }
                else
                {
                        manager.sendTextMessage(phonenumber, null, message, piSend, piDelivered);
                }
        }
    }
    
    private View.OnClickListener onButtonClick = new View.OnClickListener()
        {
                public void onClick(View v)
                {
                        switch(v.getId())
                        {
                                case R.id.send:
                                {
                                	
                                	String phonenumber = phno.getText().toString();
                                        String message = ((TextView)findViewById(R.id.message)).getText().toString();
                                        boolean isBinary = ((CheckBox)findViewById(R.id.binary)).isChecked();
                                        
                                        sendSms(phonenumber,message, isBinary);
                                        Cursor c3= data.getscnt(conid);
                                		while(c3.moveToNext())
                                		{
                                			count=c3.getInt(0);
                                		}	
                                		if(count==0)
                                		{
                                			count=count+1;
                                			System.out.println("Count "+count);
                                			data.insertsmslog(conid,count);
                                
                                		}
                                		else
                                		{
                                			count=count+1;
                                			data.updatesmscnt(conid, count);
                                   		}
                                		Cursor c=data.getsmsdata();
                                		while(c.moveToNext())
                                		{
                                			int s1=c.getInt(0);
                                			int s2=c.getInt(1);
                                			System.out.println("SID "+s1);
                                			System.out.println("Cnt "+s2);
                                		}
                                        break;
                                }
                        }
                }
        };
        
        private BroadcastReceiver sendreceiver = new BroadcastReceiver()
        {
                @Override
                public void onReceive(Context context, Intent intent)
                {
                        String info = "Send information: ";
                        
                        switch(getResultCode())
                        {
                                case Activity.RESULT_OK: info += "send successful"; break;
                                case SmsManager.RESULT_ERROR_GENERIC_FAILURE: info += "send failed, generic failure"; break;
                                case SmsManager.RESULT_ERROR_NO_SERVICE: info += "send failed, no service"; break;
                                case SmsManager.RESULT_ERROR_NULL_PDU: info += "send failed, null pdu"; break;
                                case SmsManager.RESULT_ERROR_RADIO_OFF: info += "send failed, radio is off"; break;
                        }
                        
                        Toast.makeText(getBaseContext(), info, Toast.LENGTH_SHORT).show();

                }
        };
        
        private BroadcastReceiver deliveredreceiver = new BroadcastReceiver()
        {
                @Override
                public void onReceive(Context context, Intent intent)
                {
                        String info = "Delivery information: ";
                        
                        switch(getResultCode())
                        {
                                case Activity.RESULT_OK: info += "delivered"; break;
                                case Activity.RESULT_CANCELED: info += "not delivered"; break;
                        }
                        
                        Toast.makeText(getBaseContext(), info, Toast.LENGTH_SHORT).show();
                }
        };
        
        private BroadcastReceiver smsreceiver = new BroadcastReceiver()
        {
                @Override
                public void onReceive(Context context, Intent intent)
                {
                Bundle bundle = intent.getExtras();        
                SmsMessage[] msgs = null;
                
                if(null != bundle)
                {
                        String info = "Text SMS from ";
                    Object[] pdus = (Object[]) bundle.get("pdus");
                    msgs = new SmsMessage[pdus.length];
                    
                    for (int i=0; i<msgs.length; i++){
                        msgs[i] = SmsMessage.createFromPdu((byte[])pdus[i]);                
                        info += msgs[i].getOriginatingAddress();                     
                        info += "\n*****TEXT MESSAGE*****\n";
                        info += msgs[i].getMessageBody().toString();
                    }

                    Toast.makeText(context, info, Toast.LENGTH_SHORT).show();
                }                         
                }
        };
}
