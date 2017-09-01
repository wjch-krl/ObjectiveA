package com.wkrol.logreader.tests;

import com.wkrol.logreader.ILogReader;
import com.wkrol.logreader.LogParser;
import com.wkrol.logreader.LogReaderUtils;
import com.wkrol.logreader.RequestTimeAndSize;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class LogParserTests {
    @Test
    public void parseRowTest_ValidData() throws IOException {
        String[] row1 =  {"206","589926","2","768","1452106800.003","1"};
        String[] row2 =  {"206","2146","4","5","1452106800.004","1"};
        RequestTimeAndSize expected1 = new RequestTimeAndSize(589926,2 + 768 + 1);
        RequestTimeAndSize expected2 = new RequestTimeAndSize(2146,4 + 5 + 1);
        ILogReader reader = mock(ILogReader.class);
        when(reader.readRows()).thenReturn(Stream.of(row1,row2));
        HashMap<String, Integer> header = LogReaderUtils.
                ReadHeader("http_status,obj_sz,req_time,transfer_time,ts,turn_time", ",");
        when(reader.getColumnsPositionsWithNames()).thenReturn(header);

        LogParser parser = new LogParser(reader);
        List<RequestTimeAndSize> actual = parser.parseLog().collect(Collectors.toList());

        assertThat(actual,hasItem(expected1));
        assertThat(actual,hasItem(expected2));
    }

    @Test(expected = NullPointerException.class)
    public void parseRowTest_ValidDataNoHeader() throws IOException {
        String[] row1 =  {"206","589926","2","768","1452106800.003","1"};
        String[] row2 =  {"206","2146","4","5","1452106800.004","1"};
        ILogReader reader = mock(ILogReader.class);
        when(reader.readRows()).thenReturn(Stream.of(row1,row2));

        LogParser parser = new LogParser(reader);
        parser.parseLog();
    }

    @Test(expected = NumberFormatException.class)
    public void parseRowTest_InvalidData() throws IOException {
        String[] row1 =  {"206","123aa","2","768","1452106800.003","1"};
        ILogReader reader = mock(ILogReader.class);
        when(reader.readRows()).thenReturn(Stream.of(row1,row1));
        HashMap<String, Integer> header = LogReaderUtils.
                ReadHeader("http_status,obj_sz,req_time,transfer_time,ts,turn_time", ",");
        when(reader.getColumnsPositionsWithNames()).thenReturn(header);

        LogParser parser = new LogParser(reader);
        parser.parseLog().collect(Collectors.counting());
    }
}
