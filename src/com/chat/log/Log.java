package com.chat.log;

import com.chat.entity.User;

import java.io.*;

public class Log {
    public static void writeLog(String name, String text) throws IOException {
        System.out.println("Enter writeLog");
        BufferedWriter bw = new BufferedWriter(
                new FileWriter(
                        new File("./input.log"), true
                ));
        bw.newLine();
        bw.write(name + " writted: " + text);
        bw.flush();
        bw.close();
    }
    public static String readLog () throws IOException {
        System.out.println("Enter readLog");
        BufferedReader br = new BufferedReader(
                new FileReader(
                        new File("./input.log")
                ));
        String line = "Last 100 massages here:\n";
        for (int l = 0; l <= 100; l++) {
            if (br.readLine() == null) {break;}
            line=line+ "\t"+br.readLine()+ "\n";
        }
        return line;
        }

}
