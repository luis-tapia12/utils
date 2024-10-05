package com.jcontrerast.utils.service;

import com.jcontrerast.utils.core.StorageService;
import com.jcontrerast.utils.exception.ResourceNotFoundException;
import lombok.SneakyThrows;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class StorageServiceImpl implements StorageService {
    @SneakyThrows
    @Override
    public void upload(String baseDir, String fileName, InputStream stream) {
        File directory = new File(baseDir);
        if (!directory.exists() && !directory.mkdirs())
            throw new RuntimeException("There was an error while creating directory");

        Path fileStorageLocation = Paths.get(baseDir);
        Path targetLocation = fileStorageLocation.resolve(fileName);
        Files.copy(stream, targetLocation, StandardCopyOption.REPLACE_EXISTING);
    }

    @SneakyThrows
    @Override
    public Resource download(String baseDir, String fileName) {
        Path fileStorageLocation = Paths.get(baseDir);
        Path filePath = fileStorageLocation.resolve(fileName).normalize();
        Resource resource = new UrlResource(filePath.toUri());

        if (resource.exists()) {
            return resource;
        } else {
            throw new ResourceNotFoundException("The file was not found");
        }
    }

    @SneakyThrows
    @Override
    public void delete(String baseDir, String fileName) {
        Path fileStorageLocation = Paths.get(baseDir);
        Path filePath = fileStorageLocation.resolve(fileName).normalize();

        if (Files.exists(filePath)) {
            Files.delete(filePath);
        } else {
            throw new ResourceNotFoundException("The file was not found");
        }
    }
}
