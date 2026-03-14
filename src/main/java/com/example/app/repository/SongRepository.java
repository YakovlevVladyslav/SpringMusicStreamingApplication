package com.example.app.repository;

import com.example.app.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {
    // Basic CRUD (Save, Find, Delete) is inherited from JpaRepository
}