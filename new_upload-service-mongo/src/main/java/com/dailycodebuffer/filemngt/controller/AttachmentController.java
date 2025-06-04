package com.dailycodebuffer.filemngt.controller;

import com.dailycodebuffer.filemngt.dto.AttachmentDTO;
import com.dailycodebuffer.filemngt.dto.ResponseData;
import com.dailycodebuffer.filemngt.entity.Attachment;
import com.dailycodebuffer.filemngt.error.ErrorHandler;
import com.dailycodebuffer.filemngt.service.AttachmentService;
import com.dailycodebuffer.filemngt.util.Util;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/attachment")
public class AttachmentController {

    private AttachmentService attachmentService;

    public AttachmentController(AttachmentService attachmentService) {
        this.attachmentService = attachmentService;
    }

    @Value("${base.folder}")
    private String rootPath;

    @PostMapping("/upload")
    public ResponseData uploadFile(@RequestParam(value = "body", required = false) String dto, @RequestParam("file")MultipartFile file, HttpServletRequest request) throws Exception {
        Map<String, String> attachmentDTO = new HashMap<>();
        if(dto != null) {
            attachmentDTO = new ObjectMapper().readValue(dto, HashMap.class);
        }
        System.out.println(attachmentDTO);
        Attachment attachment = null;
        String downloadURl = "";

        attachment = attachmentService.saveAttachment(file, attachmentDTO, downloadURl);
        downloadURl = Util.getBaseUrl(request) + "/api/attachment/download/" + attachment.getId();


        System.out.println("downloadURl: "+ downloadURl);
        attachmentService.saveDownloadURL(attachment, downloadURl);
        return new ResponseData(
                attachment.getId(),
                attachment.getFilename(),
                downloadURl,
                file.getContentType(),
                file.getSize());
    }

    @RequestMapping(value="/download/{fileId}", method=RequestMethod.GET)
    public ResponseEntity<byte[]> getPDF1(@PathVariable String fileId) throws Exception {
        Attachment attachment = attachmentService.getAttachment(fileId);

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.parseMediaType(attachment.getFiletype()));
        String filename = attachment.getFilename();
        byte[] pdf1Bytes = attachment.getData();

        headers.add("content-disposition", "inline;filename=" + filename);

//        headers.setContentDispositionFormData(filename, filename);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

        ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(pdf1Bytes, headers, HttpStatus.OK);
        return response;
    }

//    @GetMapping("/download/{fileId:.+}")
//    public ResponseEntity<Resource> downloadFile(@PathVariable String fileId,  HttpServletRequest request) throws Exception {
//        Attachment attachment = null;
//        attachment = attachmentService.getAttachment(fileId);
//        Resource resource = new ByteArrayResource(attachment.getData());
//        String contentType = null;
//        try {
//            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
//        }catch (Exception e){
//            System.out.println("e = " + e);
//        }
//        if (contentType == null){
//            contentType ="application/octet-stream";
//        }
//        return  ResponseEntity.ok()
////                .contentType(MediaType.parseMediaType(attachment.getFileType()))
//                .contentType(MediaType.parseMediaType(contentType))
////                .header(HttpHeaders.CONTENT_DISPOSITION,
////                        "attachment; filename=\"" + attachment.getFileName()
////                + "\"")
//                .header(HttpHeaders.CONTENT_DISPOSITION,
//                        "attachment; filename=" + attachment.getFileName()
//                                + ";")
//                .body(resource);
//    }
    @DeleteMapping("/delete/{fileId}")
    public String deleteFile(@PathVariable String fileId) throws Exception {

        return attachmentService.delete(fileId);

    }
    @GetMapping("/getAll/code/{code}")
    public List<AttachmentDTO> getAll(@PathVariable String code) throws Exception {

        return attachmentService.getAll(code);

    }

    @GetMapping("/getAll/code/{code}/model/{model}")
    public List<AttachmentDTO> getAll(@PathVariable String code, @PathVariable String model) throws Exception {
        return attachmentService.getByCodeAndModel(code, model);
    }

    @PostMapping("/uploadV2")
    public Object uploadFileV2(@RequestHeader(value = "X-Tenant", required = false) String tenant,
                               @RequestParam(value = "body", required = false) String dto,
                               @RequestParam(value = "file", required = false) MultipartFile file,
                               HttpServletRequest request) {
        try {
            if (tenant == null || tenant.trim().isEmpty()) {
                tenant = "Base";
            }
            if (file == null) {
                return new ResponseData(ErrorHandler.getErrorMessage("K001"), "K001", HttpStatus.BAD_REQUEST);
            }
            String baseDownloadURL = Util.getBaseUrl(request) + "/api/attachment/download/";
            String uploadDir = rootPath + "/" + tenant + "/";
            Map<String, String> attachmentDTO = dto != null
                    ? new ObjectMapper().readValue(dto, HashMap.class)
                    : new HashMap<>();
            if (file.isEmpty()) {
                return new ResponseData(ErrorHandler.getErrorMessage("K001"), "K001", HttpStatus.BAD_REQUEST);
            }
            String contentType = file.getContentType();
            if (contentType == null || !(contentType.startsWith("image/") || contentType.startsWith("video/"))) {
                return new ResponseData(ErrorHandler.getErrorMessage("K002"), "K002", HttpStatus.BAD_REQUEST);
            }
            Attachment savedAttachment = attachmentService.saveFileAndMetadata(file, attachmentDTO, baseDownloadURL, uploadDir);
            String downloadURL = baseDownloadURL + savedAttachment.getId();
            return new ResponseData(
                    savedAttachment.getId(),
                    savedAttachment.getFilename(),
                    downloadURL,
                    savedAttachment.getFiletype(),
                    file.getSize()
            );
        } catch (JsonProcessingException e) {
            return new ResponseData("Error parsing metadata: " + e.getMessage(), "K006", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseData("Error: " + e.getMessage(), "K007", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/downloadV2/{fileId}", method = RequestMethod.GET)
    public ResponseEntity<?> getPDF1V2(@PathVariable String fileId) {
        try {
            Attachment attachment = attachmentService.getAttachment(fileId);
            String filePath = attachment.getFilepath();
            File file = new File(filePath);
            if (!file.exists() || !file.isFile()) {
                return ResponseEntity.badRequest().body(
                        new ResponseData(ErrorHandler.getErrorMessage("K003"), "K003", HttpStatus.BAD_REQUEST)
                );
            }
            byte[] fileBytes = Files.readAllBytes(file.toPath());
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType(attachment.getFiletype()));
            headers.add("Content-Disposition", "inline;filename=" + attachment.getFilename());
            headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
            return new ResponseEntity<>(fileBytes, headers, HttpStatus.OK);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body(
                    new ResponseData("Error: Unable to read file", "K005", HttpStatus.INTERNAL_SERVER_ERROR)
            );
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(
                    new ResponseData(e.getMessage(), "K003", HttpStatus.INTERNAL_SERVER_ERROR)
            );
        }
    }
}
