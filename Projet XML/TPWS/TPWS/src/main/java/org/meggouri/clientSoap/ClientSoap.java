package org.meggouri.clientSoap;

import jakarta.xml.ws.WebServiceRef;
import org.meggouri.webservice.ReleveService;
import org.meggouri.webservice.Soap;
import org.meggouri.xml.Operation;
import org.meggouri.xml.Releve;

import java.util.List;

public class ClientSoap {
    @WebServiceRef(wsdlLocation="http://localhost:8080/ReleveService?wsdl")
    static ReleveService service = new ReleveService();
    public static void main(String[] args) {
        try {
            ClientSoap client = new ClientSoap();
            client.getOperationsByType("DEBIT");
            System.out.println("/////////////////////////////////////////////////////////");
            client.getReleve();
            System.out.println("/////////////////////////////////////////////////////////");
            client.getOperationsByType("CREDIT");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void getReleve() {
        try {
            Releve releve = service.getReleve();

            System.out.println(releve.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void getOperationsByType(String args) {
        try {
            List<Operation> operations = service.getOperationByType(args);

            operations.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
