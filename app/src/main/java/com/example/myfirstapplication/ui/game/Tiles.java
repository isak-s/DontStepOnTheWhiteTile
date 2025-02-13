package com.example.myfirstapplication.ui.game;

import java.util.Random;

public class Tiles {

    FifoQueue<Column> colQueue;
    Random rng;

    public Tiles(Long seed) {
        rng = new Random(seed);
        colQueue = new FifoQueue<Column>();
    }
    public Tiles() {
        rng = new Random();
        colQueue = new FifoQueue<Column>();
    }
    public FifoQueue<Column> getColQueue() {
        return colQueue;
    }
    public void stepForward() {
        colQueue.poll();
        Column newCol = new Column();
        newCol.setBlack(rng.nextInt(4));
        colQueue.offer(newCol);
    }

    public void setupInitTiles() {
        colQueue.clear();
        for (int i = 0; i<4; i++) {
            Column col = new Column();
            col.setBlack(rng.nextInt(4));
            colQueue.offer(col);
        }

    }

    public static class Column {

        int[] columnValues;
        public int indexOfBlackTile;

        private Column(int size) {
            columnValues = new int[size];
        }
        private Column() {
            columnValues = new int[4];
        }

        private void setBlack(int index) {
            if (index >= columnValues.length) {
                throw new IndexOutOfBoundsException();
            }
            columnValues[index] = 1;
            indexOfBlackTile = index;
        }
        public int[] getColumnValues() {
            return columnValues;
        }

        public int getIndexOfBlackTile() {
            return indexOfBlackTile;
        }
    }
}
