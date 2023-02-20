package com.jayklef.acada.controller;

import com.jayklef.acada.dto.ResponseData;
import com.jayklef.acada.entity.Attachment;
import com.jayklef.acada.service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
}
