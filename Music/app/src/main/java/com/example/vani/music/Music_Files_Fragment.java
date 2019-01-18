package com.example.vani.music;

import android.Manifest;
import android.app.ActivityManager;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;

import static android.content.Context.ACTIVITY_SERVICE;


public class Music_Files_Fragment extends Fragment{


    public Music_Files_Fragment() {
    }

    ListView songsList;
    ArrayList<String> arrayList;
    ArrayAdapter<String> arrayAdapter;
    Button StopMusic;
    Button StartMusic;

    public boolean checkServiceRunning(){
        ActivityManager manager = (ActivityManager) getActivity().getSystemService(ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE))
        {
            if ("com.example.vani.music.StartService".equals(service.service.getClassName()))
            {
                return true;
            }
        }
        return false;
    }


    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_music_files,  container,false);

        songsList = (ListView) view.findViewById(R.id.listFiles);
        arrayList = new ArrayList<>();
//        final Field[] fields = R.raw.class.getFields();

        File ext = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);

        ArrayList<String>  locSongs= new ArrayList<String>();
        File[] files= ext.listFiles();

//        System.out.println("KKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKK-----------------"+ext.length()+ "    "+ ext);
//        System.out.println(files.length);
//        System.out.println("Lennnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn     "+fields.length);

        for(File file: files )
        {
            System.out.println(file.getName());
            arrayList.add(file.getName());
        }
//        for(int i=0; i< fields.length ; ++i)
//        {
//            System.out.println(fields[i].getName());
//            //Toast.makeText(getActivity(), "LISLISLISLIS", Toast.LENGTH_SHORT).show();
//            arrayList.add(fields[i].getName());
//
//        }

//        Toast.makeText(getActivity(), "LISLISLISLIS", Toast.LENGTH_SHORT).show();
        System.out.println(arrayList);
        arrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, arrayList);
        songsList.setAdapter(arrayAdapter);

        StartMusic = (Button)view.findViewById(R.id.StartButton);
        StopMusic = (Button)view.findViewById(R.id.StopButton);

//        StartMusic.setEnabled(false);
//        StopMusic.setEnabled(false);

        final Intent serv = new Intent( getActivity(),StartService.class);

        StartMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(checkServiceRunning()==true)
                {
                    Toast.makeText(getActivity(), "Song Already Running...", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    getContext().startService(serv);
                }
            }
        });

        StopMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getContext().stopService(serv);

            }
        });

        songsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String Position = (String)songsList.getItemAtPosition(position);
                System.out.println(Position);

                int resID=getResources().getIdentifier(Position, "raw", getActivity().getPackageName());
                serv.putExtra("SongName" , Position);
//                System.out.println("reeeeeessssssssssIIIIDDDD"+ resID);
                serv.putExtra("ID", resID);
                StartMusic.setEnabled(true);
                StopMusic.setEnabled(true);
            }
        });
        return view;
    }

//    public void onResume() {
//        super.onResume();
//        StartMusic.setEnabled(true);
//        StopMusic.setEnabled(true);
//    }

    public void onPause() {
        super.onPause();
        StartMusic.setEnabled(true);
        StopMusic.setEnabled(true);
    }
}
