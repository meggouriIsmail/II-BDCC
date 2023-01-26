package meggouri;

import java.util.ArrayList;
import java.util.List;

public class Impots {
    private int annee;
    private double totalImpot = 0;
    public List<Dossier> dossiers = new ArrayList<>();

    public Impots(int annee) {
        this.annee = annee;
    }

    public void add(Dossier d) {
        this.dossiers.add(d);
    }

    public int getAnnee() {
        return annee;
    }

    public double getTotalImpot() {
        for (Dossier d : dossiers) {
            totalImpot += d.montant;
        }
        return totalImpot;
    }

    @Override
    public String toString() {
        for (Dossier imp : this.dossiers) {
            imp.calculerMontant();
            imp.toString();
        }
        return "Liste des impots " + annee + "\n" + this.dossiers + "\n" + "Total : " + this.getTotalImpot();
    }


}
