package com.example.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    // Artist.java

    @ManyToMany(mappedBy = "favouriteArtists")
    @JsonIgnore // Самый безопасный вариант для этой стороны связи
    private List<User> fans;

    @OneToMany(mappedBy = "artist")
    @JsonIgnoreProperties("artist") // Вместо ManagedReference
    private List<Album> albums;
}
