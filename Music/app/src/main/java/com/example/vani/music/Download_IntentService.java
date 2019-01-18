package com.example.vani.music;
import android.app.IntentService;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Environment;
import android.os.ResultReceiver;
import android.support.annotation.Nullable;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;


public class Download_IntentService extends IntentService {

    public Download_IntentService() {

        super("Download_IntentService");
    }

    public boolean checkConnection()
    {
        ConnectivityManager cm = (ConnectivityManager)getApplicationContext().getSystemService(getApplicationContext().CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        return  isConnected;
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);

        return START_STICKY;
    }

    protected void onHandleIntent(Intent intent) {

        String songurl = intent.getStringExtra("url");

//        Toast.makeText(getApplicationContext(),"URL urlrlrlrlrlrl", Toast.LENGTH_SHORT).show();

        if (checkConnection())
            (new Download()).execute(songurl);
        else
            System.out.println("Internet connection unavailable !!");
    }

}
