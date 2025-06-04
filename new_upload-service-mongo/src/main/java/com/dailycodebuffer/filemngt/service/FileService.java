package com.dailycodebuffer.filemngt.service;

import com.dailycodebuffer.filemngt.dto.FileModel;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public interface FileService {

    FileModel uploadVideo(String path, MultipartFile file) throws IOException;

    InputStream getVideoFile(String path, String fileName, String id) throws FileNotFoundException;

}