package com.davidjlynn.codekata.kata02;

import static org.junit.Assert.*;

import org.junit.Test;

public class BinarySearchTest {

  @Test
  public void chop01() {
    assertEquals(-1, BinarySearch.chop(3, new int[] {}));
  }

  @Test
  public void chop02() {
    assertEquals(-1, BinarySearch.chop(3, new int[] {1}));
  }

  @Test
  public void chop03() {
    assertEquals(0, BinarySearch.chop(1, new int[] {1}));
  }

  @Test
  public void chop04() {
    assertEquals(0, BinarySearch.chop(1, new int[] {1, 3, 5}));
  }

  @Test
  public void chop05() {
    assertEquals(1, BinarySearch.chop(3, new int[] {1, 3, 5}));
  }

  @Test
  public void chop06() {
    assertEquals(2, BinarySearch.chop(5, new int[] {1, 3, 5}));
  }

  @Test
  public void chop07() {
    assertEquals(-1, BinarySearch.chop(0, new int[] {1, 3, 5}));
  }

  @Test
  public void chop08() {
    assertEquals(-1, BinarySearch.chop(2, new int[] {1, 3, 5}));
  }

  @Test
  public void chop09() {
    assertEquals(-1, BinarySearch.chop(4, new int[] {1, 3, 5}));
  }

  @Test
  public void chop10() {
    assertEquals(-1, BinarySearch.chop(6, new int[] {1, 3, 5}));
  }

  @Test
  public void chop11() {
    assertEquals(0, BinarySearch.chop(1, new int[] {1, 3, 5, 7}));
  }

  @Test
  public void chop12() {
    assertEquals(1, BinarySearch.chop(3, new int[] {1, 3, 5, 7}));
  }

  @Test
  public void chop13() {
    assertEquals(2, BinarySearch.chop(5, new int[] {1, 3, 5, 7}));
  }

  @Test
  public void chop14() {
    assertEquals(3, BinarySearch.chop(7, new int[] {1, 3, 5, 7}));
  }

  @Test
  public void chop15() {
    assertEquals(-1, BinarySearch.chop(0, new int[] {1, 3, 5, 7}));
  }

  @Test
  public void chop16() {
    assertEquals(-1, BinarySearch.chop(2, new int[] {1, 3, 5, 7}));
  }

  @Test
  public void chop17() {
    assertEquals(-1, BinarySearch.chop(4, new int[] {1, 3, 5, 7}));
  }

  @Test
  public void chop18() {
    assertEquals(-1, BinarySearch.chop(6, new int[] {1, 3, 5, 7}));
  }

  @Test
  public void chop19() {
    assertEquals(-1, BinarySearch.chop(8, new int[] {1, 3, 5, 7}));
  }
}
