package com.example.myfirstapplication;

import com.example.myfirstapplication.ui.game.FifoQueue;
import com.example.myfirstapplication.ui.game.Tiles;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Iterator;

public class TestTiles {

    Tiles tiles;


    @BeforeEach
    void setup() {
        tiles = new Tiles(123456L);

    }
    @AfterEach
    void tearDown() {
        tiles = null;

    }

    @Test
    void testConstructorNoSeed() {
        assertEquals(tiles.getColQueue().size(), 0);
    }
    @Test
    void testConstructorSeeded() {
        assertEquals(tiles.getColQueue().size(), 0);
    }

    @Test
    void testNewColumn() {
        // Act
        tiles.setupInitTiles();

        // Assert
        Iterator<Tiles.Column> itr = tiles.getColQueue().iterator();
        assertArrayEquals(itr.next().getColumnValues(), new int[]{0, 1, 0, 0});
        assertArrayEquals(itr.next().getColumnValues(), new int[]{0, 0, 0, 1});
        assertArrayEquals(itr.next().getColumnValues(), new int[]{0, 0, 0, 1});
        assertArrayEquals(itr.next().getColumnValues(), new int[]{0, 1, 0, 0});
    }

    @Test
    void testStepForward() {
        // Arrnge
        tiles.setupInitTiles();
        // Act
        tiles.stepForward();

        // Assert
        Iterator<Tiles.Column> itr = tiles.getColQueue().iterator();
        assertArrayEquals(itr.next().getColumnValues(), new int[]{0, 0, 0, 1});
        assertArrayEquals(itr.next().getColumnValues(), new int[]{0, 0, 0, 1});
        assertArrayEquals(itr.next().getColumnValues(), new int[]{0, 1, 0, 0});
        assertArrayEquals(itr.next().getColumnValues(), new int[]{0, 1, 0, 0});
    }
    @Test
    void stepForwardTwice() {
        // Arrange
        tiles.setupInitTiles();

        // Act
        tiles.stepForward();
        tiles.stepForward();

        // Assert
        Iterator<Tiles.Column> itr = tiles.getColQueue().iterator();
        assertArrayEquals(itr.next().getColumnValues(), new int[]{0, 0, 0, 1});
        assertArrayEquals(itr.next().getColumnValues(), new int[]{0, 1, 0, 0});
        assertArrayEquals(itr.next().getColumnValues(), new int[]{0, 1, 0, 0});
        assertArrayEquals(itr.next().getColumnValues(), new int[]{1, 0, 0, 0});
    }
}
