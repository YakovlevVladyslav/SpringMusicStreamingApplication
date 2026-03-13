package com.example.app.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Entity
@Table(name = "artists")
@NoArgsConstructor
@Data
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long artist_id;

    @Column(name = "artistName", nullable = false)
    private String artistName;

    @OneToMany(mappedBy = "artist") // Matches "private Artist artist" in Album.java
    private List<Album> albums;

    @ManyToMany
    @JoinTable(
            name = "user_favourite_artists",
            joinColumns = @JoinColumn(name = "artist_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> fans;
}
