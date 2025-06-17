package com.game.hub.gamehub.models;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class UserStats {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(columnDefinition = "uuid", updatable = false, nullable = false)
	private UUID id;

	@Column(name = "user_id", updatable = false, nullable = false)
	private UUID userId;
	
	@Column(name = "tournament_id", updatable = false, nullable = false)
	private UUID tournament_id;

	@Column(name = "matches_played", nullable = false)
	private Integer matchesPlayed;

	@Column(name = "wins", nullable = false)
	private Integer wins;

	@Column(name = "score", nullable = false)
	private Integer score;

}
