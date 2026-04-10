package com.example.app.controller;

import com.example.app.entity.Artist;
import com.example.app.service.ArtistService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/artists")
public class ArtistController {

    private final ArtistService artistService;

    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    // 1. CREATE -> 201 Created
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Artist createArtist(@RequestBody Artist artist) {
        return artistService.saveArtist(artist);
    }

    // 2. GET BY ID -> 200 OK
    @GetMapping("/{id}")
    public Artist getArtistById(@PathVariable Long id) {
        return artistService.getArtistById(id);
    }

    // 3. PATCH -> 200 OK
    @PatchMapping("/{id}")
    public Artist updateArtistName(@PathVariable Long id, @RequestBody Artist artistDetails) {
        return artistService.updateArtistName(id, artistDetails.getArtistName());
    }

    // 4. DELETE -> 204 No Content
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArtist(@PathVariable Long id) {
        artistService.deleteArtist(id);
        return ResponseEntity.noContent().build();
    }

    // 5. GET ALL -> 200 OK
    @GetMapping
    public List<Artist> getAllArtists() {
        return artistService.getAllArtists();
    }
}