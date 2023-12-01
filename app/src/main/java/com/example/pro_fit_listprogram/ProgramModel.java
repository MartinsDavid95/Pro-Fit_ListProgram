package com.example.pro_fit_listprogram;

import com.google.firebase.firestore.DocumentId;
import com.google.firebase.firestore.Exclude;

public class ProgramModel {
    // firebase needed
    @DocumentId
    private String documentId;
    private String nomProgram;
    private String description;
    private int imageProgram;
    private boolean isFavorited;

    public ProgramModel() {
    }

    public ProgramModel(String nomProgram, String description, int imageProgram, boolean isFavorited) {
        this.nomProgram = nomProgram;
        this.description = description;
        this.imageProgram = imageProgram;
        this.isFavorited = isFavorited;
    }

    public String getNomProgram() {
        return nomProgram;
    }

    public void setNomProgram(String nomProgram) {
        this.nomProgram = nomProgram;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImageProgram() {
        return imageProgram;
    }

    public void setImageProgram(int imageProgram) {
        this.imageProgram = imageProgram;
    }

    public boolean isFavorited() {
        return isFavorited;
    }

    public void setFavorited(boolean favorited) {
        isFavorited = favorited;
    }

    // firebase needed
    @Exclude
    public String getDocumentId() {
        return documentId;
    }
}
