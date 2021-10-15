package com.company.SoftServe.market;

import java.io.*;

class OutputWriter {
    private static final String fileName = "output.txt";
    private static BufferedWriter bw;

    static boolean init() {
        try {
            bw = new BufferedWriter(new FileWriter(fileName));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    static void write(String messageToWrite) {
        try {
            bw.write(messageToWrite);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void close() {
        try {
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

