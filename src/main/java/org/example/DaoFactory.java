package org.example;

public class DaoFactory {
    public static Dao<CompositePersonnel> getCompositePersonnelDao() {
        return new CompositePersonnelDao();
    }
    public static Dao<Personnel> getPersonnelDao() {
        return new PersonnelDAO();
    }

}
