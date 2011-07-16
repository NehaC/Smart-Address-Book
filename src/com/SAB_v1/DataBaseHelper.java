package com.SAB_v1;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;
public class DataBaseHelper extends SQLiteOpenHelper
{
	private static final String TAG = "DataBaseHelper";

	//Database Name
	static final String dbName="DABSAB5";
	//Add Contact Table
	static final String eTable="newcontact";
	static final String cid1="conid";
	static final String fname="firstname";
	static final String lname="lastname";
	static final String mobno="mobileno";
	static final String mobnoh="mobilenohome";
	static final String mobnow="mobilenowork";
	static final String mobnoo="mobilenooth";
	static final String mobnocust="mobilenocust";
	static final String email="emailid";
	static final String emailw="emailidwork";
	static final String emailo="emailidoth";
	static final String emailcust="emailidcust";
	static final String image="image";
	static final String tags="tag";
	//Add More Info 
	//static final String vlink="videolink";
	//static final String ring="ringtone";
	static final String moreInfoTable="MoreInfo";
	static final String mcid1="conid";
	static final String mfname="mfirstname";
	static final String mlname="mlastname";
	static final String wadd="webadd";
	static final String orgcw="cmpwork";
	static final String orgco="cmpother";
	static final String orgpw="cmpposwork";
	static final String orgpo="cmpposoth";
	static final String orgcust="cmpcust";
	static final String compadd="companyadd";
	static final String mnname="nickname";
	static final String padd="postaladd";
	static final String paw="postaladdw";
	static final String pao="postaladdo";
	static final String pacust="postaladdcust";
	static final String birth="birthdate";
	static final String ani="aniversary";

	static final String viewEmps="ViewEmps";

	//Call List Table 
	static final String listTable="calllist1";
	static final String callid1="id";
	static final String callname="listname";
	static final String calldate="date";
	static final String calllistflag="flag";

	/*//Call List Table1
	static final String callTable="calllistdata";
	static final String callno="no";
	static final String callid2="id";
	static final String callcdate="cdate";
	static final String callfname="cfname";
	static final String calllname="clname";
	static final String callmob="cmob";
	static final String callflag="flag";*/
	
	//Call List Table1
	static final String callListTable="calllistdata";
	static final String callno="no";
	static final String callid2="id";
	static final String cid2="contactid";
	static final String callcdate="cdate";
	static final String callfname="cfname";
	static final String calllname="clname";
	static final String callmob="cmob";
	static final String callflag="flag";
	//Task Table
	/*static final String taskTable="task";
	static final String tfname="tname";
	static final String ttask="task";
	static final String tdate="tdate";
	static final String ttime="ttime";*/

	//static final String dbName="my_db1";
	static final String taskTable="Task";
	static final String locationTable="Location";
	static final String contactTable="Contacts";
	static final String calllistTable="Calllist";
	static final String callsTable="Callls";
	static final String colid="TaskID";
	static final String coltasko="TaskOwner";
	static final String colname="TaskName";
	static final String coldesp="TaskDesp";
	static final String coltype="TaskType";
	static final String coltpriority="TaskPriority";
	static final String colcat="TaskCat";
	static final String colsdate="Tasksdata";
	static final String colddate="Taskddate";
	static final String coltime="Tasktime";
	static final String colloc="loc";
	static final String colcon="con";
	static final String collocid="loc_id";
	static final String colconid="c_id";
	static final String collocname="loc_name";
	static final String collocflag="loc_flag";
	static final String collat="loc_lat";
	static final String collong="loc_long";
	static final String collocaddr="loc_addr";
	static final String colconname="con_name";
	static final String colconnum="con_num";
	static final String callid="CallistID";
	static final String colclname="clname";
	static final String colcname="cname";
	static final String colcall="call";
	int cid;

	static final String notesTable="notes";
	static final String colnid="notesid";
	static final String colnname="notesname";
	static final String colnotes="notes";
	static final String colflag="flag";
	
	//email log
	static final String EmailLogTable="emaillog";
	static final String eid="conid";
	static final String elogid="logid";
	static final String ecount="count";
	static final String colelogf="flag";

	//SMS Log
	static final String SMSLogTable="smslog";
	static final String sid="conid";
	static final String slogid="smslogid";
	static final String scount="smscount";
	static final String colslogf="flag";
	
