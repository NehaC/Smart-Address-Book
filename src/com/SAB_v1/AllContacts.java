// views contacts 
package com.SAB_v1;
import java.util.ArrayList;
import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;
//import android.provider.Contacts.People;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
public class AllContacts extends Activity implements OnItemClickListener
{	
	TelephonyManager tm;	
	GridView grid_main;
	DataBaseHelper data;
	Button bback,badd; 
	ProgressDialog dialog;
	String name,name1,name2,sp,num;
	int cnt=0;
	String id;
	final static String LOG_TAG = "PocketMagic";
	TextView txt1,txt2,txt3;       
	int increment;
	int maximum;
	int count;
	private ArrayList<String> results = new ArrayList<String>();
	private ArrayList<String> results1 = new ArrayList<String>();
	private ArrayList<String> results2 = new ArrayList<String>();
	ArrayAdapter<String> adapter;
	

	@Override
	public void onCreate(Bundle icicle)
	{                       
		super.onCreate(icicle);
		data=new DataBaseHelper(this);
		/*requestWindowFeature(Window.FEATURE_NO_TITLE);  
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);*/
		setContentView(R.layout.grid);
		addcontacts();
		progress();
		 Cursor c=data.getData();

		showtask(c);

		grid_main = (GridView)findViewById(R.id.GridView01);
		grid_main.setAdapter(new ImageAdapter(this));
	}
	/*Type :Function
	name:progress
	return type:void
	date:29-6-11
	purpose:To create and view peogress bar*/
	public void progress()
	{
		// get the increment value from the text box
		increment = 10;
		System.out.println("INc....."+increment);
		dialog = new ProgressDialog(this);
		dialog.setCancelable(true);
		dialog.setMessage("Loading...");
		// set the progress to be horizontal
		dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		// reset the bar to the default value of 0
		dialog.setProgress(0);

		// get the maximum value
		// convert the text value to a integer
		Cursor c1=data.getData();
		count=c1.getCount();
		//System.out.println("Count....."+count);
		maximum = count;
		//System.out.println("Max....."+maximum);
		// set the maximum value
		dialog.setMax(maximum);
		// display the progress bar
		dialog.show();

		// create a thread for updating the progress bar
		Thread background = new Thread (new Runnable() {
			public void run() {
				try {
					// enter the code to be run while displaying the progressbar.
					// This example is just going to increment the progress bar:
					// So keep running until the progress value reaches maximum value

					while (dialog.getProgress()<= dialog.getMax())
					{
						// wait 500ms between each update
						Thread.sleep(200);

						// active the update handler
						 progressHandler.sendMessage(progressHandler.obtainMessage());
					}
				} catch (java.lang.InterruptedException e) {
					// if something fails do something smart
				}
			}
		});

		// start the background thread
		background.start();

	}

		// handler for the background updating
		Handler progressHandler = new Handler() 
		{
			public void handleMessage(Message msg) 
			{
				Cursor c1=data.getData();
				count=c1.getCount();
				//System.out.println("Count....."+count);
				maximum = count;
				//System.out.println("Max....."+maximum);
				//dialog.incrementProgressBy(increment);
				if (dialog.getProgress() >= maximum)
				{
					//dialog.cancel();
					dialog.dismiss();
				}
				else 
				{
					dialog.incrementProgressBy(increment);
				}

			}

		};

