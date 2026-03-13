package com.example.app.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "albums")
@NoArgsConstructor
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "userName", nullable = false)
    private String userName;

    @OneToMany(mappedBy = "playlistName")
    private List<Playlist> playlists;

    @ManyToMany(mappedBy = "artistName")
    private List<Artist> favouriteArtists;
}
