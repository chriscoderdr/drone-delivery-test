package com.velozient.dronedelivery.readers.file;

import com.velozient.dronedelivery.readers.DeliveryReader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DeliveryReaderFile implements DeliveryReader {
    String filePath;

    public DeliveryReaderFile(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public String read() throws IOException {
        return Files.readString(Paths.get(filePath));
    }
}
