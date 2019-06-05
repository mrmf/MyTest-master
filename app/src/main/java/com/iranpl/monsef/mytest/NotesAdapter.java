package com.iranpl.monsef.mytest;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteViewHolder> {
    private final ArrayList<NoteModel> myList;
    private final NotesAdapter.OnItemClickListener listener;
    NoteModel note = new NoteModel();
    public NotesAdapter(ArrayList<NoteModel> list, NotesAdapter.OnItemClickListener listener) {
        myList = list;
        this.listener = listener;
    }
    public interface OnItemClickListener {
        void onItemClick(View item);
    }
    @NonNull
    @Override
    public NotesAdapter.NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_note, parent, false);
        NotesAdapter.NoteViewHolder holder = new NotesAdapter.NoteViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull NotesAdapter.NoteViewHolder holder, int position) {

        //holder.root.setBackgroundResource(R.color.colorPrimary);
        String title = myList.get(position).getTitle();
        String date = myList.get(position).getDate();
        String noid = String.valueOf(myList.get(position).getId());
        holder.txtTitle.setText(title);
        holder.txtDate.setText(date);
        holder.txtnid.setText(noid);
        holder.bind(holder.root, listener);
    }
    @Override
    public int getItemCount() {
        return myList.size();
    }

    class NoteViewHolder extends RecyclerView.ViewHolder {

        TextView txtTitle;
        TextView txtDate;
        TextView txtnid;
        View root;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtDate = itemView.findViewById(R.id.txtDate);
            txtnid = itemView.findViewById(R.id.txtnoteid);
            root = itemView;// .findViewById(R.id.itemroot);
        }

        public void bind(final View item, final NotesAdapter.OnItemClickListener listener) {
            root.setBackgroundResource(R.color.colorPrimary);
            //city = myList.get(position);
            //txtInsertedCity.setText(city);
            //name.setText(item.name);
            root.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }

    }
}
