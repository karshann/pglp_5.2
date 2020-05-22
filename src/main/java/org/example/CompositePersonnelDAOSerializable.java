package org.example;

import java.io.*;

/**
 *
 */
public class CompositePersonnelDAOSerializable extends Dao<CompositePersonnel> {
    @Override
    public CompositePersonnel create(CompositePersonnel obj) {
            try (ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("CompositePersonnel")))) {
                out.writeObject(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
            return obj;
    }
        @Override
    public CompositePersonnel find(String id) {
           CompositePersonnel compositePersonnel = null;
            try (ObjectInputStream in =
                         new ObjectInputStream(new BufferedInputStream(new FileInputStream(id)))) {
                compositePersonnel = (CompositePersonnel) in.readObject();

            } catch (ClassNotFoundException | IOException e) {
                e.printStackTrace();
            }

            return compositePersonnel;
    }

    @Override
    public void delete(String file) {
        try {
            File f = new File(file);

            if (f.delete()) {
                System.out.println("Supression réussite");
            } else {
                System.out.println("Supression raté");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

