package com.brandon.dl4j.learn.datavec.loading;

import org.datavec.api.split.CollectionInputSplit;
import org.datavec.api.split.TransformSplit;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import static java.util.Arrays.asList;

public class Ex04_TransformSplit {

    public static void main(String[] args) throws Exception {
//        List<URI> inputFiles = asList(new URI("D:/opt/deeplearning/file01.txt"), new URI("D:/opt/deeplearning/~~/file02.txt"));
//        TransformSplit.URITransform normalizeUriTransform = uri -> uri.normalize();
//        TransformSplit transformSplit1 = new TransformSplit(new CollectionInputSplit(inputFiles), normalizeUriTransform);
//        URI[] transformSplit1Uris = transformSplit1.locations();
//        for (int i = 0; i < transformSplit1Uris.length; i++) {
//            System.out.println(String.format("%s -> %s", inputFiles.get(i), transformSplit1Uris[i]));
//        }
//        System.out.println("------------------------------------------------------------\n\n\n");

        List<URI> inputFiles2 = asList(new URI("file:///foo/1-in.csv"), new URI("file:///foo/2-in.csv"));
        TransformSplit transformSplit = TransformSplit.ofSearchReplace(new CollectionInputSplit(inputFiles2), "-in.csv", "-out.csv");
        URI[] locations = transformSplit.locations();
        for(int i =0; i < locations.length; i++){
          System.out.println(String.format("%s -> %s", inputFiles2.get(i), locations[i]));
      }


    }
}
