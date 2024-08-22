package com.java14.service;



import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class MediaService {



        public String handleFileUpload(MultipartFile file) {
            if (file.isEmpty()) {
                return "File is empty";
            }

            try {
                // Dosya kaydetme yolu
                String uploadDir = "C:\\Users\\ASUS\\Desktop\\Resimler2\\";
                File directory = new File(uploadDir);
                if (!directory.exists()) {
                    directory.mkdirs();
                }

                // Dosyayı belirlenen konuma kaydet
                String filePath = uploadDir + file.getOriginalFilename();
                File dest = new File(filePath);
                file.transferTo(dest);

                // Dosya yolu ile birlikte döndür
                return filePath;
            } catch (IOException e) {
                e.printStackTrace();
                return "Failed to upload file";
            }
        }
    }












