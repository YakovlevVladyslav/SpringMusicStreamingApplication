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
    private Long album_id;


    @Column(name = "albumName", nullable = false)
    // @JsonProperty("album_title") in json i'll call it by album_title but in java code i can call it title
    private String albumName;

    @ManyToOne
    @JoinColumn(name = "artist_id")
    private Artist artist;



    @OneToMany(mappedBy = "fromAlbum")
    private List<Song> songs;

}
