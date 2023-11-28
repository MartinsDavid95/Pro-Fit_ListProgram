package com.example.pro_fit_listprogram;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerProgramAdapter extends RecyclerView.Adapter<RecyclerProgramAdapter.MyViewHolder> {
    private Context context;
    private String[] nomProgram, descriptions;
    private int[] imageProgram;

    public RecyclerProgramAdapter() {
    }

    public RecyclerProgramAdapter(Context context, String[] nomProgram, String[] descriptions, int[] imageProgram) {
        this.context = context;
        this.nomProgram = nomProgram;
        this.descriptions = descriptions;
        this.imageProgram = imageProgram;
    }

    @NonNull
    @Override
    public RecyclerProgramAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_recycler_program,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerProgramAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.tvTitre.setText(nomProgram[position]);
        holder.tvDescription.setText(descriptions[position]);
        holder.ivProgram.setImageResource(imageProgram[position]);
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ProgramActivity.class);
                intent.putExtra("nomProgram",nomProgram[position]);
                intent.putExtra("description",descriptions[position]);
                intent.putExtra("imageProgram",imageProgram[position]);
                v.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return imageProgram.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private ImageView ivProgram;
        private TextView tvTitre, tvDescription;
        private ConstraintLayout mainLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ivProgram = itemView.findViewById(R.id.iv_program);
            tvTitre = itemView.findViewById(R.id.tv_titre);
            tvDescription = itemView.findViewById(R.id.tv_description);
            mainLayout = itemView.findViewById(R.id.main_layout);
        }
    }
}
