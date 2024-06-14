package com.brandon.dl4j.learn.deeplearning.transferlearning;

import org.deeplearning4j.nn.graph.ComputationGraph;
import org.deeplearning4j.nn.transferlearning.FineTuneConfiguration;
import org.deeplearning4j.nn.weights.WeightInit;
import org.deeplearning4j.zoo.PretrainedType;
import org.deeplearning4j.zoo.ZooModel;
import org.deeplearning4j.zoo.model.VGG16;
import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.learning.config.Nesterovs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class Vgg16Transfer {

    private static  final Logger log = LoggerFactory.getLogger(Vgg16Transfer.class);

    private static long seed = 12345;

    public static void main(String[] args) throws Exception {
        ZooModel zooModel = VGG16.builder().build();
        ComputationGraph pretrainedNet = (ComputationGraph) zooModel.initPretrained(PretrainedType.IMAGENET);
        System.out.println(pretrainedNet.summary());

        // 定义微调设置，在已经存在设置的情况下，新的微调设置会对所有没有被冻结的层进行重写
        FineTuneConfiguration fineTuneConf = new FineTuneConfiguration.Builder()
                .activation(Activation.LEAKYRELU)
                .weightInit(WeightInit.RELU)
                .updater(new Nesterovs(5e-5))
                .dropOut(0.5)
                .seed(seed)
                .build();


    }

}
