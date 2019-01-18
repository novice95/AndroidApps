package com.example.vani.quizapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.health.SystemHealthManager;
import android.widget.Toast;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class ExportCSV extends AsyncTask<String, String, Boolean> {

    private Context context;
//    private ProgressDialog dialog;
    private ProgressDialog pg;
    boolean flag=false;
    private int serverResponseCode=0;

    public ExportCSV(Context context) {
        super();
        this.context = context;
//        dialog = new ProgressDialog(context);
        pg = new ProgressDialog(context);

    }

    public boolean checkConnection()
    {
        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        return  isConnected;
    }

        @Override
        protected void onPreExecute() {
//            dialog.setMessage("Uploading CSV...");
//              dialog.show();
            if(!checkConnection()) {
                flag = false;
                Toast.makeText(context, "No Internet Connection !! ", Toast.LENGTH_SHORT).show();
            }
            else {

                pg.setCancelable(false);
                pg.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                pg.setProgress(0);
                pg.setMax(100);
                pg.setMessage("Loading...");
                pg.show();
            }
        }

        protected Boolean doInBackground(final String... args) {

            File exportDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).toString());
            System.out.println(exportDir);

            try {

                File file = new File(exportDir, "quizzze.csv");
                String path= file.toString();

                if(!checkConnection())
                {
                    flag=false;
                    Toast.makeText(context, "No Internet Connection !! ", Toast.LENGTH_SHORT).show();
                    if (pg.isShowing()) {
                        pg.dismiss();
                    }
                }
                else {

                    // reference taken for export csv code
                    try {
                        String sourceFileUri = path;

                        HttpURLConnection conn = null;
                        DataOutputStream dataOut = null;
                        String lineEnd = "\r\n";
                        String twoHyphens = "--";
                        String boundary = "*****";
                        int bytesRead, bytesAvailable, bufferSize;
                        byte[] buffer;
                        int maxBufferSize = 1 * 1024 * 1024;
                        File sourceFile = new File(sourceFileUri);

                        if(sourceFile.isFile()) {

                            try {
                                //https://vani17068.000webhostapp.com/upload_php.php

                                //http://192.168.58.18:8080/fileupload
                                //http://192.168.43.126/cgi-bin/upload.php
                                String upLoadServerUri = "http://vani17068.000webhostapp.com/upload_php.php";

                                FileInputStream fileInputStream = new FileInputStream(
                                        sourceFile);
                                URL url = new URL(upLoadServerUri);

                                conn = (HttpURLConnection) url.openConnection();
                                conn.setDoInput(true);
                                conn.setDoOutput(true);
                                conn.setUseCaches(false);
                                conn.setRequestMethod("POST");
                                conn.setRequestProperty("Connection", "Keep-Alive");
                                conn.setRequestProperty("ENCTYPE",
                                        "multipart/form-data");
                                conn.setRequestProperty("Content-Type",
                                        "multipart/form-data;boundary=" + boundary);
                                conn.setRequestProperty("uploaded_file", sourceFileUri);

                                System.out.println("SF  " + sourceFileUri);

                                dataOut = new DataOutputStream(conn.getOutputStream());

                                dataOut.writeBytes(twoHyphens + boundary + lineEnd);
                                dataOut.writeBytes("Content-Disposition: form-data; name=\"uploaded_file\";filename=\""
                                        + sourceFileUri + "\"" + lineEnd);

                                dataOut.writeBytes(lineEnd);

                                bytesAvailable = fileInputStream.available();

                                bufferSize = Math.min(bytesAvailable, maxBufferSize);
                                buffer = new byte[bufferSize];
                                System.out.println("buffer created");
                                bytesRead = fileInputStream.read(buffer, 0, bufferSize);

                                while (bytesRead > 0) {

                                    dataOut.write(buffer, 0, bufferSize);
                                    bytesAvailable = fileInputStream.available();
                                    bufferSize = Math.min(bytesAvailable, maxBufferSize);
                                    bytesRead = fileInputStream.read(buffer, 0,
                                            bufferSize);

                                }
                                System.out.println("Writing bytes ");
                                Integer count = 1;

                                dataOut.writeBytes(lineEnd);
                                dataOut.writeBytes(twoHyphens + boundary + twoHyphens
                                        + lineEnd);

                                for (; count<=100; count++) {

                                    try {
                                    Thread.sleep(100);
//                                    publishProgress();
                                        publishProgress("Loading... " + count + "%");
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }

                                serverResponseCode = conn.getResponseCode();
                                String serverResponseMessage = conn
                                        .getResponseMessage();

                                System.out.println(serverResponseCode+ "    "+  serverResponseMessage);
                                if (serverResponseCode == 200) {
                                    System.out.println("fff");
                                    // messageText.setText(msg);
                                    //Toast.makeText(ctx, "File Upload Complete.",
                                    //      Toast.LENGTH_SHORT).show();

                                    // recursiveDelete(mDirectory1);

                                }
                                System.out.println("Done");
                                fileInputStream.close();
                                dataOut.flush();
                                dataOut.close();

                            } catch (Exception e) {
//                             dialog.dismiss();
                                e.printStackTrace();
                            }
//                         dialog.dismiss();
                        }
                        flag=true;
                    } catch (Exception ex) {
//                     dialog.dismiss();
                        ex.printStackTrace();
                    }
                    if (pg.isShowing()) {
                        pg.dismiss();
                    }
                }
                return flag;
            } catch (Exception e) {
                return false;
            }
        }


    protected void onProgressUpdate(String... progress) {
        super.onProgressUpdate(progress);
        if (pg != null) {
            pg.setMessage(progress[0]);
            pg.show();
        }
    }
//
        protected void onPostExecute(final Boolean success) {
//            if (this.dialog.isShowing()) { this.dialog.dismiss(); }
            if (success) {
                Toast.makeText(context, "Upload successful!", Toast.LENGTH_SHORT).show();
//
            } else {
                Toast.makeText(context, "Upload failed!", Toast.LENGTH_SHORT).show();
            }
        }
    }

