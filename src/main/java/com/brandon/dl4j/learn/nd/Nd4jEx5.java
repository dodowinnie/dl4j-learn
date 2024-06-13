package com.brandon.dl4j.learn.nd;

import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

public class Nd4jEx5 {

    public static void main(String[] args) {
        INDArray originArray = Nd4j.linspace(1, 15, 15).reshape(3, 5);
        System.out.println(originArray);
//        double minValue = originArray.minNumber().doubleValue();
//        double maxValue = originArray.maxNumber().doubleValue();
//        double sum = originArray.sumNumber().doubleValue();
//        double avg = originArray.meanNumber().doubleValue();
//        double stdev = originArray.stdNumber().doubleValue();
//        System.out.println("minValue:       " + minValue);
//        System.out.println("maxValue:       " + maxValue);
//        System.out.println("sum:            " + sum);
//        System.out.println("average:        " + avg);
//        System.out.println("standard dev.:  " + stdev);
//        INDArray minAlong0 = originArray.min(0);
//        INDArray maxAlong0 = originArray.max(0);
//        INDArray sumAlong0 = originArray.sum(0);
//        INDArray avgAlong0 = originArray.mean(0);
//        INDArray stdevAlong0 = originArray.std(0);
//        System.out.println("\n\n\n");
//        System.out.println("min along dimension 0:  " + minAlong0);
//        System.out.println("max along dimension 0:  " + maxAlong0);
//        System.out.println("sum along dimension 0:  " + sumAlong0);
//        System.out.println("avg along dimension 0:  " + avgAlong0);
//        System.out.println("stddev along dimension 0:  " + stdevAlong0);
//        INDArray avgAlong1 = originArray.mean(1);
//        System.out.println(avgAlong1);
        INDArray argMaxAlongDim0 = Nd4j.argMax(originArray, 0);
        System.out.println(argMaxAlongDim0);


    }

}
