package com.jayklef.acada.service;

import com.jayklef.acada.entity.Attachment;
import com.jayklef.acada.repository.AttachmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class AttachmentServiceImpl implements AttachmentService{

    @Autowired
    private AttachmentRepository attachmentRepository;
    @Override
    public Attachment saveAttachment(MultipartFile file) {

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            if (fileName.contains("...")) {
                throw new Exception("fileName " + fileName + "contains invalid path sequence");
            }

            Attachment attachment = new Attachment(
                    fileName,
                    file.getContentType(),
                    file.getBytes());

            return attachmentRepository.save(attachment);

        } catch (Exception e) {
            throw new RuntimeException("could not save file whose name is" + fileName);
        }

    }

    @Override
    public Attachment getAttachment(String fileId) {
        return attachmentRepository.findById(fileId)
                .orElseThrow(()-> new RuntimeException("file with id " + fileId + "nt found"));
    }
}