	static final String calllogTable="calllog";
	static final String colclgid="clogid";
	static final String colcid="conid";
	static final String colcnt="cnt";
	static final String colclogf="flag";
	
	
	
	
	public DataBaseHelper(Context context) 
	{
		super(context, dbName, null,33);

		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub

		db.execSQL("CREATE TABLE "+eTable+" ("+cid1+ " INTEGER PRIMARY KEY AUTOINCREMENT,"+fname+ " TEXT, "+lname+ " TEXT,"+mobno+ " TEXT,"+mobnoh+ " TEXT,"+mobnow+ " TEXT,"+mobnoo+ " TEXT,"+mobnocust+ " TEXT,"+email+ " TEXT,"+emailw+ " TEXT,"+emailo+ " TEXT,"+emailcust+" TEXT,"+image+ " TEXT,"+tags+ " TEXT,"+colflag+ " INTEGER)");
		db.execSQL("CREATE TABLE "+moreInfoTable+" ("+mcid1+ " INTEGER,"+wadd+ " TEXT,"+orgcw+ " TEXT,"+orgco+ " TEXT,"+orgpw+ " TEXT,"+orgpo+ " TEXT,"+orgcust+ " TEXT,"+compadd+ " TEXT,"+mnname+ " TEXT,"+padd+ " TEXT,"+paw+ " TEXT,"+pao+ " TEXT,"+pacust+ " TEXT,"+birth+ " TEXT,"+ani+ " TEXT,FOREIGN KEY ("+mcid1+") REFERENCES "+eTable+" ("+cid1+"))");
		db.execSQL("CREATE TABLE "+listTable+" ("+callid1+ " INTEGER PRIMARY KEY AUTOINCREMENT, "+callname+ " TEXT, "+calldate+ " TEXT,"+calllistflag+" INTEGER)");	
		//db.execSQL("CREATE TABLE "+callTable+" ("+callid2+ " INTEGER PRIMARY KEY AUTOINCREMENT,"+callno+ " INTEGER,"+callcdate+ " TEXT,"+callflag+" INTEGER,"+callfname+ " TEXT,"+calllname+ " TEXT,"+callmob+ " TEXT,FOREIGN KEY ("+callno+") REFERENCES "+listTable+" ("+callid+"),FOREIGN KEY ("+callcdate+") REFERENCES "+listTable+" ("+calldate+"),FOREIGN KEY ("+callmob+") REFERENCES "+eTable+" ("+mobno+"))");
		//db.execSQL("CREATE TABLE "+taskTable+" ("+tfname+ " TEXT, "+ttask+ " TEXT,"+tdate+ " TEXT,"+ttime+ " TEXT)");
		
		db.execSQL("CREATE TABLE "+callListTable+" ("+callid2+ " INTEGER PRIMARY KEY AUTOINCREMENT, "+callno+ " INTEGER,"+cid2+ " INTEGER,"+callfname+ " TEXT,"+calllname+ " TEXT,"+callflag+" INTEGER,FOREIGN KEY ("+callno+") REFERENCES "+listTable+" ("+callid+"),FOREIGN KEY ("+cid2+") REFERENCES "+eTable+" ("+cid1+"))");
	
				
		db.execSQL("CREATE TABLE "+callsTable+" ("+ colcall+ " INTEGER,"+colcname+ " TEXT,FOREIGN KEY ("+colcall+") REFERENCES "+calllistTable+" ("+callid+"))");
		db.execSQL("CREATE TABLE "+notesTable+" ("+colnid+ " INTEGER PRIMARY KEY AUTOINCREMENT,"+colnname+ " TEXT,"+colnotes+ " TEXT)");
		db.execSQL("CREATE TABLE "+EmailLogTable+" ("+elogid+ " INTEGER PRIMARY KEY AUTOINCREMENT,"+eid+" INTEGER,"+ecount+" INTEGER,"+colelogf+" INTEGER,FOREIGN KEY ("+eid+") REFERENCES "+eTable+" ("+cid1+"))");
		db.execSQL("CREATE TABLE "+SMSLogTable+" ("+slogid+ " INTEGER PRIMARY KEY AUTOINCREMENT,"+sid+" INTEGER,"+scount+" INTEGER,"+colslogf+" INTEGER,FOREIGN KEY ("+sid+") REFERENCES "+eTable+" ("+cid1+"))");

		db.execSQL("CREATE TABLE "+calllogTable+" ("+colclgid+ " INTEGER PRIMARY KEY AUTOINCREMENT,"+colcid+" INTEGER,"+colcnt+" INTEGER,"+colclogf+" INTEGER,FOREIGN KEY ("+colcid+") REFERENCES "+eTable+" ("+cid1+"))");

		db.execSQL("CREATE TABLE "+taskTable+" ("+colid +" INTEGER PRIMARY KEY AUTOINCREMENT,"+coltasko+" TEXT,"+colflag+" INTEGER, "+colname+" TEXT, "
				+coldesp+" Text,"+coltype+" Integer,"+coltpriority+" TEXT, "+colcat+" INTEGER,"+colsdate+" DATE,"+colddate+" DATE,"+colloc+" INTEGER,"+coltime+" Text,"
				+colcon+" Integer,FOREIGN KEY ("+colcon+") REFERENCES "+eTable+" ("+cid1+"));");		

	}

	//	private SQLiteDatabase db;


