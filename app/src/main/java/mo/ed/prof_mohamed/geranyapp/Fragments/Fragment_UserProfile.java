package mo.ed.prof_mohamed.geranyapp.Fragments;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import de.hdodenhof.circleimageview.CircleImageView;
import mo.ed.prof_mohamed.geranyapp.CollaboratorsActivity;
import mo.ed.prof_mohamed.geranyapp.DBHelper;
import mo.ed.prof_mohamed.geranyapp.OptionsEntity;
import mo.ed.prof_mohamed.geranyapp.PostAdapter;

import mo.ed.prof_mohamed.geranyapp.R;
import mo.ed.prof_mohamed.geranyapp.SessionManagement;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Prof-Mohamed on 2/7/2017.
 */
public class Fragment_UserProfile extends Fragment {

    ViewGroup header;
    private final String LOG_TAG = Fragment_UserProfile.class.getSimpleName();

    OptionsEntity optionsEntity;
    static final String Post_URL = "https://cldup.com/NooZUWngcc.json";
    String main_List="Posts" , ID = "id",username="username",description="description",post_content="post_content",post_image="post_image",profile_image="profile_image",volunteer="volunteer",donate="donate",both="both",email="email",mobile="mobile",distance="distance",collaborationType="collaborationType";
    String id_str,username_str,descriptions_str,post_content_str,post_image_str, profile_str, volunteer_str, donate_str, both_str,email_str,mobile_str,distance_str,collaboration_Type_str;
    public JSONObject PostsJson;
    public JSONArray PostsJsonAray;
    public JSONObject onePostData;
    ArrayList<OptionsEntity> list = new ArrayList<OptionsEntity>();
    PostAdapter postAdapter;
    ListView listView;
    Button button_editProfile;
    DBHelper DB;
    ListView post_list;
    private ProgressDialog progressDialog;
    private Handler handler;
    //    ArrayList<UsersEntity> Logger;
    public String LoggedUser;
    HashMap<String, String> user;
    SessionManagement sessionManagement;
    Button btn_editaddress;
    TextView change_txt_address_title,txt_changeaddress;
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

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        ProfileImage=(CircleImageView)header.findViewById(R.id.ProfileImage);
//        txt_posttitle=(TextView)header.findViewById(R.id.txt_posttitle);
//        txt_postvalue=(TextView)header.findViewById(R.id.txt_postvalue);
//        txt_followerstitle=(TextView)header.findViewById(R.id.txt_followerstitle);
//        txt_followersvalue=(TextView)header.findViewById(R.id.txt_followersvalue);
//        txt_followingtitle=(TextView)header.findViewById(R.id.txt_followingtitle);
//        txt_followingvalue=(TextView)header.findViewById(R.id.txt_followingvalue);
//        txt_UserNAme=(TextView)header.findViewById(R.id.txt_UserNAme);
//        btn_edit_profile=(Button)header.findViewById(R.id.btn_edit_profile);

        final Bundle bundle= getArguments();
        if (bundle!=null){
            optionsEntity=(OptionsEntity)bundle.getSerializable("marketInfo");
        }


        button_editProfile=(Button)header.findViewById(R.id.btn_editprofile);
        button_editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                getFragmentManager().beginTransaction()
//                        .replace(R.id.profile_container_frame, new Fragment_Edit_Profile(), "EditProfile")
//                        .commit();

                AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());

                LayoutInflater inflater=getActivity().getLayoutInflater();
                View dialougeView=inflater.inflate(R.layout.fragment_edit_profile,null);
                CircleImageView ProfileImage_edit=(CircleImageView)dialougeView.findViewById(R.id.ProfileImage_edit);
                TextView changephoto=(TextView)dialougeView.findViewById(R.id.txt_changephoto);
                TextView Address_capital=(TextView)dialougeView.findViewById(R.id.Address_capital);
                TextView txt_currentaddress=(TextView)dialougeView.findViewById(R.id.txt_currentaddress);

                TextView phone_capital=(TextView)dialougeView.findViewById(R.id.phone_capital);
                TextView txt_titlephone_home=(TextView)dialougeView.findViewById(R.id.txt_titlephone_home);
                TextView txt_phone_home=(TextView)dialougeView.findViewById(R.id.txt_phone_home);
                Button btn_editphone_home=(Button)dialougeView.findViewById(R.id.btn_editphone_home);
                TextView Mobile_capital=(TextView)dialougeView.findViewById(R.id.Mobile_capital);
                TextView txt_phone_mobile=(TextView)dialougeView.findViewById(R.id.txt_phone_mobile);
                Button btn_editphone_mob=(Button)dialougeView.findViewById(R.id.btn_editphone_mob);
                TextView Email_Capital=(TextView)dialougeView.findViewById(R.id.Email_Capital);
                TextView email_home_title=(TextView)dialougeView.findViewById(R.id.email_home_title);
                TextView txt_email_home=(TextView)dialougeView.findViewById(R.id.txt_email_home);
                Button btn_editemail_home=(Button)dialougeView.findViewById(R.id.btn_editemail_home);
                TextView email_work_title=(TextView)dialougeView.findViewById(R.id.email_work_title);
                TextView txt_email_work=(TextView)dialougeView.findViewById(R.id.txt_email_work);
                Button btn_editemail_work=(Button)dialougeView.findViewById(R.id.btn_editemail_work);
                builder.setView(dialougeView)
                        .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
//                                String txt_GivenPlaceName_STR=txt_GivenPlaceName.getText().toString();
//                                String txt_GivenPlaceAddress_STR=txt_GivenPlaceAddress.getText().toString();
//                                //Implement Saving
//                                boolean inserted=DB.insertPlacesJsonData(txt_GivenPlaceName_STR,txt_GivenPlaceAddress_STR);
//                                if (inserted==true){
//                                    Toast.makeText(getApplicationContext(), "Current Place Added", Toast.LENGTH_LONG).show();
//                                }
                            }
                        });
                AlertDialog Dialogue=builder.create();
                Dialogue.show();
            }
        });

        PostAdapter postAdapter = new PostAdapter(getActivity(),R.layout.post_list_item,list);
//        marketItemsAdapter = new MarketItemsAdapter(getActivity(),R.layout.market_item_list_row,new ArrayList<OptionsEntity>());
        postAdapter.notifyDataSetChanged();
        listView.addHeaderView(header,null,false);
        listView.setAdapter(postAdapter);
//        listView.deferNotifyDataSetChanged();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0){
                    startActivity(new Intent(getActivity(),CollaboratorsActivity.class));
                }
            }
        });
    }

    @Nullable
    @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.fragment_posts_user_history_list,container,false);
        header=(ViewGroup)inflater.inflate(R.layout.header_userprofile,listView,false);
        listView=(ListView)rootView.findViewById(R.id.listview_users_Posts);
        return rootView;
    }

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
                postAdapter = new PostAdapter(getActivity(),R.layout.post_list_item, list);
                postAdapter.notifyDataSetChanged();
                listView.setAdapter(postAdapter);
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
                postAdapter = new PostAdapter(getActivity(),R.layout.post_list_item, result);
                listView.setAdapter(postAdapter);
                list=result;
            }
        }
    }
}