package com.sharko.assignment.controller;

import com.sharko.assignment.model.EngagementStatistics;
import com.sharko.assignment.model.Video;
import com.sharko.assignment.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * VideoController is the main and the only RestController for this application.
 * Later we can split VideoController and engagement statistics functionality,
 * but now we don't store engagements as specific entities (likes, reactions by user).
 */
@RestController
@RequestMapping("/api/videos")
public class VideoController {

    @Autowired
    private VideoService videoService;

    // Publish a video
    @PostMapping
    public ResponseEntity<Video> publishVideo(@RequestBody Video video) {
        Video createdVideo = videoService.publishVideo(video);
        return ResponseEntity.ok(createdVideo);
    }

    // Add and Edit video metadata
    @PutMapping("/{id}")
    public ResponseEntity<Video> updateVideoMetadata(@PathVariable Long id, @RequestBody Video videoDetails) {
        Video updatedVideo = videoService.updateVideoMetadata(id, videoDetails);
        return ResponseEntity.ok(updatedVideo);
    }

    // Delist (soft delete) a video
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delistVideo(@PathVariable Long id) {
        videoService.delistVideo(id);
        return ResponseEntity.noContent().build();
    }

    // Load a video (metadata and content mock)
    @GetMapping("/{id}")
    public ResponseEntity<Video> loadVideo(@PathVariable Long id) {
        Video video = videoService.loadVideo(id);
        return ResponseEntity.ok(video);
    }

    // Play a video (return content mock)
    @GetMapping("/{id}/play")
    public ResponseEntity<String> playVideo(@PathVariable Long id) {
        String content = videoService.playVideo(id);
        return ResponseEntity.ok(content);
    }

    // List all available videos
    @GetMapping
    public ResponseEntity<List<Video>> listAllVideos() {
        List<Video> videos = videoService.listAllVideos();
        return ResponseEntity.ok(videos);
    }

    // Search for videos based on criteria
    @GetMapping("/search")
    public ResponseEntity<List<Video>> searchVideos(@RequestParam String query) {
        List<Video> videos = videoService.searchVideos(query);
        return ResponseEntity.ok(videos);
    }

    // Retrieve engagement statistics for a video
    @GetMapping("/{id}/engagement")
    public ResponseEntity<EngagementStatistics> getEngagementStatistics(@PathVariable Long id) {
        EngagementStatistics stats = videoService.getEngagementStatistics(id);
        return ResponseEntity.ok(stats);
    }
}