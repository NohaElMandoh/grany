package mo.ed.prof_mohamed.geranyapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

public class PostsActivity extends AppCompatActivity {

    private final String LOG_TAG = PostsActivity.class.getSimpleName();
    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    DBHelper DB;
//    OptionsEntity optionsEntity;
//    ArrayList<OptionsEntity> list = new ArrayList<OptionsEntity>();
//    public String LoggedUser;
//    HashMap<String, String> user;
//    SessionManagement sessionManagement;
//    PostAdapter postAdapter;
    ListView listview_listplaces;
    EditText txt_GivenPlaceName;
    EditText txt_GivenPlaceAddress;

//    private final String LOG_TAG = PostFragment.class.getSimpleName();

    OptionsEntity optionsEntity;
    static final String Post_URL = "https://cldup.com/NooZUWngcc.json";
    String main_List="Posts" , ID = "id",username="username",description="description",post_content="post_content",post_image="post_image",profile_image="profile_image",volunteer="volunteer",donate="donate",both="both",email="email",mobile="mobile",distance="distance",collaborationType="collaborationType";
    String id_str,username_str,descriptions_str,post_content_str,post_image_str, profile_str, volunteer_str, donate_str, both_str,email_str,mobile_str,distance_str,collaboration_Type_str;
    public JSONObject PostsJson;
    public JSONArray PostsJsonAray;
    public JSONObject onePostData;
    ViewGroup post_list_item,Posts_View;
    ImageView Image_arraw_down;
    TextView post_username;
    ArrayList<OptionsEntity> list = new ArrayList<OptionsEntity>();
    PostAdapter postAdapter;
//    DBHelper DB;
    ListView post_list;
    private ProgressDialog progressDialog;
    private Handler handler;
    //    ArrayList<UsersEntity> Logger;
    public String LoggedUser;
    HashMap<String, String> user;
    SessionManagement sessionManagement;
    TextView txtProfileOwner_Posts;
    TextView txt_AddPost_posts;
    ImageView camera_icon_posts;
    CircleImageView ProfileImage_header_Post;
    ViewGroup header;
    ViewGroup post_list_item_ViewGroup;
    LayoutInflater inflater;
    LinearLayout Linear_PhotoLauncherpostsFragment_header;

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

//    private static final int Edit_Post     = 1;
//    private static final int Remove_Post   = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(mo.ed.prof_mohamed.geranyapp.R.layout.activity_posts);
        setTheme(mo.ed.prof_mohamed.geranyapp.R.style.ArishTheme);
//        ActionItem nextItem     = new ActionItem(ID_DOWN, "Next", getResources().getDrawable(R.drawable.menu_down_arrow));
//        ActionItem prevItem     = new ActionItem(ID_UP, "Prev", getResources().getDrawable(R.drawable.menu_up_arrow));

//        sessionManagement= new SessionManagement(getApplicationContext());
//        try {
//            DB = new DBHelper(getApplicationContext());
//        } catch (Exception e) {
//            Log.e(LOG_TAG, "Didn't Create Database", e);
//        }
//        user =sessionManagement.getUserDetails();
//        String email = user.get(SessionManagement.KEY_EMAIL);
                Bundle b = getIntent().getExtras();
//        if (email != null) {
//                    LoggedUser = b.getString("Email", null);
//            LoggedUser=email;
//        }
        progressDialog = new ProgressDialog(getApplicationContext());
        progressDialog.setMessage("Please Wait ...");
        progressDialog.setCancelable(true);
        handler = new Handler();

//        user =sessionManagement.getUserDetails();
//        String email = user.get(SessionManagement.KEY_EMAIL);
//                Bundle b = getIntent().getExtras();
//        if (email != null) {
//                    LoggedUser = b.getString("Email", null);
//            LoggedUser=email;
//        }
//        listview_listplaces = (ListView)findViewById(mo.ed.prof_mohamed.geranyapp.R.id.listview_listplaces);
        toolbar = (Toolbar) findViewById(mo.ed.prof_mohamed.geranyapp.R.id.toolbar);
        setSupportActionBar(toolbar);
        navigationView = (NavigationView) findViewById(mo.ed.prof_mohamed.geranyapp.R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                if(menuItem.isChecked()) menuItem.setChecked(false);
                else menuItem.setChecked(true);
                drawerLayout.closeDrawers();
                switch (menuItem.getItemId()){
                    case mo.ed.prof_mohamed.geranyapp.R.id.Home:
                        return true;
                    case mo.ed.prof_mohamed.geranyapp.R.id.profile:
//                        optionsEntity=DB.getLoggedUserInfo(LoggedUser);
                        Intent intent_LoggerDetails = new Intent(getApplicationContext(), Profile.class);
//                            .putExtra("LoggerInfo", optionsEntity);
//                        Bundle b = new Bundle();
//                        b.putSerializable("LoggerInfo", optionsEntity);
//                        intent_LoggerDetails.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        intent_LoggerDetails.putExtras(b);
                        startActivity(intent_LoggerDetails);
                        return true;
                    case mo.ed.prof_mohamed.geranyapp.R.id.places:
                        Intent intent_places=new Intent(getApplicationContext(),Places_List.class);
                        startActivity(intent_places);
                        return true;
                    case mo.ed.prof_mohamed.geranyapp.R.id.add_place:
//                        Intent intent_new_place=new Intent(getApplicationContext(),Add_New_Place.class);
//                        startActivity(intent_new_place);
                        DialougeAddNewPlace();
                        return true;
                    case R.id.projects:
                        Intent intent = new Intent(getApplicationContext(),ProjectsActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        getApplicationContext().startActivity(intent);
                        return true;
                    case mo.ed.prof_mohamed.geranyapp.R.id.settings:
                        Intent intent_Setting=new Intent(getApplicationContext(),SettingsActivity.class);
                        startActivity(intent_Setting);
                        return true;
                    default:
                        return true;
                }
            }
        });
        // Initializing Drawer Layout and ActionBarToggle
        drawerLayout = (DrawerLayout) findViewById(mo.ed.prof_mohamed.geranyapp.R.id.drawer);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar, mo.ed.prof_mohamed.geranyapp.R.string.openDrawer, mo.ed.prof_mohamed.geranyapp.R.string.closeDrawer){
            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }
            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank

                super.onDrawerOpened(drawerView);
            }
        };
        //Setting the actionbarToggle to drawer layout
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        //calling sync state is necessay or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();
        getFragmentManager().beginTransaction()
                .replace(mo.ed.prof_mohamed.geranyapp.R.id.posts_container_frame, new PostFragment(), "posts")
                .commit();

