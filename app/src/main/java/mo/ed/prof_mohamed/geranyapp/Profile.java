package mo.ed.prof_mohamed.geranyapp;

import android.content.DialogInterface;
import android.content.Intent;
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

import mo.ed.prof_mohamed.geranyapp.Fragments.Fragment_UserProfile;

public class Profile extends AppCompatActivity {

//    ViewGroup header;
//    ListView listView;
//    OptionsEntity optionsEntity;
//    ArrayList<OptionsEntity> list = new ArrayList<OptionsEntity>();
//    CircleImageView ProfileImage;
//    TextView txt_postvalue,txt_followersvalue, txt_followingvalue, txt_posttitle, txt_followerstitle, txt_followingtitle, txt_UserNAme;
//    Button btn_edit_profile;
//    LayoutInflater inflater;

    private final String LOG_TAG = Profile.class.getSimpleName();

    EditText txt_GivenPlaceName;
    EditText txt_GivenPlaceAddress;

    DBHelper DB;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    PostAdapter postAdapter;
    ArrayList<OptionsEntity> list = new ArrayList<OptionsEntity>();
    ListView listview_listplaces;

//    @Override
//    public boolean onPrepareOptionsMenu(Menu menu) {
//        if(getFragmentManager().findFragmentById(R.layout.header_userprofile)!=null){
//
//        }else if (getFragmentManager().findFragmentById(R.layout.header_userprofile)!=null){
//
//        }
//        return super.onPrepareOptionsMenu(menu);
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.main, menu);//Menu Resource, Menu
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        if (item.getItemId()==R.id.actionedit_profile){
//            // go to fragment_edit_profile
////            Fragment_Edit_Profile fragment_edit_profile = new Fragment_Edit_Profile();
////            Bundle bundle = new Bundle();
////            bundle.putSerializable("LoggerInfo", getIntent().getSerializableExtra("LoggerInfo"));
////            fragment_edit_profile.setArguments(bundle);
////            getFragmentManager().beginTransaction()
////                    .replace(R.id.ProfileInfo_Container, fragment_edit_profile ,"EditProfile").commit();
//
//
//
//
//        }
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(mo.ed.prof_mohamed.geranyapp.R.layout.activity_profile);
        setTheme(mo.ed.prof_mohamed.geranyapp.R.style.ArishTheme);
        this.setTitle("Profile");
        try {
            DB = new DBHelper(getApplicationContext());
        } catch (Exception e) {
            Log.e(LOG_TAG, "Didn't Create Database", e);
        }



        if (savedInstanceState==null){
            Fragment_UserProfile fragment_userProfile = new Fragment_UserProfile();
//            Bundle bundle = new Bundle();
//            bundle.putSerializable("LoggerInfo", getIntent().getSerializableExtra("LoggerInfo"));
//            fragment_userProfile.setArguments(bundle);

            listview_listplaces = (ListView)findViewById(mo.ed.prof_mohamed.geranyapp.R.id.listview_listplaces);
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
                            Intent intent_LoggerDetails = new Intent(getApplicationContext(), PostsActivity.class);
                            startActivity(intent_LoggerDetails);
                            return true;
                        case mo.ed.prof_mohamed.geranyapp.R.id.profile:
//                        optionsEntity=DB.getLoggedUserInfo(LoggedUser);
//                            Intent intent_LoggerDetails = new Intent(getApplicationContext(), Profile.class);
//                            .putExtra("LoggerInfo", optionsEntity);
//                        Bundle b = new Bundle();
//                        b.putSerializable("LoggerInfo", optionsEntity);
//                        intent_LoggerDetails.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        intent_LoggerDetails.putExtras(b);
//                            startActivity(intent_LoggerDetails);

                            return true;
                        case mo.ed.prof_mohamed.geranyapp.R.id.places:
                            Intent intent_places=new Intent(getApplicationContext(),Places_List.class);
                            startActivity(intent_places);
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

//            getFragmentManager().beginTransaction()
//                    .replace(R.id.posts_container_frame, new PostFragment(), "posts")
//                    .commit();
//
            getFragmentManager().beginTransaction()
                    .replace(mo.ed.prof_mohamed.geranyapp.R.id.profile_container_frame, fragment_userProfile,"Profile").commit();

            /*

             */

//            header=(ViewGroup)inflater.inflate(R.layout.header_userprofile,listView,false);
//            ProfileImage=(CircleImageView)header.findViewById(R.id.ProfileImage);
//            txt_posttitle=(TextView)header.findViewById(R.id.txt_posttitle);
//            txt_postvalue=(TextView)header.findViewById(R.id.txt_postvalue);
//            txt_followerstitle=(TextView)header.findViewById(R.id.txt_followerstitle);
//            txt_followersvalue=(TextView)header.findViewById(R.id.txt_followersvalue);
//            txt_followingtitle=(TextView)header.findViewById(R.id.txt_followingtitle);
//            txt_followingvalue=(TextView)header.findViewById(R.id.txt_followingvalue);
//            txt_UserNAme=(TextView)header.findViewById(R.id.txt_UserNAme);
//            btn_edit_profile=(Button)header.findViewById(R.id.btn_edit_profile);

//            final Bundle bundle= getArguments();
//            if (bundle!=null){
//                optionsEntity=(OptionsEntity)bundle.getSerializable("marketInfo");
//            }

//            listView=(ListView)findViewById(R.id.listview_users_Posts);

//            PostAdapter postAdapter = new PostAdapter(getApplicationContext(),R.layout.post_list_item,list);

//        marketItemsAdapter = new MarketItemsAdapter(getActivity(),R.layout.market_item_list_row,new ArrayList<OptionsEntity>());

//            listView.addHeaderView(header,null,false);
//            listView.setAdapter(postAdapter);

        }
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
///    public void edit_profile_method(View view) {
//        Intent intent = new Intent(getApplicationContext(),Profile.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        getApplicationContext().startActivity(intent);
//    }
}