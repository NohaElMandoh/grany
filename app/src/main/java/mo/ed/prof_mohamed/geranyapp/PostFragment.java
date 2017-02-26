package mo.ed.prof_mohamed.geranyapp;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.ActionProvider;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Spinner;
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
import mo.ed.prof_mohamed.geranyapp.Adapter.CustomSpinnerAdapter;

/**
 * Created by hayyan2001 on 11/21/16.
 */
public class PostFragment extends Fragment implements  AdapterView.OnItemSelectedListener {

    private final String LOG_TAG = PostFragment.class.getSimpleName();

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
    DBHelper DB;
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
    LinearLayout Linear_PhotoLauncherpostsFragment_header,Linear_ImageArrowDown_Post;

    Spinner spinner_UpdatePost_categories ;



    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        txtProfileOwner_Posts=(TextView)header.findViewById(R.id.txtProfileOwner_Posts);
//        txt_AddPost_posts=(EditText) header.findViewById(R.id.txt_AddPost_posts);
        txt_AddPost_posts=(TextView) header.findViewById(R.id.txt_AddPost_posts);
        txt_AddPost_posts.setFocusable(false);
        txt_AddPost_posts.setFocusableInTouchMode(false);
        txt_AddPost_posts.setSelected(false);
//        txt_AddPost_posts.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getActivity(),AddPostActivity.class);
//                        .putExtra("movieInfo",feedItem);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                getActivity().startActivity(intent);
//            }
//        });
//        camera_icon_posts=(ImageView)header.findViewById(R.id.camera_icon_posts_header);
        Linear_PhotoLauncherpostsFragment_header=(LinearLayout)header.findViewById(R.id.Linear_PhotoLauncherpostsFragment_header);
        Linear_PhotoLauncherpostsFragment_header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),AddPostActivity.class);
//                        .putExtra("movieInfo",feedItem);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getActivity().startActivity(intent);
//                Intent intent = new Intent(getActivity(),GalleryActivity.class);
//                getActivity().startActivity(intent);
            }
        });
//        PostAdapter postAdapter = new PostAdapter(getActivity(), R.layout.post_list_item,list);
//        post_list.addHeaderView(header,null,false);
//        post_list.setAdapter(postAdapter);
//        post_list.deferNotifyDataSetChanged();

        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View dialougeView = inflater.inflate(R.layout.update_post_layout, null);
        spinner_UpdatePost_categories = (Spinner) dialougeView.findViewById(R.id.spinner_UpdatePost_categories);
        spinner_UpdatePost_categories.setOnItemSelectedListener(this);
        //Spinner Drop Down elements
        ArrayList<String> rooms_num = new ArrayList<String>();
        rooms_num.add("Project");
        rooms_num.add("District-distributing problem");
        rooms_num.add("Wedding");
        rooms_num.add("Sadness");
        rooms_num.add("Specific help");
        CustomSpinnerAdapter customSpinnerAdapter=new CustomSpinnerAdapter(getActivity(),rooms_num);
        spinner_UpdatePost_categories.setAdapter(customSpinnerAdapter);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sessionManagement= new SessionManagement(getActivity());
        try {
            DB = new DBHelper(getActivity());
        } catch (Exception e) {
            Log.e(LOG_TAG, "Didn't Create Database", e);
        }
        user =sessionManagement.getUserDetails();
        String email = user.get(SessionManagement.KEY_EMAIL);
//                Bundle b = getIntent().getExtras();
        if (email != null) {
//                    LoggedUser = b.getString("Email", null);
            LoggedUser=email;
        }
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Please Wait ...");
        progressDialog.setCancelable(true);
        handler = new Handler();


    }

//    @Override
//    public void onViewCreated(View view, Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        getActivity().findViewById(mo.ed.prof_mohamed.geranyapp.R.id.Image_arraw_down).setOnClickListener(this);
//    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mainView = inflater.inflate(R.layout.post_list, container, false);
        header= (ViewGroup) inflater.inflate(R.layout.post_settings_header,post_list,false);
        Posts_View =(ViewGroup)inflater.inflate(R.layout.post_list_item,null,false);
//        setHasOptionsMenu(true);
        post_list = (ListView)mainView.findViewById(R.id.listview);


//        Linear_ImageArrowDown_Post.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(getActivity(), "long clicked pos: " , Toast.LENGTH_LONG).show();
//            }
//        });

