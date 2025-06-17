package com.game.hub.gamehub.models;

import java.util.UUID;

import com.game.hub.gamehub.enums.Role;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @NonNull
  @Column(nullable=false)
  private String username;

  @Column(unique=true)
  private String email;

  @NonNull
  @Column(nullable=false)
  private String password;

  private Role role;

  private String rank;

  private int points;

  protected User() { }

  public User(String username, String email, String password, Role role, String rank, int points) {
    this.username = username;
    this.email = email;
    this.password = password;
    this.role = role;
    this.rank = rank;
    this.points = points;
  }

   @Override
  public String toString() {
    return String.format(
        "User[id=%s, username='%s', email='%s', role='%s']",
        id, username, email, role);
  }

  public UUID getId(){
    return id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  public String getRank() {
    return rank;
  }

  public void setRank(String rank) {
    this.rank = rank;
  }

  public int getPoints() {
    return points;
  }

  public void setPoints(int points) {
    this.points = points;
  }

}
