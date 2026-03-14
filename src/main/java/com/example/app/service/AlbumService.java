package com.example.app.service;

import com.example.app.entity.Album;
import com.example.app.repository.AlbumRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumService {

    private final AlbumRepository albumRepository;

    public AlbumService(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    // 1. CREATE
    public Album saveAlbum(Album album) {
        return albumRepository.save(album);
    }

    // 2. GET ALL
    public List<Album> getAllAlbums() {
        return albumRepository.findAll();
    }

    // 3. GET BY ID
    public Album getAlbumById(Long id) {
        return albumRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Album not found with id: " + id));
    }

    // 4. PATCH (Update name only)
    public Album updateAlbumName(Long id, String newName) {
        Album existingAlbum = getAlbumById(id);
        existingAlbum.setAlbumName(newName);
        return albumRepository.save(existingAlbum);
    }

    // 5. DELETE
    public void deleteAlbum(Long id) {
        Album album = getAlbumById(id);
        albumRepository.delete(album);
    }
}