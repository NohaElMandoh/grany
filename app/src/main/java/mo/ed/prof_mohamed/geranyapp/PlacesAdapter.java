package mo.ed.prof_mohamed.geranyapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Prof-Mohamed on 2/13/2017.
 */
public class PlacesAdapter extends ArrayAdapter {

    public transient Context mContext;
    private LayoutInflater inflater = null;
    private List<OptionsEntity> feedItemList;

    public PlacesAdapter (Context context,int Resource,ArrayList<OptionsEntity> feedItemList) {
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final OptionsEntity feedItem=feedItemList.get(position);
        View itemView = convertView;
        if (itemView == null){
            itemView = LayoutInflater.from(mContext).inflate(mo.ed.prof_mohamed.geranyapp.R.layout.places_listitem, parent, false);
        }
        TextView AreaName= (TextView) itemView.findViewById(mo.ed.prof_mohamed.geranyapp.R.id.AreaName);
        TextView Address= (TextView) itemView.findViewById(mo.ed.prof_mohamed.geranyapp.R.id.Address);
        AreaName.setText(feedItem.getUserName());
        Address.setText(feedItem.getDescription());
        return itemView;
    }
}