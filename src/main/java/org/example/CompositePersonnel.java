package org.example;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CompositePersonnel implements Composite, Serializable {
    String nom;
    /**
     *
     */

    protected List<Composite> enfantComposite = new ArrayList<Composite>();

    /**
     *
     * @param nom
     */
    CompositePersonnel(String nom){
        this.nom=nom;
    }

    @Override
    public String return_name() {
        return nom;
    }

    /**
     *
     */

    @Override
    public void print() {
        for (Composite composite : enfantComposite) {
            composite.print();
        }
    }

    /**
     *
     * @param composite
     */
    public void add(Composite composite) {
        enfantComposite.add(composite);
    }

    /**
     *
     * @param composite
     */
    public void remove(Composite composite) {
        enfantComposite.remove(composite);
    }
}
