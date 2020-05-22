package org.example;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CompositePersonnelDao extends Dao<CompositePersonnel>{
    @Override
    public CompositePersonnel create(CompositePersonnel obj) {
       this.connect();
       for (Composite composite : obj.enfantComposite) {
           try (PreparedStatement insert = this.connect.prepareStatement("INSERT INTO CompositePersonnel(Nom, Nomobj , type) values(?, ?, ?, ?)");) {
               insert.setString(1, obj.return_name());
               insert.setString(2, composite.return_name());
               if (composite instanceof Personnel) {
                   PersonnelDAO personnelDAO = new PersonnelDAO();
                   personnelDAO.create((Personnel) composite);
                   insert.setInt(3, 1);
               } else {
                   CompositePersonnelDAO compositePersonnelDAO = new CompositePersonnelDAO();
                   compositePersonnelDAO.create((CompositePersonnel) composite);
                   insert.setInt(3, 0);
               }
               insert.executeUpdate();
           } catch (SQLException throwables) {
               throwables.printStackTrace();

           }
       }
       return null;
    }

    @Override
    public CompositePersonnel find(String id) {
        return null;
    }

    @Override
    public void delete(String s) {

    }
}
