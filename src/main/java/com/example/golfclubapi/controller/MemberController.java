package com.example.golfclubapi.controller;

import com.example.golfclubapi.model.Member;
import com.example.golfclubapi.model.Tournament;
import com.example.golfclubapi.repository.MemberRepository;
import com.example.golfclubapi.repository.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    @Autowired private MemberRepository memberRepo;
    @Autowired private TournamentRepository tournamentRepo;

    @PostMapping
    public Member createMember(@RequestBody Member member) {
        return memberRepo.save(member);
    }

    @GetMapping
    public List<Member> getAllMembers() {
        return memberRepo.findAll();
    }

    @GetMapping("/search")
    public ResponseEntity<List<Member>> searchMembers(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) Integer duration,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate tournamentStartDate
    ) {
        List<Member> results = new ArrayList<>();

        if (name != null) {
            results = memberRepo.findByNameContainingIgnoreCase(name);
        } else if (phone != null) {
            results = memberRepo.findByPhoneNumber(phone);
        } else if (startDate != null) {
            results = memberRepo.findByStartDate(startDate);
        } else if (duration != null) {
            results = memberRepo.findByDurationInMonths(duration);
        } else if (tournamentStartDate != null) {
            results = memberRepo.findByTournamentStartDate(tournamentStartDate);
        } else {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(results);
    }

    @PostMapping("/{memberId}/tournaments/{tournamentId}")
    public ResponseEntity<String> addMemberToTournament(@PathVariable Long memberId, @PathVariable Long tournamentId) {
        Member member = memberRepo.findById(memberId).orElseThrow(() -> new NoSuchElementException("Member not found"));
        Tournament tournament = tournamentRepo.findById(tournamentId).orElseThrow(() -> new NoSuchElementException("Tournament not found"));

        member.getTournaments().add(tournament);
        memberRepo.save(member);

        return ResponseEntity.ok("Member added to tournament.");
    }
}