package com.example.app.entity;

import com.fasterxml.jackson.annotation.*;
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
    private Long playlist_id;

    @Column(name = "playlistName", nullable = false)
    private String playlistName;

    // Playlist.java

    @ManyToOne
    @JoinColumn(name = "user_id")
// УДАЛИ @JsonBackReference
// Добавь это, чтобы PATCH понимал, к какому пользователю относится плейлист
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "user_id", scope = User.class)
    @JsonIdentityReference(alwaysAsId = true)
    private User user;

    @ManyToMany
    @JsonIgnoreProperties("playlists") // To prevent infinite recursion during JSON serialization
    @JoinTable(
            name = "playlist_song",
            joinColumns = @JoinColumn(name = "playlist_id"),
            inverseJoinColumns = @JoinColumn(name = "song_id")
    )
    private List<Song> songs;

}
