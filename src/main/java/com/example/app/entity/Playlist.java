package com.example.app.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "playlists")
@NoArgsConstructor
@Data
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @Column(name = "playlistName", nullable = false)
    private String playlistName;

    @ManyToMany(mappedBy = "songName")
    private List<Song> songs;

}
