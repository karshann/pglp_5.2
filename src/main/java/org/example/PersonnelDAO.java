package org.example;

import java.io.Closeable;
import java.io.FileNotFoundException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonnelDAO extends Dao<Personnel>{
    @Override
    public Personnel create(Personnel obj) {
        this.connect();

        try (PreparedStatement personnelInsert =
                     this.connect.prepareStatement(
                             "INSERT INTO Personnel(Nom, Prenom, Fonction, Naissance) values(?, ?, ?, ?)");
             PreparedStatement telInsert =
                     this.connect.prepareStatement("INSERT INTO Telephone(Nom, Telephone) VALUES(?, ?)"); ) {
            personnelInsert.setString(1, obj.return_name());
            personnelInsert.setString(2, obj.getPrenom());
            personnelInsert.setString(3, obj.getFonction());
            personnelInsert.setDate(4, java.sql.Date.valueOf(obj.getNaissance()));
            personnelInsert.executeUpdate();
            for (String e : obj.getTelephone()) {

                telInsert.setString(1, obj.return_name());
                telInsert.setInt(2, Integer.parseInt(e));
                telInsert.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        this.disconnect();
        return obj;
    }

    @Override
    public Personnel find(String id) {
        Personnel p = null;
        this.connect();
        try (PreparedStatement select =
                     this.connect.prepareStatement("SELECT * FROM Personnel P WHERE P.nom = ?");
             PreparedStatement selectTel =
                     this.connect.prepareStatement("SELECT T.Telephone FROM Telephone T WHERE T.nom = ?"); ) {
            selectTel.setString(1, id);
            try (ResultSet resTel = selectTel.executeQuery()) {
                ArrayList<String> tel = new ArrayList<>();
                while (resTel.next()) {
                    tel.add(String.valueOf(resTel.getInt("tel")));
                }
                select.setString(1, id);
                try (ResultSet res = select.executeQuery()) {
                    if ((res.next())) {
                        p =
                                new Personnel.Builder(res.getString("Nom"), res.getString("Prenom")).fonction(res.getString("Fonction")).naissance(res.getDate("Naissance").toLocalDate()).Updatetelephone(tel).build();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        this.disconnect();
        return p;
    }

    @Override
    public void delete(String s) {
        this.connect();
        try (PreparedStatement delete =
                     this.connect.prepareStatement("DELETE FROM Personnel P WHERE P.Nom = ?");
             PreparedStatement deleteTel =
                     this.connect.prepareStatement("DELETE FROM Telephone T WHERE T.Nom = ?"); ) {
            delete.setString(1, s);
            deleteTel.setString(1, s);
            delete.executeUpdate();
            deleteTel.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        this.disconnect();

    }

}
