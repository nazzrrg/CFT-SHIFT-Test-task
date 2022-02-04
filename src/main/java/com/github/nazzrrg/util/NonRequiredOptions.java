package com.github.nazzrrg.util;

import picocli.CommandLine.Option;

public class NonRequiredOptions {
    @Option(names = {"-a"}, defaultValue = "false") public boolean a;
    @Option(names = {"-d"}, defaultValue = "false") public boolean d;
}
