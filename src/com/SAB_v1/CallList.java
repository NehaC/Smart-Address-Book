
package com.SAB_v1;
import java.util.ArrayList;
import android.app.Activity;
import android.app.Dialog;
import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
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
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TabHost.TabSpec;
public class CallList extends Activity implements OnItemClickListener
{	GridView grid_main;
	Button b1,b2,bback,badd;
   DataBaseHelper data;
   String name;
   int callid;
	ListView lv1;
	String ans;
	private ArrayList<String> results = new ArrayList<String>();
	@Override
	public void onCreate(Bundle icicle)
	{	
		requestWindowFeature(Window.FEATURE_NO_TITLE);  
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
     	super.onCreate(icicle);
		setContentView(R.layout.grid1);
		
		data=new DataBaseHelper(this);
		lv1=(ListView)findViewById(R.id.List);
		showtask();
		
		grid_main = (GridView)findViewById(R.id.GridView01);
		grid_main.setAdapter(new Adapter(this));
		
}     
	/*Type :Function
	name:showtask
	return type:void
	date:29-6-11
	purpose:To view call list*/
	private void showtask()
	{
		Cursor c=data.getDataCall();
			while (c.moveToNext())
			{	
				callid=c.getInt(0);
				name=c.getString(1);
				results.add(name);
			}

		
		
	}
	/*Type :Function
	name:call
	return type:void
	date:29-6-11
	purpose:calls main class*/
	public void call()
	{
		Intent i=new Intent(CallList.this,SAB_v1.class);
		startActivity(i);
	}        
	/*Type :Function
	name:call1
	return type:void
	date:29-6-11
	purpose:Calls class to create call list*/
	public void call1()
	{
		Intent i=new Intent(CallList.this,DialogforCreate.class);
		startActivity(i);
	}
	public void onItemClick(AdapterView<?> a, View v, int position, long id) 
	{
		System.out.println("Position..."+position);
		ans= (String) a.getItemAtPosition(position);
		System.out.println("Value is "+ans);
		Intent i= new Intent(this,DisplayCallList.class);
		Bundle bun=new Bundle();
		bun.putString("name",ans);
		System.out.println("Name "+name);
		i.putExtras(bun);
		startActivity(i);


	}


public boolean onCreateOptionsMenu(android.view.Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu2, menu);
		return true;
	}


	public boolean onOptionsItemSelected(MenuItem item) 
	{
		switch (item.getItemId()) 
		{
		case R.id.addlist:Intent i=new Intent(CallList.this,DialogforCreate.class);
		startActivity(i);
		break;
		case R.id.delete:
			Intent i1=new Intent(CallList.this,DeleteCallList.class);
			startActivity(i1);
		break;
		

		}
		return true;
	}  
	

	public class Adapter extends BaseAdapter{
		Context mContext;
		public static final int ACTIVITY_CREATE = 10;
		public Adapter(Context c){
			mContext = c;
		}
		public int getCount() {
			Cursor c=data.getDataCall();
			int cnt=c.getCount();
			return cnt;

		}
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}
		
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}
		
		public View getView(final int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			View v;
			if(convertView==null){
				LayoutInflater li = getLayoutInflater();
				v = li.inflate(R.layout.icon, null);
				TextView tv = (TextView)v.findViewById(R.id.icon_text);
				tv.setText(""+results.get(position));
				tv.setOnClickListener(new MyOnClickListener(position));
			

			}
			else
			{
				v = convertView;
			}
			return v;
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
			String ans=results.get(pos);
			Intent i= new Intent(CallList.this,DisplayCallList.class);
			Bundle bun=new Bundle();
			bun.putString("name",ans);
			System.out.println("Name "+name);
			i.putExtras(bun);
			startActivity(i);


		}               
	}  
	}

