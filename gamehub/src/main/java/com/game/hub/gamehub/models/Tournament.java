package com.game.hub.gamehub.models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.PostgreSQLEnumJdbcType;

import com.game.hub.gamehub.enums.TournamentStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Tournament {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(columnDefinition = "uuid", updatable = false, nullable = false)
	private UUID id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "max_players", nullable = false)
	private int maxPlayers;

	@Enumerated(EnumType.STRING)
	@JdbcType(PostgreSQLEnumJdbcType.class)
	@Column(name = "status", nullable = false)
	private TournamentStatus status;

	@OneToMany
	private List<User> players = new ArrayList<>();

}