	void Insert(String strfn,String strln,String strmob,String strmobh,String strmobw,String strmobo,String strmobcust,String streid,String streidw,String streido,String streidcust,String strimg,String strtag )
	{
		SQLiteDatabase myDB = this.getWritableDatabase();
		ContentValues cv=new ContentValues();

		cv.put(fname, strfn);
		cv.put(lname, strln);
		cv.put(mobno, strmob);
		cv.put(mobnoh, strmobh);
		cv.put(mobnow, strmobw);
		cv.put(mobnoo, strmobo);
		cv.put(mobnocust, strmobcust);
		cv.put(email, streid);
		cv.put(emailw, streidw);
		cv.put(emailo, streido);
		cv.put(emailcust, streidcust);
		cv.put(image,strimg);
		cv.put(tags,strtag);
		cv.put(colflag,"0");
		myDB.insert(eTable,null,cv);


	}
	
	void InsertMore(int id,String strwadd,String strorgcw,String strorgco,String strorgcust,String strorgpw,String strorgpo,String strcompadd, String strnname, String strpadd,String strpaw,String strpao,String strpacust, String strbirth, String strani)
	{
		SQLiteDatabase myDB = this.getWritableDatabase();
		ContentValues cv=new ContentValues();
		cv.put(mcid1, id);
		cv.put(wadd, strwadd);
	    cv.put(orgcw, strorgcw);
	    cv.put(orgco, strorgco);
	    cv.put(orgpw, strorgpw);
	    cv.put(orgpo, strorgpo);
	    cv.put(orgcust, strorgcust);
		cv.put(compadd,strcompadd);
		cv.put(mnname,strnname);
		cv.put(padd,strpadd);
		cv.put(paw, strpaw);
		cv.put(pao,strpao);
		cv.put(pacust, strpacust);
		cv.put(paw, strpaw);
		cv.put(pao, strpao);
		cv.put(birth,strbirth);
		cv.put(ani,strani);
		myDB.insert(moreInfoTable,null,cv);


	}
	Cursor getAllContacts()
	{
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur= db.rawQuery("SELECT * FROM "+eTable,null);
		return cur;
	}
	Cursor getNo(String num)
	{
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur= db.rawQuery("SELECT "+fname+" FROM "+eTable+" where "+mobno+"='"+num+"'",new String [] {});
		return cur;		
	//	Cursor cur= db.rawQuery("SELECT "+mobno+" FROM "+eTable ,new String [] {});
		
		
		
	}
	Cursor getData()
	{

		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur= db.rawQuery("SELECT "+fname+","+lname+","+mobno+","+cid+" FROM "+eTable+" ORDER BY "+fname+" DESC",new String [] {});
		return cur;		 
	}

	Cursor getDataLastName(String finame)
	{

		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur= db.rawQuery("SELECT "+cid1+","+lname+" FROM "+eTable+ " where "+fname+" ='"+finame+"'",null);
		return cur;		 
	}
	Cursor getidmob(String finame)
	{

		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur= db.rawQuery("SELECT "+cid1+","+mobno+" FROM "+eTable+ " where "+fname+" ='"+finame+"'",null);
		return cur;		 
	}
	Cursor getMoreInfoData()
	{

		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur= db.rawQuery("SELECT * FROM "+moreInfoTable,null);
		return cur;		 
	}
	Cursor Data(String name,String lname1)
	{
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur1= db.rawQuery("SELECT "+cid1+","+fname+","+lname+","+mobno+","+email+","+image+","+tags+" FROM "+eTable+ " where "+fname+" ='"+name+"' AND "+lname+" ='"+lname1+"'",new String[]{});
		return cur1;

	}


	Cursor getmobno()
	{
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur= db.rawQuery("SELECT "+mobno+" FROM "+eTable,null);
		return cur;			 
	}
	Cursor Search(String s)
	{
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur= db.rawQuery("SELECT * FROM "+eTable+ " where "+fname+" LIKE '"+s+"%'",null);			 
		return cur;			 
	}

	public Cursor SearchTag(String s1) 
	{
		System.out.println("Names "+s1);
		String tag1="";
		String [] s=new String[10];
		s=s1.split(" ");
		if(s.length==1)
		{
			tag1 =fname+" LIKE '%"+s[0]+"%' OR "
			+lname+" LIKE '%"+s[0]+"%' OR "
			+mobno+" LIKE '%"+s[0]+"%' OR "
			+email+" LIKE '%"+s[0]+"%' OR "
			+tags+" LIKE '%"+s[0]+"%'";

		}
		else
		{
			for(int i=0;i<s.length;i++)
			{
				if(tag1.equals(""))
				{

					tag1 =fname+" LIKE '%"+s[i]+"%' OR "
					+lname+" LIKE '%"+s[i]+"%' OR "
					+mobno+" LIKE '%"+s[i]+"%' OR "
					+email+" LIKE '%"+s[i]+"%' OR "
					+tags+" LIKE '%"+s[i]+"%'";

				}
				else
				{

					tag1 = tag1+" AND "+fname+" LIKE '%"+s[i]+"%' OR " 
					+lname+" LIKE '%"+s[i]+"%' OR "
					+mobno+" LIKE '%"+s[i]+"%' OR "
					+email+" LIKE '%"+s[i]+"%' OR "
					+tags+" LIKE '%"+s[i]+"%'";

				}			

			}
		}

		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur=db.rawQuery("SELECT "+fname+","+lname+","+mobno+" FROM  "+eTable+" where "+fname+" IN ( SELECT "+fname+" FROM "+eTable+
				" WHERE ("+tag1+"))",new String [] {});

		return cur;
	}

