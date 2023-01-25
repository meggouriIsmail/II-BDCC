package org.meggouri.xml;

import java.io.File;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;

public class ReadXML {
    public static void main(String[] args) throws Exception {
        JAXBContext jaxbContext = JAXBContext.newInstance(Releve.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        Releve releve = (Releve) jaxbUnmarshaller.unmarshal(new File("releve.xml"));

        System.out.println("RIB : " + releve.getRIB());
        System.out.println("Date relevé : " + releve.getDateReleve());
        System.out.println("Solde : " + releve.getSolde());
        System.out.println("Operations : ");
        System.out.println("Date debut des operations : " + releve.getOperations().getDateDebut());
        for (Operation operation : releve.getOperations().getOperations()) {
            System.out.println("Type : " + operation.getType());
            System.out.println("Date : " + operation.getDate());
            System.out.println("Montant : " + operation.getMontant());
            System.out.println("Description : " + operation.getDescription());
        }
        System.out.println("Date fin des operations : " + releve.getOperations().getDateFin());
    }
}
