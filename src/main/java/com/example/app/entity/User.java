package com.example.app.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @OneToMany(mappedBy = "user") // Matches "private User user" in Playlist.java)
    @JsonManagedReference
    private List<Playlist> playlists;

    @ManyToMany(mappedBy = "fans") // Matches "private List<User> fans" in Artist.java
    //@JsonBackReference
    @JsonIgnoreProperties("fans")
    private List<Artist> favouriteArtists;
}
