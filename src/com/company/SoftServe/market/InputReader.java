package com.company.SoftServe.market;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;

public class InputReader {
    private static String thisLine = null;
    private static boolean firstLine = true;
    private static List<String> arrays;
    private static String sb;
    private static final String fileName = "input.txt";
    private static final String splitRegex = ",";


    public static void main(String[] args) {
        ProcessingController pc = new ProcessingController();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            OutputWriter.init();

            while ((thisLine = br.readLine()) != null) {
                arrays = Arrays.asList(thisLine.split(splitRegex));
                sb = String.valueOf(pc.requestProcessing(arrays));
                if (!sb.equals("null")) {
                    if (!firstLine) {
                        OutputWriter.write("\r\n" + sb);
                    } else {
                        OutputWriter.write(sb);
                        firstLine = false;
                    }
                }
            }
            OutputWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
            OutputWriter.close();
        }
    }
}
