package mo.ed.prof_mohamed.geranyapp;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class CollaboratorsActivity extends AppCompatActivity {

    public static final int permission_checker = 1;
    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collaborators);
        setTitle("Contributers");

        // Assume thisActivity is the current activity
        int permissionCheck = ContextCompat.checkSelfPermission(getApplication(),
                Manifest.permission.CALL_PHONE);
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(getApplication(),
                Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.CALL_PHONE)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CALL_PHONE},
                        permission_checker);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        AppBarLayout appBarLayout = (AppBarLayout)findViewById(R.id.appbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case permission_checker: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_collaborators, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.project_details) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        ArrayList<String> username;
        ArrayList<Integer> profile_images;
        ArrayList<String> email;
        ArrayList<String> mobile;
        ArrayList<String> distance;
        AlertDialog alertDialog;
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_collaborators, container, false);
            username = new ArrayList<>();
            profile_images = new ArrayList<>();
            email = new ArrayList<>();
            mobile = new ArrayList<>();
            distance = new ArrayList<>();
            ListView listView = (ListView)rootView.findViewById(R.id.collaborators_listView);
            int position = getArguments().getInt(ARG_SECTION_NUMBER);
            for (int i = 0; i < Constants.username.length; i++){
                if (position == Constants.collaborationType[i]){
                    username.add(Constants.username[i]);
                    profile_images.add(Constants.profile_images[i]);
                    email.add(Constants.email[i]);
                    mobile.add(Constants.mobile[i]);
                    distance.add(Constants.distance[i]);
                    Log.v("fragment_position",Constants.username[i]);
                }
            }
            CollaboratorsAdapter collaboratorsAdapter = new CollaboratorsAdapter(getActivity(),username,
                    profile_images,distance);
            listView.setAdapter(collaboratorsAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                    View v = LayoutInflater.from(getActivity()).inflate(R.layout.profile_view_layout,null);
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    CircleImageView profilePic = (CircleImageView)v.findViewById(mo.ed.prof_mohamed.geranyapp.R.id.profile_profilePic);
                    TextView profile_username = (TextView)v.findViewById(mo.ed.prof_mohamed.geranyapp.R.id.profile_usename);
                    TextView profile_distance = (TextView)v.findViewById(mo.ed.prof_mohamed.geranyapp.R.id.profile_distance);
                    TextView Profile_email = (TextView)v.findViewById(mo.ed.prof_mohamed.geranyapp.R.id.profile_email);
                    TextView profile_mobile = (TextView)v.findViewById(mo.ed.prof_mohamed.geranyapp.R.id.profile_mobile);
                    ImageView cancel = (ImageView)v.findViewById(mo.ed.prof_mohamed.geranyapp.R.id.close);
                    profilePic.setImageResource(profile_images.get(position));
                    profile_username.setText(username.get(position));
                    profile_distance.setText("Distance: "+ distance.get(position));
                    Profile_email.setText("E-Mail: " + email.get(position));
                    profile_mobile.setText("MobileNumber: "+mobile.get(position));


                    // Inflate and set the layout for the dialog
                    // Pass null as the parent view because its going in the dialog layout
                    builder.setView(v)
                            // Add action buttons
                            .setPositiveButton("Call", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int id) {
                                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + mobile.get(position)));
                                    startActivity(intent);

                                }
                            })
                            .setNegativeButton("Send Email",  new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int id) {
                                    Intent intent = new Intent(Intent.ACTION_SENDTO); // it's not ACTION_SEND
                                    intent.setType("text/plain");
                                    intent.putExtra(Intent.EXTRA_SUBJECT, "Shehab Salah via Gerany App");
                                    intent.putExtra(Intent.EXTRA_TEXT, "");
                                    intent.setData(Uri.parse("mailto:"+email.get(position))); // or just "mailto:" for blank
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // this will make such that when user returns to your app, your app is displayed, instead of the email app.
                                    startActivity(intent);
                                }
                            });
                    alertDialog = builder.create();
                    alertDialog.show();
                    cancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            alertDialog.cancel();
                        }
                    });
                }
            });
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Volunteered";
                case 1:
                    return "Donated";
                case 2:
                    return "Both";
            }
            return super.getPageTitle(position);
        }
    }
}