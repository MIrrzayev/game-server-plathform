package com.huseyn.gameserverplathform.repository;

import com.huseyn.gameserverplathform.entity.UserGame;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserGameRepository extends JpaRepository<UserGame, Long> {
    List<UserGame> findByUserId(Long id);
    boolean existsByUserIdAndGameId(Long userId, Long gameId);
}
