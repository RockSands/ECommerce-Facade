package com.ec.upload.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author bystander
 * @date 2018/9/16
 */
public interface UploadService {

    String uploadImage(MultipartFile file);
}
