package com.jayklef.acada.service;

import com.jayklef.acada.entity.Attachment;
import org.springframework.web.multipart.MultipartFile;

public interface AttachmentService {
    Attachment saveAttachment(MultipartFile file);
}
