package com.example.yasha.carcontrol;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 */
public class AlarmONFragment extends Fragment {


    public AlarmONFragment() {
        // Required empty public constructor
    }

    private DatabaseReference mDatabase;
    private String userID;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view= inflater.inflate(R.layout.fragment_alarm_on, container, false);
        Button button=(Button)view.findViewById(R.id.off);
        userID = "Cyqqo98wl6amXIrufPlTzvFVGQg1";
        mDatabase = FirebaseDatabase.getInstance().getReference();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mDatabase.child("profile").child(userID).child("statusAlarmTrip").setValue(0);
                mDatabase.child("profile").child(userID).child("statusAlarmCommand").setValue(1);

                Fragment fragment = new BawahFragment();
                FragmentTransaction transaction=getFragmentManager().beginTransaction();
                transaction.replace(R.id.BawahLayout, fragment);
                transaction.commit();
            }
        });
        return view;
    }

}