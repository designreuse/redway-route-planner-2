package org.mkhackathon.places.kml;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class FileReaderTest {

    @Test
    public void should_read_art_trail() throws Exception {
        FileReader fileReader = new FileReader("places/mk-public-art.kml");

        String fileContents = fileReader.get();

        assertTrue(fileContents.length() > 0);
    }
}