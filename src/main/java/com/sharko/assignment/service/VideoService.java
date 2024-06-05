package com.sharko.assignment.service;

import com.sharko.assignment.model.EngagementStatistics;
import com.sharko.assignment.model.Video;
import com.sharko.assignment.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoService {

    @Autowired
    private VideoRepository videoRepository;

    /**
     * Impressions and Views is always equals 0 for new videos
     */
    public Video publishVideo(Video video) {
        video.setImpressions(0);
        video.setViews(0);
        return videoRepository.save(video);
    }

    public Video updateVideoMetadata(Long id, Video videoDetails) {
        Video video = videoRepository.findById(id).orElseThrow(() -> new RuntimeException("Video not found"));
        video.setTitle(videoDetails.getTitle());
        video.setSynopsis(videoDetails.getSynopsis());
        video.setYearOfRelease(videoDetails.getYearOfRelease());
        video.setRunningTime(videoDetails.getRunningTime());
        video.setDirectors(videoDetails.getDirectors());
        video.setActors(videoDetails.getActors());
        video.setGenres(videoDetails.getGenres());
        return videoRepository.save(video);
    }

    public void delistVideo(Long id) {
        Video video = videoRepository.findById(id).orElseThrow(() -> new RuntimeException("Video not found"));
        video.setActive(false);
        videoRepository.save(video);
    }

    public Video loadVideo(Long id) {
        Video video = videoRepository.findById(id).orElseThrow(() -> new RuntimeException("Video not found"));
        video.setImpressions(video.getImpressions() + 1);
        videoRepository.save(video);
        return video;
    }

    public String playVideo(Long id) {
        Video video = videoRepository.findById(id).orElseThrow(() -> new RuntimeException("Video not found"));
        video.setViews(video.getViews() + 1);
        videoRepository.save(video);
        return "Playing content for video: " + video.getTitle();
    }

    public List<Video> listAllVideos() {
        return videoRepository.findAllByIsActiveTrue();
    }

    public List<Video> searchVideos(String query) {
        return videoRepository.findByTitleContainingOrDirectorsNameContainingOrActorsNameContainingOrGenresNameContaining(query, query, query, query);
    }

    public EngagementStatistics getEngagementStatistics(Long id) {
        Video video = videoRepository.findById(id).orElseThrow(() -> new RuntimeException("Video not found"));
        return new EngagementStatistics(id, video.getImpressions(), video.getViews());
    }
}