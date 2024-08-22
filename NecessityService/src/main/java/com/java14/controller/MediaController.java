package com.java14.controller;


import com.java14.dto.response.ResponseDto;
import com.java14.service.EquipmentsService;
import com.java14.service.MediaService;

import lombok.RequiredArgsConstructor;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.java14.constant.EndPoints.MEDIA;

@CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT,RequestMethod.DELETE})
@RequiredArgsConstructor
@RequestMapping(MEDIA)
@RestController
public class MediaController {
    private final EquipmentsService equipmentsService;

    private final MediaService mediaService;

    @PostMapping(value = "/add-img")
    public ResponseEntity<ResponseDto<String>> uploadAvatarFile(@RequestParam("file") MultipartFile file) throws IOException {
        String filePath = mediaService.handleFileUpload(file);
        return ResponseEntity.ok(
                ResponseDto.<String>builder()
                        .data(filePath)
                        .message("ok")
                        .code(200)
                        .build()
        );
    }

    @GetMapping("/images")
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) throws MalformedURLException {
        Path file = Paths.get("C:/Users/ASUS/Desktop/Resimler2").resolve(filename);
        Resource fileResource = new UrlResource(file.toUri());
        return ResponseEntity.ok().body(fileResource);
    }







}

