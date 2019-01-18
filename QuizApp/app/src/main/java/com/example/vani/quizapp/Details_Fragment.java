package com.example.vani.quizapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class Details_Fragment extends Fragment {

//    TextView tv;
    Button btSave, btSubmit;
    String answer;
    RadioGroup rbg;
    RadioButton rbTrue, rbFalse;
    DatabaseHelper database;
//    private ProgressBar progressBar;

    public Details_Fragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

// reference taken for export csv code
    public boolean export()
    {
        File exportDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).toString());
        System.out.println(exportDir);
        if (!exportDir.exists()) {
            exportDir.mkdirs();
        }

        int row=0, col=0;
        try {

            Cursor c = database.raw();
            row = c.getCount();
            col = c.getColumnCount();

            File file = new File(exportDir, "quizzze.csv");
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            if(row>0)
            {
                c.moveToFirst();
                for(int i=0; i<col; i++)
                {
                    if(i != col-1) {
                        bufferedWriter.write(c.getColumnName(i) + ",");
                    } else {
                        bufferedWriter.write(c.getColumnName(i));
                    }
                }
                bufferedWriter.newLine();

                for(int i=0; i<row; i++)
                {
                    c.moveToPosition(i);
                    for (int j=0; j<col; j++)
                    {
                        if (j != col-1)
                            bufferedWriter.write(c.getString(j) + ",");
                        else
                            bufferedWriter.write(c.getString(j));
                    }
                    bufferedWriter.newLine();
                }
                bufferedWriter.flush();


//                btSave.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                if(rbTrue.isChecked())
//                {
//                    Toast.makeText(getContext(), "True", Toast.LENGTH_SHORT).show();
//                    answer = "True";
//                }
//                else if(rbFalse.isChecked())
//                {
//                    Toast.makeText(getContext(), "False", Toast.LENGTH_SHORT).show();
//                    answer="False";
//                }
//                else
//                {
//                    AlertDialog.Builder builder= new  AlertDialog.Builder(getActivity());
//                    builder.setTitle("Quiz")
//                            .setMessage("Please select one option !!")
//                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface dialog, int which) {
//                                    // continue with delete
//                                    Toast.makeText(getContext(), "Yes", Toast.LENGTH_SHORT).show();
//                                }
//                            })
//                            .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface dialog, int which) {
//                                    // do nothing
//                                    Toast.makeText(getContext(), "No", Toast.LENGTH_SHORT).show();
//                                }
//                            })
//                            .show();
//                }
//            }
//        });


            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details, null);

        database = new DatabaseHelper(getContext());
        TextView text = (TextView) view.findViewById(R.id.tvques);

        final String getArgument = getArguments().getString("ques");
        text.setText(getArgument);
        final Integer pos = getArguments().getInt("pos");

        btSave=(Button) view.findViewById(R.id.save);
        btSubmit=(Button) view.findViewById(R.id.submit);
        rbTrue=(RadioButton)view.findViewById(R.id.rbTrue);
        rbFalse=(RadioButton)view.findViewById(R.id.rbFalse);

        btSave.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                    Toast.makeText(getActivity(), "Save", Toast.LENGTH_SHORT).show();

                if(rbTrue.isChecked())
                {
                    Toast.makeText(getContext(), "True", Toast.LENGTH_SHORT).show();
                    boolean bd= database.updatedata(pos.toString(),getArgument,"True");
                    Question que = new Question(getArgument, "True");
//                    database.insertdata(que);
                    Toast.makeText(getContext(), "True", Toast.LENGTH_SHORT).show();
                    answer = "True";
                }
                else if(rbFalse.isChecked())
                {
                    boolean bd= database.updatedata(pos.toString(),getArgument,"False");
//                    Question que = new Question(getArgument, "False");
//                    database.insertdata(que);
                    Toast.makeText(getContext(), "False", Toast.LENGTH_SHORT).show();

                    answer="False";
                }
                else
                {
                    AlertDialog.Builder builder= new  AlertDialog.Builder(getActivity());
                    builder.setTitle("Quiz")
                            .setMessage("Please select one option !!")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // continue with delete
                                    Toast.makeText(getContext(), "Yes", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // do nothing
                                    Toast.makeText(getContext(), "No", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .show();
                }




            }
        });



        btSubmit.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Toast.makeText(getActivity(), "Submit... ", Toast.LENGTH_SHORT).show();
//                progressBar.setVisibility(View.VISIBLE);
//                progressBar.setProgress(0);

                try {

                    if(export())
                    {
                        Toast.makeText(getActivity(), "Export Successful...", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(getActivity(), "Export Failed... ", Toast.LENGTH_SHORT).show();
                    }

                    new ExportCSV(getActivity()).execute();

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            });



//        btSave.setOnClickListener((View.OnClickListener) view.getContext());
//        btSubmit.setOnClickListener((View.OnClickListener) view.getContext());
//
////        String que = getActivity().getString();
////        getString("ques");
//
////        tv.setText("Name      :  " + Name);
        return  view;
//        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.save:
                Toast.makeText(getContext(), "save", Toast.LENGTH_SHORT).show();
                break;
            case R.id.submit:
                Toast.makeText(getContext(), "submit", Toast.LENGTH_SHORT).show();
                break;
        }
    }
    public void onResume() {
        super.onResume();
        Toast.makeText(getContext(), "OnResume", Toast.LENGTH_SHORT).show();
    }
}
