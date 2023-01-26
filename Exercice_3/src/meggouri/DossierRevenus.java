package meggouri;

public class DossierRevenus extends Dossier {

    private double ca;

    public double getCa() {
        return ca;
    }

    public void setCa(double ca) {
        this.ca = ca;
    }

    public DossierRevenus(long id, double ca) {
        super(id);
        this.ca = ca;
    }

    @Override
    protected void calculerMontant() {
        montant = 0.15 * ca;
    }

    @Override
    public String toString() {
        return "15% de revenus " + ca + ", Montant : " + this.montant + "\n";
    }


}
