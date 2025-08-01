package com.example.golfclubapi.controller;

import com.example.golfclubapi.model.Member;
import com.example.golfclubapi.model.Tournament;
import com.example.golfclubapi.repository.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/api/tournaments")
public class TournamentController {

    @Autowired private TournamentRepository tournamentRepo;

    @PostMapping
    public Tournament createTournament(@RequestBody Tournament tournament) {
        return tournamentRepo.save(tournament);
    }

    @GetMapping
    public List<Tournament> getAllTournaments() {
        return tournamentRepo.findAll();
    }

    @GetMapping("/{id}/members")
    public Set<Member> getMembersInTournament(@PathVariable Long id) {
        Tournament tournament = tournamentRepo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Tournament not found"));
        return tournament.getParticipants();
    }

    @GetMapping("/search")
    public ResponseEntity<List<Tournament>> searchTournaments(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) String location
    ) {
        List<Tournament> results = new ArrayList<>();

        if (startDate != null) {
            results = tournamentRepo.findByStartDate(startDate);
        } else if (location != null) {
            results = tournamentRepo.findByLocationContainingIgnoreCase(location);
        } else {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(results);
    }
}
