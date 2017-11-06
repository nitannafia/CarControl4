/*
package com.example.yasha.carcontrol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {
    public Button button2;

    Button b1, b2;
    EditText ed1, ed2;

    TextView tx1;
    int counter = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);}

            public void init (){

        b2 = (Button) findViewById(R.id.button2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                b1 = (Button) findViewById(R.id.button);
                ed1 = (EditText) findViewById(R.id.editText);
                ed2 = (EditText) findViewById(R.id.editText2);

                b2 = (Button) findViewById(R.id.button2);
                tx1 = (TextView) findViewById(R.id.textView3);
                tx1.setVisibility(View.GONE);

                b1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (ed1.getText().toString().equals("admin") &&
                                ed2.getText().toString().equals("admin")) {
                            Toast.makeText(getApplicationContext(),
                                    "Redirecting...", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(),
                                    "Wrong Credentials", Toast.LENGTH_SHORT).show();

                            tx1.setVisibility(View.VISIBLE);
                            tx1.setBackgroundColor(Color.BLUE);
                            counter--;
                            tx1.setText(Integer.toString(counter));

                            if (counter == 0) {
                                b1.setEnabled(false);
                            }
                        }
                    }
                });

                b2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent toy = new Intent(MainActivity.this, SignUp.class);
                        startActivity(toy);
                        finish();
                    }
                });
            }
        });
    }
}*/

package com.example.yasha.carcontrol;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {
    public static String MY_SHARED_PREFERENCES = "My_Pref";
    public static String KEY_LOGIN = "Key_Login";


    public Button button2;

    Button b1, b2;
    EditText ed1, ed2;

    TextView tx1;
    int counter = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1 = (Button) findViewById(R.id.button);
        ed1 = (EditText) findViewById(R.id.editText);
        ed2 = (EditText) findViewById(R.id.editText2);
        tx1 = (TextView) findViewById(R.id.textView3);
        b2 = (Button) findViewById(R.id.button2);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ed1.getText().toString().equals("faiqzia@gmail.com") &&
                        ed2.getText().toString().equals("admin")) {
                    Toast.makeText(getApplicationContext(),
                            "Redirecting...", Toast.LENGTH_SHORT).show();

                    SharedPreferences pref = getApplicationContext().getSharedPreferences(MY_SHARED_PREFERENCES,0);
                    SharedPreferences.Editor editor = pref.edit();

                    editor.putBoolean(KEY_LOGIN, true);
                    editor.commit();
                    Intent feature = new Intent(MainActivity.this, Feature.class);
                    startActivity(feature);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Wrong Credentials", Toast.LENGTH_SHORT).show();

                    tx1.setVisibility(View.VISIBLE);
                    tx1.setBackgroundColor(Color.GRAY);
                    counter--;
                    tx1.setText(Integer.toString(counter));

                    if (counter == 0) {
                        b1.setEnabled(false);
                    }
                }
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tx1.setVisibility(View.GONE);



                Intent toy = new Intent(MainActivity.this, SignUp.class);
                startActivity(toy);
                finish();
            }
        });

        }


    }

