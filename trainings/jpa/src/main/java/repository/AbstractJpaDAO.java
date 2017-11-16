package repository;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

/**
 * Created by tkaczenko on 09.11.16.
 */
public abstract class AbstractJpaDAO<T extends Serializable> {
    private Class<T> clazz;
    protected EntityManager entityManager;

    public AbstractJpaDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    protected final void setClazz(Class<T> clazzToSet) {
        this.clazz = clazzToSet;
    }

    public T findOne(int id) {
        return entityManager.find(clazz, id);
    }

    public T findOne(String id) {
        return entityManager.find(clazz, id);
    }

    public List<T> findAll() {
        return entityManager.createNamedQuery(clazz.getSimpleName() + ".getAll", clazz)
                .getResultList();
    }

    public void create(T entity) {
        entityManager.persist(entity);
    }

    public T update(T entity) {
        return entityManager.merge(entity);
    }

    public void refresh(T entity) {
        entityManager.refresh(entity);
    }

    public void delete(T entity) {
        entityManager.remove(entity);
    }

    public void deleteById(int entityId) {
        T entity = findOne(entityId);
        delete(entity);
    }
}
