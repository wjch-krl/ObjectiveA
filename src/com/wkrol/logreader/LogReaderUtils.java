package com.wkrol.logreader;

import java.io.IOException;
import java.util.HashMap;

public final class LogReaderUtils {
    public static HashMap<String, Integer> ReadHeader(String line, String separator) throws IOException {
        HashMap<String, Integer> columnsPositionsWithNames = new HashMap<String, Integer>();
        String[] columnNames = line.split(separator);
        for (int i = 0; i < columnNames.length; i++) {
            if(!columnNames[i].isEmpty())
            {
                columnsPositionsWithNames.put(columnNames[i], i);
            }
        }
        return columnsPositionsWithNames;
    }
}
