package com.example.app.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "playlists")
@NoArgsConstructor
@Data
public class Playlist {
    @ManyToOne
    @Column(name = "playlistName", nullable = false)
    private String playlistName;

}
