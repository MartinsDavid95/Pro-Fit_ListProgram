package com.example.pro_fit_listprogram;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvProgram;
    private List<ProgramModel> programs;


    public void init(){
        rvProgram = findViewById(R.id.rvProgram);
    }

    // Firebase Start
    public void readData(){
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("programs").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error != null) {
                    // Gérer les erreurs ici
                    return;
                }

                List<ProgramModel> programList = new ArrayList<>();
                for (DocumentSnapshot document : value) {
                    ProgramModel program = document.toObject(ProgramModel.class);
                    programList.add(program);
                }

                // Mettez à jour votre RecyclerView avec les données récupérées
                // ...

            }
        });
    }




    // Firebase End

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
        RecyclerProgramAdapter adapter = new RecyclerProgramAdapter(this, programs);
        rvProgram.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvProgram.setLayoutManager(linearLayoutManager);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseApp.initializeApp(this);

        init();
        programs = createProgramsList();
        fillRecyclerProgram();

    }
}