package com.davidjlynn.codekata.kata21;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.function.Supplier;
import org.junit.jupiter.api.Test;

public class ListTest {

  @Test
  public void singleLinkedTest() {
    testList(SingleLinkedList::new);
  }

  private void testList(Supplier<KataList> listSupplier) {
    KataList list = listSupplier.get();

    assertNull(list.find("fred"));
    list.add("fred");
    assertEquals("fred", list.find("fred").value());
    assertNull(list.find("wilma"));
    list.add("wilma");
    assertEquals("fred", list.find("fred").value());
    assertEquals("wilma", list.find("wilma").value());
    assertArrayEquals(new String[] {"fred", "wilma"}, list.values());

    list = listSupplier.get();
    list.add("fred");
    list.add("wilma");
    list.add("betty");
    list.add("barney");
    assertArrayEquals(new String[] {"fred", "wilma", "betty", "barney"}, list.values());
    list.delete(list.find("wilma"));
    assertArrayEquals(new String[] {"fred", "betty", "barney"}, list.values());
    list.delete(list.find("barney"));
    assertArrayEquals(new String[] {"fred", "betty"}, list.values());
    list.delete(list.find("fred"));
    assertArrayEquals(new String[] {"betty"}, list.values());
    list.delete(list.find("betty"));
    assertArrayEquals(new String[] {}, list.values());
  }
}
