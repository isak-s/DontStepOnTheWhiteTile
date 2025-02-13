package com.example.myfirstapplication;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import com.example.myfirstapplication.ui.game.FifoQueue;

class TestAppendFifoQueue {

    private FifoQueue<Integer> myIntQueue;
    private FifoQueue<Integer> mySecondIntQueue;

    @BeforeEach
    void setUp() {
        myIntQueue = new FifoQueue<Integer>();
        mySecondIntQueue = new FifoQueue<Integer>();
    }

    @AfterEach
    void tearDown(){
        myIntQueue = null;
        mySecondIntQueue = null;
    }

    @Test
    void testAppendTwoEmptyQueues() {
        // Act
        myIntQueue.append(mySecondIntQueue);
        // Assert
        assertEquals(mySecondIntQueue.size(), 0);
        assertEquals(myIntQueue.size(), 0);
    }

    @Test
    void testAppendEmptyToNonEmptyqueue() {
        // Arrange
        myIntQueue.add(1);
        myIntQueue.add(2);
        myIntQueue.add(3);
        int sizeAfterAdding = myIntQueue.size();

        // Act
        myIntQueue.append(mySecondIntQueue);

        // Assert
        assertEquals(myIntQueue.size(), sizeAfterAdding);
        assertEquals(mySecondIntQueue.size(), 0);
    }

    @Test
    void testAppendNonEmptyToEmptyQueue() {
        // Arrange
        myIntQueue.add(1);
        myIntQueue.add(2);
        myIntQueue.add(3);

        // Act
        mySecondIntQueue.append(myIntQueue);

        // Assert
        assertEquals(mySecondIntQueue.size(), 3);
        assertEquals(myIntQueue.size(), 0);

    }

    @Test
    void testAppendNonemptyQueues() {
        // Arrange
        myIntQueue.add(1);
        myIntQueue.add(2);
        myIntQueue.add(3);
        mySecondIntQueue.add(1);
        mySecondIntQueue.add(2);
        mySecondIntQueue.add(3);

        // Act
        mySecondIntQueue.append(myIntQueue);

        // Assert
        assertEquals(mySecondIntQueue.size(), 6);
        assertEquals(myIntQueue.size(), 0);

    }

    @Test
    void testAppendWithSelf() {
        // Arrange
        myIntQueue.add(1);
        myIntQueue.add(2);
        myIntQueue.add(3);

        // act / Assert
        assertThrows(IllegalArgumentException.class, () -> myIntQueue.append(myIntQueue));
    }

    @Test
    void testOrderAfterAppendingNonEmptyQueues() {

        // Arrange
        for (int i = 1; i < 4; i++) {
            myIntQueue.add(i);
            mySecondIntQueue.add(i + 3);
        }
        // Act
        myIntQueue.append(mySecondIntQueue);

        Iterator<Integer> itr = myIntQueue.iterator();

        // Assert
        for (int i = 1; i<myIntQueue.size()+1; i++) {
            assertEquals(itr.next(), i);
        }

    }
    @Test
    void testorderAfterAppendingEmptyQueueToNonemptyQueue() {
        // Arrange
        myIntQueue.add(1);
        myIntQueue.add(2);
        myIntQueue.add(3);

        // Act
        myIntQueue.append(mySecondIntQueue);
        Iterator<Integer> itr = myIntQueue.iterator();

        // Assert
        for (int i = 1; i < myIntQueue.size() + 1; i++) {
            assertEquals(itr.next(), i);
        }
    }

    @Test
    void testAppendNonEmptyQueueToEmptyQueue() {
        // Arrange
        mySecondIntQueue.add(1);
        mySecondIntQueue.add(2);
        mySecondIntQueue.add(3);

        // Act
        myIntQueue.append(mySecondIntQueue);
        Iterator<Integer> itr = myIntQueue.iterator();

        // Assert
        for (int i = 1; i < myIntQueue.size() + 1; i++) {
            assertEquals(itr.next(), i);
        }
    }
}
