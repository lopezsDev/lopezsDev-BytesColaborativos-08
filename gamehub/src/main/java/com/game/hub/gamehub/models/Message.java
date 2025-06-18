package com.game.hub.gamehub.models;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Message {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(columnDefinition = "uuid", updatable = false, nullable = false)
	private UUID id;

	@Column(name = "sender_id", updatable = false, nullable = false)
	private UUID senderId;

	@Column(name = "message_content", updatable = true, nullable = false)
	private String content;

	@Column(name = "timestamp", updatable = false, nullable = false)
	private LocalDateTime timestamp;

	@Column(name = "match_id", updatable = false, nullable = false)
	private UUID matchId;

	@Column(name = "tournament_id", updatable = false, nullable = false)
	private UUID tournamentId;
}
