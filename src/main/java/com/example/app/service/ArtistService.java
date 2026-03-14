package com.example.app.service;

import com.example.app.entity.Artist;
import com.example.app.repository.ArtistRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistService {
    private final ArtistRepository artistRepository;

    public ArtistService(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    // --- ARTIST BUSINESS LOGIC ---

    /**
     * Create a new artist
     */
    public Artist saveArtist(Artist artist) {
        return artistRepository.save(artist);
    }

    /**
     * Get all artists for the prototype list
     */
    public List<Artist> getAllArtists() {
        return artistRepository.findAll();
    }

    /**
     * Get a single artist or throw an error if the ID is wrong
     */
    public Artist getArtistById(Long id) {
        return artistRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Artist not found with id: " + id));
    }

    /**
     * Update artist: Finds existing record, maps new values, and saves.
     * Currently only updates the artist name.
     */
    public Artist updateArtistName(Long id, String newName) {
        Artist existingArtist = getArtistById(id); // Uses the method above
        existingArtist.setArtistName(newName);
        return artistRepository.save(existingArtist);
    }

    /**
     * Delete an artist from the database
     */
    public void deleteArtist(Long id) {
        Artist artist = getArtistById(id);
        artistRepository.delete(artist);
    }

}
