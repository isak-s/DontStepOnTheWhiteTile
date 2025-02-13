package com.example.myfirstapplication.ui.game;

import java.util.*;

public class FifoQueue<E> extends AbstractQueue<E> {
    private QueueNode<E> last;
    private int size;

    public FifoQueue() {
        super();
        last = null;
        size = 0;
    }

    /**
     * Inserts the specified element into this queue, if possible
     * post:	The specified element is added to the rear of this queue
     * @param	e the element to insert
     * @return	true if it was possible to add the element
     * 			to this queue, else false
     */
    public boolean offer(E e) {
        size++;
        if (last == null) {
            last = new QueueNode<E>(e);
            last.next = last;
            return true;
        }
        QueueNode<E> newLast = new QueueNode<E>(e);
        newLast.next = last.next;
        last.next = newLast;
        last = newLast;
        return true;
    }

    /**
     * Returns the number of elements in this queue
     * @return the number of elements in this queue
     */
    public int size() {
        return size;
    }

    /**
     * Retrieves, but does not remove, the head of this queue,
     * returning null if this queue is empty
     * @return 	the head element of this queue, or null
     * 			if this queue is empty
     */
    public E peek() {
        if (last == null) {
            return null;
        }
        return last.next.element;
    }

    /**
     * Retrieves and removes the head of this queue,
     * or null if this queue is empty.
     * post:	the head of the queue is removed if it was not empty
     * @return 	the head of this queue, or null if the queue is empty
     */
    public E poll() {
        if (last == null) {
            return null;
        }
        QueueNode<E> head = last.next;

        if (last == head) {
            last = null;
        }
        else {
            last.next = head.next;
        }
        size--;
        return head.element;
    }

    /**
     * Appends the specified queue to this queue
     * post: all elements from the specified queue are appended
     * to this queue. The specified queue (q) is empty after the call.
     * @param q the queue to append
     * @throws IllegalArgumentException if this queue and q are identical
     */
    public void append(FifoQueue<E> q) {
        if (q == this) {
            throw new IllegalArgumentException();
        }
        size += q.size;
        q.size = 0;

        if (last == null) {
            last = q.last;
            q.last = null;
            q.size = 0;
            return;
        }

        if (q.last == null) {
            return;
        }
        QueueNode<E> head = last.next;
        last.next = q.last.next;
        last = q.last;
        last.next = head;
        q.last = null;
        q.size = 0;
    }

    /**
     * Returns an iterator over the elements in this queue
     * @return an iterator over the elements in this queue
     */
    public Iterator<E> iterator() {
        return new QueueIterator();
    }

    private static class QueueNode<E> {
        E element;
        QueueNode<E> next;

        private QueueNode(E x) {
            element = x;
            next = null;
        }
    }
    private class QueueIterator implements Iterator<E> {
        private QueueNode<E> current;

        private QueueIterator() {
            if (last == null) {
                current = null;
            } else {
                current = last.next;
            }
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E element = current.element;
            current = (current == last) ? null : current.next; // Stop when we reach last
            return element;
        }
    }
}
