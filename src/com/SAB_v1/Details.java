// Displays contacts details
package com.SAB_v1;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Details extends Activity 
{
	Button bimg,call;
	EditText edfn,edln,edmno,edemail,edtag;
	EditText eefn,eeln,eemno,eeemail,eetag;
	TextView tfn,tln,tmno,temail,ttag;
	TextView tbday,torg;
	String sln,sfn,smno,semail,stag,sn,sn1,simg;
	String seln,sefn,semno,seemail,setag,sen; 
	String sdln,sdfn,sdmno,sdemail,sdtag,sdn;
	String bday,org;
	String[] s;
	DataBaseHelper data;
	Button ed,de,up,del,back,eba,dba; 
	int inid;
	String img1,imgpath;
	String name1,cno,sno;
	int column_index;
	Intent intent=null;
	String logo,imagePath,Logo;
	private static final int SELECT_PICTURE = 1;
	String selectedImagePath;
	//ADDED
	String filemanagerstring;
	ImageView img3,img2;
	public void onCreate(Bundle savedInstanceState)
	{
		requestWindowFeature(Window.FEATURE_NO_TITLE);  
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.details);
		data=new DataBaseHelper(this);
		s=new String[3];
		Bundle bundle=getIntent().getExtras();
		name1=bundle.getString("name2");
		s=name1.split(" ");
		sn=s[0];
		sn1=s[2];
		System.out.println("NAME "+sn);
		System.out.println("LNAME "+sn1);
		tfn=(TextView)findViewById(R.id.dfname);
		tln=(TextView)findViewById(R.id.dlname);
		tmno=(TextView)findViewById(R.id.dmno);
		temail=(TextView)findViewById(R.id.demail); 
		ttag=(TextView)findViewById(R.id.dtags);
		call=(Button)findViewById(R.id.bcall);
		img2=(ImageView)findViewById(R.id.gimg2);
		tbday=(TextView)findViewById(R.id.dbday);
		torg=(TextView)findViewById(R.id.dorg);
		Cursor c=data.Data(sn,sn1);
		while (c.moveToNext())
		{	
			inid=c.getInt(0); 
			sfn=c.getString(1);
			sln=c.getString(2);
			smno=c.getString(3);
			semail=c.getString(4);
			simg=c.getString(5);
			stag=c.getString(6);
			System.out.println("Data ID "+inid);
			System.out.println("IMAGE: "+simg);
			System.out.println("EMAIL: "+semail);
			System.out.println("TAG: "+stag);
			tfn.setText(""+sfn);
			tln.setText(""+sln);
			tmno.setText(""+smno);
			temail.setText(""+semail);
			ttag.setText(""+stag);	
			/* final byte[] image_byte = c.getBlob(5);
	        	  Bitmap image_bitmap = BitmapFactory.decodeByteArray(image_byte, 0, image_byte.length);*/
			//img3.setImageBitmap(image_bitmap);
			// System.out.println("IMAGEssss: "+image_byte)
			
		}
		if(simg==null)
		{
			
			img2.setImageResource(R.drawable.propic);
		}
		else
		{
		Bitmap bm = BitmapFactory.decodeFile(simg);
		img2.setImageBitmap(bm); 
		}
		
		Cursor cursor=data.getMoreInfo(inid);
		while(cursor.moveToNext())
		{
			org=cursor.getString(4);
			bday=cursor.getString(15);
			torg.setText(""+org);
			tbday.setText(""+bday);
			
		}
		
		
		call.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View v) 
			{
				Intent i=new Intent(Details.this,Call1.class);
				startActivity(i);
			}
		});

	}
 
	
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu1, menu);
		return true;
	}
	//Call1.class

	public boolean onOptionsItemSelected(MenuItem item) 
	{
		switch (item.getItemId()) 
		{
		case R.id.medit:Intent i = new Intent(this, Edit.class);
		Bundle bun2=new Bundle();
		bun2.putInt("inid",inid);
		System.out.println("Idd....... "+inid);
		i.putExtras(bun2);
		startActivity(i);
		break;
		case R.id.mdelete:Intent i2 = new Intent(this, Delete.class);
		Bundle bun3=new Bundle();
		bun3.putInt("inid",inid);
		System.out.println("Id....... "+inid);
		i2.putExtras(bun3);
		startActivity(i2);
		break;
		case R.id.msync:Intent i4 = new Intent(this, Sync.class);
		Bundle bun4=new Bundle();
		bun4.putInt("inid",inid);
		System.out.println("Id....... "+inid);
		i4.putExtras(bun4);
		startActivity(i4);
		break;
		case R.id.mmail:Intent i6 = new Intent(this, SendMail.class);
		Bundle bun=new Bundle();
		bun.putString("semail",semail);
		//System.out.println("Email "+semail);
		i6.putExtras(bun);
		startActivity(i6);
		break;
		case R.id.msms: Intent i1 = new Intent(this, SMS.class);
		Bundle bun1=new Bundle();
		bun1.putString("smno",smno);
		//System.out.println("Sms No "+smno);
		i1.putExtras(bun1);
		startActivity(i1);
		break;
		}
		return true;
	}    
	
	/*Type :Function
	name:call1
	return type:void
	date:29-6-11
	purpose:Select the image from sdcard*/
	public void call1()
	{
		Intent intent = new Intent();
		intent.setType("image/*");
		intent.setAction(Intent.ACTION_GET_CONTENT);
		startActivityForResult(Intent.createChooser(intent,
				"Select Picture"), SELECT_PICTURE);
	}
	/*Type :Function
	name:OnActivityResult
	return type:void
	date:29-6-11
	purpose:To set the image*/
	//UPDATED
	/*@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == Activity.RESULT_OK) {
			if (requestCode == SELECT_PICTURE) {
				Uri selectedImageUri = data.getData();

				//OI FILE Manager
				filemanagerstring = selectedImageUri.getPath();

				//MEDIA GALLERY
				selectedImagePath = getPath(selectedImageUri);
				img2.setImageURI(selectedImageUri);

                 
				

				imagePath.getBytes();

				imagePath=(imagePath.toString());
				System.out.println("MY PATH: "+imagePath);
				Bitmap bm = BitmapFactory.decodeFile(imagePath);

			}

		}

	}*/
	/*Type :Function
	name:getPath
	return type:void
	date:29-6-11
	purpose:To get the image path */
	//UPDATED!
	/*public String getPath(Uri uri) {
		String[] projection = { MediaColumns.DATA };
		Cursor cursor = managedQuery(uri, projection, null, null, null);
		column_index = cursor
		.getColumnIndexOrThrow(MediaColumns.DATA);
		cursor.moveToFirst();
		imagePath = cursor.getString(column_index);

		return cursor.getString(column_index);
	}*/


}
