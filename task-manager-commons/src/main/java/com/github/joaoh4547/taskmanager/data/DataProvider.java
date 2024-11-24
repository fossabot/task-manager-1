package com.github.joaoh4547.taskmanager.data;

import java.util.Collection;

public class DataProvider<T> {

  private final DataResolver<T> resolver;

  private int limit;

  public DataProvider(DataResolver<T> resolver, int limit) {
    this.resolver = resolver;
    this.limit = limit;
  }

  public DataProvider(DataResolver<T> resolver) {
    this(resolver, 20);
  }

  public void setLimit(int limit) {
    this.limit = limit;
  }

  public DataResolver<T> getResolver() {
    return resolver;
  }

  Collection<T> get(int page) {

    Collection<T> result = resolver.resolve();

    // Evita o erro de stream em uma coleção vazia
    if (result == null || result.isEmpty()) {
      return result;
    }

    // Corrige a fórmula de paginação
    return result.stream().skip((long) (page - 1) * limit) // pula os elementos
                                                           // anteriores
        .limit(limit) // limita o número de elementos retornados
        .toList();
  }

  @FunctionalInterface
  public interface DataResolver<R> {
    Collection<R> resolve();
  }

}
