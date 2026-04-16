package com.LeaderBoard.Real_timeLeader.repo;

import com.LeaderBoard.Real_timeLeader.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {

    // Add this line so AuthService can find it!
    Optional<User> findByUsername(String username);

}