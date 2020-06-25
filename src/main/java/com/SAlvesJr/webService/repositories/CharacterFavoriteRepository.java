package com.SAlvesJr.webService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SAlvesJr.webService.model.CharacterFavorite;

@Repository
public interface CharacterFavoriteRepository extends JpaRepository<CharacterFavorite, Long> {

}