package com.brandon.dl4j.learn.datavec.loading;

import com.brandon.dl4j.learn.util.DownloaderUtility;
import org.datavec.api.split.CollectionInputSplit;
import org.datavec.api.split.FileSplit;

import java.io.File;
import java.net.URI;
import java.util.Iterator;

public class Ex02_CollectionSplit {

    public static String dataLocalPath;
    public static void main(String[] args) throws Exception {
        dataLocalPath = DownloaderUtility.INPUTSPLIT.Download();
        File directoryToLook = new File(dataLocalPath, "files/cats");
        FileSplit fileSplit = new FileSplit(directoryToLook, new String[]{".jpg"}, false);
        URI[] locations = fileSplit.locations();
        for(URI uri : locations){
            System.out.println(uri);
        }
        CollectionInputSplit collectionInputSplit = new CollectionInputSplit(locations);
        System.out.println("--------------- Printing the input splits from CollectionInputSplit ---------------");
        Iterator<URI> collectionInputSplitIterator = collectionInputSplit.locationsIterator();
        while (collectionInputSplitIterator.hasNext()) {
            System.out.println(collectionInputSplitIterator.next());
        }
        System.out.println("---------------------------------------------------------------------------\n\n\n");
    }

}
