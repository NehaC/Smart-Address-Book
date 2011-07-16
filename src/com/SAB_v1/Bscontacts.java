//Take snap of business card and sync image path with server 
package com.SAB_v1;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
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
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.MediaStore.MediaColumns;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.*;

public class Bscontacts extends Activity
{
	Button b1,b2,b3,b4;
	EditText e1;
	String filemanagerstring,imagePath,img,ipath,in;
	private static final int SELECT_PICTURE = 1;
	private static final int TAKE_PICTURE_WITH_CAMERA = 0;

	DataBaseHelper db1;
	String selectedImagePath;
	int column_index;
	private EditText uname = null;
	private EditText pwd = null;
	private Button login = null,done,cancel,reset;
	TextView text1,text2;
	String u_id;
	String result = "";
	String temp="";
	InputStream is = null;
	HttpURLConnection connection = null;
    DataOutputStream outputStream = null;
    DataInputStream inputStream = null;

    String pathToOurFile ="";
    String urlServer = "http://192.168.1.196/handle_upload.php";
    String lineEnd = "\r\n";
    String twoHyphens = "--";
    String boundary =  "*****";
    Button up;
    int bytesRead, bytesAvailable, bufferSize;
    byte[] buffer;
    int maxBufferSize = 1*1024*1024;
	
