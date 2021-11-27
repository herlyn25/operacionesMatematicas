/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Herly Castillo C
 */
@Entity
@Table(name = "tipo_registro")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoRegistro.findAll", query = "SELECT t FROM TipoRegistro t"),
    @NamedQuery(name = "TipoRegistro.findByIdTiporegistro", query = "SELECT t FROM TipoRegistro t WHERE t.idTiporegistro = :idTiporegistro"),
    @NamedQuery(name = "TipoRegistro.findByDescripcion", query = "SELECT t FROM TipoRegistro t WHERE t.descripcion = :descripcion")})
public class TipoRegistro implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idTipo_registro")
    private Integer idTiporegistro;
    @Basic(optional = false)
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoOperacion")
    private Collection<Operaciones> operacionesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoRegistro")
    private Collection<Registro> registroCollection;

    public TipoRegistro() {
    }

    public TipoRegistro(Integer idTiporegistro) {
        this.idTiporegistro = idTiporegistro;
    }

    public TipoRegistro(Integer idTiporegistro, String descripcion) {
        this.idTiporegistro = idTiporegistro;
        this.descripcion = descripcion;
    }

    public Integer getIdTiporegistro() {
        return idTiporegistro;
    }

    public void setIdTiporegistro(Integer idTiporegistro) {
        this.idTiporegistro = idTiporegistro;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public Collection<Operaciones> getOperacionesCollection() {
        return operacionesCollection;
    }

    public void setOperacionesCollection(Collection<Operaciones> operacionesCollection) {
        this.operacionesCollection = operacionesCollection;
    }

    @XmlTransient
    public Collection<Registro> getRegistroCollection() {
        return registroCollection;
    }

    public void setRegistroCollection(Collection<Registro> registroCollection) {
        this.registroCollection = registroCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTiporegistro != null ? idTiporegistro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoRegistro)) {
            return false;
        }
        TipoRegistro other = (TipoRegistro) object;
        if ((this.idTiporegistro == null && other.idTiporegistro != null) || (this.idTiporegistro != null && !this.idTiporegistro.equals(other.idTiporegistro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.model.TipoRegistro[ idTiporegistro=" + idTiporegistro + " ]";
    }
    
}
