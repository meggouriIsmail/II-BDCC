package meggouri;

public class DossierVehicule extends Dossier {
    private int nbChevaux;
    private char categorie;

    public DossierVehicule(int id, int nbc, char cat) {
        super(id);
        this.nbChevaux = nbc;
        this.categorie = cat;
    }

    @Override
    protected void calculerMontant() {
        switch (this.categorie) {
            case 'E':
                if (this.nbChevaux >= 15) {
                    montant = 8000;
                } else if (this.nbChevaux >= 11) {
                    montant = 3000;
                } else if (this.nbChevaux >= 8) {
                    montant = 650;
                } else montant = 350;
                break;
            case 'G':
                if (this.nbChevaux >= 15) {
                    montant = 20000;
                } else if (this.nbChevaux >= 11) {
                    montant = 6000;
                } else if (this.nbChevaux >= 8) {
                    montant = 1300;
                } else montant = 700;
        }
    }

    @Override
    public String toString() {
        this.calculerMontant();
        return "Vehicule " + categorie + " " + nbChevaux + " CH, montant : " + montant + "\n";
    }
}
