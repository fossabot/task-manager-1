package com.github.joaoh4547.taskmanager.data;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;

import org.ehcache.Cache;
import org.ehcache.Cache.Entry;

import com.github.joaoh4547.taskmanager.cache.EhcacheManager;
import com.github.joaoh4547.taskmanager.db.JpaManager;
import com.github.joaoh4547.taskmanager.utils.ReflectionUtils;

import jakarta.persistence.*;

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

  private Cache<R, T> cache;

  private EntityManagerFactory entityManagerFactory;

  protected AbstractJPARepository() {
    init();
  }

  void init() {
    entityManagerFactory = configureEntityManagerFactory();
    cache = createCache();
  }

  private Cache<R, T> createCache() {
    return EhcacheManager.getInstance()
        .createCache(getEntityClass().getName(), getKeyClass(),
                     getEntityClass());
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
    if (cache.containsKey(key)) {
      return cache.get(key);
    }
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
      cache.put(extractKey(entity), merged);
      return merged;
    } catch (Exception e) {
      if (t != null && t.isActive()) {
        t.rollback();
      }
      throw new RuntimeException(e);
    }
  }

  private R extractKey(T entity) {
    Field f = ReflectionUtils.getFieldWithAnnotation(getEntityClass(),
                                                     Id.class);
    if (f != null) {
      f.setAccessible(true);
      try {
        Object key = f.get(entity);
        if (ReflectionUtils.isSubtypeOf(key.getClass(), getKeyClass())) {
          return getKeyClass().cast(key);
        }
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    }
    return null;
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
      cache.remove(key);
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
    cache.remove(extractKey(entity));
  }

  @Override
  public synchronized boolean exists(R key) {
    if (cache.containsKey(key)) {
      return true;
    }
    EntityManager em = getEntityManager();
    T entity = em.find(getEntityClass(), key);
    return entity != null;
  }

  @Override
  public synchronized Collection<T> loadAll() {
    Collection<T> results = new ArrayList<>();
    if (!cache.iterator().hasNext()) {
      TypedQuery<T> typedQuery = getEntityManager()
          .createQuery("select p from Process p", getEntityClass());
      results = typedQuery.getResultList();
    } else {
      for (Entry<R, T> entry : cache) {
        results.add(entry.getValue());
      }
    }
    return results;
  }
}
