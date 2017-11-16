package common;

import java.util.List;

/**
 * Created by tkaczenko on 03.12.16.
 */
public interface DAO<E> {
    E find(Long id);

    void persist(E entity);

    E merge(E entity);

    void delete(E entity);

    void refresh(E entity);

    List<E> findAll();
}
