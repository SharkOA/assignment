package com.sharko.assignment.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sharko.assignment.model.EngagementStatistics;
import com.sharko.assignment.model.Video;
import com.sharko.assignment.service.VideoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(VideoController.class)
public class VideoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VideoService videoService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testPublishVideo() throws Exception {
        Video video = new Video();
        video.setTitle("Test Video");

        when(videoService.publishVideo(any(Video.class))).thenReturn(video);

        mockMvc.perform(post("/api/videos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(video)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Test Video"));
    }

    @Test
    public void testUpdateVideoMetadata() throws Exception {
        Video video = new Video();
        video.setTitle("Updated Video");

        when(videoService.updateVideoMetadata(anyLong(), any(Video.class))).thenReturn(video);

        mockMvc.perform(put("/api/videos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(video)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Updated Video"));
    }

    @Test
    public void testLoadVideo() throws Exception {
        Video video = new Video();
        video.setTitle("Test Video");

        when(videoService.loadVideo(anyLong())).thenReturn(video);

        mockMvc.perform(get("/api/videos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Test Video"));
    }

    @Test
    public void testPlayVideo() throws Exception {
        when(videoService.playVideo(anyLong())).thenReturn("Playing content for video: Test Video");

        mockMvc.perform(get("/api/videos/1/play"))
                .andExpect(status().isOk())
                .andExpect(content().string("Playing content for video: Test Video"));
    }

    @Test
    public void testListAllVideos() throws Exception {
        Video video = new Video();
        video.setTitle("Test Video");

        when(videoService.listAllVideos()).thenReturn(Collections.singletonList(video));

        mockMvc.perform(get("/api/videos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].title").value("Test Video"));
    }

    @Test
    public void testSearchVideos() throws Exception {
        Video video = new Video();
        video.setTitle("Test Video");

        when(videoService.searchVideos(anyString())).thenReturn(Collections.singletonList(video));

        mockMvc.perform(get("/api/videos/search")
                        .param("query", "Test"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].title").value("Test Video"));
    }

    @Test
    public void testGetEngagementStatistics() throws Exception {
        EngagementStatistics engagementStatistics = new EngagementStatistics(1L, 10, 5);

        when(videoService.getEngagementStatistics(anyLong())).thenReturn(engagementStatistics);

        mockMvc.perform(get("/api/videos/1/engagement"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.impressions").value(10))
                .andExpect(jsonPath("$.views").value(5));
    }
}