package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        int c = 1, choice = 0, id = 0;

        MetierProduitImpl metierProduit = new MetierProduitImpl("produits.dat");
        Scanner scanner = new Scanner(System.in);
        while (c>0){
            System.out.println("1. Afficher la liste des produits.");
            System.out.println("2. Rechercher un produit par son id.");
            System.out.println("3. Ajouter un nouveau produit dans la liste.");
            System.out.println("4. Supprimer un produit par id.");
            System.out.println("5. Sauvegarder les produits.");
            System.out.println("6. Quitter ce programme.");
            System.out.print("Entrer votre reponse : ");
            choice = scanner.nextInt();
            System.out.println();

            switch (choice) {
                case 1 -> {
                    System.out.println("La liste des produits : ");
                    for (Produit prod : metierProduit.getProduits()) {
                        System.out.println(prod.toString());
                    }
                    System.out.println();
                }
                case 2 -> {
                    System.out.print("Donner l'id du produit : ");
                    id = scanner.nextInt();
                    System.out.println(metierProduit.findById(id).toString());
                    System.out.println();
                }
                case 3 -> {
                    Produit newProd = new Produit();
                    System.out.print("Donner l'id du produit : ");
                    newProd.setId(scanner.nextInt());
                    System.out.print("Donner le nom du produit : ");
                    newProd.setNom(scanner.next());
                    System.out.print("Donner la marque du produit : ");
                    newProd.setMarque(scanner.next());
                    System.out.print("Donner la description du produit : ");
                    newProd.setDescription(scanner.next());
                    System.out.print("Donner le nombre de stock du produit : ");
                    newProd.setNbrStock(scanner.nextInt());
                    System.out.print("Donner le prix du produit : ");
                    newProd.setPrix(scanner.nextDouble());
                    metierProduit.add(newProd);
                    System.out.println();
                }
                case 4 -> {
                    System.out.print("Donner l'id du produit que vous voulez supprimer: ");
                    id = scanner.nextInt();
                    metierProduit.delete(id);
                    System.out.println();
                }
                case 5 -> {
                    metierProduit.saveAll();
                    System.out.println("Tous les produits sont sauvegarder sous fichier 'produits.dat' avec succes.");
                }
                default -> c = -1;
            }
        }
    }
}
