package mo.ed.prof_mohamed.geranyapp;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import mo.ed.prof_mohamed.geranyapp.Fragments.Fragment_PlacesList;
import mo.ed.prof_mohamed.geranyapp.Fragments.Fragment_UserProfile;

public class Places_List extends AppCompatActivity {

    private final String LOG_TAG = Places_List.class.getSimpleName();

    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;

    EditText txt_GivenPlaceName;
    EditText txt_GivenPlaceAddress;

    DBHelper DB;
    private Handler handler;
    private ProgressDialog progressDialog;

    ArrayList<OptionsEntity> list = new ArrayList<OptionsEntity>();
    PostAdapter postAdapter;
    ListView listview_listplaces;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(mo.ed.prof_mohamed.geranyapp.R.layout.activity_places__list);
        setTheme(mo.ed.prof_mohamed.geranyapp.R.style.ArishTheme);
        this.setTitle("My Places");
        try {
            DB = new DBHelper(getApplicationContext());
        } catch (Exception e) {
            Log.e(LOG_TAG, "Didn't Create Database", e);
        }
        if (savedInstanceState==null){
            listview_listplaces = (ListView)findViewById(mo.ed.prof_mohamed.geranyapp.R.id.listview_listplaces);
            toolbar = (Toolbar) findViewById(mo.ed.prof_mohamed.geranyapp.R.id.toolbar);
            setSupportActionBar(toolbar);
            Fragment_PlacesList fragment_placesList= new Fragment_PlacesList();
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
                            Intent intent_Home = new Intent(getApplicationContext(), PostsActivity.class);
                            startActivity(intent_Home);
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
//                            Intent intent_places=new Intent(getApplicationContext(),Places_List.class);
//                            startActivity(intent_places);
                            return true;
                        case mo.ed.prof_mohamed.geranyapp.R.id.add_place:
//                        Intent intent_new_place=new Intent(getApplicationContext(),Add_New_Place.class);
//                        startActivity(intent_new_place);
                            DialougeAddNewPlace();
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
                    .replace(mo.ed.prof_mohamed.geranyapp.R.id.placeslist_container_frame, fragment_placesList,"Places").commit();
        }

        progressDialog = new ProgressDialog(getApplicationContext());
        progressDialog.setMessage("Please Wait ...");
        progressDialog.setCancelable(true);
        handler = new Handler();
    }

//    private void DialougeAddNewPlace() {
//        AlertDialog.Builder builder=new AlertDialog.Builder(this);
//
//        LayoutInflater inflater=this.getLayoutInflater();
//        View dialougeView=inflater.inflate(mo.ed.prof_mohamed.geranyapp.R.layout.add_new_place_layout,null);
//        EditText txt_Name=(EditText) dialougeView.findViewById(mo.ed.prof_mohamed.geranyapp.R.id.txt_GivenPlaceName);
//        EditText txt_Address=(EditText)dialougeView.findViewById(R.id.txt_GivenPlaceAddress);
//        final String areaName=txt_Name.getText().toString();
//        final String areaAddress=txt_Address.getText().toString();
//        builder.setView(dialougeView)
//                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        //Implement Saving
////                        int initialVal=DB.SpecifyPlaces_Initial_max_Value();
////                        if (initialVal==0){
////                            //insert
////                            if (list!=null){
////                                for (final OptionsEntity optionsEntity : list) {
//                                    progressDialog.show();
//                                    new Thread(new Runnable() {
//                                        @Override
//                                        public void run() {
//                                            boolean inserted=DB.insertPlacesJsonData(areaName,areaAddress);
//                                            if (inserted==true){
//                                                handler.postDelayed(new Runnable() {
//                                                    @Override
//                                                    public void run() {
//                                                        progressDialog.cancel();
//                                                    }
//                                                },8000);
//                                            }else {
//                                                progressDialog = ProgressDialog.show(getApplicationContext(), "Please Wait ...",
//                                                        "Error", true);
//                                                Toast.makeText(getApplicationContext(), "Current Place Added", Toast.LENGTH_LONG).show();
//                                            }
//                                        }
//                                    }).start();
//
//
////                        }else {
////                            list =DB.selectAllPlacesData();
////                            placesAdapter= new PlacesAdapter(getActivity(),R.layout.places_listitem, list);
////                            placesAdapter.notifyDataSetChanged();
////                            listView.setAdapter(placesAdapter);
////                            placesAdapter.notifyDataSetChanged();
////                        }
//                    }
//                });
//
//
//
//
//
//
//        AlertDialog Dialogue=builder.create();
//        Dialogue.show();
//    }

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
                            listview_listplaces.setAdapter(postAdapter);
                        }
                    }
                });
        AlertDialog Dialogue=builder.create();
        Dialogue.show();
    }
}
