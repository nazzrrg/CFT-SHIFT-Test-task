package com.github.nazzrrg.util;

import picocli.CommandLine.Option;

public class RequiredOptions {
    @Option(names = {"-i"}, required = true, description = "Integer sort") public boolean i;
    @Option(names = {"-s"}, required = true, description = "String sort") public boolean s;
}
