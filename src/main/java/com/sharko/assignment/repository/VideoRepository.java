package com.sharko.assignment.repository;

import com.sharko.assignment.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VideoRepository extends JpaRepository<Video, Long> {
    List<Video> findAllByIsActiveTrue();

    List<Video> findByTitleContainingOrDirectorsNameContainingOrActorsNameContainingOrGenresNameContaining(
            String title, String directorName, String actorName, String genreName);
}