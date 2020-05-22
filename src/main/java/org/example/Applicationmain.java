package org.example;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;

public enum Applicationmain {
    APPLICATION;

    public void createtable(String s) {
        Connection connection = null;
        Statement stmt = null;
        try{
            Class.forName ("org.apache.derby.jdbc.EmbeddedDriver");
            connection = DriverManager.getConnection ("jdbc:derby:./BD/bd;create=true");
            stmt=connection.createStatement();
            String sql="DROP TABLE PERSONNEL";
            stmt.executeUpdate(sql);
            sql="DROP TABLE TELEPHONE";
            stmt.executeUpdate(sql);
            sql ="CREATE TABLE PERSONNEL(Nom VARCHAR(255), Prenom VARCHAR(255), Fonction VARCHAR(255) , Naissance date )";
            stmt.executeUpdate(sql);
            sql = "CREATE TABLE TELEPHONE(Nom VARCHAR(255),Telephone int)";
            stmt.executeUpdate(sql);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Chargement de la base de données");
        }
    }

    /**
     *
     * @param args
     */
    public void run(String [] args) {
        this.createtable("PERSONNEL");
        Personnel p1 = new Personnel
                .Builder("P1", "P1")
                .telephone("+23333333").naissance(LocalDateTime.of(1912,12,12,12,12)).fonction("salariéYYY")
                .build();

    }
    /**
     *
     */

    public static void main(String [] args) {
        APPLICATION.run(args);
    }
}
