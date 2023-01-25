package org.meggouri.webservice;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;
import org.meggouri.xml.Operation;
import org.meggouri.xml.Releve;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@WebService(name = "ReleveService")
public class ReleveService {
    @WebMethod
    public Releve getReleve() throws Exception{
        JAXBContext jaxbContext = JAXBContext.newInstance(Releve.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        Releve releve = (Releve) unmarshaller.unmarshal(new File("releve.xml"));

        return releve;
    }

    @WebMethod
    public List<Operation> getOperationByType(@WebParam(name = "type") String type) throws Exception{
        JAXBContext jaxbContext = JAXBContext.newInstance(Releve.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        Releve releve = (Releve) unmarshaller.unmarshal(new File("releve.xml"));

        List<Operation> operations = releve.getOperations().getOperations()
                .stream()
                .filter(operation -> Objects.equals(operation.getType(), type)).collect(Collectors.toList());

        return operations;
    }
}
