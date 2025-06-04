package com.dailycodebuffer.filemngt.service;

import com.dailycodebuffer.filemngt.dto.AttachmentDTO;
import com.dailycodebuffer.filemngt.entity.Attachment;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface AttachmentService {
    Attachment saveDownloadURL(Attachment attachment, String url);
    Attachment saveAttachment(MultipartFile file, Map<String, String> attachmentDTO, String downloadURL) throws Exception;

    Attachment getAttachment(String fileId) throws Exception;

    String delete(String fileId);

    List<AttachmentDTO> getAll(String code);
    List<AttachmentDTO> getByCodeAndModel(String code, String model);

    Attachment saveFileAndMetadata(MultipartFile file, Map<String, String> attachmentDTO, String baseDownloadURL, String uploadDir) throws Exception;
}
