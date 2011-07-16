//edit contacts
package com.SAB_v1;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore.MediaColumns;
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
import android.widget.Toast;

public class Edit extends Activity
{
	Button bimg;
	EditText edfn,edln,edmno,edemail,edtag;
	EditText eefn,eeln,eemno,eeemail,eetag;
	TextView tfn,tln,tmno,temail,ttag,timg;
	String sln,sfn,smno,semail,stag,sn,sn1,simg;
	String seln,sefn,semno,seemail,setag,sen;
	String sdln,sdfn,sdmno,sdemail,sdtag,sdn;
	String[] s;
	DataBaseHelper data;
	Button ed,de,up,del,back,eba,dba;
	int inid1,id,inid;
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
		data=new DataBaseHelper(this);
		Bundle bundle=getIntent().getExtras();
		inid1=bundle.getInt("inid");
		System.out.println("Edit id..."+inid1);
		setContentView(R.layout.edit);
		eefn=(EditText)findViewById(R.id.efname);
		eeln=(EditText)findViewById(R.id.elname);
		eemno=(EditText)findViewById(R.id.emno);
		eeemail=(EditText)findViewById(R.id.eemail);
		eetag=(EditText)findViewById(R.id.etags);
		img2= (ImageView)findViewById(R.id.gimg1);
		Cursor c=data.Data1(inid1);
		while (c.moveToNext())
		{	
			id=c.getInt(0); 
			sfn=c.getString(1);
			sln=c.getString(2);
			smno=c.getString(3);
			semail=c.getString(4);
			simg=c.getString(5);
			stag=c.getString(6);
			System.out.println("Data ID "+id);
			System.out.println("IMAGE: "+simg);
			System.out.println("TAG: "+stag);
		}
		eefn.setText(sfn);
		eeln.setText(sln);
		eemno.setText(smno);
		eeemail.setText(semail);
		eetag.setText(stag);
		timg=(TextView)findViewById(R.id.Button01);
		timg.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View v) 
			{
				call1();
			}
		});
		
		up=(Button)findViewById(R.id.update);
		up.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View v) 
			{
				sefn=eefn.getText().toString();
				seln=eeln.getText().toString();
				semno=eemno.getText().toString();
				seemail=eeemail.getText().toString();
				setag=eetag.getText().toString();
				updatecont(inid1);
			}
		});


	}
	/*Type :Function
	name:updatecont
	return type:void
	date:29-6-11
	purpose:To update contact in database*/
	public void updatecont(int contid)
	{
		data.updatecontact(contid, sefn,seln,semno,seemail,imagePath,setag);
		Toast t=Toast.makeText(getBaseContext(),"Contact Updated",Toast.LENGTH_LONG);
		t.show();

	}
	
	/*Type :Function
	name:call1
	return type:void
	date:29-6-11
	purpose:To select image from sdcard*/
	public void call1()
	{
		Intent intent = new Intent();
		intent.setType("image/*");
		intent.setAction(Intent.ACTION_GET_CONTENT);
		startActivityForResult(Intent.createChooser(intent,
				"Select Picture"), SELECT_PICTURE);
	}
	/*Type :Function
	name:onAcivityResult
	return type:void
	date:29-6-11
	purpose:To set the image */
	//UPDATED
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == Activity.RESULT_OK) {
			if (requestCode == SELECT_PICTURE) {
				Uri selectedImageUri = data.getData();

				//OI FILE Manager
				filemanagerstring = selectedImageUri.getPath();

				//MEDIA GALLERY
				selectedImagePath = getPath(selectedImageUri);
				img2.setImageURI(selectedImageUri);


				//img.setImageURI(selectedImageUri);

				imagePath.getBytes();

				imagePath=(imagePath.toString());
				System.out.println("MY PATH: "+imagePath);
				Bitmap bm = BitmapFactory.decodeFile(imagePath);

			}

		}

	}
	/*Type :Function
	name:getPath
	return type:void
	date:29-6-11
	purpose:To get the image path*/
	//UPDATED!
	public String getPath(Uri uri) {
		String[] projection = { MediaColumns.DATA };
		Cursor cursor = managedQuery(uri, projection, null, null, null);
		column_index = cursor
		.getColumnIndexOrThrow(MediaColumns.DATA);
		cursor.moveToFirst();
		imagePath = cursor.getString(column_index);

		return cursor.getString(column_index);
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
		System.out.println("Id....... "+inid);
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
}
