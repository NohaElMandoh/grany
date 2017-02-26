package mo.ed.prof_mohamed.geranyapp.Fragments;

import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import mo.ed.prof_mohamed.geranyapp.R;

/**
 * Created by Prof-Mohamed on 2/9/2017.
 */
public class Fragment_Edit_Profile extends Fragment {

    Button btn_editaddress,btn_editphone_home, btn_editphone_mob,btn_editemail_home,btn_editemail_work;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.fragment_edit_profile,container,false);
        btn_editaddress=(Button)rootView.findViewById(R.id.btn_editaddress);
        btn_editphone_home=(Button)rootView.findViewById(R.id.btn_editphone_home);
        btn_editphone_mob=(Button)rootView.findViewById(R.id.btn_editphone_mob);
        btn_editemail_home=(Button)rootView.findViewById(R.id.btn_editemail_home);
        btn_editemail_work=(Button)rootView.findViewById(R.id.btn_editemail_work);
        btn_editaddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                edit_address();
            }
        });
        return rootView;
    }



    /*public void edit_address(){
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=getActivity().getLayoutInflater();
        View dialougeView=inflater.inflate(mo.ed.prof_mohamed.geranyapp.R.layout.add_new_place_layout,null);
        txt_changeaddress=(EditText) dialougeView.findViewById(mo.ed.prof_mohamed.geranyapp.R.id.txt_changeaddress);
        txt_GivenPlaceAddress=(EditText) dialougeView.findViewById(mo.ed.prof_mohamed.geranyapp.R.id.txt_GivenPlaceAddress);
        builder.setView(dialougeView)
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String txt_GivenPlaceName_STR=txt_GivenPlaceName.getText().toString();
                        String txt_GivenPlaceAddress_STR=txt_GivenPlaceAddress.getText().toString();
                        //Implement Saving
//                        boolean inserted=DB.insertPlacesJsonData(txt_GivenPlaceName_STR,txt_GivenPlaceAddress_STR);
//                        if (inserted==true){
//                            Toast.makeText(getApplicationContext(), "Current Place Added", Toast.LENGTH_LONG).show();
//                        }
                    }
                });
        AlertDialog Dialogue=builder.create();
        Dialogue.show();
    }*/
}
