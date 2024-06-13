package com.brandon.dl4j.learn.draft;

import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

import java.util.Arrays;

public class Draft {

    public static void main(String[] args) {
        INDArray tmp = Nd4j.create(new double[][]{{1, 2}});
        System.out.println(Arrays.toString(tmp.shape()));
    }
}
