package org.meggouri.xml;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.SchemaOutputResolver;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class Main {
    public static void main(String[] args) throws Exception {

        JAXBContext jaxbContext = JAXBContext.newInstance(Releve.class);
        jaxbContext.generateSchema(new SchemaOutputResolver() {
            @Override
            public Result createOutput(String namespaceUri, String suggestedFileName) {
                StreamResult result = new StreamResult(new File("releve.xsd"));
                result.setSystemId("releve.xsd");
                return result;
            }
        });
    }
}