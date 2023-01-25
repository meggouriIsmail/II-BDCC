package org.meggouri.xml;

import java.util.Date;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlType(name = "operations")
public class Operations {

    private Date dateDebut;
    private Date dateFin;
    private List<Operation> operations;

    @XmlAttribute(name = "dateDebut")
    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    @XmlAttribute(name = "dateFin")
    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    @XmlElement(name = "operation")
    public List<Operation> getOperations() {
        return operations;
    }

    public void setOperations(List<Operation> operations) {
        this.operations = operations;
    }

    public Operations() {
    }

    @Override
    public String toString() {
        return "Operations{" +
                "dateDebut=" + dateDebut +
                ", dateFin=" + dateFin +
                ", operations=" + operations +
                '}';
    }
}
