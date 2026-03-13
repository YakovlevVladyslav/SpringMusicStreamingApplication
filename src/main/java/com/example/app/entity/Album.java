package com.example.app.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.IdGeneratorType;

import java.util.List;

@Entity
@Table(name = "albums")
@NoArgsConstructor
@Data
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @Column(name = "albumName", nullable = false, insertable=false, updatable=false)
    // @JsonProperty("album_title") in json i'll call it by album_title but in java code i can call it title
    private String albumName;
    @Column(name = "artist", nullable = false)
    private String artist;

    @OneToMany(mappedBy = "fromAlbum")
    private List<Song> songs;

}
