package com.wkrol.logreader.tests;

import com.wkrol.logreader.LogReader;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class LogReaderTests{
    @Test(expected = IOException.class)
    public void TestLogReader_MissingFile() throws Exception{
        try (LogReader reader = new LogReader("InvalidPATH")) {
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void TestLogReader_EptyFilePath() throws Exception{
        try (LogReader reader = new LogReader("")) {
        }
    }

    @Test(expected = NullPointerException.class)
    public void TestLogReader_EmptyFile() throws Exception {
        try (LogReader reader = new LogReader("src/com/wkrol/logreader/tests/empty")) {
        }

    }

    @Test()
    public void TestLogReader_HeaderOnly() throws Exception {
        try (LogReader reader = new LogReader("src/com/wkrol/logreader/tests/headerOnly")) {
            assertEquals(reader.readRows().count(), 0);
        }
    }

    @Test()
    public void TestLogReader_SimpleFile() throws Exception {
        try (LogReader reader = new LogReader("src/com/wkrol/logreader/tests/simple")) {
            assertEquals(reader.readRows().count(), 2);
        }
    }
}
