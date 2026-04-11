package com.example.app.controller;

import com.example.app.entity.Playlist;
import com.example.app.service.PlaylistService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/playlists")
public class PlaylistController {

    private final PlaylistService playlistService;

    public PlaylistController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    // 1. CREATE -> 201 Created
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Playlist createPlaylist(@RequestBody Playlist playlist) {
        return playlistService.savePlaylist(playlist);
    }

    // 2. GET BY ID -> 200 OK
    @GetMapping("/{id}")
    public Playlist getPlaylistById(@PathVariable Long id) {
        return playlistService.getPlaylistById(id);
    }

    // 3. PATCH -> 200 OK
    @PatchMapping("/{id}")
    public Playlist updatePlaylistName(@PathVariable Long id, @RequestBody Playlist playlistDetails) {
        return playlistService.updatePlaylistName(id, playlistDetails.getPlaylistName());
    }

    // 4. DELETE -> 204 No Content
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlaylist(@PathVariable Long id) {
        playlistService.deletePlaylist(id);
        return ResponseEntity.noContent().build();
    }

    // 5. GET ALL -> 200 OK
    @GetMapping
    public List<Playlist> getAllPlaylists() {
        return playlistService.getAllPlaylists();
    }
}