		/*Type :Function
		name:addcontacts
		return type:void
		date:29-6-11
		purpose:To retrieve contacts from phone and inserts to SAB database*/
	private void addcontacts()
	{  
//		ContentResolver cr = getContentResolver();
//	
//		Cursor cursor = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI,null,null,null,ContactsContract.Contacts.DISPLAY_NAME +" ASC");
//			//getContentResolver().query(People.CONTENT_URI, null, null, null,People.NAME + " ASC");
//		startManagingCursor(cursor);            
//		while(cursor.moveToNext())
//		{ 	         
//			// ContentResolver cr = getContentResolver();
//			long id =  cursor.getLong(cursor.getColumnIndex(ContactsContract.Contacts._ID));
//			String id1 =  cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
//			String name=cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
//			//String pdata = cursor.getString( cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DATA));
//			 String phone = cursor.getString(cursor.getColumnIndexOrThrow(Phone.NUMBER));
//			//int num=cursor.getInt(cursor.getColumnIndex(ContactsContract.Contacts.Phone.NUMBER));
//			System.out.println("name=="+name);
//			 if (Integer.parseInt(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0)
//			 {
//	                Cursor pCur = cr.query(Phone.CONTENT_URI,null,Phone.CONTACT_ID +" = ?", new String[]{id1}, null);
//
//	          while(pCur.moveToNext())
//	          {
//	              phone = cursor.getString(cursor.getColumnIndex(Phone.NUMBER));
//	              Log.v("getting phone number", "Phone Number: " + phone);
//	          }
//			//Uri uri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, id);
//			
//	 	    }
//		}
//			//Bitmap bitmap = People.loadContactPhoto(getBaseContext(),uri, R.drawable.icon, null);
//			          
//			System.out.println("no:=="+num);
//			Cursor c=data.getNo(num);
//			int cnt=c.getCount();
//			System.out.println("cnt=="+cnt);
//			if(cnt==0)
//			{
//				System.out.println("no::");
//				data.Insert(name,null,num,null,null,null,null,null,null,null,null,null,null);
//			}
		ContentResolver cr = getContentResolver();
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
                null, null, null, null);
        if (cur.getCount() > 0) {
            while (cur.moveToNext()) {
                  String id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));
                  String name = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                  if (Integer.parseInt(cur.getString(
                        cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
                     Cursor pCur = cr.query(
                               ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                               null,
                               ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = ?",
                               new String[]{id}, null);
                     while (pCur.moveToNext()) {
                         String phoneNo = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                         //Toast.makeText(NativeContentProvider.this, "Name: " + name + ", Phone No: " + phoneNo, Toast.LENGTH_SHORT).show();
                     System.out.println("num"+phoneNo);
                     String num=phoneNo.replace("-","");
                     System.out.println("num11"+num);
                    Cursor c=data.getNo(num);
         			int cnt=c.getCount();
         			//System.out.println("cnt=="+cnt);
         			if(cnt==0)
         			{
         				System.out.println("no::");
         				data.Insert(name,null,num,null,null,null,null,null,null,null,null,null,null);
         			}

                     
                     
                     }
                    pCur.close();
                }
            }
        }
    }
		
	

	
	
	/*Type :Function
	name:showtask
	return type:void
	date:29-6-11
	purpose:View Contacts*/
	private void showtask(Cursor c)             
	{
		while (c.moveToNext())
		{	
			name=c.getString(0);
			name1=c.getString(1);
			num=c.getString(2); 
			id=c.getString(3);
			results1.add(num);
			results2.add(id);
			if(name1==null)
			{
				name1="";
			}
			results.add(name+"  "+name1);

			System.out.println("My Name :"+name);
			//System.out.println("Mob :"+mob);
		}

	}
	
	public void call()
	{
		Intent i=new Intent(AllContacts.this,SAB_v1.class);
		startActivity(i);
	}
	/*Type :Function
	name:call1
	return type:void
	date:29-6-11
	purpose:To call Add contacts class*/
	public void call1()
	{
		Intent i=new Intent(AllContacts.this,AddContact.class);
		startActivity(i);
	}
	public void onItemClick(AdapterView<?> a, View v, int position, long id) 
	{
		System.out.println("Position..."+position);
		String ans= (String) a.getItemAtPosition(position);
		System.out.println("Value is "+ans);
		Intent i= new Intent(this,Details.class);
		Bundle bun=new Bundle();
		bun.putString("name2",ans);
		i.putExtras(bun);
		startActivity(i);


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
	public class ImageAdapter extends BaseAdapter{
		Context mContext;
		public static final int ACTIVITY_CREATE = 10;
		public ImageAdapter(Context c){
			mContext = c;
		}
		/*Type :Function
		name:getCount
		return type:void
		date:29-6-11
		purpose:To get total number of entries in database for add contacts table*/
		public int getCount() {
			Cursor c=data.getData();
			cnt=c.getCount();
			return cnt;
		}
		/*Type :Function
		name:checkvalidate
		return type:void
		date:29-6-11
		purpose:To get the image path from phone contact and set that image*/
		public View getView(final int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			View v;
			if(convertView==null){
				LayoutInflater li = getLayoutInflater();
				v = li.inflate(R.layout.icon, null);
				TextView tv = (TextView)v.findViewById(R.id.icon_text);
				tv.setText(""+results.get(position));
				tv.setOnClickListener(new MyOnClickListener1(position));
				ImageView iv = (ImageView)v.findViewById(R.id.icon_image);
				iv.setImageResource(R.drawable.call1);
				iv.setOnClickListener(new MyOnClickListener(position)); 
				/*  iv.setOnClickListener(new ImageView.OnClickListener() {
	                public void onClick(View v) 
	                {
	                	String name=(String) grid_main.getItemAtPosition(position);
	              	System.out.println("name=="+name);
	                }
	             }
	             );*/

			}
			else
			{
				v = convertView;
			}
			return v;
		}
		public Object getItem(int arg0) {

			return null;
		}
		public long getItemId(int arg0) {

			return 0;
		}
		/*private OnItemClickListener itemClickListener = new OnItemClickListener() {
		    public void onItemClick(AdapterView<?> parent, View v, int position, long id)
		    {
		    	grid_main.getItemAtPosition(position)
		    }
		};*/


	}
	class MyOnClickListener implements OnClickListener  
	{  
		private final int position;  

		public MyOnClickListener(int position)  
		{  
			this.position = position;  

		}                     

		public void onClick(View v)  
		{  

			int pos=position;
			String num=results1.get(pos);
			System.out.println("position"+pos+"name=="+num);
			Intent intent = new Intent(Intent.ACTION_CALL);
			intent.setData(Uri.parse("tel:" +num));
			startActivity(intent);


		}               
	}  
	class MyOnClickListener1 implements OnClickListener  
	{  
		private final int position;  

		public MyOnClickListener1(int position)  
		{  
			this.position = position;  

		}  

		public void onClick(View v)  
		{  

			int pos=position;

			String name=results.get(pos);
			System.out.println("Position... nameeeeeeee"+position+" iddd"+id);

			//String ans= (String) a.getItemAtPosition(position);
			//System.out.println("Value is "+ans);
			Intent i= new Intent(AllContacts.this,Details.class);
			Bundle bun=new Bundle();
			bun.putString("name2",name);
			i.putExtras(bun);
			startActivity(i);


		}               
	}  
}
