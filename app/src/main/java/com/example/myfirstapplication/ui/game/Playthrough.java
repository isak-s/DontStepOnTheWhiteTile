package com.example.myfirstapplication.ui.game;

public class Playthrough {
    private String player = "Isak";

    private int points;
    private Long startTime;
    private Long checkPoints;

    public Playthrough() {
        points = 0;
        startTime = System.currentTimeMillis();
        checkPoints = 0L;
    }
    public Playthrough(String player, int points, Long startTime) {
        this.player = player;
        this.points = points;
        this.startTime = startTime;
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
    public Long getStartTime() {
        return startTime;
    }
    public String getPlayer() {
        return player;
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
