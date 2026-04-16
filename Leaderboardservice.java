package com.LeaderBoard.Real_timeLeader.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class Leaderboardservice {

    private final RedisTemplate<String, Object> redisTemplate;
    private static final String REDIS_KEY = "global_leaderboard";

    public Leaderboardservice(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    // Requirement: Score Submission (ZADD)
    public void submitScore(String username, double score) {
        // This adds the score or updates it if the user already exists
        redisTemplate.opsForZSet().add(REDIS_KEY, username, score);
    }

    // Requirement: Leaderboard Updates (Top Users)
    public Set<ZSetOperations.TypedTuple<Object>> getTopPlayers(int limit) {
        return redisTemplate.opsForZSet().reverseRangeWithScores(REDIS_KEY, 0, limit - 1);
    }

    // Requirement: User Rankings (ZREVRANK)
    public Long getUserRank(String username) {
        Long rank = redisTemplate.opsForZSet().reverseRank(REDIS_KEY, username);
        // Redis rank is 0-indexed, so we add 1 for display
        return (rank != null) ? rank + 1 : null;
    }
}

