package com.brandon.dl4j.learn.deeplearning.modeling.classification;

import com.brandon.dl4j.learn.util.DownloaderUtility;
import com.brandon.dl4j.learn.util.PlotUtil;
import org.datavec.api.records.reader.impl.csv.CSVRecordReader;
import org.datavec.api.split.FileSplit;
import org.datavec.api.writable.Writable;
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
import org.nd4j.linalg.dataset.api.iterator.DataSetIterator;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.learning.config.Nesterovs;
import org.nd4j.linalg.lossfunctions.LossFunctions;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 线性数据分类
 */
public class LinearDataClassifier {
    public static boolean visualize = true;
    public static String dataLocalPath;

    public static void main(String[] args) throws Exception {
        int seed = 123;
        double learningRate = 0.01; // 学习率
        int batchSize = 50; // 批量大小
        int nEpochs = 30; // 训练轮次

        int numInputs = 2;
        int numOutputs = 2;
        int numHiddenNodes = 20;

        dataLocalPath = DownloaderUtility.CLASSIFICATIONDATA.Download();
        // 加载train数据
        CSVRecordReader rr = new CSVRecordReader();
        rr.initialize(new FileSplit(new File(dataLocalPath, "linear_data_train.csv")));
        RecordReaderDataSetIterator trainIter = new RecordReaderDataSetIterator(rr, batchSize, 0, 2);

        // 加载验证数据
        CSVRecordReader rrTest = new CSVRecordReader();
        rrTest.initialize(new FileSplit(new File(dataLocalPath, "linear_data_eval.csv")));
        RecordReaderDataSetIterator testIter = new RecordReaderDataSetIterator(rrTest, batchSize, 0, 2);

        // 配置
        MultiLayerConfiguration conf = new NeuralNetConfiguration.Builder()
                .seed(seed)
                .weightInit(WeightInit.XAVIER) // 权重初始化
                .updater(new Nesterovs(learningRate, 0.9))
                .list()
                .layer(0,new DenseLayer.Builder().nIn(numInputs).nOut(numHiddenNodes).activation(Activation.RELU).build())
                .layer(1,new OutputLayer.Builder(LossFunctions.LossFunction.NEGATIVELOGLIKELIHOOD).activation(Activation.SOFTMAX).nIn(numHiddenNodes).nOut(numOutputs).build())
                .build();

        // 创建模型
        MultiLayerNetwork model = new MultiLayerNetwork(conf);
        model.init();
        model.setListeners(new ScoreIterationListener(10));
        String summary = model.summary();
        System.out.println(summary);

        model.fit(trainIter, nEpochs);
        System.out.println("Evaluate model....");
        Evaluation evaluate = model.evaluate(testIter);
        System.out.println(evaluate.stats());
//        generateVisuals(model, trainIter, testIter);
        File locationToSave = new File("LinearDataClassifier.zip");
        model.save(locationToSave, true);

//        INDArray test = Nd4j.create(new double[][]{{0.655338925515076, 0.590301641707133}});
//        int[] predict = model.predict(test);
//        System.out.println(Arrays.toString(predict));

    }

    public static void generateVisuals(MultiLayerNetwork model, DataSetIterator trainIter, DataSetIterator testIter) throws Exception {
        if (visualize) {
            double xMin = 0;
            double xMax = 1.0;
            double yMin = -0.2;
            double yMax = 0.8;
            int nPointsPerAxis = 100;

            //Generate x,y points that span the whole range of features
            INDArray allXYPoints = PlotUtil.generatePointsOnGraph(xMin, xMax, yMin, yMax, nPointsPerAxis);
            //Get train data and plot with predictions
            PlotUtil.plotTrainingData(model, trainIter, allXYPoints, nPointsPerAxis);
            TimeUnit.SECONDS.sleep(3);
            //Get test data, run the test data through the network to generate predictions, and plot those predictions:
            PlotUtil.plotTestData(model, testIter, allXYPoints, nPointsPerAxis);
        }
    }
}
