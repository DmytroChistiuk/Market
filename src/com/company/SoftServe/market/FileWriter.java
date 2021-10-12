package com.company.SoftServe.market;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.stream.Stream;

import static java.nio.charset.StandardCharsets.*;

public class FileWriter {
    public static void write(String fileName, StringBuilder messageToWrite) {
        try {
            Files.writeString(Paths.get(fileName),
                    messageToWrite,
                    StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

