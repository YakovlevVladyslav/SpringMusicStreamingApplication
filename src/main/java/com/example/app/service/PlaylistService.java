package com.example.app.service;

import com.example.app.entity.Playlist;
import com.example.app.repository.PlaylistRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaylistService {

    private final PlaylistRepository playlistRepository;

    public PlaylistService(PlaylistRepository playlistRepository) {
        this.playlistRepository = playlistRepository;
    }

    /**
     * Create a new playlist
     */
    public Playlist savePlaylist(Playlist playlist) {
        return playlistRepository.save(playlist);
    }

    /**
     * Get all playlists
     */
    public List<Playlist> getAllPlaylists() {
        return playlistRepository.findAll();
    }

    /**
     * Get a single playlist by ID
     */
    public Playlist getPlaylistById(Long id) {
        return playlistRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Playlist not found with id: " + id));
    }

    /**
     * Update playlist name (PATCH logic)
     */
    public Playlist updatePlaylistName(Long id, String newName) {
        Playlist existingPlaylist = getPlaylistById(id);
        // Matches the field 'playlistName' in Playlist.java
        existingPlaylist.setPlaylistName(newName);
        return playlistRepository.save(existingPlaylist);
    }

    /**
     * Delete a playlist
     */
    public void deletePlaylist(Long id) {
        Playlist playlist = getPlaylistById(id);
        playlistRepository.delete(playlist);
    }
}