	//Insert Data into CallList table
	void InsertCall(String strcn,String strdate)
	{
		SQLiteDatabase DB1 = this.getWritableDatabase();
		ContentValues cv=new ContentValues();
		cv.put(callname, strcn);
		cv.put(calldate, strdate);
		cv.put(calllistflag,0);
		DB1.insert(listTable,null,cv);

	}
	Cursor getDataCall()
	{
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur= db.rawQuery("SELECT * FROM "+listTable,null);
		return cur;		 
	}
	public int updateflag(int tId) {
		SQLiteDatabase db= this.getWritableDatabase();
		 ContentValues cv=new ContentValues();
		 cv.put(colflag,"1");
		 return db.update(taskTable, cv,colid+"="+tId, new String []{});
		
	}

	Cursor getDataCallid(String name1)
	{
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur= db.rawQuery("SELECT "+callid1+" FROM "+listTable,null);
		return cur;		 
	}
	public Cursor gettask()
	{
		//String tname,town,tdesp,tprio,tsdate,tddate,ttime,con,loc,ttype,tcat;
		SQLiteDatabase db=this.getReadableDatabase();
		 Cursor c=db.rawQuery("SELECT "+coltasko+","+colname+","+coldesp+","+coltype+","+coltpriority+","+colcat+","+colsdate+","+colddate+","+coltime+","+colcon+","+colloc+","+colid+" from "+taskTable,new String [] {});
		 return c;
	}

	void InsertCallList(int intid,int intid1,String strcfn,String strcln)
	{
		SQLiteDatabase DB = this.getWritableDatabase();
		ContentValues cv=new ContentValues();
		cv.put(callno, intid);
		cv.put(cid2, intid1);
		cv.put(callfname, strcfn);
		cv.put(calllname, strcln);
		cv.put(callflag,0);
		DB.insert(callListTable,null,cv);

	}
	public void deletecallist(int cid)
	{
		SQLiteDatabase db= this.getWritableDatabase();
		db.delete(callListTable,callid2+"="+cid, new String []{} );

	}
	Cursor getCallListName()
	{

		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur=db.rawQuery("SELECT "+callname+" from "+listTable,new String [] {});
		return cur;		 
	}
	Cursor getCallListid1(String listname)
	{

		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur=db.rawQuery("SELECT "+callid1+" from "+listTable+" Where "+callname+" ='"+listname+"'",new String [] {});
		return cur;		 
	}
	public void deleteCallListName(int id)
	{
		SQLiteDatabase db=this.getReadableDatabase();
		db.delete(listTable,callid1+"="+id, new String []{} );
	
	}
	public int updatecalllist(int cid,String fname,String lname)
	{
		SQLiteDatabase db= this.getWritableDatabase();
		ContentValues cv=new ContentValues();
		cv.put(callfname,fname);
		cv.put(calllname,lname);
		return db.update(callListTable, cv,callno+"="+cid, new String []{});
	}
	Cursor getCallListid(String name,String name2)
	{

		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur=db.rawQuery("SELECT "+callid2+" from "+callListTable+" Where "+callfname+" ='"+name+"' AND "+calllname+" ='"+name2+"'",new String [] {});


		return cur;		 
	}
	Cursor getDataCallList(int callid1)
	{
        System.out.println("CallId" +callid1);
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur=db.rawQuery("SELECT "+callfname+","+calllname+" from "+callListTable+" Where "+callno+"="+callid1,new String [] {});


		return cur;		 
	}
	Cursor getDataCallList1(int callid2,String n1)
	{

		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur=db.rawQuery("SELECT "+mobno+" FROM "+eTable+" Where "+cid1+"="+callid2+" AND "+fname+" ='"+n1+"'",new String [] {});
		return cur;		 
	}
	Cursor getDataCallList2(String n2)
	{
		System.out.println("Func: "+n2);
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur=db.rawQuery("SELECT "+callno+" FROM "+callListTable+" Where  "+callfname+" ='"+n2+"'",new String [] {});
		return cur;

	}

	public int updatecontact(int coid, String scname, String sclname,String scmobno,String scemail,String scimg,String sctags) 
	{
		SQLiteDatabase db= this.getWritableDatabase();
		ContentValues cv=new ContentValues();
		cv.put(fname,scname);
		cv.put(lname,sclname);
		cv.put(mobno,scmobno);
		cv.put(image,scimg);
		cv.put(tags,sctags);
		return db.update(eTable, cv,cid1+"="+coid, new String []{});
	}
	public void deletecontact(int id)
	{
		SQLiteDatabase db= this.getWritableDatabase();
		ContentValues cv=new ContentValues();
		db.delete(eTable,cid1+"="+id, new String []{} );

	}


