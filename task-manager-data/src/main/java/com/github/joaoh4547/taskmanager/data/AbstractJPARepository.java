package com.github.joaoh4547.taskmanager.data;

import java.util.Collection;

import org.ehcache.Cache;

import com.github.joaoh4547.taskmanager.cache.EhcacheManager;
import com.github.joaoh4547.taskmanager.db.JpaManager;
import com.github.joaoh4547.taskmanager.utils.ReflectionUtils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

/**
 * AbstractJPARepository is an abstract class that provides basic CRUD
 * operations for entities using the Java Persistence API (JPA). This class
 * implements the Repository interface and provides a set of synchronized
 * methods to interact with the database.
 *
 * @param <T> The type of entity.
 * @param <R> The type of key used to identify the entity.
 */
public abstract class AbstractJPARepository<T, R>
  implements Repository<T, R> {

  private Cache<T, R> cache;

  private EntityManagerFactory entityManagerFactory;

  protected AbstractJPARepository() {
    init();
  }

  void init() {
    entityManagerFactory = configureEntityManagerFactory();
    cache = createCache();
  }

  private Cache<T, R> createCache() {
    return EhcacheManager.getInstance()
        .createCache(getEntityClass().getName(), getEntityClass(),
                     getKeyClass());
  }

  private EntityManagerFactory configureEntityManagerFactory() {
    return JpaManager.getInstance().getEntityManagerFactory();
  }

  private synchronized EntityManager getEntityManager() {
    return getEntityManagerFactory().createEntityManager();
  }

  private EntityManagerFactory getEntityManagerFactory() {
    return entityManagerFactory;
  }

  private Class<T> getEntityClass() {
    return ReflectionUtils.getGenericParamAt(getClass(), 0);
  }

  private Class<R> getKeyClass() {
    return ReflectionUtils.getGenericParamAt(getClass(), 1);
  }

  @Override
  public synchronized T find(R key) {
    EntityManager em = getEntityManager();
    return em.find(getEntityClass(), key);
  }

  @Override
  public synchronized T save(T entity) {
    EntityTransaction t = null;
    try (EntityManager em = getEntityManager()) {
      t = em.getTransaction();
      t.begin();
      T merged = em.merge(entity);
      t.commit();
      return merged;
    } catch (Exception e) {
      if (t != null && t.isActive()) {
        t.rollback();
      }
      throw new RuntimeException(e);
    }
  }

  @Override
  public synchronized void deleteByKey(R key) {
    EntityManager em = getEntityManager();
    T entity = em.find(getEntityClass(), key);
    if (entity != null) {
      EntityTransaction t = em.getTransaction();
      t.begin();
      em.remove(entity);
      t.commit();
    }
  }

  @Override
  public synchronized void delete(T entity) {
    EntityManager em = getEntityManager();
    if (!em.contains(entity)) {
      entity = em.merge(entity);
    }
    EntityTransaction t = em.getTransaction();
    t.begin();
    em.remove(entity);
    t.commit();
  }

  @Override
  public synchronized boolean exists(R key) {
    EntityManager em = getEntityManager();
    T entity = em.find(getEntityClass(), key);
    return entity != null;
  }

  @Override
  public synchronized Collection<T> loadAll() {
    TypedQuery<T> typedQuery = getEntityManager()
        .createQuery("select p from Process p", getEntityClass());
    return typedQuery.getResultList();
  }
}
