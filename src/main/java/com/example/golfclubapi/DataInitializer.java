package com.example.golfclubapi;

import com.example.golfclubapi.model.Member;
import com.example.golfclubapi.model.Tournament;
import com.example.golfclubapi.repository.MemberRepository;
import com.example.golfclubapi.repository.TournamentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {

    private final MemberRepository memberRepo;
    private final TournamentRepository tournamentRepo;

    public DataInitializer(MemberRepository memberRepo, TournamentRepository tournamentRepo) {
        this.memberRepo = memberRepo;
        this.tournamentRepo = tournamentRepo;
    }

    @Override
    public void run(String... args) {
        if (memberRepo.count() == 0 && tournamentRepo.count() == 0) {
            Member alice = new Member("Alice Johnson", "123 Golf St", "alice@example.com", "1234567890", LocalDate.now().minusMonths(3), 12);
            Member bob = new Member("Bob Smith", "456 Swing Rd", "bob@example.com", "9876543210", LocalDate.now().minusMonths(1), 6);

            Tournament springCup = new Tournament(LocalDate.of(2025, 9, 1), LocalDate.of(2025, 9, 3), "Toronto", 50.0, 500.0);
            Tournament fallOpen = new Tournament(LocalDate.of(2025, 10, 10), LocalDate.of(2025, 10, 12), "Montreal", 70.0, 750.0);

            alice.setTournaments(Set.of(springCup));
            bob.setTournaments(Set.of(springCup, fallOpen));

            memberRepo.save(alice);
            memberRepo.save(bob);
        }
    }
}
