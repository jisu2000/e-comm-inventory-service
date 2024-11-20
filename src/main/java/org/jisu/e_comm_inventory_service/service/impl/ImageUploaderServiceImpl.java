package org.jisu.e_comm_inventory_service.service.impl;

import java.util.Map;

import org.jisu.e_comm_inventory_service.service.ImageUploaderService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImageUploaderServiceImpl implements ImageUploaderService {

    private final Cloudinary cloudinary;
    @Override
    public Map<?, ?> uploadFile(MultipartFile multipartFile, String folderName) {

        Map<?,?> imageRes = null;
        try {
            imageRes = cloudinary.uploader().upload(multipartFile.getBytes(), Map.of("folder",folderName));
        } catch (Exception e) {
        }

        return imageRes;
    }

}
