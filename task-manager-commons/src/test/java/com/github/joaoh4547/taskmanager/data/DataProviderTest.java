package com.github.joaoh4547.taskmanager.data;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DataProviderTest {

  private final DataProvider<Object> provider = new DataProvider<>(() -> Arrays
      .asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18,
              19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30));;

  @DisplayName("Test dataprovider tamanho da pagina (pagina 1 = 20)")
  @Test
  void testDataProviderFirstPage() {
    var data = provider.get(1);
    assertEquals(20, data.size());
  }

  @DisplayName("Test dataprovider tamanho da pagina (pagina 2 = 10)")
  @Test
  void testDataProviderLastPage() {
    var data = provider.get(2);
    assertEquals(10, data.size());
  }

}
