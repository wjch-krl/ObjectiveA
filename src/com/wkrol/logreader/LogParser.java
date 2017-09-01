package com.wkrol.logreader;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Stream;

public class LogParser implements ILogParser {

    private static final String DATA_SIZE_FIELD_NAME = "obj_sz";
    private static final String HTTP_STATUS_FIELD_NAME = "http_status";
    private static final String REQ_TIME_FIELD_NAME = "req_time";
    private static final String TRANSFER_TIME_FIELD_NAME = "transfer_time";
    private static final String TURN_TIME_FIELD_NAME = "turn_time";

    private final ILogReader ILogReader;
    private int sizeIdx;
    private int requestTimeIdx;
    private int transferTimeIdx;
    private int turnTimeIdx;

    public LogParser(ILogReader ILogReader) {
        this.ILogReader = ILogReader;
    }

    @Override
    public Stream<RequestTimeAndSize> parseLog() throws IOException {
        GetColumnIdxs();
        return ILogReader.readRows().map(this::parseRequestTimeAndSize);
    }

    private RequestTimeAndSize parseRequestTimeAndSize(String[] rowValues)  {
        try {
            long size = Long.parseLong(rowValues[sizeIdx]);
            double time = Double.parseDouble(rowValues[requestTimeIdx])
                    + Double.parseDouble(rowValues[turnTimeIdx])
                    + Double.parseDouble(rowValues[transferTimeIdx]);
            return new RequestTimeAndSize(size, time);

        } catch (NumberFormatException e) {
            System.out.println("Invalid row");
            System.out.println(Arrays.toString(rowValues));
            throw e;
        }
    }

    private void GetColumnIdxs() {
        HashMap<String, Integer> columnsPositionsWithNames = ILogReader.getColumnsPositionsWithNames();
        sizeIdx = columnsPositionsWithNames.get(DATA_SIZE_FIELD_NAME);
        requestTimeIdx = columnsPositionsWithNames.get(REQ_TIME_FIELD_NAME);
        turnTimeIdx = columnsPositionsWithNames.get(TURN_TIME_FIELD_NAME);
        transferTimeIdx = columnsPositionsWithNames.get(TRANSFER_TIME_FIELD_NAME);
    }
}
