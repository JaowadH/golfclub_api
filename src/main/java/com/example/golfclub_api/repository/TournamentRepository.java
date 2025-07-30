package com.example.golfclubapi.repository;

import com.example.golfclubapi.model.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TournamentRepository extends JpaRepository<Tournament, Long> {
    List<Tournament> findByLocationContainingIgnoreCase(String location);
    List<Tournament> findByStartDate(LocalDate startDate);
}

