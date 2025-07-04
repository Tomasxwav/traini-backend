package com.traini.traini_backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.traini.traini_backend.enums.Department;
import com.traini.traini_backend.services.VideoServiceImpl;

@RestController
@RequestMapping("/videos")
public class VideoController {
    @Autowired
    private VideoServiceImpl videoService; 

    @PostMapping
    public ResponseEntity<String> uploadVideo(
            @RequestParam("video") MultipartFile video,
            @RequestParam("thumbnail") MultipartFile thumbnail,
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam Department category
            ) {
        
        try {
            String videoUrl = videoService.uploadAndSaveVideo(video, thumbnail, title, description, category);
            return ResponseEntity.ok("Video subido exitosamente. URL: " + videoUrl);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllVideos() {
        try {
            return ResponseEntity.ok(videoService.findAll());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }
}