package com.example.yasha.carcontrol;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class AlarmFragment extends Fragment {

    public AlarmFragment (){};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View PageOne=inflater.inflate(R.layout.fragment_alarm, container, false);
        FragmentManager manager = getChildFragmentManager();
        AtasFragment atasFragment = new AtasFragment();
        manager.beginTransaction()
                .replace(R.id.AtasLayout, atasFragment, atasFragment.getTag())
                .commit();

        AlarmONFragment alarmONFragment = new AlarmONFragment();
        manager.beginTransaction()
                .replace(R.id.BawahLayout, alarmONFragment, alarmONFragment.getTag())
                .commit();
        return PageOne;
    }
}
