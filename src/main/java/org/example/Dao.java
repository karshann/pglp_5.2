package org.example;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class Dao<T>  {


    public Connection connect = null;

    public Statement stmt = null;

    public abstract T create(T obj);

    public abstract T find(String id);

    public abstract void delete(String s);

    /**
     *
     */
    public void connect() {

        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            this.connect = DriverManager.getConnection("jdbc:derby:./BD/bd;create=true");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            try {
                connect.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     *
     */
    public void disconnect() {

        try {
            connect.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}