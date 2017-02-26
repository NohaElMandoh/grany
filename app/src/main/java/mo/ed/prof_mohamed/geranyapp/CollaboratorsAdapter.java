package mo.ed.prof_mohamed.geranyapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/*
 * Created by hayyan2001 on 11/24/16.
 */
public class CollaboratorsAdapter extends BaseAdapter{
    PostHolder postHolder;
    private Context activity;
    ArrayList<String> username,distance;
    ArrayList<Integer> profilePic;
    private LayoutInflater inflater = null;
    public CollaboratorsAdapter(Context context, ArrayList<String> username,ArrayList<Integer> profilePic,ArrayList<String> distance) {
        activity = context;
        this.username = username;
        this.profilePic = profilePic;
        this.distance = distance;
    }
    @Override
    public int getCount() {
        return username.size() == 0 ? 0 : username.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = convertView;
        if (itemView == null){
            inflater = (LayoutInflater) this.activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            itemView = inflater.inflate(mo.ed.prof_mohamed.geranyapp.R.layout.collaborators_list_item, parent, false);
            postHolder = new PostHolder();
            postHolder.profile_picture = (CircleImageView) itemView.findViewById(mo.ed.prof_mohamed.geranyapp.R.id.coll_profile_picture);
            postHolder.username = (TextView) itemView.findViewById(mo.ed.prof_mohamed.geranyapp.R.id.coll_username);
            postHolder.distance = (TextView) itemView.findViewById(mo.ed.prof_mohamed.geranyapp.R.id.coll_user_distance);

            itemView.setTag(postHolder);
        }else{
            postHolder = (PostHolder) itemView.getTag();
        }
        postHolder.profile_picture.setImageResource(profilePic.get(position));
        postHolder.username.setText(username.get(position));
        postHolder.distance.setText("Distance: "+distance.get(position));
        return itemView;
    }
    private class PostHolder{
        CircleImageView profile_picture;
        TextView username;
        TextView distance;

    }
}
