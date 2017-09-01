package com.wkrol.logreader.tests;
import com.wkrol.logreader.*;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertTrue;

public class ChartCreatorTests {
    @Test
    public void testCreateChart() throws IOException {
        String tmpPath = "tmp.png";
        ChartCreator creator = new ChartCreator(tmpPath);
        List<LogProccesingResult>  list = new ArrayList<>();
        list.add(new LogProccesingResult("aa",2.0,2,2.0));
        creator.GenerateChart(list);

        File file = new File(tmpPath);
        assertTrue(file.exists());
        assertTrue(file.length() > 0);
        file.delete();
    }
}
