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
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
public class DisplayCallList extends Activity implements OnItemClickListener
{	
	TelephonyManager tm;	
	GridView grid_main;
	DataBaseHelper data;
	Button bback,badd; 
	ProgressDialog dialog;
	String name,name2,sp,num;
	int cnt=0;
	String id;
	final static String LOG_TAG = "PocketMagic";
	TextView txt1,txt2,txt3;       
		int callid,id1,id2;
	String ans1;
	String name1,n,n1,oname;
	private ArrayList<String> results = new ArrayList<String>();
	private ArrayList<String> results1 = new ArrayList<String>();
	private ArrayList<String> results2 = new ArrayList<String>();
	ArrayAdapter<String> adapter;
	String nam,nam1,ans;

	@Override
	public void onCreate(Bundle icicle)
	{                       
		super.onCreate(icicle);
		data=new DataBaseHelper(this);
		setContentView(R.layout.grid1);
		Bundle bundle = getIntent().getExtras(); 
		name1=bundle.getString("name");
		System.out.println("My name is "+name1);
		showtask();
		grid_main = (GridView)findViewById(R.id.GridView01);
		grid_main.setAdapter(new ImageAdapter(this));
	}
	
	/*Type :Function
	name:showtask
	return type:void
	date:29-6-11
	purpose:View Contacts*/
	private void showtask()
	{
		Cursor c1=data.getDataCallid(name1);
		while (c1.moveToNext())
		{	
			id1=c1.getInt(0);
			System.out.println("IDs "+id1);
		}
		Cursor c=data.getDataCallList(id1);
		System.out.println("ID1 "+id1);
		while (c.moveToNext())
		{	

			n=c.getString(0);
			n1=c.getString(1);
			results.add(n+"  "+n1);
			System.out.println("FName"+n);
			System.out.println("LName"+n1);
			//oname=n+n1;
		}
		Cursor c2=data.getCallListid1(n,n1);
		while(c2.moveToNext())
		{
			id2=c2.getInt(0);
			System.out.println("ContactID "+id2);
			
		}
		Cursor c4=data.getMobNo(id2);
		while(c4.moveToNext())
		{
			num=c4.getString(0);
			System.out.println("MOBLIE NO" +num);
			results1.add(num);
		}
	}
	public void call()
	{
		Intent i=new Intent(DisplayCallList.this,SAB_v1.class);
		startActivity(i);
	}
	/*Type :Function
	name:call1
	return type:void
	date:29-6-11
	purpose:To call Add contacts class*/
	public void call1()
	{
		Intent i=new Intent(DisplayCallList.this,AddContact.class);
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
		inflater.inflate(R.menu.calllistmenu, menu);
		return true;
	}


	public boolean onOptionsItemSelected(MenuItem item) 
	{
		switch (item.getItemId()) 
		{
		case R.id.cmedit:Intent i=new Intent(DisplayCallList.this,EditList.class);
		startActivity(i);
		break;
		case R.id.cmdelete:
			/*Intent i1=new Intent(DisplayCallList.this,DeleteList.class);
			Bundle bun=new Bundle();
			bun.putString("name2",name1);
			System.out.println("Name "+name);
			i1.putExtras(bun);
			startActivity(i1);*/
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
			if(convertView==null)
			{
				LayoutInflater li = getLayoutInflater();
				v = li.inflate(R.layout.icon, null);
				TextView tv = (TextView)v.findViewById(R.id.icon_text);
				String pos=results.get(position);
				tv.setText(pos);
				//System.out.println(""+results.get(position));
				tv.setOnClickListener(new MyOnClickListener1(position));
				ImageView iv = (ImageView)v.findViewById(R.id.icon_image);
				iv.setImageResource(R.drawable.call1);
				iv.setOnClickListener(new MyOnClickListener(position)); 
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
			System.out.println("position"+pos+"no=="+num);
			Intent intent = new Intent(Intent.ACTION_CALL);
			intent.setData(Uri.parse("tel:" +num));
			startActivity(intent);
			Cursor c6=data.getNAME(num);
			while(c6.moveToNext())
			{
				nam=c6.getString(0);
				nam1=c6.getString(1);
				System.out.println("N1 "+nam);
				System.out.println("N12 "+nam1);
				ans=nam+" "+nam1;
			}
			tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
			tm.listen(mPhoneListener, PhoneStateListener.LISTEN_CALL_STATE);
			
		}               
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
					Toast.makeText(DisplayCallList.this, "CALL_STATE_RINGING", Toast.LENGTH_SHORT).show();
					break;
				case TelephonyManager.CALL_STATE_OFFHOOK:
					Toast.makeText(DisplayCallList.this, "CALL_STATE_OFFHOOK", Toast.LENGTH_SHORT).show();
					break;
				case TelephonyManager.CALL_STATE_IDLE:
					Toast.makeText(DisplayCallList.this, "CALL_STATE_IDLE", Toast.LENGTH_SHORT).show();
					Intent i = new Intent(DisplayCallList.this,UpdateStatus.class);
					Bundle bun=new Bundle();
					bun.putString("name",ans);
					System.out.println("Name "+ans);
					i.putExtras(bun);
					startActivity(i);
					//onRestart(); 
					break;
				default:
					Toast.makeText(DisplayCallList.this, "default", Toast.LENGTH_SHORT).show();
					Log.i("Default", "Unknown phone state=" + state);
				}
			} catch (Exception e) 
			{
				Log.i("Exception", "PhoneStateListener() e = " + e);
			}
		}
	};

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
			Intent i= new Intent(DisplayCallList.this,Details.class);
			Bundle bun=new Bundle();
			bun.putString("name2",name);
			i.putExtras(bun);
			startActivity(i);


		}               
	}  
}
