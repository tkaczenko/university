package common;

import util.EntityManagerUtil;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

/**
 * Created by tkaczenko on 02.12.16.
 */
public abstract class JpaDAOImp<E extends Serializable> implements DAO<E> {
    private Class<E> persistentClass;
    protected EntityManager entityManager;

    public JpaDAOImp() {
        this.entityManager = EntityManagerUtil.getEntityManagerFactory().createEntityManager();
    }

    protected final void setPersistentClass(Class<E> persistentClass) {
        this.persistentClass = persistentClass;
    }

    @Override
    public E find(Long id) {
        return entityManager.find(persistentClass, id);
    }

    @Override
    public void persist(E entity) {
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
    }

    @Override
    public E merge(E entity) {
        return entityManager.merge(entity);
    }

    @Override
    public void delete(E entity) {
        entityManager.getTransaction().begin();
        entityManager.remove(entity);
        entityManager.getTransaction().commit();
    }

    @Override
    public List<E> findAll() {
        return entityManager.createNamedQuery(persistentClass.getSimpleName() + ".getAll", persistentClass)
                .getResultList();
    }

    @Override
    public void refresh(E entity) {
        entityManager.refresh(entity);
    }
}