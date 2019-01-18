package com.example.vani.music;

import android.app.ActivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import static android.support.v4.content.ContextCompat.getSystemService;

public class MultiFragment_Activity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_fragment);

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().add(R.id.menu, new MenuFragment()).commit();
        }
    }

//    protected void onResume() {
//        super.onResume();
//        boolean br = isMyServiceRunning();
//        Toast.makeText(this, br + "avavavav avava avav", Toast.LENGTH_SHORT).show();
//        System.out.println("Yayayyyayaaa     " + br);
//
//    }
}
