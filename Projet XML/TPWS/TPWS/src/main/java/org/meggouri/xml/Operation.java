package org.meggouri.xml;

import jakarta.xml.bind.annotation.XmlAttribute;

import java.util.Date;

public class Operation {
    private String type;
    private Date date;
    private double montant;
    private String description;

    public Operation() {
    }

    @XmlAttribute
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    @XmlAttribute
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    @XmlAttribute
    public double getMontant() {
        return montant;
    }
    public void setMontant(double montant) {
        this.montant = montant;
    }
    @XmlAttribute
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Operation(String type, Date date, double montant, String description) {
        this.type = type;
        this.date = date;
        this.montant = montant;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Operation{" +
                "type='" + type + '\'' +
                ", date=" + date +
                ", montant=" + montant +
                ", description='" + description + '\'' +
                '}';
    }
}
