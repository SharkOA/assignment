package com.sharko.assignment.service;

import com.sharko.assignment.model.EngagementStatistics;
import com.sharko.assignment.model.Video;
import com.sharko.assignment.repository.VideoRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class VideoServiceTest {

    @InjectMocks
    private VideoService videoService;

    @Mock
    private VideoRepository videoRepository;

    public VideoServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testPublishVideo() {
        Video video = new Video();
        video.setTitle("Test Video");

        when(videoRepository.save(any(Video.class))).thenReturn(video);

        Video created = videoService.publishVideo(video);
        assertEquals("Test Video", created.getTitle());
    }

    @Test
    public void testUpdateVideoMetadata() {
        Video video = new Video();
        video.setTitle("Updated Video");

        when(videoRepository.findById(anyLong())).thenReturn(Optional.of(video));
        when(videoRepository.save(any(Video.class))).thenReturn(video);

        Video updated = videoService.updateVideoMetadata(1L, video);
        assertEquals("Updated Video", updated.getTitle());
    }

    @Test
    public void testDelistVideo() {
        Video video = new Video();
        when(videoRepository.findById(anyLong())).thenReturn(Optional.of(video));

        videoService.delistVideo(1L);
        assertFalse(video.isActive());
        verify(videoRepository, times(1)).save(video);
    }

    @Test
    public void testLoadVideo() {
        Video video = new Video();
        video.setTitle("Test Video");
        video.setImpressions(0);

        when(videoRepository.findById(anyLong())).thenReturn(Optional.of(video));
        when(videoRepository.save(any(Video.class))).thenReturn(video);

        Video loaded = videoService.loadVideo(1L);
        assertEquals("Test Video", loaded.getTitle());
        assertEquals(1, loaded.getImpressions());
    }

    @Test
    public void testPlayVideo() {
        Video video = new Video();
        video.setTitle("Test Video");
        video.setViews(0);

        when(videoRepository.findById(anyLong())).thenReturn(Optional.of(video));
        when(videoRepository.save(any(Video.class))).thenReturn(video);

        String result = videoService.playVideo(1L);
        assertEquals("Playing content for video: Test Video", result);
        assertEquals(1, video.getViews());
    }

    @Test
    public void testListAllVideos() {
        Video video = new Video();
        video.setTitle("Test Video");

        when(videoRepository.findAllByIsActiveTrue()).thenReturn(Collections.singletonList(video));

        assertEquals(1, videoService.listAllVideos().size());
    }

    @Test
    public void testSearchVideos() {
        Video video = new Video();
        video.setTitle("Test Video");

        when(videoRepository.findByTitleContainingOrDirectorsNameContainingOrActorsNameContainingOrGenresNameContaining(anyString(), anyString(), anyString(), anyString()))
                .thenReturn(Collections.singletonList(video));

        assertEquals(1, videoService.searchVideos("Test").size());
    }

    @Test
    public void testGetEngagementStatistics() {
        Video video = new Video();
        video.setImpressions(10);
        video.setViews(5);

        when(videoRepository.findById(anyLong())).thenReturn(Optional.of(video));

        EngagementStatistics stats = videoService.getEngagementStatistics(1L);
        assertEquals(10, stats.getImpressions());
        assertEquals(5, stats.getViews());
    }
}