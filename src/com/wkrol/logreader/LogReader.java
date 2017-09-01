package com.wkrol.logreader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.stream.Stream;

public class LogReader implements ILogReader {
    private static final String DEFAULT_SEPARATOR = ",";
    private final BufferedReader reader;
    private final String separator;
    private HashMap<String, Integer> columnsPositionsWithNames;

    public LogReader(String filePath) throws IOException {
        this(filePath, DEFAULT_SEPARATOR);
    }

    public LogReader(String filePath, String separator) throws IOException {
        if (filePath == null || filePath.isEmpty()) {
            throw new IllegalArgumentException("filePath must be provided");
        }
        this.separator = separator;
        this.columnsPositionsWithNames = new HashMap<>();
        reader = new BufferedReader(new FileReader(filePath));
        columnsPositionsWithNames = LogReaderUtils.ReadHeader(reader.readLine(),separator);
    }

    @Override
    public HashMap<String, Integer> getColumnsPositionsWithNames() {
        return columnsPositionsWithNames;
    }

    @Override
    public Stream<String[]> readRows() throws IOException {
        return reader.lines().map(x -> x.split(separator));
    }


    @Override
    public void close() throws Exception {
        if (reader != null) {
            reader.close();
        }
    }


}
