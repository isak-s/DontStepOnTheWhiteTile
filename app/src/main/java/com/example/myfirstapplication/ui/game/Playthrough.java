package com.example.myfirstapplication.ui.game;

public class Playthrough {

    private int points;
    Long startTime;
    Long checkPoints;

    public Playthrough() {
        points = 0;
        startTime = System.currentTimeMillis();
        checkPoints = 0L;
    }

    public void incrementPoints() {
        points++;
    }
    public int getPoints() {
        return points;
    }
    public Long getTimeLeft() {
        return startTime - System.currentTimeMillis() + 10000 + checkPoints * 10000;
    }
    private String formatTime(long millis) {
        long seconds = millis / 1000;
        return String.format("%02d:%02d", seconds, millis%1000); // Format as MM:SS
    }
    public String getFormattedTimeLeft() {
        return formatTime(getTimeLeft());
    }
    public void checkCheckPoint() {
        if (points % 50 == 0) {
            checkPoints += 1;
        }

    }

}
