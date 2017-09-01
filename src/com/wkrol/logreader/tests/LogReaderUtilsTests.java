package com.wkrol.logreader.tests;

import com.wkrol.logreader.LogReaderUtils;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class LogReaderUtilsTests {
    @Test
    public void getHeaderTest_SimpleHeader() throws IOException {

        HashMap<String, Integer> stringIntegerHashMap = LogReaderUtils.ReadHeader("aaa,bbbb", ",");
        assertEquals(stringIntegerHashMap.size() , 2);
        assertEquals(new Integer(0), stringIntegerHashMap.get("aaa"));
        assertEquals( new Integer(1), stringIntegerHashMap.get("bbbb"));
    }

    @Test
    public void getHeaderTest_EmptyHeader() throws IOException {

        HashMap<String, Integer> stringIntegerHashMap = LogReaderUtils.ReadHeader("", ",");
        assertEquals(0, stringIntegerHashMap.size() );
    }
}
