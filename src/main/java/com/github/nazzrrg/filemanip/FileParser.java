package com.github.nazzrrg.filemanip;


public interface FileParser<T> extends Comparable<FileParser<T>> {
    T getLastItem() throws Exception;
    T peekLastItem() throws Exception;
    int compareToT(T o);
    boolean hasAvailableLines();
}
