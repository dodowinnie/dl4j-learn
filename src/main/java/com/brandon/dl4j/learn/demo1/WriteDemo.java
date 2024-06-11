package com.brandon.dl4j.learn.demo1;

import org.datavec.api.writable.Writable;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class WriteDemo {
    public static void main(String[] args) throws IOException {
        BufferedWriter writer = null;
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter("data/iris-convert.txt");
            writer = new BufferedWriter(fileWriter);
            writer.write("hello world");
            writer.flush();

        }catch (Exception e) {
            e.printStackTrace();
            fileWriter.close();
            writer.close();
        }
    }

}
