package com.example.pro_fit_listprogram;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvProgram;
    private ImageButton btnFav;
    private boolean isFavorited = false;
    private String[] nomProgram, description;
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


    public void init(){
        rvProgram = findViewById(R.id.rvProgram);
        nomProgram = getResources().getStringArray(R.array.nomProgram);
        description = getResources().getStringArray(R.array.descriptions);
        btnFav = findViewById(R.id.btn_fav);
    }

    private void fillRecyclerProgram(){
        RecyclerProgramAdapter adapter = new RecyclerProgramAdapter(this,nomProgram,description,imageProgram);
        rvProgram.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
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

    public void onFavButtonClick(View view) {
        isFavorited = !isFavorited;

        // Mettez à jour l'icône en fonction de l'état
        if (isFavorited) {
            btnFav.setImageResource(R.drawable.star_24_y);
        } else {
            btnFav.setImageResource(R.drawable.star_24_g);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        fillRecyclerProgram();


    }
}