//        Postlist_ViewContainer=(ViewGroup)getLayoutInflater().inflate(R.layout.post_list,null);
//        post_list = (ListView)findViewById(R.id.listview);


//        header= (ViewGroup) inflater.inflate(R.layout.post_settings_header,post_list,false);

//        post_list_item_ViewGroup=(ViewGroup)getLayoutInflater().inflate(R.layout.post_list_item,null);

//        header=(ViewGroup)getLayoutInflater().inflate(R.layout.post_settings_header,null);
//        txtProfileOwner_Posts=(TextView)header.findViewById(R.id.txtProfileOwner_Posts);
//        txt_AddPost_posts=(TextView) header.findViewById(R.id.txt_AddPost_posts);
//        txt_AddPost_posts.setFocusable(false);
//        txt_AddPost_posts.setFocusableInTouchMode(false);
//        Linear_PhotoLauncherpostsFragment_header=(LinearLayout)header.findViewById(R.id.Linear_PhotoLauncherpostsFragment_header);
//        Linear_PhotoLauncherpostsFragment_header.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getApplicationContext(),AddPostActivity.class);
//                        .putExtra("movieInfo",feedItem);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(intent);
//                Intent intent = new Intent(getActivity(),GalleryActivity.class);
//                getActivity().startActivity(intent);
//            }
//        });
//        PostAdapter postAdapter = new PostAdapter(getApplicationContext(), R.layout.post_list_item,list);
//        post_list.addHeaderView(header,null,false);
//        post_list.setAdapter(postAdapter);
//        postAdapter.notifyDataSetChanged();
//        registerForContextMenu(post_list);
    }

    private void DialougeAddNewPlace() {
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setMessage("Name: ")
//                .setTitle("Your Location now: ")
//                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        // FIRE ZE MISSILES!
//                    }
//                });
//        // Create the AlertDialog object and return it
//        AlertDialog Dialogue=builder.create();
//        Dialogue.show();

//        getFragmentManager().beginTransaction()
//                .add(R.id.posts_container_frame, new PostFragment(), "posts")
//                .commit();
        AlertDialog.Builder builder=new AlertDialog.Builder(this);

        LayoutInflater inflater=this.getLayoutInflater();
        View dialougeView=inflater.inflate(mo.ed.prof_mohamed.geranyapp.R.layout.add_new_place_layout,null);
        txt_GivenPlaceName=(EditText) dialougeView.findViewById(mo.ed.prof_mohamed.geranyapp.R.id.txt_GivenPlaceName);
        txt_GivenPlaceAddress=(EditText) dialougeView.findViewById(mo.ed.prof_mohamed.geranyapp.R.id.txt_GivenPlaceAddress);
        builder.setView(dialougeView)
                .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String txt_GivenPlaceName_STR=txt_GivenPlaceName.getText().toString();
                        String txt_GivenPlaceAddress_STR=txt_GivenPlaceAddress.getText().toString();
                        //Implement Saving
                        boolean inserted=DB.insertPlacesJsonData(txt_GivenPlaceName_STR,txt_GivenPlaceAddress_STR);
                        if (inserted==true){
                            Toast.makeText(getApplicationContext(), "Current Place Added", Toast.LENGTH_LONG).show();
                            list =DB.selectAllPostsData();
                            postAdapter = new PostAdapter(getApplicationContext(), mo.ed.prof_mohamed.geranyapp.R.layout.post_list_item, list);
                            postAdapter.notifyDataSetChanged();
//                            listview_listplaces.setAdapter(postAdapter);
                        }
                    }
                });
        AlertDialog Dialogue=builder.create();
        Dialogue.show();
    }

