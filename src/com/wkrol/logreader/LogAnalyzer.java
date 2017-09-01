package com.wkrol.logreader;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LogAnalyzer {

    public List<LogProccesingResult> ProccesData(Stream<RequestTimeAndSize> data) {

        List<RequestTimeAndSize> validData = GetValidData(data);
        Map<Double, Map<String, Long>> groupedByTimeAndSize = AgregateData(validData);
        long valuesCount = validData.size();
        return PrepareResults(valuesCount, groupedByTimeAndSize);
    }

    private List<RequestTimeAndSize> GetValidData(Stream<RequestTimeAndSize> data) {
        return data.
                filter(RequestTimeAndSize::hasValidSizeClass).collect(Collectors.toList());
    }

    private Map<Double, Map<String, Long>> AgregateData(List<RequestTimeAndSize> validData) {
        return validData.stream().
                    collect(Collectors.groupingBy(RequestTimeAndSize::getRequestTime,
                            Collectors.groupingBy(RequestTimeAndSize::getSizeClass,
                                    Collectors.counting()
                            )));
    }

    private List<LogProccesingResult> PrepareResults(long valuesCount, Map<Double, Map<String, Long>> groupedByTimeAndSize) {
        return generateResults(groupedByTimeAndSize, valuesCount).
                sorted(Comparator.comparingDouble(LogProccesingResult::getRequestTotalTime)).
                collect(Collectors.toList());
    }

    private Stream<LogProccesingResult> generateResults(Map<Double, Map<String, Long>> groupedByTimeAndSize, long totalCount) {
        return groupedByTimeAndSize.entrySet().stream().flatMap(x -> this.generateResults2(x, totalCount));
    }

    private Stream<LogProccesingResult> generateResults2(Map.Entry<Double, Map<String, Long>> mapEntry, long totalCount) {
        return mapEntry.getValue().entrySet().stream().map(x -> this.createResult(x, totalCount, mapEntry.getKey()));
    }

    private LogProccesingResult createResult(Map.Entry<String, Long> mapEntry, long totalCount, Double totalTime) {
        LogProccesingResult result = new LogProccesingResult();
        long count = mapEntry.getValue();
        result.setCount(count);
        result.setPrecentOfTotalRequests((double) count / totalCount * 100);
        result.setRequestTotalTime(totalTime);
        result.setSizeCategoryClass(mapEntry.getKey());
        return result;
    }
}
