package org.example;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CompositePersonnelDao extends Dao<CompositePersonnel>{
    @Override
    public CompositePersonnel create(CompositePersonnel obj) {
       this.connect();
        try (PreparedStatement insert = this.connect.prepareStatement("INSERT INTO COMPOSITEPERSONNEL(Nom, Nomobj , type) values(?, ?, ?)");) {
        for (Composite composite : obj.enfantComposite) {
               insert.setString(1, obj.return_name());
               insert.setString(2, composite.return_name());
               if (composite instanceof Personnel) {
                   PersonnelDAO personnelDAO = new PersonnelDAO();
                   personnelDAO.create((Personnel) composite);
                   insert.setInt(3, 1);
               } else {
                   CompositePersonnelDao compositePersonnelDAO = new CompositePersonnelDao();
                   compositePersonnelDAO.create((CompositePersonnel) composite);
                   insert.setInt(3, 0);
               }
               insert.executeUpdate();
           }
       }
        catch (SQLException throwables) {
            throwables.printStackTrace();

        }
       return null;
    }

    @Override
    public CompositePersonnel find(String id) {
        CompositePersonnel compositePersonnel = null;
        this.connect();
        try (PreparedStatement select =
                     this.connect.prepareStatement("SELECT * FROM COMPOSITEPERSONNEL CP WHERE CP.Nom = ?")) {
            select.setString(1, id);
            compositePersonnel = new CompositePersonnel(id);
            try {
                ResultSet res = select.executeQuery();
                while (res.next()) {
                    if (res.getInt("type") == 0) {
                        CompositePersonnelDao compositePersonnelDao = new CompositePersonnelDao();
                        compositePersonnel.add(compositePersonnelDao.find(res.getString("Nomobj")));
                    } else {
                        PersonnelDAO personnelDAO = new PersonnelDAO();
                        compositePersonnel.add(personnelDAO.find(res.getString("Nomobj")));
                        System.out.println("reussi");
                    }
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return compositePersonnel;
    }

    @Override
    public void delete(String s) {
        this.connect();
        try (PreparedStatement select =
                     this.connect.prepareStatement("SELECT * FROM COMPOSITEPERSONNEL CP WHERE CP.Nom = ?")) {
            select.setString(1, s);
            try {
                ResultSet res = select.executeQuery();
                while (res.next()) {
                    if (res.getInt("type") == 0) {
                        CompositePersonnelDao compositePersonnelDao = new CompositePersonnelDao();
                        compositePersonnelDao.delete("Nomobj");
                    } else {
                        PersonnelDAO personnelDAO = new PersonnelDAO();
                        personnelDAO.delete(s);
                    }
                }
                PreparedStatement delete = this.connect.prepareStatement("DELETE FROM COMPOSITEPERSONNEL CP WHERE CP.Nom = ?");
                delete.setString(1,s);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
