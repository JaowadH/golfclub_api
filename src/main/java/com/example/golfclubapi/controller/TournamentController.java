package com.example.golfclubapi.controller;

import com.example.golfclubapi.model.Member;
import com.example.golfclubapi.model.Tournament;
import com.example.golfclubapi.repository.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

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
        return tournamentRepo.findById(id).orElseThrow().getParticipants();
    }
}
