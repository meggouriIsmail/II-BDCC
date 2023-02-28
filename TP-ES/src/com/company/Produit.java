package com.company;

import java.io.Serializable;

public class Produit implements Serializable {
    private int id;
    private String nom;
    private String marque;
    private String description;
    private int nbrStock;
    private double prix;

    public Produit(int id, String nom, String marque, String description, int nbrStock, double prix) {
        this.id = id;
        this.nom = nom;
        this.marque = marque;
        this.description = description;
        this.nbrStock = nbrStock;
        this.prix = prix;
    }

    public Produit() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNbrStock() {
        return nbrStock;
    }

    public void setNbrStock(int nbrStock) {
        this.nbrStock = nbrStock;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Produit{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", marque='" + marque + '\'' +
                ", description='" + description + '\'' +
                ", nbrStock=" + nbrStock +
                ", prix=" + prix +
                '}';
    }
}
