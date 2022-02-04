package com.github.nazzrrg.filemanip;

import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileWriter<T> {
    private final Path path;

    @SneakyThrows
    public FileWriter(String path) {
        this.path = Paths.get(path);
        Files.write(this.path, "".getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
    }

    @SneakyThrows
    public void writeNext(T item) {
        Files.write(path, (item.toString() + System.lineSeparator()).getBytes(), StandardOpenOption.APPEND);
    }
}
