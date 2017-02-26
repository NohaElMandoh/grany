package mo.ed.prof_mohamed.geranyapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Prof-Mohamed on 2/7/2017.
 */
public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="gerany.db";
    public static final String Posts_TBL="Posts";
    public static final String Places_TBL="Places";

    //// Columns of Posts Table
    private static final String Posts_id = "post_id";
    private static final String UserName = "username";
    private static final String Description = "description";
    private static final String Post_Content= "post_content";
    private static final String Post_Image= "post_image";
    private static final String Profile_Image= "profile_image";
    private static final String Volunteer= "volunteer";
    private static final String Donate= "donate";
    private static final String Both= "both";
    private static final String Email= "email";
    private static final String Mobile= "mobile";
    private static final String Distance= "distance";
    private static final String CollaborationType= "collaborationType";


    //// Columns of Places Table

    private static final String AreaName = "AreaName";
    private static final String AreaAddress= "AreaAddress";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME,null , 2);
        SQLiteDatabase Db=this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL("create table "+Posts_TBL+" (ID INTEGER PRIMARY KEY AUTOINCREMENT, post_id TEXT, username TEXT, description TEXT,post_content TEXT, post_image TEXT, profile_image TEXT, volunteer TEXT, donate TEXT, both TEXT, email TEXT, mobile TEXT, distance TEXT, collaborationType TEXT) ");
            db.execSQL("create table "+ Places_TBL+" (ID INTEGER PRIMARY KEY AUTOINCREMENT, AreaName TEXT, AreaAddress TEXT) ");
        }catch (Exception e){
            e.getMessage();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+Posts_TBL);
        db.execSQL("DROP TABLE IF EXISTS "+Places_TBL);
        onCreate(db);
    }


    public int SpecifyPosts_Initial_max_Value(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("SELECT * from "+Posts_TBL,null);
        int maxID=0;
        try{
            if (res.moveToNext()){
                do {
                    OptionsEntity optionsEntity=new OptionsEntity();
                    optionsEntity.setID(res.getString(res.getColumnIndex("ID")));
                    maxID=Integer.parseInt(optionsEntity.getID());
                } while (res.moveToNext());
            }
            return maxID;
        }catch (Exception e){
            return 0;
        }
    }

    public ArrayList<OptionsEntity> selectAllPostsData(){
        SQLiteDatabase db=this.getWritableDatabase();
        ArrayList PostsArray=new ArrayList<OptionsEntity>();
        Cursor res=db.rawQuery("SELECT * from "+Posts_TBL,null);
        try{
            if (res.moveToNext()){
                do {

                    OptionsEntity optionsEntity=new OptionsEntity();
                    optionsEntity.setID(res.getString(res.getColumnIndex("post_id")));
                    optionsEntity.setUserName(res.getString(res.getColumnIndex("username")));
                    optionsEntity.setDescription(res.getString(res.getColumnIndex("description")));
                    optionsEntity.setPost_Content(res.getString(res.getColumnIndex("post_content")));
                    optionsEntity.setPost_images(res.getString(res.getColumnIndex("post_image")));
                    optionsEntity.setProfile_Images(res.getString(res.getColumnIndex("profile_image")));
                    optionsEntity.setVolunteer(res.getString(res.getColumnIndex("volunteer")));
                    optionsEntity.setDonate(res.getString(res.getColumnIndex("donate")));
                    optionsEntity.setBoth(res.getString(res.getColumnIndex("both")));
                    optionsEntity.setEmail(res.getString(res.getColumnIndex("email")));
                    optionsEntity.setMobile(res.getString(res.getColumnIndex("mobile")));
                    optionsEntity.setDistance(res.getString(res.getColumnIndex("distance")));
                    optionsEntity.setCollaborationType(res.getString(res.getColumnIndex("collaborationType")));
                    PostsArray.add(optionsEntity);
                } while (res.moveToNext());
            }
            res.close();
            return  PostsArray;
        } catch (Exception e) {
            return PostsArray;
        }
    }

    public boolean insertJsonData(String id, String userName, String description, String post_content, String post_images, String profile_images, String volunteer, String donate, String both, String email, String mobile, String distance, String collaborationType) {
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues content_values=new ContentValues();


        content_values.put(Posts_id,id);
        content_values.put(UserName,userName);
        content_values.put(Description,description);
        content_values.put(Post_Content,post_content);
        content_values.put(Post_Content,post_images);
        content_values.put(Profile_Image,profile_images);
        content_values.put(Volunteer,volunteer);
        content_values.put(Donate,donate);
        content_values.put(Both,both);
        content_values.put(Email,email);
        content_values.put(Mobile,mobile);
        content_values.put(Distance,distance);
        content_values.put(CollaborationType,collaborationType);

        long result= db.insert(Posts_TBL,null,content_values);

        if (result==-1){
            return false;
        }
        else
            return true;
    }

    public int SpecifyPlaces_Initial_max_Value() {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("SELECT * from "+Places_TBL,null);
        int maxID=0;
        try{
            if (res.moveToNext()){
                do {
                    OptionsEntity optionsEntity=new OptionsEntity();
                    optionsEntity.setID(res.getString(res.getColumnIndex("ID")));
                    maxID=Integer.parseInt(optionsEntity.getID());
                } while (res.moveToNext());
            }
            return maxID;
        }catch (Exception e){
            return 0;
        }
    }

    public boolean insertPlacesJsonData( String areaname, String areaaddress) {

        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues content_values=new ContentValues();


        content_values.put(AreaName,areaname);
        content_values.put(AreaAddress,areaaddress);

        long result= db.insert(Places_TBL,null,content_values);

        if (result==-1){
            return false;
        }
        else
            return true;
    }

    public ArrayList<OptionsEntity> selectAllPlacesData() {
        SQLiteDatabase db=this.getWritableDatabase();
        ArrayList PostsArray=new ArrayList<OptionsEntity>();
        Cursor res=db.rawQuery("SELECT * from "+Places_TBL,null);
        try{
            if (res.moveToNext()){
                do {
                    OptionsEntity optionsEntity=new OptionsEntity();
                    optionsEntity.setUserName(res.getString(res.getColumnIndex("AreaName")));
                    optionsEntity.setDescription(res.getString(res.getColumnIndex("AreaAddress")));
                    PostsArray.add(optionsEntity);
                } while (res.moveToNext());
            }
            res.close();
            return  PostsArray;
        } catch (Exception e) {
            return PostsArray;
        }
    }
}