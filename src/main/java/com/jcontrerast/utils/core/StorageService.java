package com.jcontrerast.utils.core;

import com.jcontrerast.utils.exception.ResourceNotFoundException;
import org.springframework.core.io.Resource;

import java.io.InputStream;

public interface StorageService {
    /**
     * Uploads a file to the specified directory.
     *
     * <p>This method creates the directory if it does not exist and
     * uploads the file with the given name.
     * If the file already exists, it will be replaced.</p>
     *
     * @param baseDir the directory to which the file will be uploaded
     * @param fileName the name of the file to be uploaded
     * @param stream the InputStream containing the file data
     * @throws RuntimeException if there is an error while creating the directory
     */
    void upload(String baseDir, String fileName, InputStream stream);

    /**
     * Downloads a file from the specified directory.
     *
     * <p>This method returns a Resource representing the file with the given name.</p>
     *
     * @param baseDir the directory from which the file will be downloaded
     * @param fileName the name of the file to be downloaded
     * @return a {@link Resource} representing the downloaded file
     * @throws ResourceNotFoundException if the file is not found
     */
    Resource download(String baseDir, String fileName);

    /**
     * Deletes a file from the specified directory.
     *
     * <p>This method removes the file with the given name from the directory.</p>
     *
     * @param baseDir the directory from which the file will be deleted
     * @param fileName the name of the file to be deleted
     * @throws ResourceNotFoundException if the file is not found
     */
    void delete(String baseDir, String fileName);
}
