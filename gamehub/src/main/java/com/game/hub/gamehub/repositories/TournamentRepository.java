package com.game.hub.gamehub.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.game.hub.gamehub.models.Tournament;

public interface TournamentRepository extends CrudRepository<Tournament, UUID> {
    List<Tournament> findAllByName(String name);
    Tournament findFirstByName(String name);
}
