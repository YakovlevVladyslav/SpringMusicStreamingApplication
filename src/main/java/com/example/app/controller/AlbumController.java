package com.example.app.controller;

import com.example.app.entity.Album;
import com.example.app.service.AlbumService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/albums")
public class AlbumController {

    private final AlbumService albumService;

    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    // 1. CREATE
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // Можно задать через аннотацию
    public Album createAlbum(@Validated @RequestBody Album album) {
        return albumService.saveAlbum(album);
    }

    // 2. GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Album> getAlbumById(@PathVariable Long id) {
        Album album = albumService.getAlbumById(id);
        return ResponseEntity.ok(album);
    }

    // 3. PATCH -> 200 OK
    @PatchMapping("/{id}")
    public ResponseEntity<Album> updateAlbumName(@PathVariable Long id, @RequestBody Album albumDetails) {
        Album updatedAlbum = albumService.updateAlbumName(id, albumDetails.getAlbumName());
        return ResponseEntity.ok(updatedAlbum);
    }

    // 4. DELETE -> 204 No Content
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlbum(@PathVariable Long id) {
        albumService.deleteAlbum(id);
        return ResponseEntity.noContent().build(); // Статус 204: "Успешно, отвечать нечем"
    }

    // 5. GET ALL -> 200 OK
    @GetMapping
    public List<Album> getAllAlbums() {
        return albumService.getAllAlbums();
    }
}