package com.example.app.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    private String albumName;

    @ManyToOne
    @JoinColumn(name = "artist_id")
    @JsonBackReference // to prevent infinite recursion during JSON serialization
    private Artist artist;



    @OneToMany(mappedBy = "album") // Matches "private Album album" in Song.java
    private List<Song> songs;

}
