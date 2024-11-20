package org.jisu.e_comm_inventory_service.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public interface ImageUploaderService {
    
    Map<?,?> uploadFile(MultipartFile multipartFile, String folderName);
}
