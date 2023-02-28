package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MetierProduitImpl implements Imetier{

    private List<Produit> produits;
    private String file;

    public List<Produit> getProduits() {
        return produits;
    }

    public void setProduits(List<Produit> produits) {
        this.produits = produits;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public MetierProduitImpl() {
        this.produits = new ArrayList<>();
    }

    public MetierProduitImpl(String file) {
        this.produits = new ArrayList<>();
        this.file = file;
    }

    @Override
    public Produit add(Object o) {
        produits.add((Produit) o);
        return (Produit) o;
    }

    @Override
    public List<Produit> getAll() {
        return produits;
    }

    @Override
    public Object findById(Object id) {
        for (Produit produit :
                produits) {
            if (produit.getId() == (int) id) {
                return produit;
            }
        }
        return null;
    }

    @Override
    public void delete(Object id) {
        produits.removeIf(produit -> produit.getId() == (int) id);
    }

    @Override
    public void saveAll() {
        try {
            File file=new File(getFile());
            FileOutputStream fos=new FileOutputStream(file);
            ObjectOutputStream oos=new ObjectOutputStream(fos);

            oos.writeObject(produits);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
