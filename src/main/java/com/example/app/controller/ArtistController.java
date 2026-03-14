package com.example.app.controller;

import com.example.app.entity.Artist;
import com.example.app.service.ArtistService; // Updated import
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/artists")
public class ArtistController {

    private final ArtistService artistService; // Using the specialized service

    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    // 1. CREATE
    @PostMapping
    public ResponseEntity<Artist> createArtist(@RequestBody Artist artist) {
        Artist savedArtist = artistService.saveArtist(artist);
        return ResponseEntity.ok(savedArtist);
    }

    // 2. GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Artist> getArtistById(@PathVariable Long id) {
        Artist artist = artistService.getArtistById(id);
        return ResponseEntity.ok(artist);
    }

    // 3. PATCH - Update only the name
    @PatchMapping("/{id}")
    public ResponseEntity<Artist> updateArtistName(@PathVariable Long id, @RequestBody Artist artistDetails) {
        // We extract the name from the incoming object
        Artist updatedArtist = artistService.updateArtistName(id, artistDetails.getArtistName());
        return ResponseEntity.ok(updatedArtist);
    }

    // 4. DELETE BY ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArtist(@PathVariable Long id) {
        artistService.deleteArtist(id);
        return ResponseEntity.noContent().build();
    }

    // 5. GET ALL
    @GetMapping
    public ResponseEntity<List<Artist>> getAllArtists() {
        return ResponseEntity.ok(artistService.getAllArtists());
    }
}