	public void delete()
	{ 
		SQLiteDatabase db=this.getWritableDatabase();

		db.execSQL("DROP TABLE IF EXISTS "+eTable);
	}
	
	Cursor gettaskData()
	{

		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur= db.rawQuery("SELECT * FROM "+taskTable,null);
		return cur;		 
	}
	int getTaskCnt()
	{
		SQLiteDatabase db=this.getWritableDatabase();
		Cursor cur= db.rawQuery("Select * from "+taskTable, null);
		int x= cur.getCount();
		cur.close();
		return x;
	}


	Cursor gettask_today(String sdate)
	{
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur=db.rawQuery("SELECT "+colname+" ,"+coldesp+","+colddate+" from "+taskTable+" where "+colddate+" = '"+sdate+"'",new String [] {});

		return cur;
	}


	void getAllloc()
	{
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur=db.rawQuery("SELECT * from "+locationTable,new String [] {});
		while(cur.moveToNext())
		{
			int lid=cur.getInt(0);
			String lname=cur.getString(1);
			String laddr=cur.getString(2);
			System.out.print("lid=="+lid+" lname="+lname+" laddr=="+laddr);

		}


	}

	void Inserttask(String towner,String tname,String tdesp,int type,String pr,int cat,String sdate,String date,String time,int cid,int lid)
	{	 
		SQLiteDatabase db= this.getWritableDatabase();
		ContentValues cv=new ContentValues();
		cv.put(coltasko,towner);
		cv.put(colname,tname);
		cv.put(coldesp,tdesp);
		cv.put(coltype,type);
		cv.put(coltpriority,pr);
		cv.put(colcat,cat);
		cv.put(colsdate,sdate);
		cv.put(colddate,date);
		cv.put(coltime,time);
		cv.put(colloc,lid);
		cv.put(colcon,cid);
		db.insert(taskTable,null,cv);



	}
	public int updateTask(int id, String towner,String tname, String tdesp, int type, String pr, int cat, String sdate, String date, String time, int cid2, int lid)
	{
		SQLiteDatabase db=this.getWritableDatabase();
		ContentValues cv=new ContentValues();
		cv.put(coltasko,towner);
		cv.put(colname,tname);
		cv.put(coldesp,tdesp);
		cv.put(coltype,type);
		cv.put(coltpriority,pr);
		cv.put(colcat,cat);
		cv.put(colsdate,sdate);
		cv.put(colddate,date);
		cv.put(coltime,time);
		cv.put(colloc,lid);
		cv.put(colcon,cid2);
		return db.update(taskTable, cv,colid+"="+id, new String []{});

	}
	void insertcontact(String cname,String cnum)
	{
		SQLiteDatabase db= this.getWritableDatabase();
		ContentValues cv=new ContentValues();
		cv.put(colconname,cname);
		cv.put(colconnum,cnum);
		db.insert(contactTable,null,cv);

	}

	void insertlocn(String lname,String laddr)
	{
		SQLiteDatabase db= this.getWritableDatabase();
		ContentValues cv=new ContentValues();
		cv.put(collocname,lname);
		cv.put(collocaddr,laddr);
		db.insert(locationTable,null,cv);

	}
	public Cursor getconid(String cname,String cnum)
	{
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur=db.rawQuery("SELECT "+cid1+" from "+eTable+" Where "+fname+"='"+cname+"'"+
				" and "+mobno+" ='"+cnum+"'",new String [] {});

		return cur;
	}




	public Cursor getlocid(String tlname,String tladdr)
	{ 
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur=db.rawQuery("SELECT "+collocid+" from "+locationTable+" Where "+collocname+"='"+tlname+
				"'and "+collocaddr+"='"+tladdr+"'",new String [] {});
		return cur;
	}

	public Cursor callalert(String date)
	{
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur=db.rawQuery("SELECT "+colcon+" from "+taskTable+" where "+colddate
				+" = '"+date+"'",new String [] {});
		while(cur.moveToNext())
		{
			cid=cur.getInt(0);
		}
		System.out.println("ciddd"+cid);
		Cursor c = db.rawQuery("SELECT "+colconname+" ,"+colconnum+" from "+contactTable+" where "+colconid
				+" = "+cid+" ",new String [] {});

		return c;
	}

	public Cursor get_task_home() 
	{
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur=db.rawQuery("SELECT "+colname+" ,"+coldesp+","+colddate+" from "+taskTable+" where "+colcat+" = 2",new String [] {});
		return cur;


	}


	public Cursor get_task_work() 
	{
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur=db.rawQuery("SELECT "+colname+" ,"+coldesp+","+colddate+" from "+taskTable+" where "+colcat+" = 1",new String [] {});
		return cur;


	}


	public Cursor get_task_personal() 
	{
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur=db.rawQuery("SELECT "+colname+" ,"+coldesp+","+colddate+" from "+taskTable+" where "+colcat+" = 0",new String [] {});
		return cur;


	}

