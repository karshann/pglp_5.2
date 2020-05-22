package org.example;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class CompositePersonnelDaoTest {

    @Test
    public void create() {
        CompositePersonnelDAO compositePersonnelDAO=new CompositePersonnelDAO();
        Personnel P=new Personnel.Builder("Jean","Paul").telephone("02589445272").naissance(LocalDateTime.of(1997,11,18,21,05)).fonction("PDG").build();
        CompositePersonnel compositePersonnel=new CompositePersonnel("groupe");
        compositePersonnel.add(P);
        compositePersonnelDAO.create(compositePersonnel);
    }

    @Test
    public void find() {
    }

    @Test
    public void delete() {
    }
}