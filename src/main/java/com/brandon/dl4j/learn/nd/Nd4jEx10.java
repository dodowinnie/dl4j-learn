package com.brandon.dl4j.learn.nd;

import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

public class Nd4jEx10 {

    public static void main(String[] args) {
        INDArray ndv;
        INDArray nd1 = Nd4j.create(new double[]{1, 2, 3, 4, 5, 6}, 2, 3);
        System.out.println(nd1);

//        INDArray ndv = nd1.add(1);
//        System.out.println(ndv);
//        ndv = nd1.mul(5);
//        System.out.println(ndv);

//        INDArray nd2 = Nd4j.create(new double[]{10, 20}, 2, 1);
//        System.out.println(nd2);
//
//        ndv = nd1.addColumnVector(nd2);
//        System.out.println(ndv);
        INDArray nd3 = Nd4j.create(new double[]{30, 40, 50}, 1, 3);
        System.out.println(nd3);
        ndv = nd1.addRowVector(nd3);
        System.out.println(ndv);



    }

}
