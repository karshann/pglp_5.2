package org.example;

import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.Iterator;

public class IteratorComposite implements Iterator<Composite> {
    /**
     *
     */
    private final Deque<Iterator<Composite>> iterators = new ArrayDeque<>();
    /**
     *
     */
    private final boolean profondeur;

    /**
     *
     * @param composite
     * @param profondeur
     */
    public IteratorComposite(Composite composite, boolean profondeur) {
        this.iterators.add(Collections.singleton(composite).iterator());
        this.profondeur = profondeur;
    }

    /**
     *
     * @return
     */
    @Override
    public boolean hasNext() {
        return!this.iterators.isEmpty();
    }

    /**
     *
     * @return
     */
    @Override
    public Composite next() {
        Iterator<Composite> iterator = this.iterators.removeFirst();
        Composite composite= iterator.next();
        if (iterator.hasNext()) {
            this.iterators.addFirst(iterator);
        }
        if (composite instanceof CompositePersonnel) {
            if (! ((CompositePersonnel) composite).enfantComposite .isEmpty()) {
                if (this.profondeur) {
                    this.iterators.addLast(((CompositePersonnel) composite).enfantComposite.iterator());
                }
                else
                    this.iterators.addFirst(((CompositePersonnel) composite).enfantComposite.iterator());
            }
        }
        return composite;
    }

}
