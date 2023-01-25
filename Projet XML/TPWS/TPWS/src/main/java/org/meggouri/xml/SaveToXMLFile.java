package org.meggouri.xml;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SaveToXMLFile {
    public static void main(String[] args) throws Exception {

        Releve releve = new Releve();
        releve.setRIB("011112222333344445555666");
        releve.setDateReleve(new Date());
        releve.setSolde(14500);

        Operations operations = new Operations();
        operations.setDateDebut(new Date());
        operations.setDateFin(new Date());

        List<Operation> operationList = new ArrayList<>();

        Operation operation1 = new Operation();
        operation1.setType("CREDIT");
        operation1.setDate(new Date());
        operation1.setMontant(9000);
        operation1.setDescription("Vers Espèce");
        operationList.add(operation1);

        Operation operation2 = new Operation();
        operation2.setType("DEBIT");
        operation2.setDate(new Date());
        operation2.setMontant(3400);
        operation2.setDescription("Chèque Guichet");
        operationList.add(operation2);

        // Ajouter les autres opérations

        operations.setOperations(operationList);
        releve.setOperations(operations);

        // Sérialisation de l'objet Releve en fichier XML
        JAXBContext jaxbContext = JAXBContext.newInstance(Releve.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        jaxbMarshaller.marshal(releve, new File("releve.xml"));
    }
}
