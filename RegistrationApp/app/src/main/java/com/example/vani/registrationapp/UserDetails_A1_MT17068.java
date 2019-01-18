package com.example.vani.registrationapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UserDetails_A1_MT17068 extends AppCompatActivity implements View.OnClickListener {

    EditText et1_name, et2_roll, et3_branch, et4_course1, et5_course2, et6_course3, et7_course4;
    Button btnSubmit, btnClear;
    private static final String TAG = "UserDetails";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            Toast.makeText(this, "Welcome to RegistrationApp!" , Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Welcome back!" , Toast.LENGTH_SHORT).show();
        }

        setContentView(R.layout.activity_user_details_a1_mt17068);

        et1_name = (EditText) findViewById(R.id.et_name);
        et1_name.addTextChangedListener(new TextWatcher()  {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s)  {
                if (!et1_name.getText().toString().matches("[A-Za-z ?]*"))
                    et1_name.setError("Only alphabets and space allowed");
            }
        });

        et2_roll = (EditText) findViewById(R.id.et_roll_no);
        et2_roll.addTextChangedListener(new TextWatcher()  {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s)  {
                if (et2_roll.getText().toString().length()<7)
                    et2_roll.setError("Roll No. can be of 7 length only");
            }
        });

        et3_branch = (EditText) findViewById(R.id.et_branch);
        et4_course1 = (EditText) findViewById(R.id.et_course1);
        et5_course2 = (EditText) findViewById(R.id.et_course2);
        et6_course3 = (EditText) findViewById(R.id.et_course3);
        et7_course4 = (EditText) findViewById(R.id.et_course4);

        btnSubmit = (Button) findViewById(R.id.submit);
        btnSubmit.setOnClickListener(this);

        btnClear = (Button) findViewById(R.id.clear);
        btnClear.setOnClickListener(this);
    }

    private Boolean validationSuccess()
    {
        int flag=0;
        if(et1_name.getText().toString().equalsIgnoreCase("")) {
            et1_name.setError("Please Enter Name");
            Toast.makeText(this, "Please enter Name" , Toast.LENGTH_SHORT).show();
            flag=1;
        }
        else if (!et1_name.getText().toString().matches("[A-Za-z ]*"))
        {
            et1_name.setError("Only albphabets and space allowed");
            Toast.makeText(this, "Please enter Valid Name" , Toast.LENGTH_SHORT).show();
            flag=1;
        }

        if(et2_roll.getText().toString().equalsIgnoreCase("")) {
            et2_roll.setError("Please Enter Roll No.");
            Toast.makeText(this, "Please enter Roll No" , Toast.LENGTH_SHORT).show();
            flag=1;
        }
        else if(et2_roll.getText().toString().length()>1 && et2_roll.getText().toString().length()<7)
        {
            et2_roll.setError("Roll No. can be of 7 length only");
            Toast.makeText(this, "Please enter Valid Roll No." , Toast.LENGTH_SHORT).show();
            flag=1;
        }

        if(et3_branch.getText().toString().equalsIgnoreCase("")) {
            et3_branch.setError("Please Enter Branch");
            Toast.makeText(this, "Please enter Branch" , Toast.LENGTH_SHORT).show();
            flag=1;
        }

        if(et4_course1.getText().toString().equalsIgnoreCase("")) {
            et4_course1.setError("Please Enter Course 1");
            Toast.makeText(this, "Please enter Course 1" , Toast.LENGTH_SHORT).show();
            flag=1;
        }

        if(et5_course2.getText().toString().equalsIgnoreCase("")) {
            et5_course2.setError("Please Enter Course 2");
            Toast.makeText(this, "Please enter Course 2" , Toast.LENGTH_SHORT).show();
            flag=1;
        }

        if(et6_course3.getText().toString().equalsIgnoreCase("")) {
            et6_course3.setError("Please Enter Course 3");
            Toast.makeText(this, "Please enter Course 3" , Toast.LENGTH_SHORT).show();
            flag=1;
        }

        if(et7_course4.getText().toString().equalsIgnoreCase("")) {
            et7_course4.setError("Please Enter Course 4");
            Toast.makeText(this, "Please enter Course 4" , Toast.LENGTH_SHORT).show();
            flag=1;
        }

        if (flag==1)
            return  false;
        else
            return true;
    }

    private boolean checkEmpty()
    {
        int flag=0;
        if(!et1_name.getText().toString().equalsIgnoreCase("")) {
            flag=1;
        }
        if(!et2_roll.getText().toString().equalsIgnoreCase("")) {
            flag=1;
        }
        if(!et3_branch.getText().toString().equalsIgnoreCase("")) {
            flag=1;
        }
        if(!et4_course1.getText().toString().equalsIgnoreCase("")) {
            flag=1;
        }
        if(!et5_course2.getText().toString().equalsIgnoreCase("")) {
            flag=1;
        }
        if(!et6_course3.getText().toString().equalsIgnoreCase("")) {
            flag=1;
        }
        if(!et7_course4.getText().toString().equalsIgnoreCase("")) {
            flag=1;
        }
        if (flag==0)
            return  true;
        else
            return false;
    }

    //Alert user by throwing error message
    private void AlertDialog()
    {
        Toast.makeText(this, "Please fill all the details !", Toast.LENGTH_SHORT).show();
    }

    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.submit:
                //On click of Submit Button
                if (validationSuccess()) {

                    //Creating new explicit intent
                    Intent intent = new Intent(this, DisplayUserDetails_A1_MT17068.class);
                    intent.putExtra("name", et1_name.getText().toString());
                    intent.putExtra("roll", et2_roll.getText().toString());
                    intent.putExtra("branch", et3_branch.getText().toString());
                    intent.putExtra("c1", et4_course1.getText().toString());
                    intent.putExtra("c2", et5_course2.getText().toString());
                    intent.putExtra("c3", et6_course3.getText().toString());
                    intent.putExtra("c4", et7_course4.getText().toString());
                    startActivity(intent);
                }
                else
                {
                    AlertDialog();
                }

                break;

            case R.id.clear:
                //On click of Clear Button
                if(checkEmpty())
                {
                    Toast.makeText(this, "No detials filled yet !", Toast.LENGTH_SHORT).show();
                }
                else {
                    et1_name.getText().clear();
                    et2_roll.getText().clear();
                    et3_branch.getText().clear();
                    et4_course1.getText().clear();
                    et5_course2.getText().clear();
                    et6_course3.getText().clear();
                    et7_course4.getText().clear();
                }
                break;

        }
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
