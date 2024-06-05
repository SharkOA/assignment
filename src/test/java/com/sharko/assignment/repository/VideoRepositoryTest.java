package com.sharko.assignment.repository;

import com.sharko.assignment.model.Video;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class VideoRepositoryTest {

    @Autowired
    private VideoRepository videoRepository;

    @Test
    public void testFindAllByIsActiveTrue() {
        List<Video> activeVideos = videoRepository.findAllByIsActiveTrue();
        assertFalse(activeVideos.isEmpty());
    }

    @Test
    public void testFindByTitleContaining() {
        List<Video> videos = videoRepository.findByTitleContainingOrDirectorsNameContainingOrActorsNameContainingOrGenresNameContaining("Inception", "", "", "");
        assertFalse(videos.isEmpty());
        assertTrue(videos.stream().anyMatch(video -> "Inception".equals(video.getTitle())));
    }
}