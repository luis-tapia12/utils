package com.jcontrerast.utils.service;

import com.jcontrerast.utils.core.StorageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.springframework.core.io.Resource;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import static org.junit.jupiter.api.Assertions.*;

class StorageServiceTest {
    @TempDir
    private File tmpDir;
    private StorageService service;

    private final static String FILE_NAME = "test.txt";
    private final static String SECOND_FILE_NAME = "second.txt";
    private final static String FILE_CONTENT = "Hello World";

    @BeforeEach
    void setUp() throws IOException {
        service = new StorageServiceImpl();

        Path fileStorageLocation = Paths.get(tmpDir.getAbsolutePath());
        Path targetLocation = fileStorageLocation.resolve(FILE_NAME);
        Files.copy(new ByteArrayInputStream(FILE_CONTENT.getBytes()), targetLocation, StandardCopyOption.REPLACE_EXISTING);
    }

    @Test
    void testUpload() {
        InputStream stream = new ByteArrayInputStream(FILE_CONTENT.getBytes());
        service.upload(tmpDir.getAbsolutePath(), SECOND_FILE_NAME, stream);

        Path fileStorageLocation = Paths.get(tmpDir.getAbsolutePath());
        Path filePath = fileStorageLocation.resolve(SECOND_FILE_NAME).normalize();
        assertTrue(Files.exists(filePath));
    }

    @Test
    void testDownload() throws IOException {
        Resource resource = service.download(tmpDir.getAbsolutePath(), FILE_NAME);

        assertEquals(FILE_NAME, resource.getFilename());
        assertEquals(FILE_CONTENT, resource.getContentAsString(StandardCharsets.UTF_8));
    }

    @Test
    void testDelete() throws IOException {
        service.delete(tmpDir.getAbsolutePath(), FILE_NAME);

        Path fileStorageLocation = Paths.get(tmpDir.getAbsolutePath());
        Path filePath = fileStorageLocation.resolve(FILE_NAME).normalize();
        assertFalse(Files.exists(filePath));
    }

    @Test
    void testUpload_whenDirectoryDoesNotExist() {
        String path = tmpDir.getAbsolutePath() + "/test/";
        InputStream stream = new ByteArrayInputStream(FILE_CONTENT.getBytes());
        service.upload(path, SECOND_FILE_NAME, stream);

        Path fileStorageLocation = Paths.get(path);
        Path filePath = fileStorageLocation.resolve(SECOND_FILE_NAME).normalize();
        assertTrue(Files.exists(filePath));
    }

    @Test
    void testUpload_whenDirectoryDoesNotExist_fails() {
        InputStream stream = new ByteArrayInputStream(FILE_CONTENT.getBytes());

        assertThrows(RuntimeException.class, () -> service.upload(tmpDir.getAbsolutePath() + "/ test /*/\\", SECOND_FILE_NAME, stream));
    }

    @Test
    void testDownload_fails() throws IOException {
        assertThrows(RuntimeException.class, () -> service.download(tmpDir.getAbsolutePath(), SECOND_FILE_NAME));
    }

    @Test
    void testDelete_fails() throws IOException {
        assertThrows(RuntimeException.class, () -> service.delete(tmpDir.getAbsolutePath(), SECOND_FILE_NAME));
    }

}
