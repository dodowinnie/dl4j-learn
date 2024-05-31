package com.brandon.dl4j.learn.demo1;

import org.datavec.api.conf.Configuration;
import org.datavec.api.records.reader.impl.csv.CSVRecordReader;
import org.datavec.api.split.FileSplit;
import org.deeplearning4j.datasets.datavec.RecordReaderDataSetIterator;
import org.deeplearning4j.iterator.provider.CollectionLabeledSentenceProvider;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.dataset.SplitTestAndTrain;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class IrisClassifier {

    public static void main(String[] args) {
        try {

            Map<String, Integer> labelMap = new HashMap<>();
            labelMap.put("setosa", 0);
            labelMap.put("versicolor", 1);
            labelMap.put("virginica", 2);


            int numLinesToSkip = 1;
            char delimiter = ' ';
            CSVRecordReader recordReader = new CSVRecordReader(numLinesToSkip, delimiter);
            FileSplit fileSplit = new FileSplit(new File("data/iris.txt"));
            Configuration entries = new Configuration();

            recordReader.initialize(fileSplit);

            int labelIndex = 5;
            int numClasses = 3;
            int batchSize = 150;

            RecordReaderDataSetIterator dataSetIterator = new RecordReaderDataSetIterator(recordReader, batchSize, labelIndex, numClasses);
            DataSet allData = dataSetIterator.next();
            allData.shuffle();
            SplitTestAndTrain testAndTrain = allData.splitTestAndTrain(0.65);
            DataSet trainData = testAndTrain.getTrain();
            for (DataSet dataSet : trainData.asList()) {
                INDArray labels = dataSet.getLabels();
                System.out.println(labels.shape());

            }


            DataSet testData = testAndTrain.getTest();


        }catch (Exception e){
            e.printStackTrace();
        }


    }


}
