/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;

import com.mycompany.controller.exceptions.IllegalOrphanException;
import com.mycompany.controller.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.mycompany.model.Operaciones;
import com.mycompany.model.Sistema;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Herly Castillo C
 */
public class SistemaJpaController implements Serializable {

    public SistemaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Sistema sistema) {
        if (sistema.getOperacionesCollection() == null) {
            sistema.setOperacionesCollection(new ArrayList<Operaciones>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Operaciones> attachedOperacionesCollection = new ArrayList<Operaciones>();
            for (Operaciones operacionesCollectionOperacionesToAttach : sistema.getOperacionesCollection()) {
                operacionesCollectionOperacionesToAttach = em.getReference(operacionesCollectionOperacionesToAttach.getClass(), operacionesCollectionOperacionesToAttach.getIdOperaciones());
                attachedOperacionesCollection.add(operacionesCollectionOperacionesToAttach);
            }
            sistema.setOperacionesCollection(attachedOperacionesCollection);
            em.persist(sistema);
            for (Operaciones operacionesCollectionOperaciones : sistema.getOperacionesCollection()) {
                Sistema oldIdSistemaOfOperacionesCollectionOperaciones = operacionesCollectionOperaciones.getIdSistema();
                operacionesCollectionOperaciones.setIdSistema(sistema);
                operacionesCollectionOperaciones = em.merge(operacionesCollectionOperaciones);
                if (oldIdSistemaOfOperacionesCollectionOperaciones != null) {
                    oldIdSistemaOfOperacionesCollectionOperaciones.getOperacionesCollection().remove(operacionesCollectionOperaciones);
                    oldIdSistemaOfOperacionesCollectionOperaciones = em.merge(oldIdSistemaOfOperacionesCollectionOperaciones);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Sistema sistema) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Sistema persistentSistema = em.find(Sistema.class, sistema.getIdSistema());
            Collection<Operaciones> operacionesCollectionOld = persistentSistema.getOperacionesCollection();
            Collection<Operaciones> operacionesCollectionNew = sistema.getOperacionesCollection();
            List<String> illegalOrphanMessages = null;
            for (Operaciones operacionesCollectionOldOperaciones : operacionesCollectionOld) {
                if (!operacionesCollectionNew.contains(operacionesCollectionOldOperaciones)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Operaciones " + operacionesCollectionOldOperaciones + " since its idSistema field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Operaciones> attachedOperacionesCollectionNew = new ArrayList<Operaciones>();
            for (Operaciones operacionesCollectionNewOperacionesToAttach : operacionesCollectionNew) {
                operacionesCollectionNewOperacionesToAttach = em.getReference(operacionesCollectionNewOperacionesToAttach.getClass(), operacionesCollectionNewOperacionesToAttach.getIdOperaciones());
                attachedOperacionesCollectionNew.add(operacionesCollectionNewOperacionesToAttach);
            }
            operacionesCollectionNew = attachedOperacionesCollectionNew;
            sistema.setOperacionesCollection(operacionesCollectionNew);
            sistema = em.merge(sistema);
            for (Operaciones operacionesCollectionNewOperaciones : operacionesCollectionNew) {
                if (!operacionesCollectionOld.contains(operacionesCollectionNewOperaciones)) {
                    Sistema oldIdSistemaOfOperacionesCollectionNewOperaciones = operacionesCollectionNewOperaciones.getIdSistema();
                    operacionesCollectionNewOperaciones.setIdSistema(sistema);
                    operacionesCollectionNewOperaciones = em.merge(operacionesCollectionNewOperaciones);
                    if (oldIdSistemaOfOperacionesCollectionNewOperaciones != null && !oldIdSistemaOfOperacionesCollectionNewOperaciones.equals(sistema)) {
                        oldIdSistemaOfOperacionesCollectionNewOperaciones.getOperacionesCollection().remove(operacionesCollectionNewOperaciones);
                        oldIdSistemaOfOperacionesCollectionNewOperaciones = em.merge(oldIdSistemaOfOperacionesCollectionNewOperaciones);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = sistema.getIdSistema();
                if (findSistema(id) == null) {
                    throw new NonexistentEntityException("The sistema with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Sistema sistema;
            try {
                sistema = em.getReference(Sistema.class, id);
                sistema.getIdSistema();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The sistema with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Operaciones> operacionesCollectionOrphanCheck = sistema.getOperacionesCollection();
            for (Operaciones operacionesCollectionOrphanCheckOperaciones : operacionesCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Sistema (" + sistema + ") cannot be destroyed since the Operaciones " + operacionesCollectionOrphanCheckOperaciones + " in its operacionesCollection field has a non-nullable idSistema field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(sistema);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Sistema> findSistemaEntities() {
        return findSistemaEntities(true, -1, -1);
    }

    public List<Sistema> findSistemaEntities(int maxResults, int firstResult) {
        return findSistemaEntities(false, maxResults, firstResult);
    }

    private List<Sistema> findSistemaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Sistema.class));
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

    public Sistema findSistema(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Sistema.class, id);
        } finally {
            em.close();
        }
    }

    public int getSistemaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Sistema> rt = cq.from(Sistema.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
