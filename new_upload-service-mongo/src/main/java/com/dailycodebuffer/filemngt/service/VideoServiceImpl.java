package com.dailycodebuffer.filemngt.service;

import com.dailycodebuffer.filemngt.entity.VideoEntity;
import com.dailycodebuffer.filemngt.exception.ResourceNotFoudException;
import com.dailycodebuffer.filemngt.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class VideoServiceImpl implements VideoService{

    @Autowired
    private VideoRepository videoRepository;

    @Override
    public VideoEntity createPost(VideoEntity videoEntity) {
        if (videoEntity.getDoctorid().isEmpty()) {
            throw new ResourceNotFoudException(false, "Doctorid cannot be null or empty");
        }
        try {
            videoEntity.setAddedDate(new Date());
            VideoEntity video = videoRepository.save(videoEntity);
            return video;
        }catch (Exception e) {
            throw new ResourceNotFoudException(false, "Something went wrong");
        }
    }

    @Override
    public VideoEntity getById(String id) {
        VideoEntity video = videoRepository.findById(id).orElseThrow(()-> new ResourceNotFoudException(false,"Video Id not found"));
        return video;
    }

    @Override
    public VideoEntity updatePost(VideoEntity videoEntity, String id) {
        VideoEntity video = videoRepository.findById(id).get();
        if (video == null){
            video.setId(id);
        }
        video.setDoctorid(videoEntity.getDoctorid());
        video.setPatientid(videoEntity.getPatientid());
        video.setAddedDate(new Date());
        video.setVideoName(videoEntity.getVideoName());
        VideoEntity updateVideosFields = videoRepository.save(video);
        return updateVideosFields;
    }

    @Override
    public VideoEntity post(VideoEntity v) {
        return videoRepository.save(v);
    }

    @Override
    public void deleteVideos(String id) {

    }

    @Override
    public List<VideoEntity> getallPost() {

        return videoRepository.findAll();
    }
}
