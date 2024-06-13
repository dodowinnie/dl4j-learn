package com.brandon.dl4j.learn.datavec.loading;

import com.brandon.dl4j.learn.util.DownloaderUtility;
import org.datavec.api.io.filters.RandomPathFilter;
import org.datavec.api.split.FileSplit;
import org.datavec.api.split.InputSplit;

import java.io.File;
import java.util.Iterator;
import java.util.Random;

public class Ex05_SamplingBaseInputSplit {

    public static String dataLocalPath;
    public static void main(String[] args) throws Exception {
        dataLocalPath = DownloaderUtility.INPUTSPLIT.Download();
        FileSplit fileSplit = new FileSplit(new File(dataLocalPath, "files"));
        InputSplit[] inputSplits1 = fileSplit.sample(new RandomPathFilter(new Random(123), null), 8, 5,9,11);
        System.out.println(inputSplits1.length);
//        System.out.println(String.format(("Random filtered splits -> Total(%d) = Splits of (%s)"), fileSplit.length(),
//                String.join(" + ", () -> new InputSplitLengthIterator(inputSplits1))));
    }


    private static class InputSplitLengthIterator implements Iterator<CharSequence> {

        InputSplit[] inputSplits;
        int i;

        public InputSplitLengthIterator(InputSplit[] inputSplits) {
            this.inputSplits = inputSplits;
            this.i = 0;
        }

        @Override
        public boolean hasNext() {
            return i < inputSplits.length;
        }

        @Override
        public CharSequence next() {
            return String.valueOf(inputSplits[i++].length());
        }
    }
}
