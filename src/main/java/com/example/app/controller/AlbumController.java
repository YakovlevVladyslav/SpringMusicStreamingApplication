package com.example.app.controller;

import com.example.app.entity.Album;
import com.example.app.service.AlbumService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/albums")
public class AlbumController {

    private final AlbumService albumService;

    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    // 1. CREATE
    @PostMapping
    public ResponseEntity<Album> createAlbum(@RequestBody Album album) {
        Album savedAlbum = albumService.saveAlbum(album);
        return ResponseEntity.ok(savedAlbum);
    }

    // 2. GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Album> getAlbumById(@PathVariable Long id) {
        Album album = albumService.getAlbumById(id);
        return ResponseEntity.ok(album);
    }

    // 3. PATCH - Update only the name by ID
    @PatchMapping("/{id}")
    public ResponseEntity<Album> updateAlbumName(@PathVariable Long id, @RequestBody Album albumDetails) {
        // Calls the specific name-update logic in the service
        Album updatedAlbum = albumService.updateAlbumName(id, albumDetails.getAlbumName());
        return ResponseEntity.ok(updatedAlbum);
    }

    // 4. DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlbum(@PathVariable Long id) {
        albumService.deleteAlbum(id);
        return ResponseEntity.noContent().build();
    }

    // 5. GET ALL
    @GetMapping
    public ResponseEntity<List<Album>> getAllAlbums() {
        return ResponseEntity.ok(albumService.getAllAlbums());
    }
}