package mo.ed.prof_mohamed.geranyapp.Fragments;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import mo.ed.prof_mohamed.geranyapp.DBHelper;
import mo.ed.prof_mohamed.geranyapp.OptionsEntity;
import mo.ed.prof_mohamed.geranyapp.PlacesAdapter;

import mo.ed.prof_mohamed.geranyapp.R;
import mo.ed.prof_mohamed.geranyapp.SessionManagement;

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

/**
 * Created by Prof-Mohamed on 2/13/2017.
 */

public class Fragment_PlacesList  extends Fragment{

    private final String LOG_TAG = Fragment_UserProfile.class.getSimpleName();

    OptionsEntity optionsEntity;
    static final String Post_URL = "https://cldup.com/Qy--TkyGJp.json";
    String main_List="Places" , ID = "id",areaname="areaname",areaaddress="areaaddress";
    String id_str,areaname_str,areaaddress_str;
    public JSONObject PostsJson;
    public JSONArray PostsJsonAray;
    public JSONObject onePostData;
    ArrayList<OptionsEntity> list = new ArrayList<OptionsEntity>();
    ListView listView;
    DBHelper DB;
    ListView post_list;
    private ProgressDialog progressDialog;
    private Handler handler;
    //    ArrayList<UsersEntity> Logger;
    public String LoggedUser;
    HashMap<String, String> user;
    SessionManagement sessionManagement;
    PlacesAdapter placesAdapter;

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
        final Bundle bundle= getArguments();
        if (bundle!=null){
//            optionsEntity=(OptionsEntity)bundle.getSerializable("marketInfo");
        }
    }


//    @Override
//    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
//        super.onCreateContextMenu(menu, v, menuInfo);
//        AdapterView.AdapterContextMenuInfo info=(AdapterView.AdapterContextMenuInfo)menuInfo;
//        if (info.position!=-1){
//            if (v.getId()==R.id.listview_listplaces) {
////                String PostUsername =post_username.getText().toString();
////                menu.setHeaderTitle(PostUsername+"'s Post");
//                String[] menuItem = {"Edit Post", "Remove Post"};
//                Integer[]menuIcons={R.drawable.editicon,R.drawable.recyclebin};
//                for (int i = 0; i < menuItem.length; i++) {
//                    menu.add(menu.NONE, i, i, menuItem[i]);//.setIcon(menuIcons[i]);
//                }
////                getApplicationContext().getMenuInflater().inflate(R.menu.post_menu_control, menu);
//            }
//        }
//    }

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


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.fragment_placeslist,container,false);
        container= (ViewGroup) inflater.inflate(R.layout.places_listitem,container,false);
        listView=(ListView)rootView.findViewById(R.id.listview_listplaces);
        placesAdapter= new PlacesAdapter(getActivity(),R.layout.places_listitem,list);
//        marketItemsAdapter = new MarketItemsAdapter(getActivity(),R.layout.market_item_list_row,new ArrayList<OptionsEntity>());
        placesAdapter.notifyDataSetChanged();
        listView.setAdapter(placesAdapter);
        placesAdapter.notifyDataSetChanged();
//        registerForContextMenu(listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0){
//                    startActivity(new Intent(getActivity(),CollaboratorsActivity.class));
                }
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getActivity(), "long clicked position: " + i, Toast.LENGTH_LONG).show();
                return true;
            }
        });
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        try{
            int initialVal=DB.SpecifyPlaces_Initial_max_Value();
            if (initialVal==0){
                startFetchingPlacesJson();
                //insert
                if (list!=null){
                    for (final OptionsEntity optionsEntity : list) {
                        progressDialog.show();
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                boolean inserted=DB.insertPlacesJsonData(optionsEntity.getAreaname(),optionsEntity.getAreaaddress());
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
                list =DB.selectAllPlacesData();
                placesAdapter= new PlacesAdapter(getActivity(),R.layout.places_listitem, list);
                placesAdapter.notifyDataSetChanged();
                listView.setAdapter(placesAdapter);
                placesAdapter.notifyDataSetChanged();
            }
        }catch (Exception e){
        }
    }

    public void startFetchingPlacesJson() {
        try {
            FetchPlacesItems fetchTrailers = new FetchPlacesItems ();
            fetchTrailers.execute(Post_URL);
        } catch (Exception e) {
            Log.v(LOG_TAG, "didn't Execute Desires");
        }
    }

    public class FetchPlacesItems extends AsyncTask<String, Void, ArrayList<OptionsEntity>> {

        private final String LOG_TAG = FetchPlacesItems .class.getSimpleName();

        private ArrayList<OptionsEntity> getMarketItemsFromJson(String UsersDesires)
                throws JSONException {

            PostsJson = new JSONObject(UsersDesires);
            PostsJsonAray= PostsJson.getJSONArray(main_List);

            list.clear();
            for (int i = 0; i < PostsJsonAray.length(); i++) {
                // Get the JSON object representing a movie per each loop
                onePostData= PostsJsonAray.getJSONObject(i);
                id_str = onePostData.getString(ID);
                areaname_str=onePostData.getString(areaname);
                areaaddress_str=onePostData.getString(areaaddress);
                OptionsEntity entity = new OptionsEntity(id_str,areaname_str,areaaddress_str);
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
                placesAdapter= new PlacesAdapter(getActivity(),R.layout.places_listitem, result);
                listView.setAdapter(placesAdapter);
                list=result;
            }
        }
    }
}
