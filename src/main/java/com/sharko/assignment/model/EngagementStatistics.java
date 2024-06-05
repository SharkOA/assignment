package com.sharko.assignment.model;

import lombok.Data;


@Data
public class EngagementStatistics {

    private Long videoId;
    private int impressions;
    private int views;

    public EngagementStatistics(Long videoId, int impressions, int views) {
        this.videoId = videoId;
        this.impressions = impressions;
        this.views = views;
    }

}