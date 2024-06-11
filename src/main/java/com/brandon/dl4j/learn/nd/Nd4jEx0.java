package com.brandon.dl4j.learn.nd;

import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.api.ops.impl.transforms.Pad;
import org.nd4j.linalg.factory.Nd4j;

public class Nd4jEx0 {

    public static void main(String[] args) {
        // 2维数组
        int nRows = 2;
        int nCols = 3;
        INDArray zeros = Nd4j.zeros(nRows, nCols);
        System.out.println(zeros);

        INDArray ones = Nd4j.ones(nRows, nCols);
        System.out.println(ones);

        // 按行拼接
        INDArray concat = Nd4j.concat(0, ones, zeros);
        System.out.println(concat);

        // 按列拼接
//        INDArray concat2 = Nd4j.concat(1, zeros, ones);
//        System.out.println(concat2);

        //Padding
//        INDArray padded = Nd4j.pad(ones, new int[]{2,1}, Pad.Mode.CONSTANT, 0 );
//        System.out.println("### Padded ####");
//        System.out.println(padded);

        //hstack

//        INDArray hstack = Nd4j.hstack(ones,zeros);
//        System.out.println("### HSTACK ####");
//        System.out.println(hstack);

        //vstack
        INDArray vstack = Nd4j.vstack(ones,zeros);
        System.out.println("### VSTACK ####");
        System.out.println(vstack);

    }

}
