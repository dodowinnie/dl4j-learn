package com.brandon.dl4j.learn.datavec.loading;

import com.brandon.dl4j.learn.util.DownloaderUtility;
import org.datavec.api.split.FileSplit;

import java.io.File;
import java.net.URI;
import java.util.Iterator;
import java.util.Random;

public class Ex01_FileSplit {

    public static String dataLocalPath;

    /**
     * 加载所有
     *
     * @param directoryToLook
     */
    public static void loadEverything(File directoryToLook) {
        // 加载所有
        FileSplit fileSplit1 = new FileSplit(directoryToLook);
        System.out.println("--------------- Example 1: Loading every file ---------------");
        for (URI location : fileSplit1.locations()) {
            System.out.println(location);
        }
        System.out.println("------------------------------------------------------------\n\n\n");
    }

    /**
     * 非递归加载
     *
     * @param directoryToLook
     */
    public static void loadNonRecursively(File directoryToLook) {
        System.out.println("--------------- Example 2: Loading non-recursively ---------------");
        FileSplit fileSplit = new FileSplit(directoryToLook, null, false);
        for (URI location : fileSplit.locations()) {
            System.out.println(location);
        }
        System.out.println("------------------------------------------------------------\n\n\n");
    }

    /**
     * 过滤加载
     * @param directoryToLook
     */
    public static void loadWithFilters(File directoryToLook){
        System.out.println("--------------- Example 3: Loading with filters ---------------");
        String[] filters = new String[]{".jpg"};
        FileSplit fileSplit = new FileSplit(directoryToLook, filters, false);
        for (URI location : fileSplit.locations()) {
            System.out.println(location);
        }
        System.out.println("------------------------------------------------------------\n\n\n");

    }

    public static void loadWithSeed(File directoryToLook){
        System.out.println("--------------- Example 4: Loading with a random seed ---------------");
        FileSplit fileSplit = new FileSplit(directoryToLook, null, new Random(12345));
        Iterator<URI> uriIterator = fileSplit.locationsIterator();
        while (uriIterator.hasNext()){
            URI uri = uriIterator.next();
            System.out.println(uri);
        }
    }

    public static void loadSingleFile(File directoryToLook) {
        System.out.println("--------------- Example 5: FileSplit with a single file ---------------");
        FileSplit fileSplit = new FileSplit(new File(directoryToLook.getAbsolutePath(), "cats/domestic_cat_s_001970.jpg"));
        Iterator<URI> uriIterator = fileSplit.locationsIterator();
        while (uriIterator.hasNext()){
            URI uri = uriIterator.next();
            System.out.println(uri);
        }
    }

    public static void main(String[] args) throws Exception {

        dataLocalPath = DownloaderUtility.INPUTSPLIT.Download();
        File directoryToLook = new File(dataLocalPath, "files");

//        loadEverything(directoryToLook);
//        loadNonRecursively(directoryToLook);
//        loadWithSeed(directoryToLook);
        loadSingleFile(directoryToLook);
    }

}
