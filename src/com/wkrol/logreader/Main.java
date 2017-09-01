package com.wkrol.logreader;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

        String inputFilePath = validateParameters(args);
        String outputFilePath = args[1];
        try (ILogReader reader = new LogReader(inputFilePath)) {
            ProcessLogFile(outputFilePath, reader);
        }catch (NumberFormatException e){
            System.out.print("Can't parse data ");
            e.printStackTrace();
        }
        catch (IOException e) {
            System.out.print("Exception while reading file. ");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.print("Other error occurred. ");
            e.printStackTrace();
        }
    }

    private static void ProcessLogFile(String outputFilePath, ILogReader reader) throws IOException {
        ILogParser parser = new LogParser(reader);
        Stream<RequestTimeAndSize> logData = parser.parseLog();
        List<LogProccesingResult> results = new LogAnalyzer().ProccesData(logData);
        ChartCreator creator = new ChartCreator(outputFilePath);
        creator.GenerateChart(results);
    }

    private static String validateParameters(String[] args) {
        if(args.length < 2){
            System.out.println("USAGE: PROGRAM INPUT_FILE OUTPUT_FILE");
            System.exit(1);
        }
        String inputFilePath = args[0];
        File file = new File(inputFilePath);
        if(!file.exists()) {
            System.out.print("File ");
            System.out.print(inputFilePath);
            System.out.print(" does not exists.");
            System.exit(1);
        }
        if(file.isDirectory()){
            System.out.print("File ");
            System.out.print(inputFilePath);
            System.out.print(" is a directory");
            System.exit(1);
        }
        return inputFilePath;
    }
}
