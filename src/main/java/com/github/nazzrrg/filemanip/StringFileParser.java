package com.github.nazzrrg.filemanip;


import lombok.SneakyThrows;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class StringFileParser implements FileParser<String> {
    private final Path path;
    private long readLines = 0;
    private long totalLines;

    public StringFileParser(String path) {
        this.path = Paths.get(path);
        try (Stream<String> stream = Files.lines(this.path, StandardCharsets.UTF_8)) {
            totalLines = stream.count();
        } catch (IOException e) {
            System.out.println("Failed to parse " + path);
            totalLines = -1;
        }
    }

    @Override
    public String peekLastItem() throws Exception {
        try (Stream<String> lines = Files.lines(this.path, StandardCharsets.UTF_8)) {
            String res = lines.skip(readLines).findFirst().get();

            if (res.contains(" ")) {
                throw new Exception("Bad string!");
            }

            return res;
        }
    }

    @Override
    public String getLastItem() throws Exception {
        String res = peekLastItem();
        readLines++;
        return res;
    }

    @Override
    public boolean hasAvailableLines() {
        return readLines < totalLines;
    }

    @SneakyThrows
    @Override
    public int compareTo(FileParser<String> o) {
        return this.peekLastItem().compareTo(o.peekLastItem());
    }

    @SneakyThrows
    @Override
    public int compareToT(String o) {
        if (o == null) {
            return 0;
        }

        return this.peekLastItem().compareTo(o);
    }
}
