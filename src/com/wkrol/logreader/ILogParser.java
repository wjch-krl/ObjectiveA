package com.wkrol.logreader;

import java.io.IOException;
import java.util.stream.Stream;

public interface ILogParser {
    Stream<RequestTimeAndSize> parseLog() throws IOException;
}
