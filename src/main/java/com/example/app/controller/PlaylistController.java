package com.example.app.controller;

import com.example.app.entity.Playlist;
import com.example.app.service.PlaylistService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/playlists")
public class PlaylistController {

    private final PlaylistService playlistService;

    public PlaylistController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    // 1. CREATE
    @PostMapping
    public ResponseEntity<Playlist> createPlaylist(@RequestBody Playlist playlist) {
        Playlist savedPlaylist = playlistService.savePlaylist(playlist);
        return ResponseEntity.ok(savedPlaylist);
    }

    // 2. GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Playlist> getPlaylistById(@PathVariable Long id) {
        Playlist playlist = playlistService.getPlaylistById(id);
        return ResponseEntity.ok(playlist);
    }

    // 3. PATCH - Update only the name by ID
    @PatchMapping("/{id}")
    public ResponseEntity<Playlist> updatePlaylistName(@PathVariable Long id, @RequestBody Playlist playlistDetails) {
        // Calls the specific name-update logic in the PlaylistService
        Playlist updatedPlaylist = playlistService.updatePlaylistName(id, playlistDetails.getPlaylistName());
        return ResponseEntity.ok(updatedPlaylist);
    }

    // 4. DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlaylist(@PathVariable Long id) {
        playlistService.deletePlaylist(id);
        return ResponseEntity.noContent().build();
    }

    // 5. GET ALL
    @GetMapping
    public ResponseEntity<List<Playlist>> getAllPlaylists() {
        return ResponseEntity.ok(playlistService.getAllPlaylists());
    }
}