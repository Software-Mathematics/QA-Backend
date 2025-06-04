package com.dailycodebuffer.filemngt.service;

import com.dailycodebuffer.filemngt.dto.AttachmentDTO;
import com.dailycodebuffer.filemngt.entity.Attachment;
import com.dailycodebuffer.filemngt.error.ErrorHandler;
import com.dailycodebuffer.filemngt.repository.AttachmentRepository;
import com.dailycodebuffer.filemngt.util.AutoGenerator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class AttachmentServiceImpl implements AttachmentService {

    private AttachmentRepository attachmentRepository;

    public AttachmentServiceImpl(AttachmentRepository attachmentRepository) {
        this.attachmentRepository = attachmentRepository;
    }

    @Autowired
    private AutoGenerator autoGenerator;

    @Override
    public Attachment saveDownloadURL(Attachment attachment, String url) {
        Attachment res = new Attachment();
        if (attachment.getId() != null) {
            attachment.setDownloadurl(url);
            res = attachmentRepository.save(attachment);
        }
        return res;
    }

    @Override
    public Attachment saveAttachment(MultipartFile file, Map<String, String> attachmentDTO, String downloadURL) throws Exception {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if (fileName.contains("..")) {
                throw new Exception("Filename contains invalid path sequence "
                        + fileName);
            }

            Attachment attachment
                    = new Attachment(fileName,
                    file.getContentType(),
                    file.getBytes(),
                    attachmentDTO.get("username"),
                    attachmentDTO.get("resourcecode"),
                    attachmentDTO.get("name"),
                    attachmentDTO.get("description"),
                    attachmentDTO.get("code"),
                    downloadURL

            );
            attachment.setDoctype(attachmentDTO.get("doctype"));
            attachment.setUniquecode(attachmentDTO.get("uniquecode"));
            attachment.setUniquecode(attachmentDTO.get("uniquecode"));
            attachment.setId(autoGenerator.getSequenceNumber());
            attachment.setModel(attachmentDTO.get("model"));
            return attachmentRepository.save(attachment);
//            String downloadURl = ServletUriComponentsBuilder.fromCurrentContextPath()
//                   .path("/api/attachment/download/")
//                   .path(attachment.getId())
//                   .toUriString();
//            attachment.setDownloadURL(downloadURl);
//           return attachmentRepository.save(attachment);
        } catch (Exception e) {
            throw new Exception("Could not save File: " + fileName);
        }
    }

    @Override
    public Attachment getAttachment(String fileId) throws Exception {
        return attachmentRepository
                .findById(fileId)
                .orElseThrow(
                        () -> new Exception("File not found with Id: " + fileId));
    }

    @Override
    public String delete(String fileId) {
        attachmentRepository
                .deleteById(fileId);
        return "delete Successfully";
    }

    @Override
    public List<AttachmentDTO> getAll(String code) {
        List<AttachmentDTO> response = new ArrayList<>();
        List<Attachment> entityList = attachmentRepository.findByCodeIgnoreCase(code);
        for (Attachment entity : entityList) {
            AttachmentDTO dto = new AttachmentDTO();
            BeanUtils.copyProperties(entity, dto);
            response.add(dto);
        }
        return response;
    }

    @Override
    public List<AttachmentDTO> getByCodeAndModel(String code, String model) {
        List<AttachmentDTO> response = new ArrayList<>();
        List<Attachment> entityList = attachmentRepository.findByCodeIgnoreCaseAndModelIgnoreCase(code, model.trim());
        for (Attachment entity : entityList) {
            AttachmentDTO dto = new AttachmentDTO();
            BeanUtils.copyProperties(entity, dto);
            response.add(dto);
        }
        return response;
    }

    public Attachment saveFileAndMetadata(MultipartFile file, Map<String, String> attachmentDTO, String baseDownloadURL, String uploadDir) throws Exception {
        try {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            String fileType = file.getContentType();
            String uniqueFileName = (System.currentTimeMillis() + "_" + fileName).replace(" ", "");
            Path filePath = Paths.get(uploadDir + uniqueFileName);
            try {
                Files.createDirectories(filePath.getParent());
                Files.write(filePath, file.getBytes());
            } catch (IOException e) {
                String errorMessage = ErrorHandler.getErrorMessage("K004");
                throw new Exception(errorMessage, e);
            }
            Attachment attachment = new Attachment(
                    fileName,
                    fileType,
                    null,
                    attachmentDTO.getOrDefault("username", "Unknown"),
                    attachmentDTO.get("resourcecode"),
                    attachmentDTO.get("name"),
                    attachmentDTO.get("description"),
                    attachmentDTO.get("code"),
                    baseDownloadURL + uniqueFileName
            );
            attachment.setDoctype(attachmentDTO.get("doctype"));
            attachment.setUniquecode(attachmentDTO.get("uniquecode"));
            attachment.setModel(attachmentDTO.get("model"));
            attachment.setId(autoGenerator.getSequenceNumber());
            attachment.setFilepath(String.valueOf(filePath));
            return attachmentRepository.save(attachment);
        } catch (IOException e) {
            String errorMessage = ErrorHandler.getErrorMessage("K005");
            throw new Exception(errorMessage + " - File I/O error occurred", e);
        } catch (NullPointerException e) {
            String errorMessage = ErrorHandler.getErrorMessage("K005");
            throw new Exception(errorMessage + " - Missing required metadata", e);
        } catch (Exception e) {
            String errorMessage = ErrorHandler.getErrorMessage("K005");
            throw new Exception(errorMessage + " - Unexpected error occurred", e);
        }
    }

}
