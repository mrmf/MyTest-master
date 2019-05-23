package com.iranpl.monsef.mytest;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.support.v7.widget.LinearLayoutCompat;
import android.widget.TextView;

import java.util.ArrayList;
public class TestAdapterLocation extends RecyclerView.Adapter<TestAdapterLocation.TestLocationViewHolder> {
    private final ArrayList<String> myList;
    private final OnItemClickListener listener;
    String city;
    public TestAdapterLocation(ArrayList<String> list, OnItemClickListener listener) {
        myList = list;
        this.listener = listener;
    }
    public interface OnItemClickListener {
        void onItemClick(TextView item);
    }
    @NonNull
    @Override
    public TestLocationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_location, parent, false);
        TestAdapterLocation.TestLocationViewHolder holder = new TestAdapterLocation.TestLocationViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull TestAdapterLocation.TestLocationViewHolder holder, int position) {

        //holder.root.setBackgroundResource(R.color.colorPrimary);
        city = myList.get(position);
        holder.txtInsertedCity.setText(city);
          holder.bind(holder.txtInsertedCity, listener);
    }
    @Override
    public int getItemCount() {
        return myList.size();
    }

    class TestLocationViewHolder extends RecyclerView.ViewHolder {

        TextView txtInsertedCity;
        LinearLayoutCompat root;

        public TestLocationViewHolder(@NonNull View itemView) {
            super(itemView);
            txtInsertedCity = itemView.findViewById(R.id.txtInsertedCity);
            root = itemView.findViewById(R.id.root);
        }

        public void bind(final TextView item, final OnItemClickListener listener) {
            root.setBackgroundResource(R.color.colorPrimary);
            //city = myList.get(position);
            //txtInsertedCity.setText(city);
            //name.setText(item.name);
            txtInsertedCity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }

    }
}
