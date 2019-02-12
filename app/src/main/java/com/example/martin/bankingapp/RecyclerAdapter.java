package com.example.martin.bankingapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>
{

    private ArrayList<String> list;
    private static ClickListener clickListener;


    public RecyclerAdapter(ArrayList<String> list)
    {
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        TextView textView = (TextView) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.text_view_layout, viewGroup,false);

        return new MyViewHolder(textView, this.list);
    }

    @Override
    public void onBindViewHolder(MyViewHolder  viewHolder, int i) {
       viewHolder.name.setText(list.get(i));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        TextView name;
        ArrayList<String> list;



        public MyViewHolder(TextView itemView, ArrayList<String> ls) {
            super(itemView);
            this.name = itemView;
            this.list = ls;
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(getAdapterPosition(), v);
        }

        @Override
        public boolean onLongClick(View v) {
            clickListener.onItemLongClick(getAdapterPosition(), v);
            int p = getAdapterPosition();
            String s = this.list.get(p);
            Toast.makeText(v.getContext(),s, Toast.LENGTH_LONG).show();
            return true;
        }
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        RecyclerAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(int position, View v);
        void onItemLongClick(int position, View v);
    }

}
