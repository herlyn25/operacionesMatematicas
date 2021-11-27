package com.mycompany.controller;

import com.mycompany.controller.exceptions.IllegalOrphanException;
import com.mycompany.controller.exceptions.NonexistentEntityException;
import com.mycompany.controller.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.*;
import com.mycompany.model.Operaciones;
import java.util.ArrayList;
import java.util.Collection;
import com.mycompany.model.Registro;
import com.mycompany.model.Usuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class UsuarioJpaController implements Serializable {

    public UsuarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuario usuario) throws PreexistingEntityException, Exception {
        if (usuario.getOperacionesCollection() == null) {
            usuario.setOperacionesCollection(new ArrayList<Operaciones>());
        }
        if (usuario.getRegistroCollection() == null) {
            usuario.setRegistroCollection(new ArrayList<Registro>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Operaciones> attachedOperacionesCollection = new ArrayList<Operaciones>();
            for (Operaciones operacionesCollectionOperacionesToAttach : usuario.getOperacionesCollection()) {
                operacionesCollectionOperacionesToAttach = em.getReference(operacionesCollectionOperacionesToAttach.getClass(), operacionesCollectionOperacionesToAttach.getIdOperaciones());
                attachedOperacionesCollection.add(operacionesCollectionOperacionesToAttach);
            }
            usuario.setOperacionesCollection(attachedOperacionesCollection);
            Collection<Registro> attachedRegistroCollection = new ArrayList<Registro>();
            for (Registro registroCollectionRegistroToAttach : usuario.getRegistroCollection()) {
                registroCollectionRegistroToAttach = em.getReference(registroCollectionRegistroToAttach.getClass(), registroCollectionRegistroToAttach.getIdRegistro());
                attachedRegistroCollection.add(registroCollectionRegistroToAttach);
            }
            usuario.setRegistroCollection(attachedRegistroCollection);
            em.persist(usuario);
            for (Operaciones operacionesCollectionOperaciones : usuario.getOperacionesCollection()) {
                Usuario oldCedulaIdOfOperacionesCollectionOperaciones = operacionesCollectionOperaciones.getCedulaId();
                operacionesCollectionOperaciones.setCedulaId(usuario);
                operacionesCollectionOperaciones = em.merge(operacionesCollectionOperaciones);
                if (oldCedulaIdOfOperacionesCollectionOperaciones != null) {
                    oldCedulaIdOfOperacionesCollectionOperaciones.getOperacionesCollection().remove(operacionesCollectionOperaciones);
                    oldCedulaIdOfOperacionesCollectionOperaciones = em.merge(oldCedulaIdOfOperacionesCollectionOperaciones);
                }
            }
            for (Registro registroCollectionRegistro : usuario.getRegistroCollection()) {
                Usuario oldCedulaUsuarioOfRegistroCollectionRegistro = registroCollectionRegistro.getCedulaUsuario();
                registroCollectionRegistro.setCedulaUsuario(usuario);
                registroCollectionRegistro = em.merge(registroCollectionRegistro);
                if (oldCedulaUsuarioOfRegistroCollectionRegistro != null) {
                    oldCedulaUsuarioOfRegistroCollectionRegistro.getRegistroCollection().remove(registroCollectionRegistro);
                    oldCedulaUsuarioOfRegistroCollectionRegistro = em.merge(oldCedulaUsuarioOfRegistroCollectionRegistro);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findUsuario(usuario.getCedula()) != null) {
                throw new PreexistingEntityException("Usuario " + usuario + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuario usuario) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario persistentUsuario = em.find(Usuario.class, usuario.getCedula());
            Collection<Operaciones> operacionesCollectionOld = persistentUsuario.getOperacionesCollection();
            Collection<Operaciones> operacionesCollectionNew = usuario.getOperacionesCollection();
            Collection<Registro> registroCollectionOld = persistentUsuario.getRegistroCollection();
            Collection<Registro> registroCollectionNew = usuario.getRegistroCollection();
            List<String> illegalOrphanMessages = null;
            for (Operaciones operacionesCollectionOldOperaciones : operacionesCollectionOld) {
                if (!operacionesCollectionNew.contains(operacionesCollectionOldOperaciones)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Operaciones " + operacionesCollectionOldOperaciones + " since its cedulaId field is not nullable.");
                }
            }
            for (Registro registroCollectionOldRegistro : registroCollectionOld) {
                if (!registroCollectionNew.contains(registroCollectionOldRegistro)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Registro " + registroCollectionOldRegistro + " since its cedulaUsuario field is not nullable.");
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
            usuario.setOperacionesCollection(operacionesCollectionNew);
            Collection<Registro> attachedRegistroCollectionNew = new ArrayList<Registro>();
            for (Registro registroCollectionNewRegistroToAttach : registroCollectionNew) {
                registroCollectionNewRegistroToAttach = em.getReference(registroCollectionNewRegistroToAttach.getClass(), registroCollectionNewRegistroToAttach.getIdRegistro());
                attachedRegistroCollectionNew.add(registroCollectionNewRegistroToAttach);
            }
            registroCollectionNew = attachedRegistroCollectionNew;
            usuario.setRegistroCollection(registroCollectionNew);
            usuario = em.merge(usuario);
            for (Operaciones operacionesCollectionNewOperaciones : operacionesCollectionNew) {
                if (!operacionesCollectionOld.contains(operacionesCollectionNewOperaciones)) {
                    Usuario oldCedulaIdOfOperacionesCollectionNewOperaciones = operacionesCollectionNewOperaciones.getCedulaId();
                    operacionesCollectionNewOperaciones.setCedulaId(usuario);
                    operacionesCollectionNewOperaciones = em.merge(operacionesCollectionNewOperaciones);
                    if (oldCedulaIdOfOperacionesCollectionNewOperaciones != null && !oldCedulaIdOfOperacionesCollectionNewOperaciones.equals(usuario)) {
                        oldCedulaIdOfOperacionesCollectionNewOperaciones.getOperacionesCollection().remove(operacionesCollectionNewOperaciones);
                        oldCedulaIdOfOperacionesCollectionNewOperaciones = em.merge(oldCedulaIdOfOperacionesCollectionNewOperaciones);
                    }
                }
            }
            for (Registro registroCollectionNewRegistro : registroCollectionNew) {
                if (!registroCollectionOld.contains(registroCollectionNewRegistro)) {
                    Usuario oldCedulaUsuarioOfRegistroCollectionNewRegistro = registroCollectionNewRegistro.getCedulaUsuario();
                    registroCollectionNewRegistro.setCedulaUsuario(usuario);
                    registroCollectionNewRegistro = em.merge(registroCollectionNewRegistro);
                    if (oldCedulaUsuarioOfRegistroCollectionNewRegistro != null && !oldCedulaUsuarioOfRegistroCollectionNewRegistro.equals(usuario)) {
                        oldCedulaUsuarioOfRegistroCollectionNewRegistro.getRegistroCollection().remove(registroCollectionNewRegistro);
                        oldCedulaUsuarioOfRegistroCollectionNewRegistro = em.merge(oldCedulaUsuarioOfRegistroCollectionNewRegistro);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = usuario.getCedula();
                if (findUsuario(id) == null) {
                    throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.");
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
            Usuario usuario;
            try {
                usuario = em.getReference(Usuario.class, id);
                usuario.getCedula();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Operaciones> operacionesCollectionOrphanCheck = usuario.getOperacionesCollection();
            for (Operaciones operacionesCollectionOrphanCheckOperaciones : operacionesCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Operaciones " + operacionesCollectionOrphanCheckOperaciones + " in its operacionesCollection field has a non-nullable cedulaId field.");
            }
            Collection<Registro> registroCollectionOrphanCheck = usuario.getRegistroCollection();
            for (Registro registroCollectionOrphanCheckRegistro : registroCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Registro " + registroCollectionOrphanCheckRegistro + " in its registroCollection field has a non-nullable cedulaUsuario field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(usuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usuario> findUsuarioEntities() {
        return findUsuarioEntities(true, -1, -1);
    }

    public List<Usuario> findUsuarioEntities(int maxResults, int firstResult) {
        return findUsuarioEntities(false, maxResults, firstResult);
    }

    private List<Usuario> findUsuarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuario.class));
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

    public Usuario findUsuario(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuario> rt = cq.from(Usuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    public List<Usuario> validarLoginByNick(String usuario,String clave) {       
      EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuario.class));
            CriteriaBuilder cb = em.getCriteriaBuilder();
            Root usuarioRoot = cq.from(Usuario.class);
            Predicate predicateUsuario = cb.equal(usuarioRoot.get("nickname"), usuario);
            Predicate predicateClave = cb.equal(usuarioRoot.get("password"), clave);
            Predicate predicate = cb.or(predicateUsuario, predicateClave);
            cq.where(predicate);
            Query q = em.createQuery(cq);
            return q.getResultList();
        } finally {
            em.close();
        }
    }   
}
