package com.dailycodebuffer.filemngt.service;

import com.dailycodebuffer.filemngt.entity.VideoEntity;

import java.util.List;

public interface VideoService {

    public VideoEntity createPost(VideoEntity videoEntity);

    public VideoEntity getById(String id);
    public VideoEntity updatePost(VideoEntity videoEntity,String id);
    VideoEntity post(VideoEntity v);
    public void deleteVideos(String id);
    public List<VideoEntity> getallPost();

}
