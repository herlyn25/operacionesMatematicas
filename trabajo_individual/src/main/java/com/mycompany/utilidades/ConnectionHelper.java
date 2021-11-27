package com.mycompany.utilidades;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConnectionHelper {
private static EntityManagerFactory emf;

public static EntityManagerFactory getEMF(){
    if(emf==null){
        emf=Persistence.createEntityManagerFactory("com.mycompany_trabajo_individual_jar_1.0-SNAPSHOTPU");
    }    
    return emf;
}

}
