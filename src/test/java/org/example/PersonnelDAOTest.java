package org.example;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class PersonnelDAOTest {

    @Test
    public void create() {
        Personnel P=new Personnel.Builder("Jean","Paul").telephone("02589445272").naissance(LocalDateTime.of(1997,11,18,21,05)).fonction("PDG").build();
        PersonnelDAO PersD =new PersonnelDAO();
        PersD.create(P);
    }

    @Test
    public void find() {
        Personnel P=new Personnel.Builder("Jean","Paul").telephone("02589445272").naissance(LocalDateTime.of(1997,11,18,21,05)).fonction("PDG").build();
        PersonnelDAO PersD =new PersonnelDAO();
        Personnel P1=PersD.find("Jean");
        P.equals(P1);
    }

    @Test
    public void delete() {
        PersonnelDAO PersD =new PersonnelDAO();
        PersD.delete("Jean");
    }
}