package com.example.app.repository;

import com.example.app.entity.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
    // Standard CRUD operations (save, find, delete) are inherited automatically
}