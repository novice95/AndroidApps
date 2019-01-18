package com.example.vani.music;

import android.app.ActivityManager;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import static android.content.Context.ACTIVITY_SERVICE;
import static android.support.v4.content.ContextCompat.getSystemService;

public class Download_Fragment extends Fragment {

    Button btnDownlaod;
    Button playDownload , stopDownload;

    public Download_Fragment() {
    }

    MediaPlayer mediaPlayer;

//    public boolean checkServiceRunning(){
//        ActivityManager manager = (ActivityManager) getActivity().getSystemService(ACTIVITY_SERVICE);
//        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE))
//        {
//            if ("com.example.vani.music.StartService"
//                    .equals(service.service.getClassName()))
//            {
//                return true;
//            }
//        }
//        return false;
//    }

    public void onStart() {
        super.onStart();

//        playDownload.setEnabled(false);
//        stopDownload.setEnabled(false);
    }

    public boolean checkConnection()
    {
        ConnectivityManager cm = (ConnectivityManager)getActivity().getSystemService(getActivity().CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        return  isConnected;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

//        Toast.makeText(getActivity(), "onCreateView ", Toast.LENGTH_SHORT).show();
        View viewDownload= inflater.inflate(R.layout.fragment_download, null);

        final  String songurl = "http://faculty.iiitd.ac.in/~mukulika/s1.mp3";
        btnDownlaod = (Button)viewDownload.findViewById(R.id.download1);
        btnDownlaod.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

               if(!checkConnection())
               {
                   Toast.makeText(getActivity(), "No Internet Connection !! ", Toast.LENGTH_SHORT).show();
               }
               else {
                   Toast.makeText(getActivity(), "Search song named - faculty_iiitd.mp3  in MusicFileList  ", Toast.LENGTH_SHORT).show();
                   Intent serv = new Intent(getActivity(), Download_IntentService.class);
                   serv.putExtra("url", "http://faculty.iiitd.ac.in/~mukulika/s1.mp3");
                   //serv.putExtra("receiver", new MessageReceiver(new Handler()));
                   getContext().startService(serv);
                   //                startService(serv);
                   //Toast.makeText(getActivity(), "Download starting ", Toast.LENGTH_SHORT).show();
                   btnDownlaod.setEnabled(false);
               }
            }
        });


//        playDownload = (Button)viewDownload.findViewById(R.id.PlayDownload);
//        playDownload.setOnClickListener(new View.OnClickListener() {
//
//            public void onClick(View v) {
//
//                String songPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC).toString()+ "/file11.mp3";
////                String filePath = Environment.getExternalStorageDirectory().toString() + "/file11.mp3";
//               try {
//
//                   mediaPlayer = new MediaPlayer();
//                   mediaPlayer.setDataSource(songPath);
//                   mediaPlayer.prepare();
//                   mediaPlayer.start();
//                   playDownload.setEnabled(false);
//                   stopDownload.setEnabled(true);
//               } catch (Exception e) {
//                   System.out.println(e);
//               }
//            }
//        });
//
//        stopDownload = (Button)viewDownload.findViewById(R.id.StopDownload);
//        stopDownload.setOnClickListener(new View.OnClickListener() {
//
//            public void onClick(View v) {
//                try {
//                    mediaPlayer.stop();
//                    stopDownload.setEnabled(false);
//                    playDownload.setEnabled(true);
//                } catch (Exception e) {
//                    System.out.println(e);
//                }
//            }
//        });

        return viewDownload;
    }
}

