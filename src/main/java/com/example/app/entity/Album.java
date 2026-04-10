package com.example.app.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.IdGeneratorType;
import org.hibernate.validator.constraints.Length;

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
    @Length(min = 1, max = 255, message = "Album name must be between 1 and 255 characters")
    private String albumName;

    @ManyToOne
    @JoinColumn(name = "artist_id")
    @JsonBackReference // to prevent infinite recursion during JSON serialization
    private Artist artist;



    @OneToMany(mappedBy = "album") // Matches "private Album album" in Song.java
    @JsonManagedReference("album-song") // to manage the forward part of the reference
    private List<Song> songs;

}
