package meggouri;

import java.util.ArrayList;
import java.util.List;

public class Societe {

    private long id;
    private String raisonSociale;
    List<Impots> impots = new ArrayList<>();

    public Societe(long id, String rs) {
        this.id = id;
        this.raisonSociale = rs;
    }

    public void addImpots(Impots imp) {
        this.impots.add(imp);
    }

    public Impots getImpots(int annee) {
        for (Impots imp : impots) {
            if (imp.getAnnee() == annee) {
                return imp;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Societe : " + id + " - " + raisonSociale;
    }
}
