package com.meggouri;

import java.beans.XMLEncoder;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception{
        Releve releve = new Releve();
        releve.setRIB("011112222333344445555666");
        releve.setDateReleve(new Date());
        releve.setSolde(14500);

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

        releve.setOperations(operationList);

        XMLEncoder encoder = new XMLEncoder(new FileOutputStream("releve.xml"));
        encoder.writeObject(releve);
        encoder.close();
    }
}
