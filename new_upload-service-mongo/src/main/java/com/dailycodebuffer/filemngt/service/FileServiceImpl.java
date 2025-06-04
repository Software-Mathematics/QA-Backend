package com.dailycodebuffer.filemngt.service;

import com.dailycodebuffer.filemngt.dto.FileModel;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService{


    @Override
    public FileModel uploadVideo(String path, MultipartFile file) throws IOException {
        FileModel fileModel = new FileModel();
        String fileName = file.getOriginalFilename();

        //trying to generate random names and uniques
        String randomId = UUID.randomUUID().toString();
        String finalName = randomId.concat(fileName).substring(fileName.indexOf("."));

        //file full path
        String filePath = path + File.separator + finalName;

        //creating directory to save file
        File f = new File(path);
        if(!f.exists()) {
            f.mkdir();
        }
        Files.copy(file.getInputStream(), Paths.get(filePath));
        fileModel.setVideoFileName(finalName);
        return fileModel;
    }

    @Override
    public InputStream getVideoFile(String path, String fileName, String id) throws FileNotFoundException {
        String fullPath = path + File.separator + fileName;
        InputStream inputStream = new FileInputStream(fullPath);
        return inputStream;
    }
}
