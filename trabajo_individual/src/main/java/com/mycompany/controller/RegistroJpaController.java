/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;

import com.mycompany.controller.exceptions.NonexistentEntityException;
import com.mycompany.model.Registro;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.mycompany.model.TipoRegistro;
import com.mycompany.model.Usuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Herly Castillo C
 */
public class RegistroJpaController implements Serializable {

    public RegistroJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Registro registro) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoRegistro idTipoRegistro = registro.getIdTipoRegistro();
            if (idTipoRegistro != null) {
                idTipoRegistro = em.getReference(idTipoRegistro.getClass(), idTipoRegistro.getIdTiporegistro());
                registro.setIdTipoRegistro(idTipoRegistro);
            }
            Usuario cedulaUsuario = registro.getCedulaUsuario();
            if (cedulaUsuario != null) {
                cedulaUsuario = em.getReference(cedulaUsuario.getClass(), cedulaUsuario.getCedula());
                registro.setCedulaUsuario(cedulaUsuario);
            }
            em.persist(registro);
            if (idTipoRegistro != null) {
                idTipoRegistro.getRegistroCollection().add(registro);
                idTipoRegistro = em.merge(idTipoRegistro);
            }
            if (cedulaUsuario != null) {
                cedulaUsuario.getRegistroCollection().add(registro);
                cedulaUsuario = em.merge(cedulaUsuario);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Registro registro) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Registro persistentRegistro = em.find(Registro.class, registro.getIdRegistro());
            TipoRegistro idTipoRegistroOld = persistentRegistro.getIdTipoRegistro();
            TipoRegistro idTipoRegistroNew = registro.getIdTipoRegistro();
            Usuario cedulaUsuarioOld = persistentRegistro.getCedulaUsuario();
            Usuario cedulaUsuarioNew = registro.getCedulaUsuario();
            if (idTipoRegistroNew != null) {
                idTipoRegistroNew = em.getReference(idTipoRegistroNew.getClass(), idTipoRegistroNew.getIdTiporegistro());
                registro.setIdTipoRegistro(idTipoRegistroNew);
            }
            if (cedulaUsuarioNew != null) {
                cedulaUsuarioNew = em.getReference(cedulaUsuarioNew.getClass(), cedulaUsuarioNew.getCedula());
                registro.setCedulaUsuario(cedulaUsuarioNew);
            }
            registro = em.merge(registro);
            if (idTipoRegistroOld != null && !idTipoRegistroOld.equals(idTipoRegistroNew)) {
                idTipoRegistroOld.getRegistroCollection().remove(registro);
                idTipoRegistroOld = em.merge(idTipoRegistroOld);
            }
            if (idTipoRegistroNew != null && !idTipoRegistroNew.equals(idTipoRegistroOld)) {
                idTipoRegistroNew.getRegistroCollection().add(registro);
                idTipoRegistroNew = em.merge(idTipoRegistroNew);
            }
            if (cedulaUsuarioOld != null && !cedulaUsuarioOld.equals(cedulaUsuarioNew)) {
                cedulaUsuarioOld.getRegistroCollection().remove(registro);
                cedulaUsuarioOld = em.merge(cedulaUsuarioOld);
            }
            if (cedulaUsuarioNew != null && !cedulaUsuarioNew.equals(cedulaUsuarioOld)) {
                cedulaUsuarioNew.getRegistroCollection().add(registro);
                cedulaUsuarioNew = em.merge(cedulaUsuarioNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = registro.getIdRegistro();
                if (findRegistro(id) == null) {
                    throw new NonexistentEntityException("The registro with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Registro registro;
            try {
                registro = em.getReference(Registro.class, id);
                registro.getIdRegistro();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The registro with id " + id + " no longer exists.", enfe);
            }
            TipoRegistro idTipoRegistro = registro.getIdTipoRegistro();
            if (idTipoRegistro != null) {
                idTipoRegistro.getRegistroCollection().remove(registro);
                idTipoRegistro = em.merge(idTipoRegistro);
            }
            Usuario cedulaUsuario = registro.getCedulaUsuario();
            if (cedulaUsuario != null) {
                cedulaUsuario.getRegistroCollection().remove(registro);
                cedulaUsuario = em.merge(cedulaUsuario);
            }
            em.remove(registro);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Registro> findRegistroEntities() {
        return findRegistroEntities(true, -1, -1);
    }

    public List<Registro> findRegistroEntities(int maxResults, int firstResult) {
        return findRegistroEntities(false, maxResults, firstResult);
    }

    private List<Registro> findRegistroEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Registro.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Registro findRegistro(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Registro.class, id);
        } finally {
            em.close();
        }
    }

    public int getRegistroCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Registro> rt = cq.from(Registro.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
