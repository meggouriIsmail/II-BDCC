package org.example;

public class Etudiant {
    private String nom;
    private String note;
    private String resultat;
    private String mention;

    public Etudiant(String nom, String note, String resultat, String mention) {
        this.nom = nom;
        this.note = note;
        this.resultat = resultat;
        this.mention = mention;
    }

    public String getNom() {
        return nom;
    }

    public String getNote() {
        return note;
    }

    public String getResultat() {
        return resultat;
    }

    public String getMention() {
        return mention;
    }

    @Override
    public String toString() {
        return "Etudiant{" +
                "nom='" + nom + '\'' +
                ", note='" + note + '\'' +
                ", resultat='" + resultat + '\'' +
                ", mention='" + mention + '\'' +
                '}';
    }
}