	public Cursor getDetails(int id) 
	{
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur=db.rawQuery("SELECT "+colname+","+coldesp+","+colcat+","+coltpriority+","+colddate+","+coltime+","+colcon+","+colloc+" from "+taskTable+" where "+colid+" ="+id,new String [] {});
		return cur;


	}
	public Cursor getDetails1() 
	{
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur=db.rawQuery("SELECT * from "+taskTable,new String [] {});
		return cur;


	}
	public void insercalllist(String listname)
	{
		SQLiteDatabase db= this.getWritableDatabase();
		ContentValues cv=new ContentValues();
		cv.put(colclname,listname);
		db.insert(calllistTable,null,cv);

	}

	public void insertcall(int id,String name) 
	{
		SQLiteDatabase db= this.getWritableDatabase();
		ContentValues cv=new ContentValues();
		cv.put(colcall,id);
		cv.put(colcname,name);
		db.insert(callsTable,null,cv);

	}

	public Cursor getcallid(String listname) 
	{

		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur=db.rawQuery("SELECT "+callid+" from "+calllistTable+" Where "+colclname+"='"+listname+"'",new String [] {});
		return cur;

	}

	public Cursor getcalllist()
	{
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur=db.rawQuery("SELECT * from "+calllistTable,new String [] {});
		return cur;
	}

	public Cursor getcallnamelist(int callid2)
	{
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur=db.rawQuery("SELECT "+colcname+" from "+callsTable+" Where "+colcall+"="+callid2,new String [] {});
		return cur;


	}

	public Cursor gettaskid(String ans)
	{

		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur=db.rawQuery("SELECT "+colid+","+coltype+" from "+taskTable+" where "+colname+"='"+ans+"'",new String [] {});
		return cur;
	}

	public void deletetask(int id)
	{
		SQLiteDatabase db= this.getWritableDatabase();
		db.delete(taskTable,colid+"="+id, new String []{} );

	}

	public Cursor getcdetails(int cid2)
	{
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur=db.rawQuery("SELECT "+colconname+","+colconnum+" from "+contactTable+" Where "+colconid+"="+cid2,new String [] {});
		return cur;


	}

	public Cursor getldetails(int lid) 
	{

		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur=db.rawQuery("SELECT "+collocname+","+collocaddr+" from "+locationTable+" Where "+collocid+"="+lid,new String [] {});
		return cur;

	}

	public int updatecontact(int cid2, String tcname2, String tcnum2) 
	{
		SQLiteDatabase db= this.getWritableDatabase();
		ContentValues cv=new ContentValues();
		cv.put(colconname,tcname2);
		cv.put(colconnum,tcnum2);
		return db.update(contactTable, cv,colconid+"="+cid2, new String []{});
	}

	public int updatelocn(int lid, String tlname2, String tladdr2) 
	{
		SQLiteDatabase db= this.getWritableDatabase();
		ContentValues cv=new ContentValues();
		cv.put(collocname,tlname2);
		cv.put(collocaddr,tladdr2);
		return db.update(locationTable, cv,collocid+"="+lid, new String []{});

	}

	public Cursor gettask_upcomi(String sdate)
	{
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur=db.rawQuery("SELECT "+colname+" ,"+coldesp+","+colddate+" from "+taskTable+" where "+colddate+" > '"+sdate+"'",new String [] {});
		return cur;
	}

	public Cursor gettask_overdue(String sdate)
	{
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur=db.rawQuery("SELECT "+colname+" ,"+coldesp+","+colddate+" from "+taskTable+" where "+colddate+" < '"+sdate+"'",new String [] {});
		return cur;
	}

	void Insertnotes(String nname,String note)
	{	 
		SQLiteDatabase db= this.getWritableDatabase();
		ContentValues cv=new ContentValues();
		cv.put(colnname,nname);
		cv.put(colnotes,note);
		db.insert(notesTable,null,cv);
	}
	public Cursor getnotes()
	{
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur=db.rawQuery("SELECT "+colnid+" ,"+colnname+","+colnotes+" from "+notesTable ,new String [] {});
		return cur;
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		onCreate(db);

	}

	public Cursor gettask(String oname, String sdate)
	{
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur=db.rawQuery("SELECT "+colname+" from "+taskTable+" where "+colddate+" = '"+sdate+"' and "+coltasko+" = '"+oname+"'",new String [] {});
		String str5="SELECT "+colname+" from "+taskTable+" where "+colddate+" = '"+sdate+"' and "+coltasko+" = '"+oname+"'";
		System.out.println("Query "+str5);
		return cur;
	}
	public Cursor get_todaytask(String sdate)
	{
		
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur=db.rawQuery("SELECT distinct "+colname+" from "+taskTable+" where "+colsdate+" = '"+sdate+"'",new String [] {});
		return cur;
	}
    
