package conn;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Emf {
    
    public static EntityManagerFactory getEmf() {
        return Persistence.createEntityManagerFactory("CantinaPU");
    }
}