package com.huseyn.gameserverplathform.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_games", uniqueConstraints = {@UniqueConstraint(name = "uk_user_game", columnNames = {"user_id", "game_id"})})
@Getter
@Setter
@NoArgsConstructor
public class UserGame {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;
    @Column(nullable = false)
    private String accountId;
    private String accountUsername;
    @Column(nullable = false)
    private String accountEmail;
}
