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
import java.util.ArrayList;
import java.util.Collection;
import com.mycompany.model.Registro;
import com.mycompany.model.TipoRegistro;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Herly Castillo C
 */
public class TipoRegistroJpaController implements Serializable {

    public TipoRegistroJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoRegistro tipoRegistro) {
        if (tipoRegistro.getOperacionesCollection() == null) {
            tipoRegistro.setOperacionesCollection(new ArrayList<Operaciones>());
        }
        if (tipoRegistro.getRegistroCollection() == null) {
            tipoRegistro.setRegistroCollection(new ArrayList<Registro>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Operaciones> attachedOperacionesCollection = new ArrayList<Operaciones>();
            for (Operaciones operacionesCollectionOperacionesToAttach : tipoRegistro.getOperacionesCollection()) {
                operacionesCollectionOperacionesToAttach = em.getReference(operacionesCollectionOperacionesToAttach.getClass(), operacionesCollectionOperacionesToAttach.getIdOperaciones());
                attachedOperacionesCollection.add(operacionesCollectionOperacionesToAttach);
            }
            tipoRegistro.setOperacionesCollection(attachedOperacionesCollection);
            Collection<Registro> attachedRegistroCollection = new ArrayList<Registro>();
            for (Registro registroCollectionRegistroToAttach : tipoRegistro.getRegistroCollection()) {
                registroCollectionRegistroToAttach = em.getReference(registroCollectionRegistroToAttach.getClass(), registroCollectionRegistroToAttach.getIdRegistro());
                attachedRegistroCollection.add(registroCollectionRegistroToAttach);
            }
            tipoRegistro.setRegistroCollection(attachedRegistroCollection);
            em.persist(tipoRegistro);
            for (Operaciones operacionesCollectionOperaciones : tipoRegistro.getOperacionesCollection()) {
                TipoRegistro oldIdTipoOperacionOfOperacionesCollectionOperaciones = operacionesCollectionOperaciones.getIdTipoOperacion();
                operacionesCollectionOperaciones.setIdTipoOperacion(tipoRegistro);
                operacionesCollectionOperaciones = em.merge(operacionesCollectionOperaciones);
                if (oldIdTipoOperacionOfOperacionesCollectionOperaciones != null) {
                    oldIdTipoOperacionOfOperacionesCollectionOperaciones.getOperacionesCollection().remove(operacionesCollectionOperaciones);
                    oldIdTipoOperacionOfOperacionesCollectionOperaciones = em.merge(oldIdTipoOperacionOfOperacionesCollectionOperaciones);
                }
            }
            for (Registro registroCollectionRegistro : tipoRegistro.getRegistroCollection()) {
                TipoRegistro oldIdTipoRegistroOfRegistroCollectionRegistro = registroCollectionRegistro.getIdTipoRegistro();
                registroCollectionRegistro.setIdTipoRegistro(tipoRegistro);
                registroCollectionRegistro = em.merge(registroCollectionRegistro);
                if (oldIdTipoRegistroOfRegistroCollectionRegistro != null) {
                    oldIdTipoRegistroOfRegistroCollectionRegistro.getRegistroCollection().remove(registroCollectionRegistro);
                    oldIdTipoRegistroOfRegistroCollectionRegistro = em.merge(oldIdTipoRegistroOfRegistroCollectionRegistro);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoRegistro tipoRegistro) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoRegistro persistentTipoRegistro = em.find(TipoRegistro.class, tipoRegistro.getIdTiporegistro());
            Collection<Operaciones> operacionesCollectionOld = persistentTipoRegistro.getOperacionesCollection();
            Collection<Operaciones> operacionesCollectionNew = tipoRegistro.getOperacionesCollection();
            Collection<Registro> registroCollectionOld = persistentTipoRegistro.getRegistroCollection();
            Collection<Registro> registroCollectionNew = tipoRegistro.getRegistroCollection();
            List<String> illegalOrphanMessages = null;
            for (Operaciones operacionesCollectionOldOperaciones : operacionesCollectionOld) {
                if (!operacionesCollectionNew.contains(operacionesCollectionOldOperaciones)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Operaciones " + operacionesCollectionOldOperaciones + " since its idTipoOperacion field is not nullable.");
                }
            }
            for (Registro registroCollectionOldRegistro : registroCollectionOld) {
                if (!registroCollectionNew.contains(registroCollectionOldRegistro)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Registro " + registroCollectionOldRegistro + " since its idTipoRegistro field is not nullable.");
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
            tipoRegistro.setOperacionesCollection(operacionesCollectionNew);
            Collection<Registro> attachedRegistroCollectionNew = new ArrayList<Registro>();
            for (Registro registroCollectionNewRegistroToAttach : registroCollectionNew) {
                registroCollectionNewRegistroToAttach = em.getReference(registroCollectionNewRegistroToAttach.getClass(), registroCollectionNewRegistroToAttach.getIdRegistro());
                attachedRegistroCollectionNew.add(registroCollectionNewRegistroToAttach);
            }
            registroCollectionNew = attachedRegistroCollectionNew;
            tipoRegistro.setRegistroCollection(registroCollectionNew);
            tipoRegistro = em.merge(tipoRegistro);
            for (Operaciones operacionesCollectionNewOperaciones : operacionesCollectionNew) {
                if (!operacionesCollectionOld.contains(operacionesCollectionNewOperaciones)) {
                    TipoRegistro oldIdTipoOperacionOfOperacionesCollectionNewOperaciones = operacionesCollectionNewOperaciones.getIdTipoOperacion();
                    operacionesCollectionNewOperaciones.setIdTipoOperacion(tipoRegistro);
                    operacionesCollectionNewOperaciones = em.merge(operacionesCollectionNewOperaciones);
                    if (oldIdTipoOperacionOfOperacionesCollectionNewOperaciones != null && !oldIdTipoOperacionOfOperacionesCollectionNewOperaciones.equals(tipoRegistro)) {
                        oldIdTipoOperacionOfOperacionesCollectionNewOperaciones.getOperacionesCollection().remove(operacionesCollectionNewOperaciones);
                        oldIdTipoOperacionOfOperacionesCollectionNewOperaciones = em.merge(oldIdTipoOperacionOfOperacionesCollectionNewOperaciones);
                    }
                }
            }
            for (Registro registroCollectionNewRegistro : registroCollectionNew) {
                if (!registroCollectionOld.contains(registroCollectionNewRegistro)) {
                    TipoRegistro oldIdTipoRegistroOfRegistroCollectionNewRegistro = registroCollectionNewRegistro.getIdTipoRegistro();
                    registroCollectionNewRegistro.setIdTipoRegistro(tipoRegistro);
                    registroCollectionNewRegistro = em.merge(registroCollectionNewRegistro);
                    if (oldIdTipoRegistroOfRegistroCollectionNewRegistro != null && !oldIdTipoRegistroOfRegistroCollectionNewRegistro.equals(tipoRegistro)) {
                        oldIdTipoRegistroOfRegistroCollectionNewRegistro.getRegistroCollection().remove(registroCollectionNewRegistro);
                        oldIdTipoRegistroOfRegistroCollectionNewRegistro = em.merge(oldIdTipoRegistroOfRegistroCollectionNewRegistro);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tipoRegistro.getIdTiporegistro();
                if (findTipoRegistro(id) == null) {
                    throw new NonexistentEntityException("The tipoRegistro with id " + id + " no longer exists.");
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
            TipoRegistro tipoRegistro;
            try {
                tipoRegistro = em.getReference(TipoRegistro.class, id);
                tipoRegistro.getIdTiporegistro();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoRegistro with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Operaciones> operacionesCollectionOrphanCheck = tipoRegistro.getOperacionesCollection();
            for (Operaciones operacionesCollectionOrphanCheckOperaciones : operacionesCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This TipoRegistro (" + tipoRegistro + ") cannot be destroyed since the Operaciones " + operacionesCollectionOrphanCheckOperaciones + " in its operacionesCollection field has a non-nullable idTipoOperacion field.");
            }
            Collection<Registro> registroCollectionOrphanCheck = tipoRegistro.getRegistroCollection();
            for (Registro registroCollectionOrphanCheckRegistro : registroCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This TipoRegistro (" + tipoRegistro + ") cannot be destroyed since the Registro " + registroCollectionOrphanCheckRegistro + " in its registroCollection field has a non-nullable idTipoRegistro field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(tipoRegistro);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoRegistro> findTipoRegistroEntities() {
        return findTipoRegistroEntities(true, -1, -1);
    }

    public List<TipoRegistro> findTipoRegistroEntities(int maxResults, int firstResult) {
        return findTipoRegistroEntities(false, maxResults, firstResult);
    }

    private List<TipoRegistro> findTipoRegistroEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TipoRegistro.class));
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

    public TipoRegistro findTipoRegistro(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoRegistro.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoRegistroCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TipoRegistro> rt = cq.from(TipoRegistro.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
