package com.brandon.dl4j.learn.nd;

import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

public class Nd4jEx7 {

    public static void main(String[] args) {

        INDArray nd = Nd4j.create(new float[]{1, 2}, 1, 2);
        INDArray nd2 = Nd4j.create(new float[]{3,4}, 2, 1);
        INDArray nd3 = Nd4j.create(new float[][]{{1, 2}, {3,4}});
        INDArray nd4 = Nd4j.create(new float[][]{{3,4},{5,6}});
//        System.out.println(nd);
//        System.out.println(nd2);
//        System.out.println(nd3);
//        System.out.println(nd4);

//        System.out.println("Creating nd array with data type " + Nd4j.dataType());
        INDArray ndv = nd.mmul(nd2);
//        ndv = nd.mmul(nd4);
//        ndv = nd3.mmul(nd4);
        ndv = nd4.mmul(nd3);
        System.out.println(ndv);


    }

}
