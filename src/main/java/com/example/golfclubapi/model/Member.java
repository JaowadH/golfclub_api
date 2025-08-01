package com.example.golfclubapi.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;
    private String email;
    private String phoneNumber;
    private LocalDate startDate;
    private int durationInMonths;

    @ManyToMany(mappedBy = "participants")
    private Set<Tournament> tournaments = new HashSet<>();

    // ✅ No-arg constructor for JPA
    public Member() {
    }

    // ✅ All-args constructor for seeding or test data
    public Member(String name, String address, String email, String phoneNumber, LocalDate startDate, int durationInMonths) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.startDate = startDate;
        this.durationInMonths = durationInMonths;
    }
}
