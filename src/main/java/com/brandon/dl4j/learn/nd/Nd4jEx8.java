package com.brandon.dl4j.learn.nd;

import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

public class Nd4jEx8 {

    public static void main(String[] args) {
//        INDArray nd = Nd4j.create(new float[]{1, 2, 3, 4}, 2, 2);
//        System.out.println(nd);
        INDArray ndv;
//        ndv = nd.transpose(); // 翻转
//        System.out.println(ndv);
//        INDArray nd2 = Nd4j.create(new float[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12}, 2, 6);
//        System.out.println(nd2);
//        ndv = nd2.transpose();
//        System.out.println(ndv);
//        ndv = nd2.reshape(3,4);
//        System.out.println(ndv);
        INDArray nd3 = Nd4j.create(new float[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12}, 3,4);
        System.out.println(nd3);
        ndv = nd3.broadcast(12,4);
        System.out.println(ndv);


    }
}
