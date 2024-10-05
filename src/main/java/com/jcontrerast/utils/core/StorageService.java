package com.jcontrerast.utils.core;

import org.springframework.core.io.Resource;

import java.io.InputStream;

public interface StorageService {
    void upload(String baseDir, String fileName, InputStream stream);

    Resource download(String baseDir, String fileName);

    void delete(String baseDir, String fileName);
}
