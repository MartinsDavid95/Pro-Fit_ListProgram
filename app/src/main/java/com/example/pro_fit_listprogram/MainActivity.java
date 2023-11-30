package com.example.pro_fit_listprogram;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvProgram;
    //private ImageButton btnFav;
    //private boolean isFavorited = false;
   // private String[] nomProgram, description;
    private List<ProgramModel> programs;
//    int[] imageProgram = {
//            R.drawable.program02,
//            R.drawable.program02,
//            R.drawable.program02,
//            R.drawable.program02,
//            R.drawable.program02,
//            R.drawable.program02,
//            R.drawable.program02,
//            R.drawable.program02,
//            R.drawable.program02,
//            R.drawable.program02,
//            R.drawable.program02,
//            R.drawable.program02,
//            R.drawable.program02,
//            R.drawable.program02,
//    };


    public void init(){
        rvProgram = findViewById(R.id.rvProgram);
        //nomProgram = getResources().getStringArray(R.array.nomProgram);
        //description = getResources().getStringArray(R.array.descriptions);
        //btnFav = findViewById(R.id.btn_fav);
    }

    private List<ProgramModel> createProgramsList() {
        List<ProgramModel> programList = new ArrayList<>();
        String[] nomProgram = getResources().getStringArray(R.array.nomProgram);
        String[] description = getResources().getStringArray(R.array.descriptions);
        int[] imageProgram = {
                R.drawable.program02,
                R.drawable.program02,
                R.drawable.program02,
                R.drawable.program02,
                R.drawable.program02,
                R.drawable.program02,
                R.drawable.program02,
                R.drawable.program02,
                R.drawable.program02,
                R.drawable.program02,
                R.drawable.program02,
                R.drawable.program02,
                R.drawable.program02,
                R.drawable.program02,
        };
        for (int i = 0; i < nomProgram.length; i++) {
            // Créez un nouvel objet ProgramModel pour chaque programme
            ProgramModel program = new ProgramModel();
            program.setNomProgram(nomProgram[i]);
            program.setDescription(description[i]);
            program.setImageProgram(imageProgram[i]);

            // Ajoutez le programme à la liste
            programList.add(program);
        }
        return programList;
    }

    private void fillRecyclerProgram(){
        //RecyclerProgramAdapter adapter = new RecyclerProgramAdapter(this,nomProgram,description,imageProgram,btnFav,programs);
        RecyclerProgramAdapter adapter = new RecyclerProgramAdapter(this, programs);
        rvProgram.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvProgram.setLayoutManager(linearLayoutManager);
    }

//    private void favProgram(){
//        btnFav.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (btnFav.callOnClick()) {
//                    btnFav.setImageResource(R.drawable.star_24_y);
//                }else{
//                    btnFav.setImageResource(R.drawable.star_24_g);
//                }
//            }
//        });
//    }

//    public void onFavButtonClick(View view) {
//        isFavorited = !isFavorited;
//
//        // Mettez à jour l'icône en fonction de l'état
//        if (isFavorited) {
//            btnFav.setImageResource(R.drawable.star_24_y);
//        } else {
//            btnFav.setImageResource(R.drawable.star_24_g);
//        }
//    }

//    public void onImageButtonClick(View view) {
//        // Assurez-vous que l'ImageButton est non null avant d'appeler setImageResource
//        if (btnFav != null) {
//            // Changez l'image du bouton lorsqu'il est cliqué
//            btnFav.setImageResource(R.drawable.star_24_y);
//        } else {
//            Log.e("MainActivity", "ImageButton is null");
//        }
//    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        programs = createProgramsList();
        fillRecyclerProgram();

        SharedPreferences preferences = getPreferences(Context.MODE_PRIVATE);
        List<Integer> savedItemPositions = new ArrayList<>();

        for (int i = 0; i < programs.size(); i++) {
            int savedPosition = preferences.getInt("item_position_" + i, i);
            savedItemPositions.add(savedPosition);
        }


        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvProgram.setLayoutManager(layoutManager);

        for (int i = 0; i < savedItemPositions.size(); i++) {
            layoutManager.scrollToPositionWithOffset(savedItemPositions.get(i), 0);
        }

    }

    @Override
    protected void onStop() {
        super.onStop();

        // Enregistrez la position de chaque élément dans les préférences partagées
        SharedPreferences preferences = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        for (int i = 0; i < programs.size(); i++) {
            editor.putInt("item_position_" + i, i);
        }

        editor.apply();
    }
}