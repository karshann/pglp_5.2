package org.example;

public class DaoFactory {
    public static Dao<CompositePersonnel> getCompositePersonnelDao() {
        return new CompositePersonnelDAO();
    }
    public static Dao<Personnel> getPersonnelDao() {
        return new PersonnelDAO();
    }

}
