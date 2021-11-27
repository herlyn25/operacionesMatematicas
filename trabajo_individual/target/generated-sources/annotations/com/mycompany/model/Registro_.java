package com.mycompany.model;

import com.mycompany.model.TipoRegistro;
import com.mycompany.model.Usuario;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-08-15T22:32:58")
@StaticMetamodel(Registro.class)
public class Registro_ { 

    public static volatile SingularAttribute<Registro, TipoRegistro> idTipoRegistro;
    public static volatile SingularAttribute<Registro, Usuario> cedulaUsuario;
    public static volatile SingularAttribute<Registro, String> fecha;
    public static volatile SingularAttribute<Registro, String> hora;
    public static volatile SingularAttribute<Registro, Integer> idRegistro;

}