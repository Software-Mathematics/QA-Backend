package com.dailycodebuffer.filemngt.controller;

import com.dailycodebuffer.filemngt.dto.FileModel;
import com.dailycodebuffer.filemngt.entity.VideoEntity;
import com.dailycodebuffer.filemngt.repository.VideoRepository;
import com.dailycodebuffer.filemngt.service.FileService;
import com.dailycodebuffer.filemngt.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.awt.dnd.DropTarget;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;


@RestController
@RequestMapping("/api")
public class VideoController {

    @Value("${project.video}")
    private String rootPath;

    @Autowired
    private VideoService videoService;

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private FileService fileService;

    @PostMapping("/save")
    public ResponseEntity<?> saveVideo(@RequestBody VideoEntity videoEntity){
        return new ResponseEntity<VideoEntity>(videoService.createPost(videoEntity), HttpStatus.OK);
    }

    @GetMapping("/allVideos")
    public ResponseEntity<?> getAllVideos(){
        return new ResponseEntity<List<VideoEntity>>(videoService.getallPost(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public VideoEntity getVideoById(@PathVariable String id){
        return videoService.getById(id);
    }

    //API for uploading video after posting titles and other video fields
    @PostMapping("/post/{id}")
    public VideoEntity uploadingVideo(@RequestParam("video") MultipartFile video,@PathVariable String id) throws IOException{
//        VideoEntity v = videoService.getById(id);
        FileModel fileModel = fileService.uploadVideo(generateUploadPath(),video);
        VideoEntity v = new VideoEntity(
                id,
                fileModel.getVideoFileName(),
                new Date()
        );
        VideoEntity finallyUpload = videoService.post(v);
        return finallyUpload;
    }


    private String generateUploadPath() {
        SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");
        SimpleDateFormat sdfMonth = new SimpleDateFormat("MMMM", Locale.ENGLISH);
        SimpleDateFormat sdfDate = new SimpleDateFormat("dd");
        Date uploadDate = new Date(); // You might want to extract the actual upload date
        String year = sdfYear.format(uploadDate);
        String month = sdfMonth.format(uploadDate);
        String date = sdfDate.format(uploadDate);

        // Create the subdirectories based on the year, month, and date
        String subdirectories = year + File.separator + month + File.separator + date;

        // Combine the rootPath and the subdirectories to form the final upload path
        String uploadPath = rootPath + File.separator + subdirectories;

        // Create the directories if they don't exist
        new File(uploadPath).mkdirs();

        // Return the final path for saving the video
        return uploadPath;
    }

    @GetMapping(value = "/save/{id}", produces = MediaType.ALL_VALUE)
    public void saveVideo(@PathVariable String id, HttpServletResponse response) throws IOException {
        Optional<VideoEntity> video = videoRepository.findById(id);

        if (video.isPresent()) {
            Date addedDate = video.get().getAddedDate();
            SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");
            SimpleDateFormat sdfMonth = new SimpleDateFormat("MMMM", Locale.ENGLISH);
            SimpleDateFormat sdfDate = new SimpleDateFormat("dd");
            String year = sdfYear.format(addedDate);
            String month = sdfMonth.format(addedDate);
            String date = sdfDate.format(addedDate);

            String videoPath = rootPath + File.separator + year + File.separator + month + File.separator + date;

            InputStream resource = fileService.getVideoFile(videoPath, video.get().getVideoName(), id);

            response.setContentType(MediaType.ALL_VALUE);
            StreamUtils.copy(resource, response.getOutputStream());
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }


    @GetMapping(value = "/play/{id}")
    public void playVideo(@PathVariable String id, HttpServletResponse response) throws IOException {
        Optional<VideoEntity> video = videoRepository.findById(id);

        if (video.isPresent()) {
            Date addedDate = video.get().getAddedDate();
            SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");
            SimpleDateFormat sdfMonth = new SimpleDateFormat("MMMM", Locale.ENGLISH);
            SimpleDateFormat sdfDate = new SimpleDateFormat("dd");
            String year = sdfYear.format(addedDate);
            String month = sdfMonth.format(addedDate);
            String date = sdfDate.format(addedDate);

            String videoPath = rootPath + File.separator + year + File.separator + month + File.separator + date;

            InputStream resource = fileService.getVideoFile(videoPath, video.get().getVideoName(), id);

            response.setContentType("video/mp4");

            response.setHeader("Content-Length", String.valueOf(resource.available()));
            response.setHeader("Content-Disposition", "inline; filename=" + video.get().getVideoName());

            try (OutputStream out = response.getOutputStream()) {
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = resource.read(buffer)) != -1) {
                    out.write(buffer, 0, bytesRead);
                }
            }
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }


}
