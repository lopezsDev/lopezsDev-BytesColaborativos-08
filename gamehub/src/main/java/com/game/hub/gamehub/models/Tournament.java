package com.game.hub.gamehub.models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.game.hub.gamehub.enums.Status;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Tournament {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Nonnull
  @Column(nullable=false)
  private String name;

  private int maxPlayers;

  private Status status;

  @OneToMany
  private List<User> players = new ArrayList<>();

  protected Tournament() {}

  public Tournament(String name, int maxPlayers, Status status, List<User> players) {
    this.name = name;
    this.maxPlayers = maxPlayers;
    this.status = status;
    this.players = players;
  }

  @Override
  public String toString() {
    return String.format(
        "Tournament[id=%s, name='%s', maxPlayers='%d', status='%s']",
        id, name, maxPlayers, status);
  }

  public UUID getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getMaxPlayers() {
    return maxPlayers;
  }

  public void setMaxPlayers(int maxPlayers) {
    this.maxPlayers = maxPlayers;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public List<User> getPlayers() {
    return players;
  }

  public void setPlayers(List<User> players) {
    this.players = players;
  }

}
