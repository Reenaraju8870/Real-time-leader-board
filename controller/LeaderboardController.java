package com.LeaderBoard.Real_timeLeader.controller;
import com.LeaderBoard.Real_timeLeader.dto.ScoreRequestDTO;
import com.LeaderBoard.Real_timeLeader.service.Leaderboardservice; // Check casing here
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/leaderboard")
@CrossOrigin(origins = "*")
public class LeaderboardController {


    // Fix 1: Ensure Class name matches the file exactly (LeaderboardService)
    private final Leaderboardservice leaderboardService;

    public LeaderboardController(Leaderboardservice leaderboardService) {
        this.leaderboardService = leaderboardService;
        // Delete the duplicate line that was here
    }

    @PostMapping("/submit")
    public String submit(@RequestBody ScoreRequestDTO scoreRequest) {
        leaderboardService.submitScore(scoreRequest.getUsername(), scoreRequest.getScore());
        return "Score updated successfully for " + scoreRequest.getUsername();
    }

    @GetMapping("/top")
    public Set<ZSetOperations.TypedTuple<Object>> getTopPlayers() {
        return leaderboardService.getTopPlayers(10);
    }
}