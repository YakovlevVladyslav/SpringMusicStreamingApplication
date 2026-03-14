package com.example.app.service;

import com.example.app.entity.Song;
import com.example.app.repository.SongRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongService {

    private final SongRepository songRepository;

    public SongService(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    /**
     * Create or save a song track.
     */
    public Song saveSong(Song song) {
        return songRepository.save(song);
    }

    /**
     * Retrieve all songs in the database.
     */
    public List<Song> getAllSongs() {
        return songRepository.findAll();
    }

    /**
     * Find a specific song by its ID.
     */
    public Song getSongById(Long id) {
        return songRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Song not found with id: " + id));
    }

    /**
     * Update the song name (PATCH logic).
     */
    public Song updateSongName(Long id, String newName) {
        Song existingSong = getSongById(id);
        existingSong.setSongName(newName); // Matches @Data generated setter for songName
        return songRepository.save(existingSong);
    }

    /**
     * Delete a song by ID.
     */
    public void deleteSong(Long id) {
        Song song = getSongById(id);
        songRepository.delete(song);
    }
}