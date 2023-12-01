package com.example.pro_fit_listprogram;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerProgramAdapter extends RecyclerView.Adapter<RecyclerProgramAdapter.MyViewHolder> {
    private Context context;
    private String[] nomProgram, descriptions;
    private int[] imageProgram;
    private ImageButton btnFav;
    private boolean isFavorited = false;
    private List<ProgramModel> programs;

    public RecyclerProgramAdapter() {
    }

    public RecyclerProgramAdapter(Context context, List<ProgramModel> programs) {
        this.context = context;
        this.programs = programs;
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
        ProgramModel program = programs.get(position);
        holder.tvTitre.setText(program.getNomProgram());
        holder.tvDescription.setText(program.getDescription());
        holder.ivProgram.setImageResource(program.getImageProgram());

        if (program.isFavorited()) {
            holder.btnFav.setImageResource(R.drawable.star_24_y);
        } else {
            holder.btnFav.setImageResource(R.drawable.star_24_g);
        }
        holder.btnFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int originalPosition = position;
                program.setFavorited(!program.isFavorited());

                if (program.isFavorited()) {
                    // Déplacez l'élément en haut de la liste
                    programs.remove(originalPosition);
                    programs.add(0, program);
                }else {
                    programs.remove(program);
                    programs.add(originalPosition, program);
                }
                notifyDataSetChanged();
            }
        });
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ProgramActivity.class);
                intent.putExtra("nomProgram", program.getNomProgram());
                intent.putExtra("description", program.getDescription());
                intent.putExtra("imageProgram", program.getImageProgram());
                v.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        if (programs != null) {
            return programs.size();
        } else {
            return 0; // ou une valeur par défaut si programs est null
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private ImageView ivProgram;
        private TextView tvTitre, tvDescription;
        private ImageButton btnFav;
        private ConstraintLayout mainLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ivProgram = itemView.findViewById(R.id.iv_program);
            tvTitre = itemView.findViewById(R.id.tv_titre);
            tvDescription = itemView.findViewById(R.id.tv_description);
            mainLayout = itemView.findViewById(R.id.main_layout);
            btnFav = itemView.findViewById(R.id.btn_fav);
        }
    }




}
