package com.example.app.repository;

import com.example.app.entity.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {
    // Standard CRUD (Save, Find, Delete) is now available for Albums
}