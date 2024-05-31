package com.brandon.dl4j.learn.data;

import org.datavec.api.io.labels.ParentPathLabelGenerator;
import org.datavec.api.records.Record;
import org.datavec.api.records.metadata.RecordMetaData;
import org.datavec.api.records.reader.RecordReader;
import org.datavec.api.records.reader.impl.filebatch.FileBatchRecordReader;
import org.datavec.api.split.FileSplit;
import org.datavec.image.loader.BaseImageLoader;
import org.datavec.image.recordreader.ImageRecordReader;
import org.datavec.image.transform.ImageTransform;
import org.datavec.image.transform.MultiImageTransform;
import org.datavec.image.transform.ShowImageTransform;
import org.deeplearning4j.datasets.datavec.RecordReaderDataSetIterator;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.DataSet;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class DataLoadDemo {

    private static final String[] allowExtensions = BaseImageLoader.ALLOWED_FORMATS;
    private static Random randNumGen = new Random(1234);

    public static void main(String[] args) throws Exception {

        String trainPath = "D:\\python\\data-set\\cats_vs_dogs_small\\train";
        String validationPath = "D:\\python\\data-set\\cats_vs_dogs_small\\validation";


        ParentPathLabelGenerator labelMaker = new ParentPathLabelGenerator();
        FileSplit trainFiles = new FileSplit(new File(trainPath), allowExtensions, randNumGen);

        ImageRecordReader trainRecord = new ImageRecordReader(150, 150, 3, labelMaker);
//        ImageTransform transform = new MultiImageTransform(new ShowImageTransform("Display - before "));
        trainRecord.initialize(trainFiles);
        int outputNumTrain = trainRecord.numLabels();
        RecordReaderDataSetIterator trainDataIterator = new RecordReaderDataSetIterator(trainRecord, 20, 1, outputNumTrain);
        while (trainDataIterator.hasNext()) {
            DataSet ds = trainDataIterator.next();
            int batch = trainDataIterator.batch();
            System.out.println("batch is " + batch);
            DataSet dataSet = ds.get(0);
            INDArray features = dataSet.getFeatures();
            System.out.println(Arrays.toString(features.shape()));
            INDArray labels = dataSet.getLabels();
            break;
        }


//        FileSplit validationFiles = new FileSplit(new File(validationPath), allowExtensions, randNumGen);
//
//        ImageRecordReader validationRecord = new ImageRecordReader(150, 150, 3, labelMaker);
////        ImageTransform transform = new MultiImageTransform(new ShowImageTransform("Display - before "));
//        validationRecord.initialize(validationFiles);
//        int outputNumValidation = validationRecord.numLabels();
//        RecordReaderDataSetIterator validationDataIterator = new RecordReaderDataSetIterator(trainRecord, 20, 1, outputNumValidation);


    }

}
