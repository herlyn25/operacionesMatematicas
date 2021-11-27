/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;

import com.mycompany.controller.exceptions.NonexistentEntityException;
import com.mycompany.model.Operaciones;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.mycompany.model.Sistema;
import com.mycompany.model.TipoRegistro;
import com.mycompany.model.Usuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Herly Castillo C
 */
public class OperacionesJpaController implements Serializable {

    public OperacionesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Operaciones operaciones) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Sistema idSistema = operaciones.getIdSistema();
            if (idSistema != null) {
                idSistema = em.getReference(idSistema.getClass(), idSistema.getIdSistema());
                operaciones.setIdSistema(idSistema);
            }
            TipoRegistro idTipoOperacion = operaciones.getIdTipoOperacion();
            if (idTipoOperacion != null) {
                idTipoOperacion = em.getReference(idTipoOperacion.getClass(), idTipoOperacion.getIdTiporegistro());
                operaciones.setIdTipoOperacion(idTipoOperacion);
            }
            Usuario cedulaId = operaciones.getCedulaId();
            if (cedulaId != null) {
                cedulaId = em.getReference(cedulaId.getClass(), cedulaId.getCedula());
                operaciones.setCedulaId(cedulaId);
            }
            em.persist(operaciones);
            if (idSistema != null) {
                idSistema.getOperacionesCollection().add(operaciones);
                idSistema = em.merge(idSistema);
            }
            if (idTipoOperacion != null) {
                idTipoOperacion.getOperacionesCollection().add(operaciones);
                idTipoOperacion = em.merge(idTipoOperacion);
            }
            if (cedulaId != null) {
                cedulaId.getOperacionesCollection().add(operaciones);
                cedulaId = em.merge(cedulaId);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Operaciones operaciones) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Operaciones persistentOperaciones = em.find(Operaciones.class, operaciones.getIdOperaciones());
            Sistema idSistemaOld = persistentOperaciones.getIdSistema();
            Sistema idSistemaNew = operaciones.getIdSistema();
            TipoRegistro idTipoOperacionOld = persistentOperaciones.getIdTipoOperacion();
            TipoRegistro idTipoOperacionNew = operaciones.getIdTipoOperacion();
            Usuario cedulaIdOld = persistentOperaciones.getCedulaId();
            Usuario cedulaIdNew = operaciones.getCedulaId();
            if (idSistemaNew != null) {
                idSistemaNew = em.getReference(idSistemaNew.getClass(), idSistemaNew.getIdSistema());
                operaciones.setIdSistema(idSistemaNew);
            }
            if (idTipoOperacionNew != null) {
                idTipoOperacionNew = em.getReference(idTipoOperacionNew.getClass(), idTipoOperacionNew.getIdTiporegistro());
                operaciones.setIdTipoOperacion(idTipoOperacionNew);
            }
            if (cedulaIdNew != null) {
                cedulaIdNew = em.getReference(cedulaIdNew.getClass(), cedulaIdNew.getCedula());
                operaciones.setCedulaId(cedulaIdNew);
            }
            operaciones = em.merge(operaciones);
            if (idSistemaOld != null && !idSistemaOld.equals(idSistemaNew)) {
                idSistemaOld.getOperacionesCollection().remove(operaciones);
                idSistemaOld = em.merge(idSistemaOld);
            }
            if (idSistemaNew != null && !idSistemaNew.equals(idSistemaOld)) {
                idSistemaNew.getOperacionesCollection().add(operaciones);
                idSistemaNew = em.merge(idSistemaNew);
            }
            if (idTipoOperacionOld != null && !idTipoOperacionOld.equals(idTipoOperacionNew)) {
                idTipoOperacionOld.getOperacionesCollection().remove(operaciones);
                idTipoOperacionOld = em.merge(idTipoOperacionOld);
            }
            if (idTipoOperacionNew != null && !idTipoOperacionNew.equals(idTipoOperacionOld)) {
                idTipoOperacionNew.getOperacionesCollection().add(operaciones);
                idTipoOperacionNew = em.merge(idTipoOperacionNew);
            }
            if (cedulaIdOld != null && !cedulaIdOld.equals(cedulaIdNew)) {
                cedulaIdOld.getOperacionesCollection().remove(operaciones);
                cedulaIdOld = em.merge(cedulaIdOld);
            }
            if (cedulaIdNew != null && !cedulaIdNew.equals(cedulaIdOld)) {
                cedulaIdNew.getOperacionesCollection().add(operaciones);
                cedulaIdNew = em.merge(cedulaIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = operaciones.getIdOperaciones();
                if (findOperaciones(id) == null) {
                    throw new NonexistentEntityException("The operaciones with id " + id + " no longer exists.");
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
            Operaciones operaciones;
            try {
                operaciones = em.getReference(Operaciones.class, id);
                operaciones.getIdOperaciones();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The operaciones with id " + id + " no longer exists.", enfe);
            }
            Sistema idSistema = operaciones.getIdSistema();
            if (idSistema != null) {
                idSistema.getOperacionesCollection().remove(operaciones);
                idSistema = em.merge(idSistema);
            }
            TipoRegistro idTipoOperacion = operaciones.getIdTipoOperacion();
            if (idTipoOperacion != null) {
                idTipoOperacion.getOperacionesCollection().remove(operaciones);
                idTipoOperacion = em.merge(idTipoOperacion);
            }
            Usuario cedulaId = operaciones.getCedulaId();
            if (cedulaId != null) {
                cedulaId.getOperacionesCollection().remove(operaciones);
                cedulaId = em.merge(cedulaId);
            }
            em.remove(operaciones);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Operaciones> findOperacionesEntities() {
        return findOperacionesEntities(true, -1, -1);
    }

    public List<Operaciones> findOperacionesEntities(int maxResults, int firstResult) {
        return findOperacionesEntities(false, maxResults, firstResult);
    }

    private List<Operaciones> findOperacionesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Operaciones.class));
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

    public Operaciones findOperaciones(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Operaciones.class, id);
        } finally {
            em.close();
        }
    }

    public int getOperacionesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Operaciones> rt = cq.from(Operaciones.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
