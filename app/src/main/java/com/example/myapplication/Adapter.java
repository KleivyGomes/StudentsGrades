package com.example.myapplication;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>{
    Context context;
    ArrayList<Database> db;

    public Adapter(Context context, ArrayList<Database> db) {
        this.context = context;
        this.db = db;
    }


    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.student_single, parent, false);

        return new Adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
        holder.name.setText(db.get(position).getName());
        if(Float.valueOf(db.get(position).getEvaluation())<12){
            holder.evaluation.setText(db.get(position).getEvaluation());
        }else{
            holder.evaluation.setText(db.get(position).getEvaluation());
        }

    }

    @Override
    public int getItemCount() {
        return db.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener{
        TextView name, evaluation;
        ImageView photo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            evaluation = itemView.findViewById(R.id.evaluate);
            photo = itemView.findViewById(R.id.perfil);
            photo.setImageResource(R.drawable.profile);
            itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            if(Float.valueOf(db.get(0).getEvaluation())>=12){
                menu.add(this.getAdapterPosition(), 101 , 1, "Delete");
            }else{
                menu.add(this.getAdapterPosition(), 102 , 1, "Delete");
            }
        }
    }
}
