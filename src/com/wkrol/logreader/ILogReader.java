package com.wkrol.logreader;

import java.io.IOException;
import java.util.HashMap;
import java.util.stream.Stream;

public interface ILogReader extends AutoCloseable {
    HashMap<String, Integer> getColumnsPositionsWithNames();

    Stream<String[]> readRows() throws IOException;

    @Override
    void close() throws Exception;
}