	public Cursor get_task_upcoming(String sdate1)
	{
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur=db.rawQuery("SELECT distinct "+colname+" from "+taskTable+" where "+colddate+" > '"+sdate1+"'",new String [] {});
		return cur;
	}

	public Cursor get_task_pending(String sdate2)
	{
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur=db.rawQuery("SELECT distinct "+colname+" from "+taskTable+" where "+colddate+" < '"+sdate2+"'",new String [] {});
		return cur;
	}


	public Cursor get_task_todaydetails(String taskname)
	{
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur=db.rawQuery("SELECT "+coltasko+","+coldesp+" from "+taskTable+" where "+colname+" = '"+taskname+"'",new String [] {});
		return cur;
	}

	public Cursor getcontact()
	{
		
		SQLiteDatabase db=this.getReadableDatabase();
		 Cursor c=db.rawQuery("SELECT "+fname+","+lname+","+mobno+","+mobnoh+","+mobnow+","+mobnoo+","+mobnocust+","+email+","+emailw+","+emailo+","+emailcust+","+image+","+tags+","+cid1+" from "+eTable+" where "+colflag+"=0",new String [] {});
		 return c;
	}

	public Cursor getFlagC()
	{
		SQLiteDatabase db=this.getReadableDatabase();
		 Cursor c=db.rawQuery("SELECT "+colflag+","+cid1+" from "+eTable,new String [] {});
		 return c;
	}

	public int updateflagc(int cId2) {
		SQLiteDatabase db= this.getWritableDatabase();
		 ContentValues cv=new ContentValues();
		 cv.put(colflag,"1");
		 return db.update(eTable, cv,cid1+"="+cId2, new String []{});
		
		
	}

	public Cursor getlistname() {
		SQLiteDatabase db=this.getReadableDatabase();
		 Cursor c=db.rawQuery("SELECT "+callid1+","+callname+","+calldate+" from "+listTable,new String [] {});
		 return c;
	}

	public Cursor getFlaglist() {
		SQLiteDatabase db=this.getReadableDatabase();
		 Cursor c=db.rawQuery("SELECT "+calllistflag+","+callid1+" from "+listTable,new String [] {});
		 return c;
	}

	public int updateflagl(String listid) {
		SQLiteDatabase db= this.getWritableDatabase();
		 ContentValues cv=new ContentValues();
		 cv.put(calllistflag,"1");
		 return db.update(listTable, cv,callid1+"="+listid, new String []{});
		
	}
	public Cursor getName(String mailid)
	{
		SQLiteDatabase db=this.getReadableDatabase();
		 Cursor c=db.rawQuery("SELECT "+cid1+","+fname+" from "+eTable+" where "+email+" = '"+mailid+"'",new String [] {});
		 return c;
	}
	public Cursor getMNo(int no)
	{
		System.out.println("Sms "+no);
		SQLiteDatabase db=this.getReadableDatabase();
		 Cursor c=db.rawQuery("SELECT "+cid1+","+fname+" from "+eTable+" where "+mobno+" = "+no,new String [] {});
		 return c;
	}
	public void insertmaillog(int conid,int cnt) 
	{
		SQLiteDatabase db= this.getWritableDatabase();
		ContentValues cv=new ContentValues();
		cv.put(eid,conid);
		cv.put(ecount,cnt);
		cv.put(colelogf,0);
		db.insert(EmailLogTable,null,cv);
	}

	public Cursor getcount(int econid) 
	{
		SQLiteDatabase db=this.getReadableDatabase();
		 Cursor c=db.rawQuery("SELECT "+ecount+" from "+EmailLogTable+" where "+eid+" ="+econid,new String [] {});
		 return c;	
	}
	public Cursor getmaildata(int econid) 
	{
		SQLiteDatabase db=this.getReadableDatabase();
		 Cursor c=db.rawQuery("SELECT "+eid+","+ecount+" from "+EmailLogTable+" where "+eid+" ="+econid,new String [] {});
		 return c;	
	}
	public int updateemailcnt(int conid, int count) 
	{
		SQLiteDatabase db= this.getWritableDatabase();
		ContentValues cv=new ContentValues();
		cv.put(ecount,count);
		return db.update(EmailLogTable, cv,eid+"="+conid, new String []{});
		
	}

