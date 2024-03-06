package com.oltim.readFile.impl;

import com.oltim.readFile.interfaces.ExtractorFile;
import org.apache.commons.compress.compressors.CompressorException;
import org.apache.commons.compress.compressors.CompressorInputStream;
import org.apache.commons.compress.compressors.CompressorStreamFactory;
import org.apache.commons.compress.compressors.bzip2.BZip2Utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;


public class ExtractorBz2FileImpl implements ExtractorFile {

    @Override
    public String extractBz2File(String sourcePath) {
        checkSourcePath(sourcePath);
        String targetFile = getTargetFileName(sourcePath);
        try (InputStream fin = Files.newInputStream(Path.of(sourcePath));
             BufferedInputStream in = new BufferedInputStream(fin);
             CompressorInputStream bzIn = new CompressorStreamFactory(true)
                     .createCompressorInputStream(in);
             OutputStream out = Files.newOutputStream(Path.of(targetFile))
        ) {
            final byte[] buffer = new byte[1024 * 8];
            int number;
            while ((number = bzIn.read(buffer)) != -1) {
                out.write(buffer, 0, number);
            }
        } catch (IOException | CompressorException e) {
            System.out.println("File extracting error " + e.getMessage());
        }
        return targetFile;
    }

    private String getTargetFileName(String sourcePath) {
        File input = new File(sourcePath);
        return BZip2Utils.getUncompressedFilename(input.getName());
    }

    private void checkSourcePath(String sourcePath) {
        File input = new File(sourcePath);
        if (!BZip2Utils.isCompressedFilename(input.getName())) {
            throw new IllegalArgumentException("You need to specify a bz2 file!");
        }
        if (!input.exists()) {
            throw new IllegalArgumentException("File " + input.getAbsolutePath() + " does not exist.");
        }

        if (!input.isFile()) {
            throw new IllegalArgumentException("Not a file " + input.getAbsolutePath());
        }

        if (!input.canRead()) {
            throw new IllegalArgumentException("Cannot read file " + input.getAbsolutePath());
        }
    }

}
