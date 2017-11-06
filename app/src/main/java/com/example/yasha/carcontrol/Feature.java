package com.example.yasha.carcontrol;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.NotificationCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Feature extends AppCompatActivity {
    private static final String TAG = Feature.class.getSimpleName();
    TabLayout MyTabs;
    ViewPager MyPage;
    private static int pos;
    private String userID;
    private FirebaseDatabase mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feature);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        MyTabs = (TabLayout)findViewById(R.id.MyTabs);
        MyPage = (ViewPager)findViewById(R.id.MyPage);

        MyTabs.setupWithViewPager(MyPage);
        SetUpViewPager(MyPage);

        userID = "Cyqqo98wl6amXIrufPlTzvFVGQg1";
        mDatabase = FirebaseDatabase.getInstance();
        mDatabase.getReference().child("profile").child(userID).child("statusAlarmTrip").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int alarmState = Integer.parseInt(dataSnapshot.child("statusAlarmTrip").getValue().toString());
                if (alarmState == 1) {
                    NotificationActivity.showNotification(getApplicationContext());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    public void SetUpViewPager (ViewPager viewPager){
        MyViewPageAdapter Adapter = new MyViewPageAdapter(getSupportFragmentManager());
        Adapter.AddFragmentPage(new AlarmFragment(), "Alarm Status");
        Adapter.AddFragmentPage(new KameraFragment(), "Monitor Camera");
        viewPager.setAdapter(Adapter);
        }

    public class MyViewPageAdapter extends FragmentPagerAdapter{
        private List<Fragment> MyFragment = new ArrayList<>();
        private List<String> MyPageTitle = new ArrayList<>();

        public MyViewPageAdapter(FragmentManager manager){
            super(manager);
        }

        public void AddFragmentPage(Fragment Frag, String Title){
            MyFragment.add(Frag);
            MyPageTitle.add(Title);
        }

        @Override
        public Fragment getItem(int position){
            return MyFragment.get(position);
        }

        @Override
        public CharSequence getPageTitle(int position){
            return MyPageTitle.get(position);
        }

        @Override
        public int getCount(){
            return 2;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_SignOut) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static int getPos() {
        return pos;
    }
}