//
//    @Override
//    public void onResume() {
//        super.onResume();
//        try{
//            int initialVal=DB.SpecifyPosts_Initial_max_Value();
//            if (initialVal==0){
//                startFetchingPostsJson();
//                //insert
//                if (list!=null){
//                    for (final OptionsEntity optionsEntity : list) {
//                        progressDialog.show();
//                        new Thread(new Runnable() {
//                            @Override
//                            public void run() {
//                                boolean inserted=DB.insertJsonData(optionsEntity.getID(),optionsEntity.getUserName(),optionsEntity.getDescription(),optionsEntity.getPost_Content(),optionsEntity.getPost_images(),optionsEntity.getProfile_Images(),optionsEntity.getVolunteer(),optionsEntity.getDonate(),optionsEntity.getBoth(),optionsEntity.getEmail(),optionsEntity.getMobile(),optionsEntity.getDistance(),optionsEntity.getCollaborationType());
//                                if (inserted==true){
//                                    handler.postDelayed(new Runnable() {
//                                        @Override
//                                        public void run() {
//                                            progressDialog.cancel();
//                                        }
//                                    },8000);
//                                }else {
//                                    progressDialog = ProgressDialog.show(getApplicationContext(), "Please Wait ...",
//                                            "Error", true);
//                                }
//                            }
//                        }).start();
//                    }
//                }
//            }else {
//                list =DB.selectAllPostsData();
//                postAdapter = new PostAdapter(getApplicationContext(), mo.ed.prof_mohamed.geranyapp.R.layout.post_list_item, list);
//                postAdapter.notifyDataSetChanged();
//                post_list.setAdapter(postAdapter);
//            }
//        }catch (Exception e){
//        }
//    }
//
//
//    @Override
//    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
//        super.onCreateContextMenu(menu, v, menuInfo);
//        AdapterView.AdapterContextMenuInfo info=(AdapterView.AdapterContextMenuInfo)menuInfo;
//        if (info.position==0||info.position==1||info.position==2||info.position==3||info.position==4||info.position==5){
//            if (v.getId()==R.id.listview) {
//                String PostUsername =post_username.getText().toString();
//                menu.setHeaderTitle(PostUsername+"'s Post");
//                String[] menuItem = {"Edit Post", "Remove Post"};
//                Integer[]menuIcons={R.drawable.editicon,R.drawable.recyclebin};
//                for (int i = 0; i < menuItem.length; i++) {
//                    menu.add(menu.NONE, i, i, menuItem[i]);//.setIcon(menuIcons[i]);
//                }
////                getApplicationContext().getMenuInflater().inflate(R.menu.post_menu_control, menu);
//            }
//        }
//    }
//
//    @Override
//    public boolean onContextItemSelected(MenuItem item) {
//        AdapterView.AdapterContextMenuInfo info=(AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
//        int pos= info.position;
//        switch (pos){
//            case 1:
//                if (item.getTitle()=="Edit Post"){
////                    Intent intent_UdacityAndroidApp = new Intent();
////                    intent_UdacityAndroidApp .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
////                    intent_UdacityAndroidApp .setData(Uri.parse("https://play.google.com/store/apps/details?id=com.udacity.android&hl=en"));
////                    getActivity().startActivity(intent_UdacityAndroidApp );
//                }else if (item.getTitle()=="Remove"){
////                    Intent intent_UdacityiPhoneApp = new Intent();
////                    intent_UdacityiPhoneApp .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
////                    intent_UdacityiPhoneApp .setData(Uri.parse("https://itunes.apple.com/us/app/id819700933?mt=8"));
////                      getActivity().startActivity(intent_UdacityiPhoneApp );
//                }
//        }
//        return super.onContextItemSelected(item);
//    }
//
//    public void startFetchingPostsJson() {
//        try {
//            FetchPostItems fetchTrailers = new FetchPostItems();
//            fetchTrailers.execute(Post_URL);
//        } catch (Exception e) {
//            Log.v(LOG_TAG, "didn't Execute Desires");
//        }
//    }

