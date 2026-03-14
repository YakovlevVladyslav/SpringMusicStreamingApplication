package com.example.app.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "songs")
@NoArgsConstructor
@Data
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long song_id;

    @Column(name = "songName", nullable = false)
    private String songName;

    @ManyToOne
    @JsonBackReference("album-song")
    @JoinColumn(name = "album_id")
    private Album album;

    @ManyToMany(mappedBy = "songs")
    @JsonIgnoreProperties("songs") // To prevent infinite recursion during JSON serialization
    private List<Playlist> playlists;


}
