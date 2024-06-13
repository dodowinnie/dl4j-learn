package com.brandon.dl4j.learn.datavec.loading;

import org.datavec.api.split.NumberedFileInputSplit;

import java.net.URI;

public class Ex03_NumberedFileInputSplit {

    public static String dataLocalPath;
    public static void main(String[] args) {
        NumberedFileInputSplit split = new NumberedFileInputSplit("D:\\opt\\deeplearning\\precix-%02d.suffix", 2, 5);
        System.out.println("--------------- Example 1: Loading simple numbered files ---------------");
        URI[] split1Uris = split.locations();
        for (URI uri: split1Uris) {
            System.out.println(uri);
        }
        System.out.println("------------------------------------------------------------\n\n\n");
    }
}
