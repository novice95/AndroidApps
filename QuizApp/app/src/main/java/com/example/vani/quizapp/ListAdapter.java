package com.example.vani.quizapp;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder>  {

    private List<Question> questionsList;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
//            implements View.OnClickListener {
        public TextView question;
//        public TextView ide;
        public MyViewHolder(View view) {
            super(view);
//            ide = (TextView) view.findViewById(R.id.ide);
            question = (TextView) view.findViewById(R.id.question);
          itemView.setOnClickListener(this);

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {

//                    Toast.makeText(v.getContext(), "position = " + getLayoutPosition(), Toast.LENGTH_SHORT).show();

                    //go through each item if you have few items within recycler view
//                    if (getLayoutPosition() == 0) {
//
//                        Toast.makeText(v.getContext(), "position = " + getLayoutPosition(), Toast.LENGTH_SHORT).show();
//                        Intent intent = new Intent(v.getContext() , DetailsActivity.class);
//                        v.getContext().startActivity(intent);
//                    }      //                        //Do whatever you want here
////                Intent bookDetails_intent = new Intent(, testActivity.class);
////                startActivity(bookDetails_intent);
//                        Toast.makeText(v.getContext(), "position = " + getLayoutPosition(), Toast.LENGTH_SHORT).show();
//                    } else if (getLayoutPosition() == 1) {
//                        //Do whatever you want here
//                        Toast.makeText(v.getContext(), "position = " + getLayoutPosition(), Toast.LENGTH_SHORT).show();
//                    } else if (getLayoutPosition() == 2) {
//
//                    } else if (getLayoutPosition() == 3) {
//
//                    } else if (getLayoutPosition() == 4) {
//
//                    } else if (getLayoutPosition() == 5) {
//
//                    }

//                }});
        }

        @Override
        public void onClick(View v) {

            Toast.makeText(v.getContext(), "position = " + getLayoutPosition(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(v.getContext() , DetailsActivity.class);

            int pos = getLayoutPosition();
//            pos = pos +1;
            Question ques = questionsList.get(pos);
            intent.putExtra("pos", pos);
            intent.putExtra("ques",ques.getQuestion());
            v.getContext().startActivity(intent);

        }

//        @Override
//        public void onClick(View v) {
//            Toast.makeText(v.getContext(), "position = " + getLayoutPosition(), Toast.LENGTH_SHORT).show();
////            Toast.makeText(this, "pos "+position, Toast.LENGTH_SHORT).show();
//        }
    }




    public ListAdapter(List<Question> moviesList) {
        this.questionsList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Question ques = questionsList.get(position);
//        holder.ide.setText(ques.getId());
        holder.question.setText(ques.getQuestion());
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(view.getContext() , DetailsActivity.class);
//                view.getContext().startActivity(intent);
//            }
//        });
////        holder.genre.setText(movie.getGenre());
//        holder.year.setText(movie.getYear());
    }

    @Override
    public int getItemCount() {
        return questionsList.size();
    }
}