//    public class FetchPostItems extends AsyncTask<String, Void, ArrayList<OptionsEntity>> {
//
//        private final String LOG_TAG = FetchPostItems.class.getSimpleName();
//
//        private ArrayList<OptionsEntity> getMarketItemsFromJson(String UsersDesires)
//                throws JSONException {
//
//            PostsJson = new JSONObject(UsersDesires);
//            PostsJsonAray= PostsJson.getJSONArray(main_List);
//
//            list.clear();
//            for (int i = 0; i < PostsJsonAray.length(); i++) {
//                // Get the JSON object representing a movie per each loop
//                onePostData= PostsJsonAray.getJSONObject(i);
//                id_str = onePostData.getString(ID);
//                username_str = onePostData.getString(username);
//                descriptions_str= onePostData.getString(description);
//                post_content_str=onePostData.getString(post_content);
//                post_image_str=onePostData.getString(post_image);
//                profile_str=onePostData.getString(profile_image);
//                volunteer_str=onePostData.getString(volunteer);
//                donate_str=onePostData.getString(donate);
//                both_str=onePostData.getString(both);
//                email_str=onePostData.getString(email);
//                mobile_str=onePostData.getString(mobile);
//                distance_str=onePostData.getString(distance);
//                collaboration_Type_str=onePostData.getString(collaborationType);
//                OptionsEntity entity = new OptionsEntity(id_str,username_str,descriptions_str,post_content_str,post_image_str,profile_str,volunteer_str,donate_str,both_str,email_str,mobile_str,distance_str,collaboration_Type_str);
//                list.add(entity);
//            }
//            return list;
//        }
//
//        @Override
//        protected ArrayList<OptionsEntity> doInBackground(String... params) {
//
//            String UsersDesires_JsonSTR = null;
//
//            HttpURLConnection urlConnection = null;
//            BufferedReader reader = null;
//
//            if (params.length == 0) {
//                return null;
//            }
//            try {
//                URL url = new URL(params[0]);
//                urlConnection = (HttpURLConnection) url.openConnection();
//                urlConnection.setRequestMethod("GET");
//                urlConnection.connect();
//                InputStream inputStream = urlConnection.getInputStream();
//                StringBuffer buffer = new StringBuffer();
//                if (inputStream == null) {
//                    UsersDesires_JsonSTR = null;
//                }
//                reader = new BufferedReader(new InputStreamReader(inputStream));
//                String line;
//                while ((line = reader.readLine()) != null) {
//                    buffer.append(line + "\n");
//                }
//                if (buffer.length() == 0) {
//                    return null;
//                }
//                UsersDesires_JsonSTR = buffer.toString();
//                Log.v(LOG_TAG, "Users Desires String: " + UsersDesires_JsonSTR);
//            } catch (IOException e) {
//                Log.e(LOG_TAG, "Error here Exactly ", e);
//                return null;
//            } finally {
//                if (urlConnection != null) {
//                    urlConnection.disconnect();
//                }
//                if (reader != null) {
//                    try {
//                        reader.close();
//                    } catch (final IOException e) {
//                        Log.e(LOG_TAG, "Error closing stream", e);
//                    }
//                }
//            }
//            try {
//                return getMarketItemsFromJson(UsersDesires_JsonSTR);
//            } catch (JSONException e) {
//                Log.e(LOG_TAG, "didn't got Users Desires from getJsonData method", e);
//                e.printStackTrace();
//            }
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(ArrayList<OptionsEntity> result) {
//            if (result != null&& getApplicationContext()!=null) {
//                postAdapter = new PostAdapter(getApplicationContext(), mo.ed.prof_mohamed.geranyapp.R.layout.post_list_item, result);
//                post_list.setAdapter(postAdapter);
//                list=result;
//            }
//        }
//    }
}