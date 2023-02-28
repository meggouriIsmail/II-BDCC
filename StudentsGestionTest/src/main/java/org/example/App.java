package org.example;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Scanner;
import java.util.ListIterator;


public class App
{
    public static void main( String[] args ) {
        ArrayList<Etudiant> etu = new ArrayList<Etudiant>();
        Scanner scanner = new Scanner(System.in);
        Scanner scanner1 = new Scanner(System.in);
        int choix;
        do {
            System.out.print("Tappez 1 pour ajouter un étudiant\n");
            System.out.print("Tappez 2 pour ajouter N étudiant\n");
            System.out.print("Tappez 3 pour modifier un étudiant\n");
            System.out.print("Tappez 4 pour supprimer un étudiant\n");
            System.out.print("Tappez 5 pour quitter le programme\n");
            choix = scanner1.nextInt();
            switch (choix) {
                case 1:
                    System.out.println("Entrez le nom de l'étudiant : ");
                    String nometu = scanner.nextLine();
                    System.out.println("Entrez sa note : ");
                    String noteetu = scanner.nextLine();
                    System.out.println("Entrez son résultat : ");
                    String resultatetu = scanner.nextLine();
                    System.out.println("Entrez sa mention : ");
                    String mentionetu = scanner.nextLine();
                    etu.add(new Etudiant(nometu,noteetu,resultatetu,mentionetu));
                    for (Etudiant str : etu)
                    {
                        System.out.println(str);
                    }
                    break;
                case 2:
                    System.out.println("Ajouter N étudiant");
                    break;
                case 3:
                    System.out.println("Modifier étudiant");
                    break;
                case 4:
                    boolean found = false;
                    System.out.println("List Before remove() method = " );
                    for(Etudiant e : etu){
                        System.out.println(e + "");
                    }
                    System.out.println();
                    System.out.println("toDelete : " );
                    String toDelete = scanner.next();
                    for(Etudiant e : etu){
                        if (e.getNom().equals(toDelete)) {
                            etu.remove(e);
                            found = true;
                            break;
                        }
                    }
                    System.out.println();
                    if (found) {
                        System.out.println("Supprimer avec succès ");
                    }else {
                        System.out.println("Le nom que vous avez fourni n'appartient pas a la liste");
                    }
                    System.out.println();
                    System.out.println("List After remove() method = ") ;
                    for(Etudiant e : etu){
                        System.out.println(e + "");
                    }
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid Number");
                    return;
            }
        }
        while (choix != 5);
    }
}
