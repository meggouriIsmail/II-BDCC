package org.meggouri;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class Main {
    public static void main(String[] args) {
        SparkSession spark = SparkSession.builder()
                .appName("Main")
                .master("local[*]")
                .getOrCreate();

        Dataset<Row> volsDF = spark.read()
                .format("jdbc")
                .option("url", "jdbc:mysql://localhost:3306/db_aer")
                .option("dbtable", "vols")
                .option("user", "root")
                .option("password", "")
                .load();

        Dataset<Row> reservationsDF = spark.read()
                .format("jdbc")
                .option("url", "jdbc:mysql://localhost:3306/db_aer")
                .option("dbtable", "reservations")
                .option("user", "root")
                .option("password", "")
                .load();

        Dataset<Row> passagersDF = spark.read()
                .format("jdbc")
                .option("url", "jdbc:mysql://localhost:3306/db_aer")
                .option("dbtable", "passangers")
                .option("user", "root")
                .option("password", "")
                .load();

        volsDF.createOrReplaceTempView("vols");
        reservationsDF.createOrReplaceTempView("reservations");
        passagersDF.createOrReplaceTempView("passagers");

        Dataset<Row> result = spark.sql(
                "SELECT v.Id AS ID_VOL, v.Date_Depart, COUNT(DISTINCT r.Id_passanger) AS NOMBRE " +
                        "FROM vols v " +
                        "LEFT JOIN reservations r ON v.Id = r.Id_Vol " +
                        "GROUP BY v.Id, v.Date_Depart"
        );

        result.show();

        spark.stop();
    }
}