//Add contacts with all info
package com.SAB_v1;
import java.io.InputStream;
import java.net.URI;
import java.util.Calendar;
import java.util.GregorianCalendar;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore.MediaColumns;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
public class AddContact extends Activity
{
	DataBaseHelper data;
	Spinner shome,swork,smob,soth;
	Spinner sehome,sework,seoth;             
		int flag,flag1;
		TextView text1;
	String scust;
	Button addex,remno,remh,remw,remo;
	Button addeex,remhe,remwe,remoe;
	String mshome,mswork,msother,mscust;
	EditText mwork,mhome,moth,ecust,mno;
	String eshome,eswork,esother,escust;
	EditText ework,ehome,eoth;
	private ArrayAdapter<CharSequence> adapter;
	ArrayAdapter <CharSequence> adapter1,adapter2,adapter3;
	ArrayAdapter  <CharSequence> ehadapter,ewadapter,eoadapter;
	Button b1,b3,bim,back,clear,aclear,aback;
	TextView b2;
	ImageView img3,img2;
	EditText fn,ln,mobno,eid,eimg,tag;
	String strfn,strln,streid,strimg,strmno,strtag;
	String name,ans;
	String img1,sdate;
	int column_index,id;
	Intent intent=null;
	String logo,imagePath,Logo;
	private static final int SELECT_PICTURE = 1; 
	String selectedImagePath;
	 private int mYear;
	 private int mMonth;
	 private int mDay;
	 private String array_spinner1[],array_spinner[],array_spinner2[],array_spinner3[];
	 private String array_spinnerhe[],array_spinnerwe[],array_spinneroe[];
	Button badd,bvl,bri;
	EditText vl,rin,wadds,com,comadd,jtit,nn,bir,aniv,pa;
	String strvlink,strring,strwadd;
	String strcomp,strcompadd,strjtitle,name1;
	String strnname,strpadd,strbirth,strani,lname;
	//Add more info 
	EditText eorghc,eorgwc,eorgoc;
	EditText eorghp,eorgwp,eorgop;
	EditText epah,epaw,epao;
	Spinner spah,sorgw,sorgo,spaw,spao;
	int flag2,flag3;
	Button addmoreorg,addmorepa;
	Button rempah,remorgw,remorgo,rempaw,rempao;
	ArrayAdapter  <CharSequence> adapterorgw,adapterorgo;
	ArrayAdapter  <CharSequence> adapterpah,adapterpaw,adapterpao;
	 private String array_spinnerorgw[],array_spinnerorgo[];
	 private String array_apinnerpah[],array_spinnerpaw[],array_spinnerpao[];
	 String strorgcw,strorgco,strorgpw,strorgpo,strorgcust;
	 String strpao,strpacust,strpaw;
	static final int DATE_DIALOG_ID = 0;
	//ADDED
	String filemanagerstring;
	
	@Override
	public void onCreate(Bundle icicle)
	{
		requestWindowFeature(Window.FEATURE_NO_TITLE);  
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(icicle);
		setContentView(R.layout.addcontact);
		data= new DataBaseHelper(this);
		 Calendar cal = new GregorianCalendar();
			int month = cal.get(Calendar.MONTH);
			int year = cal.get(Calendar.YEAR);
			int day = cal.get(Calendar.DAY_OF_MONTH);
			sdate=(+ (month + 1) + "-" + day + "-" + year);  
		 
		 text1=(TextView)findViewById(R.id.text1);
		fn=(EditText)findViewById(R.id.fname);
		ln=(EditText)findViewById(R.id.lname);
		img2= (ImageView)findViewById(R.id.gimg1);
		mobno=(EditText)findViewById(R.id.mmno);
		eid=(EditText)findViewById(R.id.ehmail);
		//eimg=(EditText)findViewById(R.id.image);
		tag=(EditText)findViewById(R.id.atags);
		mhome=(EditText)findViewById(R.id.hmno);
		mwork=(EditText)findViewById(R.id.wmno);
		moth=(EditText)findViewById(R.id.omno);
		remno=(Button)findViewById(R.id.mnoexp);
		remh=(Button)findViewById(R.id.hmnexp);
		remw=(Button)findViewById(R.id.wmnexp);
		remo=(Button)findViewById(R.id.omnexp);
		ework=(EditText)findViewById(R.id.ewmail);
		eoth=(EditText)findViewById(R.id.eomail);
		remhe=(Button)findViewById(R.id.bhemail);
		remwe=(Button)findViewById(R.id.bwmail);
		remoe=(Button)findViewById(R.id.bomail);
		//Mobile no spinner
		smob= (Spinner) findViewById(R.id.smob);
		adapter2 = new ArrayAdapter(this,android.R.layout.simple_spinner_item);
		adapter2.add("Mobile");
		adapter2.add("Home");
		adapter2.add("Work");
		adapter2.add("Work Fax");
		adapter2.add("Home Fax");
		adapter2.add("Pager");
		adapter2.add("Other");
		adapter2.add("Custom");
		smob.setAdapter(adapter2);
		//Mobile no home spinner
		shome= (Spinner) findViewById(R.id.shome);
		adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item);
		adapter.add("Home");
		adapter.add("Mobile");
		adapter.add("Work");
		adapter.add("Work Fax");
		adapter.add("Home Fax");
		adapter.add("Pager");
		adapter.add("Other");
		adapter.add("Custom");
		
