package com.github.nazzrrg.util;

import picocli.CommandLine.Option;

public class NonRequiredOptions {
    @Option(names = {"-a"}, defaultValue = "false", description = "Ascending order") public boolean a;
    @Option(names = {"-d"}, defaultValue = "false", description = "Descending order") public boolean d;
}
