package com.github.nazzrrg.util;

import picocli.CommandLine.Option;

public class RequiredOptions {
    @Option(names = {"-i"}, required = true) public boolean i;
    @Option(names = {"-s"}, required = true) public boolean s;
}
