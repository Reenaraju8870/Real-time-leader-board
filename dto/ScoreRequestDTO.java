package com.LeaderBoard.Real_timeLeader.dto;

public class ScoreRequestDTO {
    private String username;
    private double score;

    // Manual No-Args Constructor
    public ScoreRequestDTO() {}

    // Manual All-Args Constructor
    public ScoreRequestDTO(String username, double score) {
        this.username = username;
        this.score = score;
    }

    // Manual Getters and Setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public double getScore() { return score; }
    public void setScore(double score) { this.score = score; }
}