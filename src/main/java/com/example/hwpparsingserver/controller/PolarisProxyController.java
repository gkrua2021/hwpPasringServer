package com.example.hwpparsingserver.controller;

import ch.qos.logback.core.CoreConstants;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.ResponseEntity;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

@RestController
@RequestMapping("/api")
public class PolarisProxyController {

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("No file uploaded");
        }
        System.out.println("!@!!!!!!!!");
        try {
            String uploadDir = "C:/Temp/uploaded-files/";
            File directory = new File(uploadDir);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // 파일명 안전하게 변환
            String safeName = file.getOriginalFilename();
            File uploadedFile = new File(uploadDir + safeName);
            file.transferTo(uploadedFile);

            System.out.println("업로드 성공: " + uploadedFile.getAbsolutePath());
            String fileUrl = "/files/" + safeName;
            return ResponseEntity.ok(fileUrl);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("File upload failed: " + e.getMessage());
        }
    }


    // 다운로드 API 추가
    @GetMapping("/download")
    public ResponseEntity<Resource> downloadFile(@RequestParam("filename") String filename) {
        try {
            String uploadDir = "C:/Temp/uploaded-files/";
            File file = new File(uploadDir + filename);
            if (!file.exists()) {
                return ResponseEntity.notFound().build();
            }

            Resource resource = new FileSystemResource(file);
            String encodedFilename = URLEncoder.encode(filename, StandardCharsets.UTF_8);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + encodedFilename + "\"")
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(resource);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/files")
    public ResponseEntity<List<Map<String, Object>>> listFiles() {
        String uploadDir = "C:/Temp/uploaded-files/";
        File directory = new File(uploadDir);
        if (!directory.exists() || !directory.isDirectory()) {
            return ResponseEntity.ok(Collections.emptyList());
        }
        File[] files = directory.listFiles();
        if (files == null) {
            return ResponseEntity.ok(Collections.emptyList());
        }

        List<Map<String, Object>> fileList = new ArrayList<>();
        for (File file : files) {
            if (file.isFile()) {
                Map<String, Object> fileInfo = new HashMap<>();
                fileInfo.put("name", file.getName());
                fileInfo.put("size", file.length());
                fileInfo.put("lastModified", file.lastModified());
                fileList.add(fileInfo);
            }
        }
        return ResponseEntity.ok(fileList);
    }




}
