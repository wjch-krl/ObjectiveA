package com.wkrol.logreader.tests;

import com.wkrol.logreader.*;
import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.*;

import java.util.List;
import java.util.stream.Stream;

public class LogAnalyzerTests {
    @Test
    public void proccesDataTest_DiffrentSizeAndTime() {
        RequestTimeAndSize inputData1 = new RequestTimeAndSize(1,1);
        RequestTimeAndSize inputData2 = new RequestTimeAndSize(20000000,2);
        Stream<RequestTimeAndSize> input = Stream.of(inputData1,inputData2);
        LogProccesingResult result1 = new LogProccesingResult(inputData1.getSizeClass(),inputData1.getRequestTime(),1,50.);
        LogProccesingResult result2 = new LogProccesingResult(inputData2.getSizeClass(),inputData2.getRequestTime(),1,50.);

        LogAnalyzer analyzer = new LogAnalyzer();
        List<LogProccesingResult> actual = analyzer.ProccesData(input);

        assertThat(actual,hasItem(result1));
        assertThat(actual,hasItem(result2));
    }

    @Test
    public void proccesDataTest_SametSizeAndTime() {
        RequestTimeAndSize inputData1 = new RequestTimeAndSize(1,1);
        RequestTimeAndSize inputData2 = new RequestTimeAndSize(1,1);
        Stream<RequestTimeAndSize> input = Stream.of(inputData1,inputData2);
        LogProccesingResult result1 = new LogProccesingResult(inputData1.getSizeClass(),inputData1.getRequestTime(),2,100.);

        LogAnalyzer analyzer = new LogAnalyzer();
        List<LogProccesingResult> actual = analyzer.ProccesData(input);

        assertThat(actual,hasItem(result1));
    }

    @Test
    public void proccesDataTest_SameSizeAndDifrentTime() {
        RequestTimeAndSize inputData1 = new RequestTimeAndSize(1,1);
        RequestTimeAndSize inputData2 = new RequestTimeAndSize(1,2);
        Stream<RequestTimeAndSize> input = Stream.of(inputData1,inputData2);
        LogProccesingResult result1 = new LogProccesingResult(inputData1.getSizeClass(),inputData1.getRequestTime(),1,50.);
        LogProccesingResult result2 = new LogProccesingResult(inputData2.getSizeClass(),inputData2.getRequestTime(),1,50.);

        LogAnalyzer analyzer = new LogAnalyzer();
        List<LogProccesingResult> actual = analyzer.ProccesData(input);

        assertThat(actual,hasItem(result1));
        assertThat(actual,hasItem(result2));
    }

    @Test
    public void proccesDataTest_DifrentSizeAndSameTime() {
        RequestTimeAndSize inputData1 = new RequestTimeAndSize(1,1);
        RequestTimeAndSize inputData2 = new RequestTimeAndSize(20000000,1);
        Stream<RequestTimeAndSize> input = Stream.of(inputData1,inputData2);
        LogProccesingResult result1 = new LogProccesingResult(inputData1.getSizeClass(),inputData1.getRequestTime(),1,50.);
        LogProccesingResult result2 = new LogProccesingResult(inputData2.getSizeClass(),inputData2.getRequestTime(),1,50.);

        LogAnalyzer analyzer = new LogAnalyzer();
        List<LogProccesingResult> actual = analyzer.ProccesData(input);

        assertThat(actual,hasItem(result1));
        assertThat(actual,hasItem(result2));
    }
}