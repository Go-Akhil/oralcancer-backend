package com.oralcancer.oralcancer.Controller;

import com.oralcancer.oralcancer.Entity.Detection;
import com.oralcancer.oralcancer.Service.DetectionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/upload")
@CrossOrigin(origins = "http://localhost:5174")
public class ImageUploadController {
    @Autowired
    DetectionServiceImpl detectionServiceimpl;
    @Value("${project.image}")
    private String path;


    @PostMapping
    public ResponseEntity<Detection> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            System.out.println("Received file: " + file.getOriginalFilename());
            Detection result = detectionServiceimpl.saveImageAndPredict(path, file);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

}
