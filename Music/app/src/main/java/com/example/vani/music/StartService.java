package com.example.vani.music;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;

import java.io.IOException;

public class StartService extends Service {
    public StartService() {
    }

    MediaPlayer player;

    public IBinder onBind(Intent intent) {

        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void onCreate() {
        super.onCreate();
        Log.d("service", "onCreate");
        player = new MediaPlayer();

    }


    public void onStart(Intent intent, int startId) {
        Log.d("service", "onStartCommand");
        try {
            int resID = intent.getIntExtra("ID", 0);
            String song = intent.getStringExtra("SongName" );
            String songPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC).toString();
            player.setDataSource(songPath+"/"+song);

//            player = MediaPlayer.create(getApplicationContext(),resID);
//            if(!player.isPlaying())
            player.prepare();
                player.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public IBinder onUnBind(Intent arg0) {
        // TO DO Auto-generated method
        return null;
    }

    public void onStop() {
        player.stop();
        player.release();
    }

    public void onPause() {

    }

    @Override
    public void onDestroy() {
        player.stop();
        player.release();
    }

}
