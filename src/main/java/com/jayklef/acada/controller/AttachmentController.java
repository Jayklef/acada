package com.jayklef.acada.controller;

import com.jayklef.acada.dto.ResponseData;
import com.jayklef.acada.entity.Attachment;
import com.jayklef.acada.service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("api/v1/attachment")
public class AttachmentController {

    @Autowired
    private AttachmentService attachmentService;

    @PostMapping("/upload")
    public ResponseData uploadFile(@RequestParam("file")MultipartFile file){
        Attachment attachment = attachmentService.saveAttachment(file);
        String downloadUrl = "";
        downloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download")
                .path(attachment.getFileId())
                .toUriString();

        return new ResponseData(
                attachment.getFileName(),
                downloadUrl,
                file.getContentType(),
                file.getSize()
        );
    }

    @GetMapping("")
    public ResponseEntity<Resource> downLoadFile(@PathVariable("fileId") String fileId){
        Attachment attachment = attachmentService.getAttachment(fileId);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(attachment.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename = \"" +
                                attachment.getFileName() + "\"")
                .body(new ByteArrayResource(attachment.getData()));

    }
}
