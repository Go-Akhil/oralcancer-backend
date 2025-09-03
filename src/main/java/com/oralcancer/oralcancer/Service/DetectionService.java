package com.oralcancer.oralcancer.Service;

import com.oralcancer.oralcancer.Entity.Detection;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface DetectionService {
    Detection saveImageAndPredict(String path, MultipartFile file) throws IOException;
    String runMlModel(String filePath);
}
