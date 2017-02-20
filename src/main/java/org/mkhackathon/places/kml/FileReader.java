package org.mkhackathon.places.kml;

import org.springframework.core.io.ClassPathResource;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

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
            return readFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String readFile() throws IOException {
        final ClassPathResource cpr = new ClassPathResource(fileName);
        final byte[] bdata = FileCopyUtils.copyToByteArray(cpr.getInputStream());
        return new String(bdata, StandardCharsets.UTF_8);
    }

}
