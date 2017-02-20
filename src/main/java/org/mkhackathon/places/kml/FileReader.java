package org.mkhackathon.places.kml;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * A convenience class for loading files from the classpath
 * with a given file name.
 */
public final class FileReader {

    private final String fileName;

    public FileReader(String fileName) {
        this.fileName = fileName;
    }

    public String get() {
        try {
            Path path =
                    Paths.get(ClassLoader.getSystemResource(fileName).toURI());
            return new String(Files.readAllBytes(path));
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

}
