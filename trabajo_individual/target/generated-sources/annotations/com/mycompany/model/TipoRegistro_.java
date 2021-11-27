package com.mycompany.model;

import com.mycompany.model.Operaciones;
import com.mycompany.model.Registro;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-08-15T22:32:58")
@StaticMetamodel(TipoRegistro.class)
public class TipoRegistro_ { 

    public static volatile SingularAttribute<TipoRegistro, String> descripcion;
    public static volatile CollectionAttribute<TipoRegistro, Operaciones> operacionesCollection;
    public static volatile SingularAttribute<TipoRegistro, Integer> idTiporegistro;
    public static volatile CollectionAttribute<TipoRegistro, Registro> registroCollection;

}