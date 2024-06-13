package com.brandon.dl4j.learn.deeplearning.modeling.classification;

import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.zoo.model.VGG16;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class IrisPredict {

    public static void main(String[] args) throws Exception {
        MultiLayerNetwork model = MultiLayerNetwork.load(new File("iris.zip"), true);
//        System.out.println(model.summary());
//        INDArray example = Nd4j.create(new double[][]{{10.8,2.6,4.0,1.2}});
//        int[] predict = model.predict(example);
//        System.out.println(Arrays.toString(predict));

    }
}
