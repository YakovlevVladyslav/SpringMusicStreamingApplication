package com.example.app.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    @Column(name = "userName", nullable = false)
    private String userName;

    @OneToMany(mappedBy = "playlistName")
    private List<Playlist> playlists;

    @ManyToMany(mappedBy = "artistName")
    private List<Artist> favouriteArtists;
}