//        post_list.setLongClickable(true);
//        post_list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
//                Toast.makeText(getActivity(), "long clicked pos: " + position, Toast.LENGTH_LONG).show();
//                return true;
//            }
//        });
        postAdapter = new PostAdapter(getActivity(), R.layout.post_list_item,list);
        post_list.addHeaderView(header,null,false);
        postAdapter.notifyDataSetChanged();
        post_list.setAdapter(postAdapter);
        postAdapter.notifyDataSetChanged();
//        registerForContextMenu(Linear_ImageArrowDown_Post);
//        post_list.deferNotifyDataSetChanged();
//        post_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                view.getpare
//            }
//        });
//        post_list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(getActivity(), "long clicked position: " + i, Toast.LENGTH_LONG).show();
//                return true;
//            }
//        });
        // arrow down
//        Image_arraw_down=(ImageView) Posts_View.findViewById(R.id.Image_arraw_down);
//         registerForContextMenu(Image_arraw_down);
//        getActivity().registerForContextMenu(post_list);
//        post_username=(TextView)container.findViewById(R.id.post_username);
        return mainView;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        AdapterView.AdapterContextMenuInfo info=(AdapterView.AdapterContextMenuInfo)menuInfo;
        if (info.position==0||info.position==1||info.position==2||info.position==3||info.position==4){
            if (v.getId()==R.id.listview) {
//                String PostUsername =post_username.getText().toString();
//                menu.setHeaderTitle(PostUsername+"'s Post");
                String[] menuItem = {"Edit Post", "Remove Post"};
                Integer[]menuIcons={R.drawable.editicon,R.drawable.recyclebin};
                for (int i = 0; i < menuItem.length; i++) {
                    menu.add(menu.NONE, i, i, menuItem[i]);//.setIcon(menuIcons[i]);
                }
//                getActivity().getMenuInflater().inflate(R.menu.post_menu_control, menu);
            }
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info=(AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        int pos= info.position;
        switch (pos){
            case 1:
                if (item.getTitle()=="Edit Post"){
//                    Intent intent_UdacityAndroidApp = new Intent();
//                    intent_UdacityAndroidApp .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    intent_UdacityAndroidApp .setData(Uri.parse("https://play.google.com/store/apps/details?id=com.udacity.android&hl=en"));
//                    getActivity().startActivity(intent_UdacityAndroidApp );
                }else if (item.getTitle()=="Remove"){
//                    Intent intent_UdacityiPhoneApp = new Intent();
//                    intent_UdacityiPhoneApp .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    intent_UdacityiPhoneApp .setData(Uri.parse("https://itunes.apple.com/us/app/id819700933?mt=8"));
//                      getActivity().startActivity(intent_UdacityiPhoneApp );
                }
        }
        return super.onContextItemSelected(item);
    }

    //    @Override
//    public void onCreateOptionsMenu(
//            Menu menu, MenuInflater inflater) {
//        inflater.inflate(R.menu.main, menu);
//    }
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // handle item selection
//        switch (item.getItemId()) {
////            case R.id.action_search:
////                // do s.th.
////                return true;
//            case R.id.action_profile:
//                // do s.th.
////                optionsEntity=DB.getLoggedUserInfo(LoggedUser);
//                /*
//                Logger Info
//                1. posts
//                2. followers11
//                3. following
//                4. User Name
//                5. User Image
//                6.
//                 */
//
//
//                Intent intent_LoggerDetails = new Intent(getActivity(), Profile.class)
//                .putExtra("LoggerInfo", optionsEntity);
////                Bundle b = new Bundle();
////                b.putSerializable("LoggerInfo", optionsEntity);
//                intent_LoggerDetails.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
////                intent_LoggerDetails.putExtras(b);
//                startActivity(intent_LoggerDetails);
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }

    @Override
    public void onResume() {
        super.onResume();
        try{
            int initialVal=DB.SpecifyPosts_Initial_max_Value();
            if (initialVal==0){
                startFetchingPostsJson();
                //insert
                if (list!=null){
                    for (final OptionsEntity optionsEntity : list) {
                        progressDialog.show();
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                boolean inserted=DB.insertJsonData(optionsEntity.getID(),optionsEntity.getUserName(),optionsEntity.getDescription(),optionsEntity.getPost_Content(),optionsEntity.getPost_images(),optionsEntity.getProfile_Images(),optionsEntity.getVolunteer(),optionsEntity.getDonate(),optionsEntity.getBoth(),optionsEntity.getEmail(),optionsEntity.getMobile(),optionsEntity.getDistance(),optionsEntity.getCollaborationType());
                                if (inserted==true){
                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            progressDialog.cancel();
                                        }
                                    },8000);
                                }else {
                                     progressDialog = ProgressDialog.show(getActivity(), "Please Wait ...",
                                    "Error", true);
                                }
                                }
                            }).start();
                    }
                }
            }else {
                list =DB.selectAllPostsData();
                postAdapter = new PostAdapter(getActivity(), mo.ed.prof_mohamed.geranyapp.R.layout.post_list_item, list);
                postAdapter.notifyDataSetChanged();
                post_list.setAdapter(postAdapter);
            }
        }catch (Exception e){
        }
    }

    public void startFetchingPostsJson() {
        try {
            FetchPostItems fetchTrailers = new FetchPostItems();
                fetchTrailers.execute(Post_URL);
        } catch (Exception e) {
            Log.v(LOG_TAG, "didn't Execute Desires");
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

//    @Override
//    public void onClick(View v) {
        // arrow down
//        Image_arraw_down=(ImageView)getView().findViewById(mo.ed.prof_mohamed.geranyapp.R.id.Image_arraw_down);
//        Image_arraw_down.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getActivity(), "Current Place Added", Toast.LENGTH_LONG).show();
//            }
//        });

//    }

    public class FetchPostItems extends AsyncTask<String, Void, ArrayList<OptionsEntity>> {

        private final String LOG_TAG = FetchPostItems.class.getSimpleName();

        private ArrayList<OptionsEntity> getMarketItemsFromJson(String UsersDesires)
                throws JSONException {

            PostsJson = new JSONObject(UsersDesires);
            PostsJsonAray= PostsJson.getJSONArray(main_List);

            list.clear();
            for (int i = 0; i < PostsJsonAray.length(); i++) {
                // Get the JSON object representing a movie per each loop
                onePostData= PostsJsonAray.getJSONObject(i);
                id_str = onePostData.getString(ID);
                username_str = onePostData.getString(username);
                descriptions_str= onePostData.getString(description);
                post_content_str=onePostData.getString(post_content);
                post_image_str=onePostData.getString(post_image);
                profile_str=onePostData.getString(profile_image);
                volunteer_str=onePostData.getString(volunteer);
                donate_str=onePostData.getString(donate);
                both_str=onePostData.getString(both);
                email_str=onePostData.getString(email);
                mobile_str=onePostData.getString(mobile);
                distance_str=onePostData.getString(distance);
                collaboration_Type_str=onePostData.getString(collaborationType);
                OptionsEntity entity = new OptionsEntity(id_str,username_str,descriptions_str,post_content_str,post_image_str,profile_str,volunteer_str,donate_str,both_str,email_str,mobile_str,distance_str,collaboration_Type_str);
                list.add(entity);
            }
            return list;
        }

        @Override
        protected ArrayList<OptionsEntity> doInBackground(String... params) {

            String UsersDesires_JsonSTR = null;

            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

            if (params.length == 0) {
                return null;
            }
            try {
                URL url = new URL(params[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    UsersDesires_JsonSTR = null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");
                }
                if (buffer.length() == 0) {
                    return null;
                }
                UsersDesires_JsonSTR = buffer.toString();
                Log.v(LOG_TAG, "Users Desires String: " + UsersDesires_JsonSTR);
            } catch (IOException e) {
                Log.e(LOG_TAG, "Error here Exactly ", e);
                return null;
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e(LOG_TAG, "Error closing stream", e);
                    }
                }
            }
            try {
                return getMarketItemsFromJson(UsersDesires_JsonSTR);
            } catch (JSONException e) {
                Log.e(LOG_TAG, "didn't got Users Desires from getJsonData method", e);
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<OptionsEntity> result) {
            if (result != null&& getActivity()!=null) {
                postAdapter = new PostAdapter(getActivity(), mo.ed.prof_mohamed.geranyapp.R.layout.post_list_item, result);
                post_list.setAdapter(postAdapter);
                list=result;
            }
        }
    }
}