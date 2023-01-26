package meggouri;

public class Program {
    public static void main(String[] args) {
        Societe societe = new Societe(1, "SDCC");
        Impots imp1 = new Impots(2022);
        societe.addImpots(imp1);

        imp1.add(new DossierRevenus(111, 1000000));
        imp1.add(new DossierVehicule(222, 6, 'E'));
        imp1.add(new DossierVehicule(222, 8, 'G'));
        imp1.add(new DossierVehicule(222, 11, 'E'));
        imp1.add(new DossierVehicule(222, 15, 'G'));

        System.out.println(societe);
        System.out.println(societe.getImpots(2022));
    }
}
