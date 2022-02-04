package com.github.nazzrrg;

import com.github.nazzrrg.service.MergeCommand;
import picocli.CommandLine;


public class Main {
    public static void main(String[] args) {
        MergeCommand sort = new MergeCommand();
        int code = new CommandLine(sort).execute(args);
        System.exit(code);
    }
}
