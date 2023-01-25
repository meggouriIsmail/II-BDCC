package org.meggouri.clientSoap;

import com.google.gson.Gson;
import org.meggouri.webservice.ReleveService;
import org.meggouri.xml.Operation;
import org.meggouri.xml.Releve;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class ClentRest {
    private HttpRequest httpRequest;
    public static void main(String[] args) {
        try {
            ClentRest client = new ClentRest();
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
        Gson gson = new Gson();
        try {
            httpRequest = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:4567/releve"))
                .GET()
                .build();

            HttpResponse<String> response = HttpClient.newHttpClient().send(httpRequest, HttpResponse.BodyHandlers.ofString());

            Releve releve = gson.fromJson(response.body(), Releve.class);

            System.out.println(releve);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void getOperationsByType(String args) {
        Gson gson = new Gson();
        try {
             httpRequest = HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:4567/operations/"+args))
                    .GET()
                    .build();

            HttpResponse<String> response = HttpClient.newHttpClient().send(httpRequest, HttpResponse.BodyHandlers.ofString());

            List<Operation> operations = List.of(gson.fromJson(response.body(), Operation[].class));

            operations.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
