package com.oltim.readFile;

import com.oltim.readFile.impl.ExtractorBz2FileImpl;
import com.oltim.readFile.impl.ReaderFileImpl;
import com.oltim.readFile.interfaces.ExtractorFile;
import com.oltim.readFile.interfaces.ListCalculator;
import com.oltim.readFile.interfaces.Sequence;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {

        if (args.length == 0) {
            throw new IllegalArgumentException("You need to specify the source  file!");
        }

        long startTime = System.nanoTime();
        String sourceFile = args[0];

        ExtractorFile extractorBz2File = new ExtractorBz2FileImpl();
        String targetPathFile = extractorBz2File.extractBz2File(sourceFile);
        ReaderFileImpl readerFile = new ReaderFileImpl();
        List<Integer> listNumber = readerFile.readFile(targetPathFile);

        System.out.println("Maximum value in the list " + ListCalculator.calculateMaxOfList(listNumber));
        System.out.println("Minimum value in the list " + ListCalculator.calculateMinOfList(listNumber));
        System.out.println("Average value in the list " + ListCalculator.calculateAverageOfList(listNumber));
        System.out.println("Median value in the list " + ListCalculator.calculateMedianOfList(listNumber));

        Set<Integer> descendingSequencesSizes = Sequence.getDescendingSequencesSizes(listNumber);
        System.out.println("Largest descending sequence " + ListCalculator.calculateMaxOfList(descendingSequencesSizes));
        Set<Integer> increasingSequencesSizes = Sequence.getIncreasingSequencesSizes(listNumber);
        System.out.println("Largest increasing sequence " + ListCalculator.calculateMaxOfList(increasingSequencesSizes));

        calculateTime(startTime);
    }

    private static void calculateTime(long startTime) {
        long elapsedTime = System.nanoTime() - startTime;
        long seconds = TimeUnit.SECONDS.convert(elapsedTime, TimeUnit.NANOSECONDS);
        System.out.println("Time taken: " + seconds);
    }

}
