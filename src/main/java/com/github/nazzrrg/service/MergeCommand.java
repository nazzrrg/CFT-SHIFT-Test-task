package com.github.nazzrrg.service;


import com.github.nazzrrg.filemanip.FileParser;
import com.github.nazzrrg.filemanip.FileWriter;
import com.github.nazzrrg.filemanip.IntFileParser;
import com.github.nazzrrg.filemanip.StringFileParser;

import com.github.nazzrrg.util.NonRequiredOptions;
import com.github.nazzrrg.util.RequiredOptions;
import picocli.CommandLine.ArgGroup;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import java.util.ArrayList;
import java.util.List;

@Command(name="sort", description = "sorts stuff")
public class MergeCommand implements Runnable {
    @ArgGroup(multiplicity = "1")
    RequiredOptions ro;

    @ArgGroup
    NonRequiredOptions nro;

    @Parameters(index = "0", description = "Output file")
    private String outPath;
    @Parameters(index = "1..*", description = "Input files")
    private String[] inPaths;

    @Override
    public void run() {
        if (nro == null) {
            // can't be none
            nro = new NonRequiredOptions();
            nro.a = true; // - default is Ascending
            System.out.println("Falling back to default sorting order (Ascending)");
        }

        if (ro.i) {
            FileWriter<Integer> writer = new FileWriter<>(outPath);
            List<FileParser<Integer>> parsers = new ArrayList<>(inPaths.length);
            for (String p : inPaths) {
                parsers.add(new IntFileParser(p));
            }

            MergeSort<Integer> sort = new MergeSort<>(nro.d);

            Integer next = sort.getBestFit(parsers);
            while (next != null) {
                try {
                    writer.writeNext(next);
                } catch (Exception e) {
                    System.out.println("Failed to append to output file!");
                    break;
                }
                next = sort.getBestFit(parsers);
            }
        } else {
            FileWriter<String> writer = new FileWriter<>(outPath);
            List<FileParser<String>> parsers = new ArrayList<>(inPaths.length);
            for (String p : inPaths) {
                parsers.add(new StringFileParser(p));
            }

            MergeSort<String> sort = new MergeSort<>(nro.d);

            String next = sort.getBestFit(parsers);
            while (next != null) {
                try {
                    writer.writeNext(next);
                } catch (Exception e) {
                    System.out.println("Failed to append to output file!");
                    break;
                }
                next = sort.getBestFit(parsers);
            }
        }
    }
}
