package com.company.SoftServe.market;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;

public class InputReader {
    private static String thisLine = null;
    private static List<String> arrays;
    private static StringBuilder sb;
    private static final String fileName = "input.txt";
    private static final String splitRegex = ",";


    public static void main(String[] args) {
        ProcessingController pc = new ProcessingController();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            while ((thisLine = br.readLine()) != null) {
                arrays = Arrays.asList(thisLine.split(splitRegex));
                sb = pc.requestProcessing(arrays);
                if (sb != null) {
                    sb.append("\r\n");
                    FileWriter.write("output.txt",sb);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
