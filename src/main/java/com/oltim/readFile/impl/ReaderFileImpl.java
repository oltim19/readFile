package com.oltim.readFile.impl;

import com.oltim.readFile.interfaces.ReaderFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ReaderFileImpl implements ReaderFile {

    @Override
    public List<Integer> readFile(String filePath) {
        List<Integer> listNumber = null;
        try (var lines = Files.lines(Paths.get(filePath), StandardCharsets.UTF_8)) {
            listNumber = lines.map(Integer::valueOf).collect(Collectors.toList());
        } catch (IOException | NumberFormatException e) {
            System.out.println("File reading error " + e.getMessage());
        }
        return  Optional.ofNullable(listNumber).orElseGet(() -> Collections.singletonList(0));
    }
}
