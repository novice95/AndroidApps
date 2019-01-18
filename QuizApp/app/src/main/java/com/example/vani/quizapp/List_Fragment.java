package com.example.vani.quizapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.Person;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;


public class List_Fragment extends Fragment implements AdapterView.OnItemClickListener{

//    private List<Question> quesList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ListAdapter mAdapter;
    private DatabaseHelper database;
    private List<Question> dataQues;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database = new DatabaseHelper(getContext());
//        dataQues =new ArrayList<Question>();

        dataQues=database.getdata();
    }


    public List_Fragment(){

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        super.onCreateView(savedInstanceState);
//        Toast.makeText(getActivity(), "onCreateView ", Toast.LENGTH_SHORT).show();
        View view = inflater.inflate(R.layout.fragment_list, null);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

//        dataQues =new ArrayList<Question>();
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);


        dataQues=  database.getdata();
        mAdapter =new ListAdapter(dataQues);

        //        mAdapter = new ListAdapter(quesList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());

        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));

// set the adapter
//        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);


//        prepareMovieData();

//        RecyclerViewClickListener listener = new RecyclerViewClickListener() {
//
//            void onClick(View v, int pos) {
//                Question st=quesList.get(pos);
//                Fragment fm=getFragmentManager().findFragmentById(R.id.details_frag);
//
//                Intent intent=new Intent(getActivity(),Details_Fragment.class) ;
////                intent.putExtra("q", quesList.get(pos));
//
//                startActivity(intent);
//
//                if(fm!=null && fm.isResumed()) {
//                    Details_Fragment Details = new Details_Fragment();
//
//                    getActivity().getIntent().putExtra("q",quesList.get(pos));
//                    //Toast.makeText(this, "Position2 " + per.name, Toast.LENGTH_SHORT).show();
//
//                    //  detail.setArguments(b);
//
//                    getFragmentManager().beginTransaction()
//                            .replace(R.id.details_frag,Details).commit();
//
//                }
//            }
//
//        };
//
//        RecyclerViewClickListener listener = (v, pos) -> {
//            Question st=quesList.get(pos);
//            Fragment fm=getFragmentManager().findFragmentById(R.id.question);
//
//            if(fm!=null && fm.isResumed()) {
//            }
//            };

//        RecyclerViewClickListener listener = (view, position) -> {
//            Person st=FullContacts.get(position);
//            Fragment fm=getFragmentManager().findFragmentById(R.id.contact_detail);
//    }
        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(view.getContext(), "position = " + position +" "+id, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(view.getContext() , DetailsActivity.class);
        view.getContext().startActivity(intent);
    }

//    private void prepareMovieData() {
//        Question que = new Question("Ques1");
//        quesList.add(que);
//
//        que = new Question("Ques1");
//        quesList.add(que);
//
//        que = new Question("Ques1");
//        quesList.add(que);
//
//        que = new Question("Ques1");
//        quesList.add(que);
//
//
//        que = new Question("Ques1");
//        quesList.add(que);
//
//        que = new Question("Ques1");
//        quesList.add(que);
//
//        que = new Question("Ques1");
//        quesList.add(que);
//
//
//        que = new Question("Ques1");
//        quesList.add(que);
//
//        que = new Question("Ques1");
//        quesList.add(que);
//
//        que = new Question("Ques1");
//        quesList.add(que);
//
//
//        que = new Question("Ques1");
//        quesList.add(que);
//
//        que = new Question("Ques1");
//        quesList.add(que);
//
//        que = new Question("Ques1");
//        quesList.add(que);
//
//
//        que = new Question("Ques1");
//        quesList.add(que);
//
//        que = new Question("Ques1");
//        quesList.add(que);
//
//        que = new Question("Ques1");
//        quesList.add(que);
//
//
//        que = new Question("Ques1");
//        quesList.add(que);
//
//        que = new Question("Ques1");
//        quesList.add(que);
//
//        que = new Question("Ques1");
//        quesList.add(que);
//
//
//        que = new Question("Ques1");
//        quesList.add(que);
//
//        que = new Question("Ques1");
//        quesList.add(que);
//
//        que = new Question("Ques1");
//        quesList.add(que);
//
//
//        mAdapter.notifyDataSetChanged();
//    }
}