package com.example.yasha.carcontrol;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



public class KameraFragment extends Fragment {

    public KameraFragment (){};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View PageTwo=inflater.inflate(R.layout.fragment_kamera, container, false);
        FragmentManager manager = getChildFragmentManager();
        KameraAtasFragment kameraAtasFragment = new KameraAtasFragment();
        manager.beginTransaction()
                .replace(R.id.KameraAtasLayout, kameraAtasFragment, kameraAtasFragment.getTag())
                .commit();

        KameraBawahFragment kameraBawahFragment = new KameraBawahFragment();
        manager.beginTransaction()
                .replace(R.id.KameraBawahLayout, kameraBawahFragment, kameraBawahFragment.getTag())
                .commit();
        return PageTwo;
    }
}
