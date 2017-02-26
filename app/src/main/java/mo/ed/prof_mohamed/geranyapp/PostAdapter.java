package mo.ed.prof_mohamed.geranyapp;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.support.v7.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import mo.ed.prof_mohamed.geranyapp.Adapter.CustomSpinnerAdapter;

/**
 * Created by hayyan2001 on 11/21/16.
 */
public class PostAdapter extends ArrayAdapter {
    PostHolder postHolder;
    public transient Context mContext;
    private LayoutInflater inflater = null;
    private List<OptionsEntity> feedItemList;

    public PostAdapter(Context context,int Resource,ArrayList<OptionsEntity> feedItemList) {
        super(context, Resource, feedItemList);
        mContext = context;
        this.feedItemList=feedItemList;
    }
    @Override
    public int getCount() {
        return feedItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @TargetApi(21)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final OptionsEntity feedItem=feedItemList.get(position);
        View itemView = convertView;
        if (itemView == null){
            inflater = (LayoutInflater) this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            itemView = inflater.inflate(R.layout.post_list_item, parent, false);
            itemView.setTag(postHolder);
        }else{
            postHolder = (PostHolder) itemView.getTag();
        }
        postHolder = new PostHolder();
        postHolder.profile_picture = (de.hdodenhof.circleimageview.CircleImageView) itemView.findViewById(R.id.post_profile_picture);
        postHolder.profile_picture.setFocusableInTouchMode(false);
        postHolder.username = (TextView) itemView.findViewById(mo.ed.prof_mohamed.geranyapp.R.id.post_username);
        postHolder.username.setFocusableInTouchMode(false);
        postHolder.username.setFocusable(false);
        postHolder.user_description = (TextView) itemView.findViewById(mo.ed.prof_mohamed.geranyapp.R.id.post_user_description);
        postHolder.user_description.setFocusable(false);
        postHolder.user_description.setFocusableInTouchMode(false);
        postHolder.post_content = (TextView)itemView.findViewById(mo.ed.prof_mohamed.geranyapp.R.id.post_text);
        postHolder.post_content.setFocusable(false);
        postHolder.post_content.setFocusableInTouchMode(false);
        postHolder.post_image = (ImageView)itemView.findViewById(mo.ed.prof_mohamed.geranyapp.R.id.post_image);
        postHolder.post_image.setFocusable(false);
        postHolder.post_image.setFocusableInTouchMode(false);
        postHolder.Image_arraw_down=(ImageView) itemView.findViewById(R.id.Image_arraw_down);
        postHolder.Image_arraw_down.setFocusable(false);
        postHolder.Image_arraw_down.setFocusableInTouchMode(false);
        postHolder.volunteer = (TextView)itemView.findViewById(mo.ed.prof_mohamed.geranyapp.R.id.post_volunteer);
        postHolder.Linear_AllPostDetails=(LinearLayout)itemView.findViewById(R.id.Linear_AllPostDetails);
        postHolder.Linear_AllPostDetails.setFocusableInTouchMode(false);
        postHolder.PostsListItemHeader=(LinearLayout)itemView.findViewById(R.id.PostsListItemHeader);
        postHolder.PostsListItemHeader.setFocusableInTouchMode(false);
        postHolder.contibuters_launcher=(LinearLayout)itemView.findViewById(R.id.contibuters_launcher);
        postHolder.contibuters_launcher.setFocusable(false);
        postHolder.contibuters_launcher.setFocusableInTouchMode(false);
        postHolder.Post_ContributionDetails_Linear=(LinearLayout)itemView.findViewById(R.id.Post_ContributionDetails_Linear);
        postHolder.Post_ContributionDetails_Linear.setFocusableInTouchMode(false);
        postHolder.Post_ContributionDetails_Linear_3=(LinearLayout)itemView.findViewById(R.id.Post_ContributionDetails_Linear_3);
        postHolder.Post_ContributionDetails_Linear_3.setFocusable(false);
        postHolder.txt_required_title=(TextView)itemView.findViewById(R.id.txt_required_title);
        postHolder.txt_required_title.setFocusable(false);
        postHolder.txt_required_all=(TextView)itemView.findViewById(R.id.txt_required_all);
        postHolder.txt_required_all.setFocusableInTouchMode(false);
        postHolder.txt_required_all.setFocusable(false);
        postHolder.txt_required_paid=(TextView)itemView.findViewById(R.id.txt_required_paid);
        postHolder.txt_required_paid.setFocusable(false);
        postHolder.img_dolarsign=(ImageView)itemView.findViewById(R.id.img_dolarsign);
        postHolder.img_dolarsign.setFocusable(false);
        postHolder.Linear_ImageArrowDown_Post=(LinearLayout)itemView.findViewById(R.id.Linear_ImageArrowDown_Post);
        postHolder.Linear_ImageArrowDown_Post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu=new PopupMenu(mContext,postHolder.Linear_ImageArrowDown_Post);
                popupMenu.getMenuInflater().inflate(R.menu.post_menu_control,popupMenu.getMenu());
                popupMenu.setGravity(Gravity.CENTER_VERTICAL);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getTitle().toString().equals("Edit Post")){
                            editPostDialogue();

                        }else if (item.getTitle().toString().equals("Remove Post")){
                            removePostDialogue();
                        }
                        return true;
                    }
                });
                popupMenu.show();
            }
        });
        postHolder.view_header_post=(View)itemView.findViewById(R.id.view_header);
        postHolder.view_header_post.setFocusable(false);
        postHolder.view_center_post=(View)itemView.findViewById(R.id.view_center_post);
        postHolder.view_center_post.setFocusable(false);
        postHolder.Linear_ContributionDetails_Post=(LinearLayout)itemView.findViewById(R.id.Linear_ContributionDetails_Post);
        postHolder.Linear_ContributionDetails_Post.setFocusable(false);
        postHolder.Linear_Dislike_post=(LinearLayout)itemView.findViewById(R.id.Linear_Dislike_post);
        postHolder.Linear_Dislike_post.setFocusable(false);
        postHolder.post_dislike_num=(TextView)itemView.findViewById(R.id.post_dislike_num);
        postHolder.post_dislike_num.setFocusable(false);
        postHolder.post_dislike_word=(TextView)itemView.findViewById(R.id.post_dislike_word);
        postHolder.post_dislike_word.setFocusable(false);
        postHolder.view_vertical_post_dislike_left=(View)itemView.findViewById(R.id.view_vertical_post_dislike_left);
        postHolder.view_vertical_post_dislike_left.setFocusable(false);
        postHolder.Linear_Volunteer_post=(LinearLayout)itemView.findViewById(R.id.Linear_Volunteer_post);
        postHolder.Linear_Volunteer_post.setFocusable(false);
        postHolder.post_volunteer_num=(TextView)itemView.findViewById(R.id.post_volunteer_num);
        postHolder.post_volunteer_num.setFocusable(false);
        postHolder.post_volunteer_num.setClickable(false);
        postHolder.post_volunteer_word=(TextView)itemView.findViewById(R.id.post_volunteer_word);
        postHolder.post_volunteer_word.setFocusable(false);
        postHolder.view_vertical_post_volunteer_right=(View)itemView.findViewById(R.id.view_vertical_post_volunteer_right);
        postHolder.view_vertical_post_volunteer_right.setFocusable(false);
        postHolder.Linear_post_Donations_above=(LinearLayout)itemView.findViewById(R.id.Linear_post_Donations_above);
        postHolder.Linear_post_Donations_above.setFocusable(false);
        postHolder.post_donate_num=(TextView)itemView.findViewById(R.id.post_donate_num);
        postHolder.post_donate_num.setFocusable(false);
        postHolder.post_donate_num.setClickable(false);
        postHolder.post_donate_word=(TextView)itemView.findViewById(R.id.post_donate_word);
        postHolder.post_donate_word.setFocusable(false);
        postHolder.post_donate_word.setClickable(false);
        postHolder.view_vertical_post_donation=(View)itemView.findViewById(R.id.view_vertical_post_donation);
        postHolder.view_vertical_post_donation.setFocusable(false);
        postHolder.Linear_Post_Reaction_images=(LinearLayout)itemView.findViewById(R.id.Linear_Post_Reaction_images);
        postHolder.Linear_Post_Reaction_images.setFocusable(false);
        postHolder.LinearDislikeImage=(LinearLayout)itemView.findViewById(R.id.LinearDislikeImage);
        postHolder.LinearDislikeImage.setFocusable(false);
        postHolder.hand_logo=(ImageView)itemView.findViewById(R.id.hand_logo) ;
        postHolder.hand_logo.setFocusable(false);
        postHolder.post_dislike=(TextView)itemView.findViewById(R.id.post_dislike);
        postHolder.post_dislike.setFocusable(false);
        postHolder.view_footer_post=(View)itemView.findViewById(R.id.view_footer_post);
        postHolder.view_footer_post.setFocusable(false);
        postHolder.volunteer_Image_Linear=(LinearLayout)itemView.findViewById(R.id.volunteer_Image_Linear);
        postHolder.volunteer_Image_Linear.setFocusable(false);
        postHolder.heart_logo=(ImageView)itemView.findViewById(R.id.heart_logo);
        postHolder.heart_logo.setFocusable(false);
        postHolder.post_volunteer=(TextView)itemView.findViewById(R.id.post_volunteer);
        postHolder.post_volunteer.setFocusable(false);
        postHolder.view_vertical_footer_left=(View)itemView.findViewById(R.id.view_vertical_footer_left);
        postHolder.view_vertical_footer_left.setFocusable(false);
        postHolder.Linear_donations_post=(LinearLayout)itemView.findViewById(R.id.Linear_donations_post);
        postHolder.Linear_donations_post.setFocusable(false);
        postHolder.donatebutton_logo=(ImageView)itemView.findViewById(R.id.donatebutton_logo);
        postHolder.donatebutton_logo.setFocusable(false);
        postHolder.txt_post_donate=(TextView)itemView.findViewById(R.id.txt_post_donate);
        postHolder.txt_post_donate.setFocusable(false);
        Picasso.with(mContext).load(feedItem.getProfile_Images()).into(postHolder.profile_picture);
        postHolder.username.setText(feedItem.getUserName());
        postHolder.user_description.setText(feedItem.getDescription());
        postHolder.post_content.setText(feedItem.getPost_Content());
        postHolder.contibuters_launcher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,CollaboratorsActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });
        Picasso.with(mContext).load(feedItem.getPost_images()).into(postHolder.post_image);
        postHolder.post_volunteer_num.setText(feedItem.getVolunteer() + " Volunteer");
        postHolder.post_donate_num.setText(feedItem.getDonate() + " Donate");
        postHolder.post_dislike_num.setText(feedItem.getBoth()+ " Dislike");
        return itemView;
    }

    private void removePostDialogue() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View dialougeView = inflater.inflate(R.layout.delete_post_layout, null);
        final TextView txt_del_post= (TextView) dialougeView.findViewById(R.id.txt_del_post);
        builder.setView(dialougeView)
                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //    Implement Deleteing
                    }
                });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        AlertDialog Dialogue = builder.create();
        Dialogue.show();
    }

    private void editPostDialogue() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View dialougeView = inflater.inflate(R.layout.update_post_layout, null);
        final EditText Edit_Text_UpdatePost = (EditText) dialougeView.findViewById(R.id.Edit_Text_UpdatePost);
        Button Btn_Update_Post_UploadPicture = (Button) dialougeView.findViewById(R.id.Btn_Update_Post_UploadPicture);
        Spinner spinner_UpdatePost_categories = (Spinner) dialougeView.findViewById(R.id.spinner_UpdatePost_categories);
        spinner_UpdatePost_categories.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        ArrayList<String> rooms_num = new ArrayList<String>();
        rooms_num.add("Project");
        rooms_num.add("District-distributing problem");
        rooms_num.add("Wedding");
        rooms_num.add("Sadness");
        rooms_num.add("Specific help");
        rooms_num.add("Critical Alarming");
        CustomSpinnerAdapter customSpinnerAdapter=new CustomSpinnerAdapter(mContext,rooms_num);
        spinner_UpdatePost_categories.setAdapter(customSpinnerAdapter);


