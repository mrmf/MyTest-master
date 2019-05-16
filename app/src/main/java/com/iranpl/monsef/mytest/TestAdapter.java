package com.iranpl.monsef.mytest;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.support.v7.widget.LinearLayoutCompat;
import android.widget.TextView;

import java.util.ArrayList;

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.TestViewHolder> {

    private final ArrayList<PersonInfo> myList;

    public TestAdapter(ArrayList<PersonInfo> list) {
        myList = list;
    }
    @NonNull
    @Override
    public TestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);

        TestViewHolder holder = new TestViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull TestViewHolder holder, int position) {


        // if (position % 2 == 1) {
        //    holder.root.setBackgroundResource(R.color.colorAccent);
        //}else
        //    holder.root.setBackgroundResource(R.color.colorPrimary);
        holder.root.setBackgroundResource(R.color.colorPrimary);
        PersonInfo personInfo = myList.get(position);

        holder.txtName.setText(personInfo.name);
        holder.mob.setText(personInfo.mobile);
        holder.Addr.setText(personInfo.Address);
        holder.imgName.setImageResource(personInfo.img);
    }

    @Override
    public int getItemCount() {
        return myList.size();
    }

    class TestViewHolder extends RecyclerView.ViewHolder {

        TextView txtName;
        ImageView imgName;
        TextView mob;
        TextView Addr;
        LinearLayoutCompat root;

        public TestViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            imgName = itemView.findViewById(R.id.imgName);
            mob = itemView.findViewById(R.id.mob);
            Addr = itemView.findViewById(R.id.Addr);
            root = itemView.findViewById(R.id.root);
        }
    }
}
