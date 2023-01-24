package com.meggouri;

import java.util.Date;
import java.util.List;
import javax.xml.datatype.

public class Releve {
    private String RIB;
    private Date dateReleve;
    private double solde;
    private List<Operation> operations;

    public Releve() {
    }

    public Releve(String RIB, Date dateReleve, double solde, List<Operation> operations) {
        this.RIB = RIB;
        this.dateReleve = dateReleve;
        this.solde = solde;
        this.operations = operations;
    }

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
    public BigDecimal getSolde() {
        return solde;
    }

    public void setSolde(BigDecimal solde) {
        this.solde = solde;
    }

    @XmlElement(name = "operations")
    public Operations getOperations() {
        return operations;
    }

    public void setOperations(Operations operations) {
        this.operations = operations;
    }
}
