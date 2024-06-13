package com.brandon.dl4j.learn.deeplearning.modeling.classification;

import org.datavec.api.records.reader.impl.csv.CSVRecordReader;
import org.datavec.api.split.FileSplit;
import org.deeplearning4j.datasets.datavec.RecordReaderDataSetIterator;
import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.layers.DenseLayer;
import org.deeplearning4j.nn.conf.layers.OutputLayer;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.nn.weights.WeightInit;
import org.deeplearning4j.optimize.listeners.ScoreIterationListener;
import org.nd4j.evaluation.classification.Evaluation;
import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.dataset.SplitTestAndTrain;
import org.nd4j.linalg.dataset.api.preprocessor.NormalizerStandardize;
import org.nd4j.linalg.learning.config.Sgd;
import org.nd4j.linalg.lossfunctions.LossFunctions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

public class IrisClassifier {

    private static Logger log = LoggerFactory.getLogger(IrisClassifier.class);

    public static void main(String[] args) throws Exception {
        CSVRecordReader recordReader = new CSVRecordReader(0, ",");
        recordReader.initialize(new FileSplit(new File("iris-change.txt")));
        int labelIndex = 4;
        int numClasses = 3;
        int batchSize = 150;

        RecordReaderDataSetIterator iterator = new RecordReaderDataSetIterator(recordReader, batchSize, labelIndex, numClasses);
        DataSet allData = iterator.next(); // 总共就150个，全取出来
        allData.shuffle();
        SplitTestAndTrain testAndTrain = allData.splitTestAndTrain(0.65);
        DataSet trainData = testAndTrain.getTrain();
        DataSet testData = testAndTrain.getTest();

        // 归一化
        NormalizerStandardize normalizer = new NormalizerStandardize();
        normalizer.fit(trainData);
        normalizer.transform(trainData);
        normalizer.transform(testData);

        final int numInputs = 4;
        int outputNum = 3;
        long seed = 6;

        log.info("build model....");
        MultiLayerConfiguration conf = new NeuralNetConfiguration.Builder()
                .seed(seed)
                .activation(Activation.TANH)
                .weightInit(WeightInit.XAVIER)
                .updater(new Sgd(0.1))
                .l2(1e-4)
                .list()
                .layer(0, new DenseLayer.Builder().nIn(numInputs).nOut(3).build())
                .layer(1, new DenseLayer.Builder().nIn(3).nOut(3).build())
                .layer(new OutputLayer.Builder(LossFunctions.LossFunction.NEGATIVELOGLIKELIHOOD).activation(Activation.SOFTMAX).nIn(3).nOut(outputNum).build())
                .build();
        MultiLayerNetwork model = new MultiLayerNetwork(conf);
        model.init();
        model.setListeners(new ScoreIterationListener(100));
        for(int i = 0; i < 1000; i++){
            model.fit(trainData);
        }
        Evaluation eval = new Evaluation(3);
        INDArray output = model.output(testData.getFeatures());
        eval.eval(testData.getLabels(), output);
        log.info(eval.stats());
        File locationToSave = new File("iris.zip");
        model.save(locationToSave, true);


    }


}