//                        Btn_Update_Post_UploadPicture.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//                                startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI), 3);
//
//                            }
//                        });
        builder.setView(dialougeView)
                .setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String Edit_Text_UpdatePost_STR = Edit_Text_UpdatePost.getText().toString();
                        //    Implement Updating
//                                        boolean inserted=DB.insertPlacesJsonData(txt_GivenPlaceName_STR,txt_GivenPlaceAddress_STR);
//                                        if (inserted==true){
//                                            Toast.makeText(getApplicationContext(), "Current Place Added", Toast.LENGTH_LONG).show();
//                                            list =DB.selectAllPostsData();
//                                            postAdapter = new PostAdapter(getApplicationContext(), mo.ed.prof_mohamed.geranyapp.R.layout.post_list_item, list);
//                                            postAdapter.notifyDataSetChanged();
                    }
                });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        AlertDialog Dialogue = builder.create();
        Dialogue.show();
    }

    private class PostHolder{
        View view_header_post,view_center_post,view_vertical_post_dislike_left,view_vertical_post_volunteer_right,view_vertical_post_donation,
                view_footer_post,view_vertical_footer_left;
        de.hdodenhof.circleimageview.CircleImageView profile_picture;
        TextView username;
        TextView user_description;
        TextView post_content;
        ImageView post_image;//contributers_image;
        ImageView Image_arraw_down,img_dolarsign,hand_logo,heart_logo,donatebutton_logo;
        TextView volunteer;
        TextView donate;
        TextView both,txt_required_title,txt_required_all,txt_required_paid,post_dislike_num,post_dislike_word,post_donate_num,post_donate_word,post_dislike,post_volunteer,txt_post_donate,
                post_volunteer_num,post_volunteer_word;
        LinearLayout Linear_AllPostDetails,contibuters_launcher,PostsListItemHeader,Post_ContributionDetails_Linear,Post_ContributionDetails_Linear_2
                ,Post_ContributionDetails_Linear_3,Linear_ImageArrowDown_Post,Linear_ContributionDetails_Post,Linear_Dislike_post,
                Linear_Volunteer_post,Linear_post_Donations_above,Linear_Post_Reaction_images,LinearDislikeImage,volunteer_Image_Linear,
                Linear_donations_post;
    }
}