		shome.setAdapter(adapter);
		//Mobile no  work spinner
		swork= (Spinner) findViewById(R.id.swork);
		adapter1 = new ArrayAdapter(this,android.R.layout.simple_spinner_item);
		adapter1.add("Work");
		adapter1.add("Mobile");
		adapter1.add("Home");
		adapter1.add("Work Fax");
		adapter1.add("Home Fax");
		adapter1.add("Pager");
		adapter1.add("Other");
		adapter1.add("Custom");
		swork.setAdapter(adapter1);
		//Mobile no other spinner
		soth= (Spinner) findViewById(R.id.sother);
		adapter3 = new ArrayAdapter(this,android.R.layout.simple_spinner_item);
		adapter3.add("Other");
		adapter3.add("Mobile");
		adapter3.add("Work");
		adapter3.add("Work Fax");
		adapter3.add("Home Fax");
		adapter3.add("Pager");
		adapter3.add("Home");
		adapter3.add("Custom");
		soth.setAdapter(adapter3);
		flag=0;
		//Add More fields button for mobile no
		addex=(Button)findViewById(R.id.mmnexp);
		addex.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View v) 
			{
				if(flag==0)
				{
			    mobno.setVisibility(0);
			    smob.setVisibility(0);
			    remno.setVisibility(0);
			    flag=1;
				}
				else if(flag==1)
				{
			    mhome.setVisibility(0);
			    shome.setVisibility(0);
			    remh.setVisibility(0);
			    flag=2;
				}
				else
				if(flag==2)
				{
			    mwork.setVisibility(0);
			    swork.setVisibility(0);
			    remw.setVisibility(0);
			    flag=3;
				}
				else
				if(flag==3)
				{
			    moth.setVisibility(0);
			    soth.setVisibility(0);
			    remo.setVisibility(0);
			    flag=0;
			    }
			}
			});
		//remove particular field button
		remno.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View v) 
			{
				
			    mobno.setVisibility(View.GONE);
			    smob.setVisibility(View.GONE);
			    remno.setVisibility(View.GONE);
			    flag=0;
			    flag=1;
			    flag=2;
			    flag=3;
			    				
			}
		});	
		//remove particular field button
		remh.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View v) 
			{
				
			    mhome.setVisibility(View.GONE);
			    shome.setVisibility(View.GONE);
			    remh.setVisibility(View.GONE);
			    flag=0;
			    flag=1;
			    flag=2;
			    flag=3;
			    				
			}
		});	
		//remove particular field button
		remw=(Button)findViewById(R.id.wmnexp);
		remw.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View v) 
			{
				
			    mwork.setVisibility(View.GONE);
			    swork.setVisibility(View.GONE);
			    remw.setVisibility(View.GONE);
			    flag=0;
			    flag=1;
			    flag=2;
			    flag=3;
			    				
			}
		});	
		//remove particular field button
		remo=(Button)findViewById(R.id.omnexp);
		remo.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View v) 
			{
				
			    moth.setVisibility(View.GONE);
			    soth.setVisibility(View.GONE);
			    remo.setVisibility(View.GONE);
			    flag=0;
			    flag=1;
			    flag=2;
			    flag=3;
			    				
			}
		});	
		int spi=shome.getSelectedItemPosition();
   
		smob.setOnItemSelectedListener(
				new AdapterView.OnItemSelectedListener() {
					public void onItemSelected(AdapterView<?> a,View view,int position,long id)
					{
						if(position==7)
						{
							 final Dialog dialog3 = new Dialog(AddContact.this);

		                      dialog3.setContentView(R.layout.dialog);
		                      dialog3.setTitle("Custom Label Name");


		                       dialog3.setCancelable(true);
		                       //set up button
		                       Button button = (Button) dialog3.findViewById(R.id.ok);
		                       ecust=(EditText)dialog3.findViewById(R.id.ecustom);

		                       button.setOnClickListener(new OnClickListener() {

		                       public void onClick(View v) 
		                       {
	                               
		                    	      
		                               
		                               addNewSpinnerItem3();
		                               dialog3.dismiss();
		                               


		                           }

		                       });
		                       Button button1 = (Button) dialog3.findViewById(R.id.cancel);

		                         button1.setOnClickListener(new OnClickListener() {

		                       public void onClick(View v) {

		                               finish();

		                           }

		                       });

		                       //now that the dialog is set up, it's time to show it    

		                       dialog3.show();

		                   }



						}

					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub
						
					}
				   });
		shome.setOnItemSelectedListener(
				new AdapterView.OnItemSelectedListener() {
					public void onItemSelected(AdapterView<?> a,View view,int position,long id)
					{
						if(position==7)
						{
							 final Dialog dialog = new Dialog(AddContact.this);

		                      dialog.setContentView(R.layout.dialog);
		                      dialog.setTitle("Custom Label Name");


		                       dialog.setCancelable(true);
		                       //set up button
		                       Button button = (Button) dialog.findViewById(R.id.ok);
		                       ecust=(EditText)dialog.findViewById(R.id.ecustom);

		                       button.setOnClickListener(new OnClickListener() {

		                       public void onClick(View v) 
		                       {
	                               
		                    	      
		                               
		                               addNewSpinnerItem();
		                               dialog.dismiss();
		                               


		                           }

		                       });
		                       Button button1 = (Button) dialog.findViewById(R.id.cancel);

		                       button1.setOnClickListener(new OnClickListener() {

		                       public void onClick(View v) {

		                               finish();

		                           }

		                       });

		                       //now that the dialog is set up, it's time to show it    

		                       dialog.show();

		                   }



						}

					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub
						
					}
				   });
		swork.setOnItemSelectedListener(
				new AdapterView.OnItemSelectedListener() {
					public void onItemSelected(AdapterView<?> a,View view,int position,long id)
					{
						if(position==7)
						{
							 final Dialog dialog1 = new Dialog(AddContact.this);

		                      dialog1.setContentView(R.layout.dialog);
		                      dialog1.setTitle("Custom Label Name");


		                       dialog1.setCancelable(true);
		                       //set up button
		                       Button button = (Button) dialog1.findViewById(R.id.ok);
		                       ecust=(EditText)dialog1.findViewById(R.id.ecustom);

		                       button.setOnClickListener(new OnClickListener() {

		                       public void onClick(View v) 
		                       {
	                               
		                    	      
		                               
		                               addNewSpinnerItem1();
		                               dialog1.dismiss();
		                               


		                           }

							
		                       });
		                       Button button1 = (Button) dialog1.findViewById(R.id.cancel);

		                       button1.setOnClickListener(new OnClickListener() {

		                       public void onClick(View v) {

		                               finish();

		                           }

		                       });

		                       //now that the dialog is set up, it's time to show it    

		                       dialog1.show();

		                   }



						}

					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub
						
					}
				   });	
		soth.setOnItemSelectedListener(
				new AdapterView.OnItemSelectedListener() {
					public void onItemSelected(AdapterView<?> a,View view,int position,long id)
					{
						if(position==7)
						{
							 final Dialog dialog2 = new Dialog(AddContact.this);

		                      dialog2.setContentView(R.layout.dialog);
		                      dialog2.setTitle("Custom Label Name");


		                       dialog2.setCancelable(true);
		                       //set up button
		                       Button button = (Button) dialog2.findViewById(R.id.ok);
		                       ecust=(EditText)dialog2.findViewById(R.id.ecustom);

		                       button.setOnClickListener(new OnClickListener() {

		                       public void onClick(View v) 
		                       {
	                               
		                    	      
		                               
		                               addNewSpinnerItem2();
		                               dialog2.dismiss();
		                               


		                           }

							
		                       });
		                       Button button1 = (Button) dialog2.findViewById(R.id.cancel);

		                       button1.setOnClickListener(new OnClickListener() {

		                       public void onClick(View v) {

		                               finish();

		                           }

		                       });

		                       //now that the dialog is set up, it's time to show it    

		                       dialog2.show();

		                   }



						}

					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub
						
					}
				   });	
		
		//Email home spinner
		sehome= (Spinner) findViewById(R.id.shmail);
		ehadapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item);
		ehadapter.add("Home");
		ehadapter.add("Work");
		ehadapter.add("Other");
		ehadapter.add("Custom");
		sehome.setAdapter(ehadapter);
		//Email work spinner
		sework= (Spinner) findViewById(R.id.swmail);
		ewadapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item);
		ewadapter.add("Work");
		ewadapter.add("Home");
		ewadapter.add("Other");
		ewadapter.add("Custom");
		sework.setAdapter(ewadapter);			
		//Email other spinner
		seoth= (Spinner) findViewById(R.id.somail);
		eoadapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item);
		eoadapter.add("Other");
		eoadapter.add("Work");
		eoadapter.add("Home");
		eoadapter.add("Custom");
		seoth.setAdapter(eoadapter);	
			flag1=0;	
		//Add More fields button for email id
		addeex=(Button)findViewById(R.id.bhmail);
		addeex.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View v) 
			{
				if(flag1==0)
				{
			    eid.setVisibility(0);
			    sehome.setVisibility(0);
			    remhe.setVisibility(0);
			    flag1=1;
			    flag1=2;
				}
				else if(flag1==1)
				{
			    ework.setVisibility(0);
			    sework.setVisibility(0);
			    remwe.setVisibility(0);
			    flag1=0;
			    flag1=2;
				}
				else
				if(flag1==2)
				{
			    eoth.setVisibility(0);
			    seoth.setVisibility(0);
			    remoe.setVisibility(0);
			    flag1=0;
			    flag1=1;
			    }
			}
			});
		//remove particular field button
		remhe.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View v) 
			{
				
			    eid.setVisibility(View.GONE);
			    sehome.setVisibility(View.GONE);
			    remhe.setVisibility(View.GONE);
			    flag1=0;
			    flag1=1;
			    flag1=2;
			    			    				
			}
		});	
		//remove particular field button
		remwe.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View v) 
			{
				
			    ework.setVisibility(View.GONE);
			    sework.setVisibility(View.GONE);
			    remwe.setVisibility(View.GONE);
			    flag1=0;
			    flag1=1;
			    flag1=2;
			    			    				
			}
		});	
		//remove particular field button
		remoe.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View v) 
			{
				
			    eoth.setVisibility(View.GONE);
			    seoth.setVisibility(View.GONE);
			    remoe.setVisibility(View.GONE);
			    flag1=0;
			    flag1=1;
			    flag1=2;
			   			    				
			}
		});	
		
		
		b1 = (Button)findViewById(R.id.save);
		b1.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View v) 
			{
				checkvalidate();
				
			}
		});

		b3 = (Button)findViewById(R.id.addm);
		b3.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View v) 
			{
				call();

			}
		});

		b2 = (TextView)findViewById(R.id.bimage);
		b2.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View v)  
			{
				call1();
		
			}
		});
				/*clear = (Button)findViewById(R.id.clear);
		clear.setOnClickListener(new OnClickListener() {

			public void onClick(View v)
			{

				fn.setText("");
				ln.setText("");
				mobno.setText("");
				eid.setText("");
				eimg.setText("");
				tag.setText("");  	 
			} 

		});   */

	}
	/*Type :Function
	name:addnewSpinnerItem
	return type:void
	date:29-6-11
	purpose:To add Items to spinner dynamically*/

	private void addNewSpinnerItem3() {
		mscust=ecust.getText().toString();
		CharSequence textHolder = "" +mscust; 
		adapter3.insert(textHolder, 0);
		smob.setAdapter(adapter3);
		}
	/*Type :Function
	name:addnewSpinnerItem
	return type:void
	date:29-6-11
	purpose:To add Items to spinner dynamically*/
	private void addNewSpinnerItem() {
		mscust=ecust.getText().toString();
		CharSequence textHolder = "" +mscust; 
		adapter.insert(textHolder, 0);
		shome.setAdapter(adapter);
		}
	/*Type :Function
	name:addnewSpinnerItem
	return type:void
	date:29-6-11
	purpose:To add Items to spinner dynamically*/
	private void addNewSpinnerItem1() {
		mscust=ecust.getText().toString();
		CharSequence textHolder = "" +mscust; 
		adapter1.insert(textHolder, 0);
		swork.setAdapter(adapter1);
		
	}
	/*Type :Function
	name:addnewSpinnerItem
	return type:void
	date:29-6-11
	purpose:To add Items to spinner dynamically*/
	private void addNewSpinnerItem2() {
		mscust=ecust.getText().toString();
		CharSequence textHolder = "" +mscust; 
		adapter2.insert(textHolder, 0);
		soth.setAdapter(adapter2);
		}
	/*Type :Function
	name:addnewSpinnerItem
	return type:void
	date:29-6-11
	purpose:To add Items to spinner dynamically*/
	private void addNewSpinnerItem4() 
	{
		mscust=ecust.getText().toString();
		CharSequence textHolder = "" +mscust; 
		adapterpah.insert(textHolder, 0);
		spah.setAdapter(adapterpah);
	}
	/*Type :Function
	name:addnewSpinnerItem
	return type:void
	date:29-6-11
	purpose:To add Items to spinner dynamically*/
	private void addNewSpinnerItem5() 
	{
		mscust=ecust.getText().toString();
		CharSequence textHolder = "" +mscust; 
		adapterorgw.insert(textHolder, 0);
		sorgw.setAdapter(adapterorgw);
	}
	/*Type :Function
	name:addnewSpinnerItem
	return type:void
	date:29-6-11
	purpose:To add Items to spinner dynamically*/
	private void addNewSpinnerItem6() 
	{
		mscust=ecust.getText().toString();
		CharSequence textHolder = "" +mscust; 
		adapterorgo.insert(textHolder, 0);
		sorgo.setAdapter(adapterorgo);
	}
	/*Type :Function
	name:addnewSpinnerItem
	return type:void
	date:29-6-11
	purpose:To add Items to spinner dynamically*/
	private void addNewSpinnerItem7() 
	{
		mscust=ecust.getText().toString();
		CharSequence textHolder = "" +mscust; 
		adapterpaw.insert(textHolder, 0);
		spaw.setAdapter(adapterpaw);
	}
	/*Type :Function
	name:addnewSpinnerItem
	return type:void
	date:29-6-11
	purpose:To add Items to spinner dynamically*/
	private void addNewSpinnerItem8() 
	{
		mscust=ecust.getText().toString();
		CharSequence textHolder = "" +mscust; 
		adapterpao.insert(textHolder, 0);
		spao.setAdapter(adapterpao);
	}
	/*Type :Function
	name:checkvalidate
	return type:void
	date:29-6-11
	purpose:validation*/
	public void checkvalidate()
	{
		if((fn.getText().toString()).equals(""))
    	{
    		text1.setText("Name not entered!!");
    	}
    	else if((mobno.getText().toString()).equals(""))
    	{
    		text1.setText("Phone not entered!!");
    	}	
    	else
    	{
    		name=fn.getText().toString();
			strln=ln.getText().toString();
			strmno=mobno.getText().toString();
			streid=eid.getText().toString();
			strtag=tag.getText().toString();
			mshome=mhome.getText().toString();
			mswork=mwork.getText().toString();
			msother=moth.getText().toString();
			eswork=ework.getText().toString();
			esother=eoth.getText().toString();
			data.Insert(name, strln, strmno,mshome,mswork,msother,mscust, streid,eswork,esother,mscust,imagePath, strtag);
			Toast.makeText(AddContact.this, "Added Successfully",Toast.LENGTH_SHORT).show();
			Cursor c=data.getData();
			while(c.moveToNext())
			{
				String name1=c.getString(0);
				System.out.println("Name:"+name1);
				
			}
    	}
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

	/*Type :Function
	name:call
	return type:void
	date:29-6-11
	purpose:to call add more details information for contacts xml file*/
	public void call()
	{

		setContentView(R.layout.addmoreinfo);
		
		wadds=(EditText)findViewById(R.id.wadd);
		comadd=(EditText)findViewById(R.id.compadd);
		epah=(EditText)findViewById(R.id.epah);
		nn=(EditText)findViewById(R.id.nname);
		bir=(EditText)findViewById(R.id.birth);
		aniv=(EditText)findViewById(R.id.ani);
		eorgwc=(EditText)findViewById(R.id.eorgcw);
		eorgoc=(EditText)findViewById(R.id.eorgco);
		eorgwp=(EditText)findViewById(R.id.eorgpw);
		eorgop=(EditText)findViewById(R.id.eorgpo);
		epaw=(EditText)findViewById(R.id.epaw);
		epao=(EditText)findViewById(R.id.epao);
		addmoreorg=(Button)findViewById(R.id.borg);
		addmorepa=(Button)findViewById(R.id.bpa);
		rempah=(Button)findViewById(R.id.bpah);
		remorgw=(Button)findViewById(R.id.borgw);
		remorgo=(Button)findViewById(R.id.borgo);
		rempaw=(Button)findViewById(R.id.bpaw);
		rempao=(Button)findViewById(R.id.bpao);
		spah=(Spinner)findViewById(R.id.spah);
		sorgw=(Spinner)findViewById(R.id.sorgw);
		sorgo=(Spinner)findViewById(R.id.sorgo);
		spaw=(Spinner)findViewById(R.id.spaw);
		spao=(Spinner)findViewById(R.id.spao);
		
			
		//Organization  spinner for work
		adapterorgw = new ArrayAdapter(this,android.R.layout.simple_spinner_item);
		adapterorgw.add("Work");
		adapterorgw.add("Home");
		adapterorgw.add("Other");
		adapterorgw.add("Custom");
		sorgw.setAdapter(adapterorgw);
		
		//Organization  spinner for other
		adapterorgo = new ArrayAdapter(this,android.R.layout.simple_spinner_item);
		adapterorgo.add("Other");
		adapterorgo.add("Work");
		adapterorgo.add("Home");
		adapterorgo.add("Custom");
		sorgo.setAdapter(adapterorgo);
		
		//Postal address  spinner for home
		adapterpah = new ArrayAdapter(this,android.R.layout.simple_spinner_item);
		adapterpah.add("Home");
		adapterpah.add("Work");
		adapterpah.add("Other");
		adapterpah.add("Custom");
		spah.setAdapter(adapterpah);
		
		//Postal Address spinner for work
		adapterpaw = new ArrayAdapter(this,android.R.layout.simple_spinner_item);
		adapterpaw.add("Work");
		adapterpaw.add("Other");
		adapterpaw.add("Custom");
		spaw.setAdapter(adapterpaw);
		
		//Postal Address spinner for other
		adapterpao = new ArrayAdapter(this,android.R.layout.simple_spinner_item);
		adapterpao.add("Other");
		adapterpao.add("Work");
		adapterpao.add("Custom");
		spao.setAdapter(adapterpao);
		
		flag2=0;	
		//Add More fields button for org
		addmoreorg.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View v) 
			{
				if(flag2==0)
				{
					 eorgwc.setVisibility(0);
					    eorgwp.setVisibility(0);
					    sorgw.setVisibility(0);
					    remorgw.setVisibility(0);
					   flag2=1;
				}
				else if(flag2==1)
				{
					eorgoc.setVisibility(0);
				    eorgop.setVisibility(0);
				    sorgo.setVisibility(0);
				    remorgo.setVisibility(0);
			        flag2=0;
				}
				}
			});
				
		//remove org field for work
		remorgw.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View v) 
			{
				
				eorgwc.setVisibility(View.GONE);
			    eorgwp.setVisibility(View.GONE);
			    sorgw.setVisibility(View.GONE);
			    remorgw.setVisibility(View.GONE);
			    flag2=0;
			    flag2=1;
			    flag2=2;
			    			    				
			}
		});	
		
		//remove org field for other
		remorgo.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View v) 
			{
				
				eorgoc.setVisibility(View.GONE);
			    eorgop.setVisibility(View.GONE);
			    sorgo.setVisibility(View.GONE);
			    remorgo.setVisibility(View.GONE);
			    flag2=0;
			    flag2=1;
			    flag2=2;
			    			    				
			}
		});	
		
		flag3=0;	
		//Add More fields button for org
		addmorepa.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View v) 
			{
				if(flag3==0)
				{
			    epah.setVisibility(0);
			    spah.setVisibility(0);
			    rempah.setVisibility(0);
			    flag3=1;
			    flag3=2;
				}
				else if(flag3==1)
				{
			    epaw.setVisibility(0);
			    spaw.setVisibility(0);
			    rempaw.setVisibility(0);
			    flag3=0;
			    flag3=2;
				}
				else if(flag3==2)
				{
			    epao.setVisibility(0);
			    spao.setVisibility(0);
			    rempao.setVisibility(0);
			    flag3=1;
			    flag3=0;
				}
			}
			});
		//remove postal add field for home
		rempah.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View v) 
			{
				
				epah.setVisibility(View.GONE);
			    spah.setVisibility(View.GONE);
			    rempah.setVisibility(View.GONE);
			    flag3=0;
			    flag3=1;
			    flag3=2;
			    			    				
			}
		});	
		//remove postal address field for work
		rempaw.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View v) 
			{
				
				epaw.setVisibility(View.GONE);
			    spaw.setVisibility(View.GONE);
			    spaw.setVisibility(View.GONE);
			    rempaw.setVisibility(View.GONE);
			    flag3=0;
			    flag3=1;
			    flag3=2;
			 			    			    				
			}
		});	
		
		//remove postal address field for other
		rempao.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View v) 
			{
				
				epao.setVisibility(View.GONE);
			    spao.setVisibility(View.GONE);
			    rempao.setVisibility(View.GONE);
			    flag3=0;
			    flag3=1;
			    flag3=2;
			    			    			    				
			}
		});	
		
		
		
		sorgw.setOnItemSelectedListener(
				new AdapterView.OnItemSelectedListener() {
					public void onItemSelected(AdapterView<?> a,View view,int position,long id)
					{
						if(position==3)
						{
							 final Dialog dialog5 = new Dialog(AddContact.this);

		                      dialog5.setContentView(R.layout.dialog);
		                      dialog5.setTitle("Custom Label Name");


		                       dialog5.setCancelable(true);
		                       //set up button
		                       Button button = (Button) dialog5.findViewById(R.id.ok);
		                       ecust=(EditText)dialog5.findViewById(R.id.ecustom);

		                       button.setOnClickListener(new OnClickListener() {

		                       public void onClick(View v) 
	                       {
		                    	   addNewSpinnerItem5();
	                               dialog5.dismiss();
	                               
	                           }

		                       });
		                       Button button1 = (Button) dialog5.findViewById(R.id.cancel);

		                       button1.setOnClickListener(new OnClickListener() {

		                       public void onClick(View v) {

		                               finish();

		                           }

		                       });

		                       //now that the dialog is set up, it's time to show it    

		                       dialog5.show();

		                   }



						}

					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub
						
					}
				   });
		
		sorgo.setOnItemSelectedListener(
				new AdapterView.OnItemSelectedListener() {
					public void onItemSelected(AdapterView<?> a,View view,int position,long id)
					{
						if(position==3)
						{
							 final Dialog dialog6 = new Dialog(AddContact.this);

		                      dialog6.setContentView(R.layout.dialog);
		                      dialog6.setTitle("Custom Label Name");


		                       dialog6.setCancelable(true);
		                       //set up button
		                       Button button = (Button) dialog6.findViewById(R.id.ok);
		                       ecust=(EditText)dialog6.findViewById(R.id.ecustom);

		                       button.setOnClickListener(new OnClickListener() {

		                       public void onClick(View v) 
		                       {
		                    	   addNewSpinnerItem6();
	                               dialog6.dismiss();
	                               
	                           }

		                       });
		                       Button button1 = (Button) dialog6.findViewById(R.id.cancel);

		                       button1.setOnClickListener(new OnClickListener() {

		                       public void onClick(View v) {

		                               finish();

		                           }

		                       });

		                       //now that the dialog is set up, it's time to show it    

		                       dialog6.show();

		                   }



						}

					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub
						
					}
				   });
		spah.setOnItemSelectedListener(
				new AdapterView.OnItemSelectedListener() {
					public void onItemSelected(AdapterView<?> a,View view,int position,long id)
					{
						if(position==3)
						{
							 final Dialog dialog4 = new Dialog(AddContact.this);

		                      dialog4.setContentView(R.layout.dialog);
		                      dialog4.setTitle("Custom Label Name");


		                       dialog4.setCancelable(true);
		                       //set up button
		                       Button button = (Button) dialog4.findViewById(R.id.ok);
		                       ecust=(EditText)dialog4.findViewById(R.id.ecustom);

		                       button.setOnClickListener(new OnClickListener() {

		                       public void onClick(View v) 
		                       {
		                    	   addNewSpinnerItem4();
	                               dialog4.dismiss();
	                               
	                           }

		                       });
		                       Button button1 = (Button) dialog4.findViewById(R.id.cancel);

		                       button1.setOnClickListener(new OnClickListener() {

		                       public void onClick(View v) {

		                               finish();

		                           }

		                       });

		                       //now that the dialog is set up, it's time to show it    

		                       dialog4.show();

		                   }



						}

					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub
						
					}
				   });
		spaw.setOnItemSelectedListener(
				new AdapterView.OnItemSelectedListener() {
					public void onItemSelected(AdapterView<?> a,View view,int position,long id)
					{
						if(position==2)
						{
							 final Dialog dialog7 = new Dialog(AddContact.this);

		                      dialog7.setContentView(R.layout.dialog);
		                      dialog7.setTitle("Custom Label Name");


		                       dialog7.setCancelable(true);
		                       //set up button
		                       Button button = (Button) dialog7.findViewById(R.id.ok);
		                       ecust=(EditText)dialog7.findViewById(R.id.ecustom);

		                       button.setOnClickListener(new OnClickListener() {

		                       public void onClick(View v) 
		                       {
		                    	   addNewSpinnerItem7();
	                               dialog7.dismiss();
	                               
	                           }

		                       });
		                       Button button1 = (Button) dialog7.findViewById(R.id.cancel);

		                       button1.setOnClickListener(new OnClickListener() {

		                       public void onClick(View v) {

		                               finish();

		                           }

		                       });

		                       //now that the dialog is set up, it's time to show it    

		                       dialog7.show();

		                   }



						}

					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub
						
					}
				   });
		spao.setOnItemSelectedListener(
				new AdapterView.OnItemSelectedListener() {
					public void onItemSelected(AdapterView<?> a,View view,int position,long id)
					{
						if(position==2)
						{
							 final Dialog dialog8 = new Dialog(AddContact.this);

		                      dialog8.setContentView(R.layout.dialog);
		                      dialog8.setTitle("Custom Label Name");


		                       dialog8.setCancelable(true);
		                       //set up button
		                       Button button = (Button) dialog8.findViewById(R.id.ok);
		                       ecust=(EditText)dialog8.findViewById(R.id.ecustom);

		                       button.setOnClickListener(new OnClickListener() {

		                       public void onClick(View v) 
		                       {
		                    	   addNewSpinnerItem8();
	                               dialog8.dismiss();
	                               
	                           }

		                       });
		                       Button button1 = (Button) dialog8.findViewById(R.id.cancel);

		                       button1.setOnClickListener(new OnClickListener() {

		                       public void onClick(View v) {

		                               finish();

		                           }

		                       });

		                       //now that the dialog is set up, it's time to show it    

		                       dialog8.show();

		                   }



						}

					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub
						
					}
				   });
		
        data= new DataBaseHelper(this);
		Cursor c1=data.getDataLastName(name);
		System.out.println("MYyyyyyyyyyyyyy"+name);
		while(c1.moveToNext())
		{
			id=c1.getInt(0);
			lname=c1.getString(1);
			System.out.println("IDDDDDD....."+id);
			System.out.println("LNAme....."+lname);
		}
		bir.setOnTouchListener(new OnTouchListener()
	    {
	      
			public boolean onTouch(View arg0, MotionEvent arg1)
			{

	            showDialog(DATE_DIALOG_ID);
				return false;
			}
	    });

		aniv.setOnTouchListener(new OnTouchListener()
	    {
	      
			public boolean onTouch(View arg0, MotionEvent arg1)
			{

	            showDialog(DATE_DIALOG_ID);
				return false;
			}
	    });

		// get the current date and time
		final Calendar c = Calendar.getInstance();
		mYear = c.get(Calendar.YEAR);
		mMonth = c.get(Calendar.MONTH);
		mDay = c.get(Calendar.DAY_OF_MONTH);
		updateDisplay();
		//get contact info and store to database
		badd = (Button)findViewById(R.id.badd);
		badd.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View v) 
			{
				strwadd=wadds.getText().toString();
				strcompadd=comadd.getText().toString();
				strnname=nn.getText().toString();
				strpadd=epah.getText().toString();
				strpaw=epaw.getText().toString();
				strbirth=bir.getText().toString();
				strani=aniv.getText().toString();
				strorgcw=eorgwc.getText().toString();
				strorgpw=eorgwp.getText().toString();
				strorgco=eorgoc.getText().toString();
				strorgpo=eorgop.getText().toString();
				strpao=epao.getText().toString();
				
				data.InsertMore(id,strwadd,strorgcw,strorgco,strorgpw,strorgpo,strorgcust,strcompadd, strnname, strpadd,strpaw,strpao,strpacust, strbirth, strani);
				Toast.makeText(AddContact.this, "Added Successfully",Toast.LENGTH_SHORT).show();
				Cursor c5=data.getMoreInfoData();
				while(c5.moveToNext())
				{
					int a=c5.getInt(0);
					String c1=c5.getString(1);
					String d=c5.getString(2);
					String e=c5.getString(3);
					String e1=c5.getString(4);
					String e2=c5.getString(5);
					String e3=c5.getString(6);
					String e4=c5.getString(7);
					System.out.println("Id...."+a);
					System.out.println("wadd...."+c1);
					System.out.println("Comp...."+d);
					System.out.println("Com Add...."+e);
					System.out.println("Job...."+e1);
					System.out.println("Nick name...."+e2);
					System.out.println("Bday...."+e3);
					System.out.println("Ani...."+e4);
				}

			}
		});
		aback = (Button)findViewById(R.id.bback);
		aback.setOnClickListener(new OnClickListener() {

			public void onClick(View v)
			{
				setContentView(R.layout.addcontact);
			} 

		});   
		/*aclear = (Button)findViewById(R.id.bclear);
		aclear.setOnClickListener(new OnClickListener() {

			public void onClick(View v)
			{
				wadds.setText("");
				com.setText("");
				comadd.setText("");
				jtit.setText("");
				nn.setText("");
				pa.setText("");
				bir.setText("");
				aniv.setText("");

			} 

		});   
*/
	}
	/*Type :Function
	name:call1
	return type:void
	date:29-6-11
	purpose:To get image from sdcard*/
	public void call1()
	{
		Intent intent = new Intent();
		intent.setType("image/*");
		intent.setAction(Intent.ACTION_GET_CONTENT);
		startActivityForResult(Intent.createChooser(intent,
				"Select Picture"), SELECT_PICTURE);


	}
	/*Type :Function
	name:onAcitvityResult
	return type:void
	date:29-6-11
	purpose:To set the updated image by retrieving the path of selected image*/
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
	/*Type :Function
	name:updateDisplay
	return type:void
	date:29-6-11
	purpose:To set updated date in text view*/
	private void updateDisplay() 
	{
		// updates the date in the TextView
        if(bir.hasFocus())
        {
		bir.setText(
				new StringBuilder()
				// Month is 0 based so add 1
				.append(mMonth + 1).append("-")
				.append(mDay).append("-")
				.append(mYear).append(" "));
        }else
        {
		aniv.setText(
				new StringBuilder()
				// Month is 0 based so add 1
				.append(mMonth + 1).append("-")
				.append(mDay).append("-")
				.append(mYear).append(" "));
        }


	}
	

	// the callback received when the user "sets" the date in the dialog
	private DatePickerDialog.OnDateSetListener mDateSetListener =
		new DatePickerDialog.OnDateSetListener() {

		public void onDateSet(DatePicker view, int year, 
				int monthOfYear, int dayOfMonth) {
			mYear = year;
			mMonth = monthOfYear;
			mDay = dayOfMonth;
			updateDisplay();
		}

	};
	
	protected Dialog onCreateDialog(int id)
	{
		switch(id)
		{
		case DATE_DIALOG_ID:
		 	return new DatePickerDialog(this,
					mDateSetListener,
					mYear, mMonth, mDay);

		}
		return null;
		
		
	}
	
}
