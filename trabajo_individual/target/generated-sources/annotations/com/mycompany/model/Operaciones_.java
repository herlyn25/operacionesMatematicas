package com.mycompany.model;

import com.mycompany.model.Sistema;
import com.mycompany.model.TipoRegistro;
import com.mycompany.model.Usuario;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-08-15T22:32:58")
@StaticMetamodel(Operaciones.class)
public class Operaciones_ { 

    public static volatile SingularAttribute<Operaciones, Usuario> cedulaId;
    public static volatile SingularAttribute<Operaciones, String> resultado;
    public static volatile SingularAttribute<Operaciones, Sistema> idSistema;
    public static volatile SingularAttribute<Operaciones, String> operador2;
    public static volatile SingularAttribute<Operaciones, Integer> idCedula;
    public static volatile SingularAttribute<Operaciones, String> operador1;
    public static volatile SingularAttribute<Operaciones, TipoRegistro> idTipoOperacion;
    public static volatile SingularAttribute<Operaciones, Integer> idOperaciones;

}