	public void insertsmslog(int conid,int cnt) 
	{
		SQLiteDatabase db= this.getWritableDatabase();
		ContentValues cv=new ContentValues();
		cv.put(sid,conid);
		cv.put(scount,cnt);
		cv.put(colslogf,0);
		db.insert(SMSLogTable,null,cv);
	}
	public int updatesmscnt(int conid, int count) 
	{
		SQLiteDatabase db= this.getWritableDatabase();
		ContentValues cv=new ContentValues();
		cv.put(scount,count);
		return db.update(SMSLogTable, cv,sid+"="+conid, new String []{});
		
	}
	public Cursor getsmsdata() 
	{
		SQLiteDatabase db=this.getReadableDatabase();
		 Cursor c=db.rawQuery("SELECT "+sid+","+scount+" from "+SMSLogTable,new String [] {});
		 return c;	
	}
	public Cursor getscnt(int sconid) 
	{
		SQLiteDatabase db=this.getReadableDatabase();
		 Cursor c=db.rawQuery("SELECT "+scount+" from "+SMSLogTable+" where "+sid+" ="+sconid,new String [] {});
		 return c;	
	}
	public Cursor getlistdetails(String listid, String listdate) {
		SQLiteDatabase db=this.getReadableDatabase();
		 Cursor c=db.rawQuery("SELECT "+callfname+","+calllname+" from "+callListTable+" where "+callno+" ="+listid,new String [] {});
		 return c;
	}
	public void insertccnt(int conid,int cnt) 
	{
		SQLiteDatabase db= this.getWritableDatabase();
		ContentValues cv=new ContentValues();
		cv.put(colcid,conid);
		cv.put(colcnt,cnt);
		cv.put(colclogf,0);
		db.insert(calllogTable,null,cv);
	}
	public int updatecnt(int conid, int cnt) 
	{
		SQLiteDatabase db= this.getWritableDatabase();
		ContentValues cv=new ContentValues();
		cv.put(colcnt,cnt);
		return db.update(calllogTable, cv,colcid+"="+conid, new String []{});
		
	}
	public Cursor getcnt(int conid) 
	{
		SQLiteDatabase db=this.getReadableDatabase();
		 Cursor c=db.rawQuery("SELECT "+colcnt+" from "+calllogTable+" where "+colcid+" ="+conid,new String [] {});
		 return c;	
	}

	public Cursor getcal_log() {
		SQLiteDatabase db=this.getReadableDatabase();
		 Cursor c=db.rawQuery("SELECT "+colclgid+","+colcid+","+colcnt+" from "+calllogTable,new String [] {});
		 return c;	
	}

	public int updateflagclog(String logId) {
		SQLiteDatabase db= this.getWritableDatabase();
		 ContentValues cv=new ContentValues();
		 cv.put(colclogf,"1");
		 return db.update(calllogTable, cv,colclgid+"="+logId, new String []{});
		
	}

	public Cursor getemail_log() {
		SQLiteDatabase db=this.getReadableDatabase();
		 Cursor c=db.rawQuery("SELECT "+elogid+","+eid+","+ecount+" from "+EmailLogTable,new String [] {});
		 return c;
	}
	public Cursor getsms_log() {
		SQLiteDatabase db=this.getReadableDatabase();
		 Cursor c=db.rawQuery("SELECT "+slogid+","+sid+","+scount+" from "+SMSLogTable,new String [] {});
		 return c;
	}

	public int updateflagelog(String elogId) {
		SQLiteDatabase db= this.getWritableDatabase();
		 ContentValues cv=new ContentValues();
		 cv.put(colelogf,"1");
		 return db.update(EmailLogTable, cv,elogid+"="+elogId, new String []{});
		
	}

	public int updateflagslog(String slogId) {
		SQLiteDatabase db= this.getWritableDatabase();
		 ContentValues cv=new ContentValues();
		 cv.put(colslogf,"1");
		 return db.update(SMSLogTable, cv,slogid+"="+slogId, new String []{});
		
	}
	

	Cursor getMoreInfo(int id)
	{
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur1= db.rawQuery("SELECT * FROM "+moreInfoTable+ " where "+mcid1+" ="+id+" ",new String[]{});
		return cur1;

	}

	Cursor Data1(int id)
	{
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur1= db.rawQuery("SELECT "+cid1+","+fname+","+lname+","+mobno+","+email+","+image+","+tags+" FROM "+eTable+ " where "+cid1+" ="+id+" ",new String[]{});
		return cur1;

	}

	public Cursor get_task_upcomingDetails(String taskname) {
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur=db.rawQuery("SELECT "+coltasko+","+coldesp+","+colddate+","+coltime+" from "+taskTable+" where "+colname+" = '"+taskname+"'",new String [] {});
		return cur;
	}

	Cursor getCallListid1(String name,String name2)
	{
        System.out.println("Fname + Lname" +name +name2);
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur=db.rawQuery("SELECT "+cid2+" from "+callListTable+" where "+callfname+" ='"+name+"' AND "+calllname+" ='"+name2+"'",new String [] {});
		return cur;		 
	}
	Cursor getContactId(String name,String name1)
	{

		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur=db.rawQuery("SELECT "+cid1+" from "+eTable+" Where "+fname+" ='"+name+"' AND "+lname+" ='"+name1+"'",new String [] {});
		return cur;		 
	}
	Cursor getMobNo(int id)
	{
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur=db.rawQuery("SELECT "+mobno+" from "+eTable+" Where "+cid1+"="+id,new String [] {});
		return cur;
	}
	Cursor getNAME(String num)
	{
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur=db.rawQuery("SELECT "+fname+","+lname+" from "+eTable+" Where "+mobno+"='"+num+"'",new String [] {});
		return cur;
	}
	 
}

