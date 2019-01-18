package com.example.vani.music;

import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class Download extends AsyncTask<String, String, String> {

    protected void onPreExecute() {
        super.onPreExecute();

    }

    protected String doInBackground(String... f_url) {
        int count;
        try {
            URL url = new URL(f_url[0]);
            URLConnection conection = url.openConnection();
            conection.connect();

            int lenghtOfFile = conection.getContentLength();
            System.out.println("File length   "+lenghtOfFile);

            InputStream readSong = new BufferedInputStream(url.openStream(), 8192);

//            String ext = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC).toString();
//                    File myDir = new File(ext + "/saved_images");
//                    if (!myDir.exists()) {
//                        myDir.mkdirs();
//                    }

            String ext = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC).toString();
            OutputStream writeSong = new FileOutputStream(ext+"/faculty_iiitd.mp3");


            byte data[]=new byte[1024];
            long total = 0;

            while ((count = readSong.read(data)) != -1) {
                total += count;
//                System.out.println(count);
                writeSong.write(data,0,count);
            }
            writeSong.flush();
            writeSong.close();
            readSong.close();
            System.out.println("Download Completed.........................");

        } catch (Exception e) {
            Log.e("Error: ", e.getMessage());
        }

        return null;
    }

    protected void onPostExecute(String file_url) {

        String ext = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC).toString();

        System.out.println("extxetxtxtxt "+ ext);
//        System.out.println("lalalalalalallaa "+ Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC));
        System.out.println("Downloaded completeeeeeeeeeeeeeeeeeeeeee ");
        Log.i("Download Completed !! ",ext );
        //Toast.makeText( , "LISLISLISLIS", Toast.LENGTH_SHORT).show();
    }

}
