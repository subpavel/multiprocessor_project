package ru.sbt.mipt.basetest.util;

import ru.sbt.mipt.basetest.Tests;
import ru.sbt.mipt.basetest.test.ResultData;

import java.io.*;
import java.util.Date;
import java.util.List;

/**
 * Created by Anton on 09.01.16.
 */
public class ReportUtil {

    private final String sep = " ";
    private final String filePath = "report/";
    private final String fileSuff = "report/";


    public String writeReport(List<ResultData> results, int maxNumThread) throws IOException {
        String file = "report/" + new Date() + "result.txt";
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
        writeHeader(results, writer);
        writeTable(results, maxNumThread, writer);

        writer.flush();
        return file;

    }

    private void writeTable(List<ResultData> results, int maxNumThread, BufferedWriter writer) throws IOException {
        for (int i = 2; i <= maxNumThread; i++) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(i + sep);
            for (ResultData result : results) {
                if (result.getTestLatResult().containsKey(i)) {
                    stringBuffer.append(result.getTestLatResult().get(i) + sep);
                    stringBuffer.append(result.getTestThrResult().get(i) + sep);

                } else {
                    stringBuffer.append(" " + sep);
                    stringBuffer.append(" " + sep);
                }
            }
            writeLine(writer, stringBuffer);
        }
    }

    private void writeHeader(List<ResultData> results, BufferedWriter writer) throws IOException {
        StringBuffer buffer = new StringBuffer();
        buffer.append("# thred" + sep);
        for (ResultData result : results) {
            buffer.append(result.getNameTest()+"lat" + sep);
            buffer.append(result.getNameTest()+"thr" + sep);
        }

        writeLine(writer, buffer);
    }

    private void writeLine(BufferedWriter writer, StringBuffer buffer) throws IOException {
        writer.write(buffer.toString());
        writer.newLine();
    }


}

