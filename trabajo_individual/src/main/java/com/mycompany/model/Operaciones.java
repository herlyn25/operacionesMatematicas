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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Herly Castillo C
 */
@Entity
@Table(name = "operaciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Operaciones.findAll", query = "SELECT o FROM Operaciones o"),
    @NamedQuery(name = "Operaciones.findByIdOperaciones", query = "SELECT o FROM Operaciones o WHERE o.idOperaciones = :idOperaciones"),
    @NamedQuery(name = "Operaciones.findByIdCedula", query = "SELECT o FROM Operaciones o WHERE o.idCedula = :idCedula"),
    @NamedQuery(name = "Operaciones.findByOperador1", query = "SELECT o FROM Operaciones o WHERE o.operador1 = :operador1"),
    @NamedQuery(name = "Operaciones.findByOperador2", query = "SELECT o FROM Operaciones o WHERE o.operador2 = :operador2"),
    @NamedQuery(name = "Operaciones.findByResultado", query = "SELECT o FROM Operaciones o WHERE o.resultado = :resultado")})
public class Operaciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_operaciones")
    private Integer idOperaciones;
    @Basic(optional = false)
    @Column(name = "id_cedula")
    private int idCedula;
    @Basic(optional = false)
    @Column(name = "operador1")
    private String operador1;
    @Basic(optional = false)
    @Column(name = "operador2")
    private String operador2;
    @Basic(optional = false)
    @Column(name = "resultado")
    private String resultado;
    @JoinColumn(name = "id_sistema", referencedColumnName = "idSistema")
    @ManyToOne(optional = false)
    private Sistema idSistema;
    @JoinColumn(name = "id_tipo_operacion", referencedColumnName = "idTipo_registro")
    @ManyToOne(optional = false)
    private TipoRegistro idTipoOperacion;
    @JoinColumn(name = "cedula_id", referencedColumnName = "cedula")
    @ManyToOne(optional = false)
    private Usuario cedulaId;

    public Operaciones() {
    }

    public Operaciones(Integer idOperaciones) {
        this.idOperaciones = idOperaciones;
    }

    public Operaciones(Integer idOperaciones, int idCedula, String operador1, String operador2, String resultado) {
        this.idOperaciones = idOperaciones;
        this.idCedula = idCedula;
        this.operador1 = operador1;
        this.operador2 = operador2;
        this.resultado = resultado;
    }

    public Integer getIdOperaciones() {
        return idOperaciones;
    }

    public void setIdOperaciones(Integer idOperaciones) {
        this.idOperaciones = idOperaciones;
    }

    public int getIdCedula() {
        return idCedula;
    }

    public void setIdCedula(int idCedula) {
        this.idCedula = idCedula;
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

    public Sistema getIdSistema() {
        return idSistema;
    }

    public void setIdSistema(Sistema idSistema) {
        this.idSistema = idSistema;
    }

    public TipoRegistro getIdTipoOperacion() {
        return idTipoOperacion;
    }

    public void setIdTipoOperacion(TipoRegistro idTipoOperacion) {
        this.idTipoOperacion = idTipoOperacion;
    }

    public Usuario getCedulaId() {
        return cedulaId;
    }

    public void setCedulaId(Usuario cedulaId) {
        this.cedulaId = cedulaId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOperaciones != null ? idOperaciones.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Operaciones)) {
            return false;
        }
        Operaciones other = (Operaciones) object;
        if ((this.idOperaciones == null && other.idOperaciones != null) || (this.idOperaciones != null && !this.idOperaciones.equals(other.idOperaciones))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.model.Operaciones[ idOperaciones=" + idOperaciones + " ]";
    }
    
}
