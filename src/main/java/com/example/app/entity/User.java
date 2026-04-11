package com.example.app.entity;

import com.fasterxml.jackson.annotation.*;
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
    @JsonProperty("user_id") // Убедись, что это есть
    private Long user_id;

    @Column(name = "userName", nullable = false)
    private String userName;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
// УДАЛИ @JsonManagedReference
    @JsonIgnoreProperties("user")
    private List<Playlist> playlists;

    // User.java

    @ManyToMany
    @JoinTable(
            name = "user_favourite_artists",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "artist_id")
    )
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "artist_id", scope = Artist.class)
    @JsonIdentityReference(alwaysAsId = true)
    private List<Artist> favouriteArtists;

}
