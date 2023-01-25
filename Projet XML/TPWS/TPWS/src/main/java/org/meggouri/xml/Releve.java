package org.meggouri.xml;

import java.util.Date;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlType;

@XmlRootElement(name = "releve")
@XmlType(propOrder = { "RIB", "dateReleve", "solde", "operations" })
public class Releve {
    private String RIB;
    private Date dateReleve;
    private double solde;
    private Operations operations;

    @XmlAttribute(name = "RIB")
    public String getRIB() {
        return RIB;
    }

    public void setRIB(String RIB) {
        this.RIB = RIB;
    }

    @XmlElement(name = "dateReleve")
    public Date getDateReleve() {
        return dateReleve;
    }

    public void setDateReleve(Date dateReleve) {
        this.dateReleve = dateReleve;
    }

    @XmlElement(name = "solde")
    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    @XmlElement(name = "operations")
    public Operations getOperations() {
        return operations;
    }

    public void setOperations(Operations operations) {
        this.operations = operations;
    }

    @Override
    public String toString() {
        return "Releve{" +
                "RIB='" + RIB + '\'' +
                ", dateReleve=" + dateReleve +
                ", solde=" + solde +
                ", operations=" + operations +
                '}';
    }
}
