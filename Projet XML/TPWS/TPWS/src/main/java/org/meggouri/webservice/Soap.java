package org.meggouri.webservice;

import jakarta.xml.ws.Endpoint;

public class Soap {
    public static void main(String[] args) {
        Endpoint.publish("http://localhost:8080/", new ReleveService());
        System.out.println("Web service deployé sur l'adresse http://localhost:8080/");
    }
}