	@Override
	public void onCreate(Bundle icicle)
	{
		requestWindowFeature(Window.FEATURE_NO_TITLE);  
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(icicle);
		setContentView(R.layout.bsc);
		b1=(Button)findViewById(R.id.br);
		b2=(Button)findViewById(R.id.snap);
		b3=(Button)findViewById(R.id.send);
		b4=(Button)findViewById(R.id.cancel);
		e1=(EditText)findViewById(R.id.browse);
		b1.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View v) 
			{
				browse();
			}
		});
		b2.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View v) 
			{
				img = getImageName(); 
				takesnap(img);
			}
		});
		b3.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View v) 
			{
				send();
			}
		});
	}

	/*Type :Function
	name:send
	return type:void
	date:29-6-11
	purpose:To call login business contacts xml file*/
	protected void send() 
	{	
		
		db1=new DataBaseHelper(this);
		setContentView(R.layout.loginbs);
		uname = (EditText)findViewById(R.id.uname);
		pwd = (EditText)findViewById(R.id.pwd);
		text1=(TextView)findViewById(R.id.text1);
		text2=(TextView)findViewById(R.id.text2);
		reset = (Button)findViewById(R.id.reset);
		initControls();
		}

	/*Type :Function
	name:initControls
	return type:void
	date:29-6-11
	purpose:To initialize controls*/
	private void initControls()
	{

		
		login = (Button)findViewById(R.id.login);
		login.setOnClickListener(new Button.OnClickListener() 
		{ public void onClick (View v)
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
	purpose:Reset controls*/

	private void reset_func()
	{
		uname.setText("");
		pwd.setText("");
	}
	/*Type :Function
	name:checkvalidate
	return type:void
	date:29-6-11
	purpose:To get the image path (from selected image)*/
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
				HttpPost httppost = new HttpPost("http://192.168.1.196/select_query_server.php");
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
				send1(ipath);
				System.out.println("PATH........." +ipath);
				 in=ipath.substring(ipath.lastIndexOf("/")+1);
				 System.out.println("PATH" +in);
				insertdb(in,u_id);
				
				
			}
			catch(JSONException e)
			{
				text1.setText("Invalid Login!!");
			}
		}

	}
		
	/*Type :Function
	name:insertdb
	return type:void
	date:29-6-11
	purpose:sync image path of business card to server*/
	private void insertdb(String in2, String uId)
	{
		System.out.println("iname..."+in2+"uid..."+uId);
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("iname",in2));
		nameValuePairs.add(new BasicNameValuePair("u_id",uId));

		//http post
		try
		{
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost("http://192.168.1.196/insert_image.php");
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			is = entity.getContent();
		}
		catch(Exception e)
		{
			//text2.setText("Error in http connection");
		}
		
	}
	/*Type :Function
	name:checkvalidate
	return type:void
	date:29-6-11
	purpose:To post*/
	private void send1(String img1) {
		
		        try
		        {
		        FileInputStream fileInputStream = new FileInputStream(new File(pathToOurFile) );

		        URL url = new URL(urlServer);
		        connection = (HttpURLConnection) url.openConnection();
		        pathToOurFile=img1;
		        // Allow Inputs & Outputs
		        connection.setDoInput(true);
		        connection.setDoOutput(true);
		        connection.setUseCaches(false);

		        // Enable POST method
		        connection.setRequestMethod("POST");

		        connection.setRequestProperty("Connection", "Keep-Alive");
		        connection.setRequestProperty("Content-Type", "multipart/form-data;boundary="+boundary);

		        outputStream = new DataOutputStream( connection.getOutputStream() );
		        outputStream.writeBytes(twoHyphens + boundary + lineEnd);
		        outputStream.writeBytes("Content-Disposition: form-data; name=\"uploadedfile\";filename=\"" + pathToOurFile +"\"" + lineEnd);
		        outputStream.writeBytes(lineEnd);

		        bytesAvailable = fileInputStream.available();
		        bufferSize = Math.min(bytesAvailable, maxBufferSize);
		        buffer = new byte[bufferSize];

		        // Read file
		        bytesRead = fileInputStream.read(buffer, 0, bufferSize);

		        while (bytesRead > 0)
		        {
		        outputStream.write(buffer, 0, bufferSize);
		        bytesAvailable = fileInputStream.available();
		        bufferSize = Math.min(bytesAvailable, maxBufferSize);
		        bytesRead = fileInputStream.read(buffer, 0, bufferSize);
		        }

		        outputStream.writeBytes(lineEnd);
		        outputStream.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

		        // Responses from the server (code and message)
		        int serverResponseCode = connection.getResponseCode();
		        String serverResponseMessage = connection.getResponseMessage();

		        fileInputStream.close();
		        outputStream.flush();
		        outputStream.close();
		        }
		        catch (Exception ex)
		        {
		        //Exception handling
		        }
		    }
	
	
	/*Type :Function
	name:takesnap
	return type:void
	date:29-6-11
	purpose:To take snap or photo of business card*/
	protected void takesnap(String ImageName) {
		 Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);


         cameraIntent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT,
                 Uri.fromFile(new File(ImageName)));

          startActivityForResult(cameraIntent, TAKE_PICTURE_WITH_CAMERA);
         	
	}
	/*Type :Function
	name:getImageName
	return type:void
	date:29-6-11
	purpose:To get the image name*/
	private String getImageName() {
        String imgname = "";
        String imgpath = "";
        String strDirectoy = "BS";
        try {
            imgname = String.format("pic%d.png", System.currentTimeMillis());

            imgpath = "sdcard/"+strDirectoy + "/" + imgname;

            File file = new File(strDirectoy);
            boolean exists = file.exists();
            if (!exists) {
                boolean success = (new File(strDirectoy)).mkdir();
                if (success)
                    Log.e("Directory Created", "Directory: " + strDirectoy
                            + " created");
                else

                    Log.e("Directory Creation","Directory Creation failed");
            }



        } catch (Exception e) {

            e.printStackTrace();
        }

        return imgpath;
    }
	/*Type :Function
	name:browse
	return type:void
	date:29-6-11
	purpose:To select or browse picture from sdcard*/
	protected void browse() 
	{
		
		Intent intent = new Intent();
		intent.setType("image/*");
		intent.setAction(Intent.ACTION_GET_CONTENT);
		startActivityForResult(Intent.createChooser(intent,
				"Select Picture"), SELECT_PICTURE);


	}
	/*Type :Function
	name:OnAcivityResult
	return type:void
	date:29-6-11
	purpose:To set the image*/
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


				//img.setImageURI(selectedImageUri);

				imagePath.getBytes();

				e1.setText(imagePath.toString());
				ipath=e1.getText().toString();
				System.out.println("path..."+ipath);


			}

		}

	}
	/*Type :Function
	name:getPath
	return type:void
	date:29-6-11
	purpose:To get the image path (from selected image)*/
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
	
	