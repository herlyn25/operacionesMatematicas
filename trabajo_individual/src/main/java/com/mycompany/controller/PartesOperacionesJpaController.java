/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;

import com.mycompany.controller.exceptions.NonexistentEntityException;
import com.mycompany.model.PartesOperaciones;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Herly Castillo C
 */
public class PartesOperacionesJpaController implements Serializable {

    public PartesOperacionesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PartesOperaciones partesOperaciones) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(partesOperaciones);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PartesOperaciones partesOperaciones) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            partesOperaciones = em.merge(partesOperaciones);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = partesOperaciones.getIdParteOperacion();
                if (findPartesOperaciones(id) == null) {
                    throw new NonexistentEntityException("The partesOperaciones with id " + id + " no longer exists.");
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
            PartesOperaciones partesOperaciones;
            try {
                partesOperaciones = em.getReference(PartesOperaciones.class, id);
                partesOperaciones.getIdParteOperacion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The partesOperaciones with id " + id + " no longer exists.", enfe);
            }
            em.remove(partesOperaciones);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PartesOperaciones> findPartesOperacionesEntities() {
        return findPartesOperacionesEntities(true, -1, -1);
    }

    public List<PartesOperaciones> findPartesOperacionesEntities(int maxResults, int firstResult) {
        return findPartesOperacionesEntities(false, maxResults, firstResult);
    }

    private List<PartesOperaciones> findPartesOperacionesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PartesOperaciones.class));
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

    public PartesOperaciones findPartesOperaciones(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PartesOperaciones.class, id);
        } finally {
            em.close();
        }
    }

    public int getPartesOperacionesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PartesOperaciones> rt = cq.from(PartesOperaciones.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
