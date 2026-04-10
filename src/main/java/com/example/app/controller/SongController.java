package com.example.app.controller;

import com.example.app.entity.Song;
import com.example.app.service.SongService;
import org.springframework.http.HttpStatus;
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

    // 1. CREATE -> 201 Created
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Song createSong(@RequestBody Song song) {
        return songService.saveSong(song);
    }

    // 2. GET BY ID -> 200 OK
    @GetMapping("/{id}")
    public Song getSongById(@PathVariable Long id) {
        return songService.getSongById(id);
    }

    // 3. PATCH -> 200 OK
    @PatchMapping("/{id}")
    public Song updateSongName(@PathVariable Long id, @RequestBody Song songDetails) {
        return songService.updateSongName(id, songDetails.getSongName());
    }

    // 4. DELETE -> 204 No Content
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSong(@PathVariable Long id) {
        songService.deleteSong(id);
        return ResponseEntity.noContent().build();
    }

    // 5. GET ALL -> 200 OK
    @GetMapping
    public List<Song> getAllSongs() {
        return songService.getAllSongs();
    }
}