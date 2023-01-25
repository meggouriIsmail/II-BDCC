package org.meggouri.webservice;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;
import org.meggouri.xml.Operation;
import org.meggouri.xml.Releve;
import spark.Spark;

import java.io.File;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static spark.Spark.get;

public class RestFul {
    public static void main(String[] args) {
        get("/releve", (req, res) -> {
            JAXBContext jaxbContext = JAXBContext.newInstance(Releve.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            Releve releve = (Releve) unmarshaller.unmarshal(new File("releve.xml"));
            return releve;
        }, new JsonTransformer());

        get("/operations/:type", (req, res) -> {
            JAXBContext jaxbContext = JAXBContext.newInstance(Releve.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            Releve releve = (Releve) unmarshaller.unmarshal(new File("releve.xml"));
            return releve.getOperations().getOperations()
                    .stream()
                    .filter(operation -> Objects.equals(operation.getType(), req.params(":type"))).collect(Collectors.toList());

        }, new JsonTransformer());
    }
}
