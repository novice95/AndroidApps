package com.example.vani.registrationapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayUserDetails_A1_MT17068 extends AppCompatActivity {

    private static final String TAG = "DisplayUserDetails";
    TextView tv1_name, tv2_roll, tv3_branch, tv4_course1, tv5_course2, tv6_course3, tv7_course4;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_display_user_details_a1_mt17068);
        tv1_name = (TextView) findViewById(R.id.name);
        tv2_roll = (TextView) findViewById(R.id.roll);
        tv3_branch = (TextView) findViewById(R.id.branch);
        tv4_course1 = (TextView) findViewById(R.id.course1);
        tv5_course2 = (TextView) findViewById(R.id.course2);
        tv6_course3 = (TextView) findViewById(R.id.course3);
        tv7_course4 = (TextView) findViewById(R.id.course4);

        Intent intent = getIntent();

        String Name = intent.getStringExtra("name");
        String roll = intent.getStringExtra("roll");
        String branch = intent.getStringExtra("branch");
        String course1 = intent.getStringExtra("c1");
        String course2 = intent.getStringExtra("c2");
        String course3 = intent.getStringExtra("c3");
        String course4 = intent.getStringExtra("c4");


        tv1_name.setText("Name      :  " + Name);
        tv2_roll.setText("Roll No.  :  " + roll);
        tv3_branch.setText("Branch    :  " + branch);
        tv4_course1.setText("Course 1  :  " + course1);
        tv5_course2.setText("Course 2  :  " + course2);
        tv6_course3.setText("Course 3  :  " + course3);
        tv7_course4.setText("Course 4  :  " + course4);
    }

    String st;
    public void onStart() {
        super.onStart();
        st= "State of activity " + TAG + " changed from Created to Started.";
        Log.v(TAG, st);
        Toast.makeText(getApplicationContext(), st, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResume() {
        super.onResume();
        st= "State of activity " + TAG + " changed from Start to Resume.";
        Log.v(TAG, st);
        Toast.makeText(getApplicationContext(), st, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPause() {
        super.onPause();
        st= "State of activity " + TAG + " changed from Running to Paused.";
        Log.v(TAG, st);
        Toast.makeText(getApplicationContext(), st, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRestart()
    {
        super.onRestart();
        st= "State of activity " + TAG + " changed from Stopped to Started.";
        Log.v(TAG, st);
        Toast.makeText(getApplicationContext(), st, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStop() {
        super.onStop();
        st= "State of activity " + TAG + " changed from Paused to Stopped.";
        Log.v(TAG, st);
        Toast.makeText(getApplicationContext(), st, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        st= "State of activity " + TAG + " changed from Stopped to Destroyed.";
        Log.v(TAG, st);
        Toast.makeText(getApplicationContext(), st, Toast.LENGTH_SHORT).show();
    }

}
