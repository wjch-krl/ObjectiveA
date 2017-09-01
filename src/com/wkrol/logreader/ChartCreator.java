package com.wkrol.logreader;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ChartCreator {

    private final String fileName;

    public ChartCreator(String fileName) {
        this.fileName = fileName;
    }

    public void GenerateChart(List<LogProccesingResult> resultList) throws IOException {
        DefaultCategoryDataset dataset = CreateDataSet(resultList);
        JFreeChart barChart = CreateChart(dataset);
        SaveChart(barChart);
    }

    private void SaveChart(JFreeChart barChart) throws IOException {
        int width = 800;
        int height = 600;
        File chartFile = new File(fileName);
        ChartUtilities.saveChartAsJPEG(chartFile, barChart, width, height);
    }

    private JFreeChart CreateChart(DefaultCategoryDataset dataset) {
        return ChartFactory.createBarChart(
                "Time To Last Byte Distribution Per Object Class Size",
                "% of TTLB times in sample",
                "TTLB in ms (logarythmic scale base 2)",
                dataset, PlotOrientation.VERTICAL,
                true, true, false);
    }

    private DefaultCategoryDataset CreateDataSet(List<LogProccesingResult> resultList) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (LogProccesingResult resutl : resultList) {
            dataset.addValue(resutl.getPrecentOfTotalRequests(),
                    resutl.getSizeCategoryClass(),
                    new Double(Math.log(resutl.getRequestTotalTime())));
        }
        return dataset;
    }
}
