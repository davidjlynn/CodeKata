package com.davidjlynn.codekata.kata02;

import static org.junit.Assert.*;

import org.junit.Test;

public class BinarySearchTest {

    @Test
    public void chop() {
        assertEquals(-1, BinarySearch.chop(3, new int[]{}));
        assertEquals(-1, BinarySearch.chop(3,new int[]{1}));
        assertEquals(0, BinarySearch.chop(1,new int[]{1}));

        assertEquals(0, BinarySearch.chop(1,new int[]{1, 3, 5}));
        assertEquals(1, BinarySearch.chop(3,new int[]{1, 3, 5}));
        assertEquals(2, BinarySearch.chop(5,new int[]{1, 3, 5}));
        assertEquals(-1, BinarySearch.chop(0,new int[]{1, 3, 5}));
        assertEquals(-1, BinarySearch.chop(2,new int[]{1, 3, 5}));
        assertEquals(-1, BinarySearch.chop(4,new int[]{1, 3, 5}));
        assertEquals(-1, BinarySearch.chop(6,new int[]{1, 3, 5}));

        assertEquals(0, BinarySearch.chop(1,new int[]{1, 3, 5, 7}));
        assertEquals(1, BinarySearch.chop(3,new int[]{1, 3, 5, 7}));
        assertEquals(2, BinarySearch.chop(5,new int[]{1, 3, 5, 7}));
        assertEquals(3, BinarySearch.chop(7,new int[]{1, 3, 5, 7}));
        assertEquals(-1, BinarySearch.chop(0,new int[]{1, 3, 5, 7}));
        assertEquals(-1, BinarySearch.chop(2,new int[]{1, 3, 5, 7}));
        assertEquals(-1, BinarySearch.chop(4,new int[]{1, 3, 5, 7}));
        assertEquals(-1, BinarySearch.chop(6,new int[]{1, 3, 5, 7}));
        assertEquals(-1, BinarySearch.chop(8,new int[]{1, 3, 5, 7}));
    }

}
