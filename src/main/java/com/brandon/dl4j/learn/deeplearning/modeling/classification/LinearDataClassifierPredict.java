package com.brandon.dl4j.learn.deeplearning.modeling.classification;

import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class LinearDataClassifierPredict {

    public static void main(String[] args) throws IOException {
        MultiLayerNetwork model = MultiLayerNetwork.load(new File("LinearDataClassifier.zip"), true);
        INDArray example = Nd4j.create(new double[][]{{0.75, 0.69}});
        int[] predict = model.predict(example);
        System.out.println(Arrays.toString(predict));
    }
}
