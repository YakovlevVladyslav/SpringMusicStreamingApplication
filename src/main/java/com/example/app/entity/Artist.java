package com.example.app.entity;

import jakarta.persistence.*;

import java.util.List;

public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "artistName", nullable = false)
    private String artistName;

    @OneToMany(mappedBy = "albumName")
    private List<Album> albums;
}
