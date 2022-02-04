package com.github.nazzrrg.service;

import com.github.nazzrrg.filemanip.FileParser;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class MergeSort<T> {
    private final boolean isDescending;
    private T last = null;

    public MergeSort(boolean isDescending) {
        this.isDescending = isDescending;
    }

    public T getBestFit(List<FileParser<T>> parsers) {
        try {
            Optional<FileParser<T>> bestParser;

            if (isDescending) {
                bestParser = parsers.stream()
                        .filter(FileParser::hasAvailableLines)
                        .filter(o -> o.compareToT(last) <= 0)
                        .max(Comparator.naturalOrder());
            } else {
                bestParser = parsers.stream()
                        .filter(FileParser::hasAvailableLines)
                        .filter(o -> o.compareToT(last) >= 0)
                        .min(Comparator.naturalOrder());
            }

            if (bestParser.isPresent()) {
                last = bestParser.get().getLastItem();
                return last;
            } else {
                System.out.println("No next best number could be found!");
                return null;
            }
        } catch (Exception e) {
            System.out.println("Error during sorting!");
            return null;
        }
    }
}
