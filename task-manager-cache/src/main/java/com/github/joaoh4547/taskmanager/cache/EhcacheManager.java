package com.github.joaoh4547.taskmanager.cache;

import java.net.URI;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.clustered.client.config.ClusteringServiceConfiguration;
import org.ehcache.clustered.client.config.builders.ClusteredResourcePoolBuilder;
import org.ehcache.clustered.client.config.builders.ClusteringServiceConfigurationBuilder;
import org.ehcache.config.CacheConfiguration;
import org.ehcache.config.ResourcePools;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.MemoryUnit;

import com.github.joaoh4547.taskmanager.config.ApplicationContext;

public class EhcacheManager {

  private static EhcacheManager instance;

  private CacheManager cacheManager;

  public static EhcacheManager getInstance() {
    if (instance == null) {
      instance = new EhcacheManager();
    }
    return instance;
  }

  public CacheManager create() {
    cacheManager = CacheManagerBuilder.newCacheManagerBuilder()
        .with(getConfig()).build(true);
    return cacheManager;
  }

  public void init() {
    cacheManager = create();
  }

  private ClusteringServiceConfiguration getConfig() {
    return ClusteringServiceConfigurationBuilder.cluster(createClusterURI())
        .autoCreateOnReconnect(c -> c).build();
  }

  private URI createClusterURI() {
    return URI.create(getTerracottaUrl());
  }

  private String getTerracottaUrl() {
    return ApplicationContext.getTerracotaUrl();
  }

  public <T, K> Cache<T, K> createCache(String cacheName, Class<T> keyType,
                                        Class<K> valueType) {
    CacheManager manager = cacheManager;
    if (manager == null) {
      init();
      manager = cacheManager;
    }

    return manager.createCache(cacheName,
                               createCacheConfig(keyType, valueType));
  }

  private <T, K> CacheConfiguration<T, K> createCacheConfig(Class<T> keyType,
                                                            Class<K> valueType) {
    return CacheConfigurationBuilder
        .newCacheConfigurationBuilder(keyType, valueType,
                                      createPoolConfig())
        .build();
  }

  private ResourcePools createPoolConfig() {
    return ResourcePoolsBuilder.newResourcePoolsBuilder()
        .heap(2, MemoryUnit.MB)
        .with(ClusteredResourcePoolBuilder.clustered()).build();
  }

}
