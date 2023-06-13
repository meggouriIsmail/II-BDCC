package com.meggo;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import static org.apache.spark.sql.functions.*;

public class Main {
    public static void main(String[] args) {
        // Creation of a Spark Session
        SparkSession sparkSQL = SparkSession.builder().appName("SparkSQL").master("local[*]").getOrCreate();

        // Load CSV file into the DataFrame
        Dataset<Row> dataset = sparkSQL.read().option("header", true).option("inferSchema", true).csv("incidents.csv");

        // Number of incidents per service
        dataset.createOrReplaceTempView("incidents");
        Dataset<Row> incidentsSql = sparkSQL.sql("SELECT service, COUNT(*) AS numbreOfIncidents FROM incidents GROUP BY service");
        incidentsSql.show();

        //the 2 years with the highest number of incidents
        Dataset<Row> incidentsDataSet = dataset.as("incidents_ds");
        Dataset<Row> highestIncidentYearsDs = incidentsDataSet.groupBy(year(col("date")).alias("annee"))
                .agg(count("*").alias("numbreOfIncidents"))
                .orderBy(desc("numbreOfIncidents"))
                .limit(2);
        highestIncidentYearsDs.show();
    }
}