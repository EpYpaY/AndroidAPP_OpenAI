package com.example.projet_android;

public class Rhum {

    String Titre;
    String Marque;
    String Degres;
    String Note;
    int ID;

    public Rhum() {

    }

    public Rhum(String titre, String marque, String degres, String note, int ID) {
        Titre = titre;
        Marque = marque;
        Degres = degres;
        Note = note;
        this.ID = ID;
    }

    public String getTitre() {
        return Titre;
    }

    public String getMarque() {
        return Marque;
    }

    public String getDegres() {
        return Degres;
    }

    public String getNote() {
        return Note;
    }
    public int getId() {
        return ID;
    }

    public void setTitre(String titre) {
        Titre = titre;
    }

    public void setMarque(String marque) {
        Marque = marque;
    }

    public void setDegres(String degres) {
        Degres = degres;
    }

    public void setNote(String note) {
        Note = note;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
