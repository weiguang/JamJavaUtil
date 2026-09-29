package com.okayjam.code.leetcode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ArrayStringTest {

    private final ArrayString arrayString = new ArrayString();

    @Test
    void isPossibleAcceptsValidSplits() {
        assertTrue(arrayString.isPossible(new int[]{1, 2, 3, 3, 4, 5}));
        assertTrue(arrayString.isPossible(new int[]{1, 2, 3, 3, 4, 4, 5, 5}));
        assertTrue(arrayString.isPossible(new int[]{1, 2, 3, 4, 5, 5, 6, 7}));
    }

    @Test
    void isPossibleRejectsInvalidSplits() {
        assertFalse(arrayString.isPossible(new int[]{1, 2, 3, 4, 4, 5}));
        assertFalse(arrayString.isPossible(new int[]{1, 2, 3, 3, 4, 4}));
        assertFalse(arrayString.isPossible(new int[]{1, 2, 3, 4, 5, 5, 6, 8}));
    }
}