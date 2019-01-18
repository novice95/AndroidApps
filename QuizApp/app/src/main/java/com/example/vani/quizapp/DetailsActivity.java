package com.example.vani.quizapp;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        FragmentManager fm= getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        Details_Fragment frag = new Details_Fragment();//Get Fragment Instance
        Bundle data = new Bundle();//Use bundle to pass data

//        tv = (TextView) findViewById(R.id.q);

        Intent intent = getIntent();

        String Name = intent.getStringExtra("ques");
        int position = intent.getIntExtra("pos" ,0);
        data.putInt("pos", position);
        data.putString("ques", Name);
//        data.putString("ques", "This is Argument Fragment");//put string, int, etc in bundle with a key value
        frag.setArguments(data);//Finally set argument bundle to fragment

        ft.add(R.id.details_frag, frag).commit();//now replace the argument fragment


//        getSupportFragmentManager().beginTransaction().add(R.id.details_frag, new Details_Fragment()).commit();
    }
}
