package com.brandon.dl4j.learn.demo1;

import org.datavec.api.conf.Configuration;
import org.datavec.api.records.metadata.RecordMetaData;
import org.datavec.api.records.reader.impl.csv.CSVRecordReader;
import org.datavec.api.split.FileSplit;
import org.deeplearning4j.datasets.datavec.RecordReaderDataSetIterator;
import org.deeplearning4j.iterator.provider.CollectionLabeledSentenceProvider;
import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.layers.DenseLayer;
import org.deeplearning4j.nn.conf.layers.OutputLayer;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.nn.weights.WeightInit;
import org.deeplearning4j.optimize.listeners.ScoreIterationListener;
import org.nd4j.common.util.ArrayUtil;
import org.nd4j.evaluation.classification.Evaluation;
import org.nd4j.evaluation.meta.Prediction;
import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.cpu.nativecpu.NDArray;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.dataset.SplitTestAndTrain;
import org.nd4j.linalg.dataset.api.preprocessor.DataNormalization;
import org.nd4j.linalg.dataset.api.preprocessor.NormalizerStandardize;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.learning.config.Sgd;
import org.nd4j.linalg.lossfunctions.LossFunctions;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IrisClassifier {

    public static void main(String[] args) {
        try {


            int numLinesToSkip = 1;
            char delimiter = ' ';
            CSVRecordReader recordReader = new CSVRecordReader(numLinesToSkip, delimiter);
            FileSplit fileSplit = new FileSplit(new File("data/iris-convert.txt"));
            Configuration entries = new Configuration();


            recordReader.initialize(fileSplit);

            int labelIndex = 4;
            int numClasses = 3;
            int batchSize = 150;

            RecordReaderDataSetIterator dataSetIterator = new RecordReaderDataSetIterator(recordReader, batchSize, labelIndex, numClasses);
            DataSet allData = dataSetIterator.next();
            allData.shuffle();
            SplitTestAndTrain testAndTrain = allData.splitTestAndTrain(0.65);
            DataSet trainData = testAndTrain.getTrain();
            DataSet testData = testAndTrain.getTest();

            List<RecordMetaData> trainMetaData = trainData.getExampleMetaData(RecordMetaData.class);
            List<RecordMetaData> testMetaData = testData.getExampleMetaData(RecordMetaData.class);

            DataNormalization normalizer = new NormalizerStandardize();
            normalizer.fit(trainData);
            normalizer.transform(trainData);
            normalizer.transform(testData);


            final int numInputs = 4;
            int outputNum = 3;
            long seed = 6;


            System.out.println("Build model....");
            MultiLayerConfiguration conf = new NeuralNetConfiguration.Builder()
                    .seed(seed)
                    .activation(Activation.TANH)
                    .weightInit(WeightInit.XAVIER)
                    .updater(new Sgd(0.1))
                    .l2(1e-4)
                    .list()
                    .layer(new DenseLayer.Builder().nIn(numInputs).nOut(3).build())
                    .layer(new OutputLayer.Builder(LossFunctions.LossFunction.NEGATIVELOGLIKELIHOOD)
                            .activation(Activation.SOFTMAX).nIn(3).nOut(outputNum).build())
                    .build();

            //Fit the model
            MultiLayerNetwork model = new MultiLayerNetwork(conf);
            model.init();
            model.setListeners(new ScoreIterationListener(100));

            for( int i=0; i<50; i++ ) {
                model.fit(trainData);
            }

            //Evaluate the model on the test set
            Evaluation eval = new Evaluation(3);
            INDArray output = model.output(testData.getFeatures());
            eval.eval(testData.getLabels(), output, testMetaData);          //Note we are passing in the test set metadata here
            System.out.println(eval.stats());

            double[] examples = new double[]{4.9,3,1.4,0.3};
//            int[] shape = new int[]{1};
            INDArray indArray = Nd4j.create(examples);
            int[] predict = model.predict(indArray);
            System.out.println(predict);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}
