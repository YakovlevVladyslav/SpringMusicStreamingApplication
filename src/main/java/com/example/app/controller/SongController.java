package com.example.app.controller;

import com.example.app.entity.Song;
import com.example.app.service.SongService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/songs")
public class SongController {

    private final SongService songService;

    public SongController(SongService songService) {
        this.songService = songService;
    }

    // 1. CREATE
    @PostMapping
    public ResponseEntity<Song> createSong(@RequestBody Song song) {
        Song savedSong = songService.saveSong(song);
        return ResponseEntity.ok(savedSong);
    }

    // 2. GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Song> getSongById(@PathVariable Long id) {
        Song song = songService.getSongById(id);
        return ResponseEntity.ok(song);
    }

    // 3. PATCH - Update only the name by ID
    @PatchMapping("/{id}")
    public ResponseEntity<Song> updateSongName(@PathVariable Long id, @RequestBody Song songDetails) {
        // Uses songName from the request body to update the entity
        Song updatedSong = songService.updateSongName(id, songDetails.getSongName());
        return ResponseEntity.ok(updatedSong);
    }

    // 4. DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSong(@PathVariable Long id) {
        songService.deleteSong(id);
        return ResponseEntity.noContent().build();
    }

    // 5. GET ALL
    @GetMapping
    public ResponseEntity<List<Song>> getAllSongs() {
        return ResponseEntity.ok(songService.getAllSongs());
    }
}