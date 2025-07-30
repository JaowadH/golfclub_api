package com.example.golfclubapi.controller;

import com.example.golfclubapi.model.Member;
import com.example.golfclubapi.model.Tournament;
import com.example.golfclubapi.repository.MemberRepository;
import com.example.golfclubapi.repository.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<Member> searchMembersByName(@RequestParam String name) {
        return memberRepo.findByNameContainingIgnoreCase(name);
    }

    @PostMapping("/{memberId}/tournaments/{tournamentId}")
    public String addMemberToTournament(@PathVariable Long memberId, @PathVariable Long tournamentId) {
        Member member = memberRepo.findById(memberId).orElseThrow();
        Tournament tournament = tournamentRepo.findById(tournamentId).orElseThrow();

        member.getTournaments().add(tournament);
        memberRepo.save(member);

        return "Member added to tournament.";
    }
}
