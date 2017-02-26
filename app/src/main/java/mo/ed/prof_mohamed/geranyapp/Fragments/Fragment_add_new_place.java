package mo.ed.prof_mohamed.geranyapp.Fragments;

import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import mo.ed.prof_mohamed.geranyapp.R;

/**
 * Created by Prof-Mohamed on 2/14/2017.
 */
public class Fragment_add_new_place extends Fragment {
    TextView txt_Name;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootview=inflater.from(getActivity()).inflate(R.layout.add_new_place_layout,null);
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        txt_Name=(TextView)rootview.findViewById(R.id.txt_GivenPlaceName);
        builder.setView(rootview)
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Implement Saving
                    }
                });

        AlertDialog Dialogue=builder.create();
        Dialogue.show();
        return rootview;
    }
}