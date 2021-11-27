/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Herly Castillo C
 */
@Entity
@Table(name = "partes_operaciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PartesOperaciones.findAll", query = "SELECT p FROM PartesOperaciones p"),
    @NamedQuery(name = "PartesOperaciones.findByIdParteOperacion", query = "SELECT p FROM PartesOperaciones p WHERE p.idParteOperacion = :idParteOperacion"),
    @NamedQuery(name = "PartesOperaciones.findBySigno", query = "SELECT p FROM PartesOperaciones p WHERE p.signo = :signo"),
    @NamedQuery(name = "PartesOperaciones.findByOperador1", query = "SELECT p FROM PartesOperaciones p WHERE p.operador1 = :operador1"),
    @NamedQuery(name = "PartesOperaciones.findByOperador2", query = "SELECT p FROM PartesOperaciones p WHERE p.operador2 = :operador2"),
    @NamedQuery(name = "PartesOperaciones.findByResultado", query = "SELECT p FROM PartesOperaciones p WHERE p.resultado = :resultado")})
public class PartesOperaciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_parte_operacion")
    private Integer idParteOperacion;
    @Basic(optional = false)
    @Column(name = "signo")
    private String signo;
    @Basic(optional = false)
    @Column(name = "operador1")
    private String operador1;
    @Basic(optional = false)
    @Column(name = "operador2")
    private String operador2;
    @Basic(optional = false)
    @Column(name = "resultado")
    private String resultado;

    public PartesOperaciones() {
    }

    public PartesOperaciones(Integer idParteOperacion) {
        this.idParteOperacion = idParteOperacion;
    }

    public PartesOperaciones(Integer idParteOperacion, String signo, String operador1, String operador2, String resultado) {
        this.idParteOperacion = idParteOperacion;
        this.signo = signo;
        this.operador1 = operador1;
        this.operador2 = operador2;
        this.resultado = resultado;
    }

    public Integer getIdParteOperacion() {
        return idParteOperacion;
    }

    public void setIdParteOperacion(Integer idParteOperacion) {
        this.idParteOperacion = idParteOperacion;
    }

    public String getSigno() {
        return signo;
    }

    public void setSigno(String signo) {
        this.signo = signo;
    }

    public String getOperador1() {
        return operador1;
    }

    public void setOperador1(String operador1) {
        this.operador1 = operador1;
    }

    public String getOperador2() {
        return operador2;
    }

    public void setOperador2(String operador2) {
        this.operador2 = operador2;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idParteOperacion != null ? idParteOperacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PartesOperaciones)) {
            return false;
        }
        PartesOperaciones other = (PartesOperaciones) object;
        if ((this.idParteOperacion == null && other.idParteOperacion != null) || (this.idParteOperacion != null && !this.idParteOperacion.equals(other.idParteOperacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.model.PartesOperaciones[ idParteOperacion=" + idParteOperacion + " ]";
    }
    
}
