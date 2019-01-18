package com.example.vani.music;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class MenuFragment extends Fragment {

    Fragment frag;
    FragmentTransaction fragTransaction;
    public MenuFragment() {

    }


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View ViewMenu = inflater.inflate(R.layout.fragment_menu , container, false);

        frag = new Music_Files_Fragment();
        fragTransaction = getFragmentManager().beginTransaction().add(R.id.container , frag);
        fragTransaction.commit();

        Button btnList=(Button)ViewMenu.findViewById(R.id.buttonFiles);
        Button btnDownload=(Button)ViewMenu.findViewById(R.id.buttonDownload);

        btnList.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                frag = new Music_Files_Fragment();
                fragTransaction = getFragmentManager().beginTransaction().replace(R.id.container , frag);
                fragTransaction.commit();

                Toast.makeText(getActivity(), "Local Music Files !!", Toast.LENGTH_SHORT).show();

            }
        });


        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                frag = new Download_Fragment();
                fragTransaction = getFragmentManager().beginTransaction().replace(R.id.container , frag);
                fragTransaction.commit();
                Toast.makeText(getActivity(), "Download song from server !!", Toast.LENGTH_SHORT).show();

            }
        });
        return ViewMenu;
    }
}
