package com.oralcancer.oralcancer.Service;

import com.oralcancer.oralcancer.Entity.Detection;
import com.oralcancer.oralcancer.Repository.DetectionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class DetectionServiceImpl implements DetectionService {
    @Autowired
    DetectionRepo detectionRepo;

    @Override
    public Detection saveImageAndPredict(String path, MultipartFile file) throws IOException {


        String filePath = path + file.getOriginalFilename();
        File imageFile = new File(path);
        if (!imageFile.exists()) imageFile.mkdir();
        Files.copy(file.getInputStream(), Paths.get(filePath));
        String prediction = runMlModel(filePath).trim();
        Detection detection = new Detection(filePath, prediction);
        detectionRepo.save(detection);
        return detection;
    }

    @Override
    public String runMlModel(String filePath) {
        String result = "";
        try {
            ProcessBuilder pb = new ProcessBuilder("python", "Predict.py", filePath);
            pb.directory(new File(System.getProperty("user.dir")));
            Process process = pb.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder output = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line);
            }

            // Extract only the label, e.g., "Predicted label: Cancer"
            String fullOutput = output.toString().trim();
            String label = "Unknown";

            // Try to extract label
            int index = fullOutput.indexOf("Predicted label:");
            if (index != -1) {
                label = fullOutput.substring(index + "Predicted label:".length()).trim();
            }

            result = label;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
