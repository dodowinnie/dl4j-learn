package com.brandon.dl4j.learn.nd;

import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

import java.util.Arrays;

public class Nd4JEx2 {

    public static void main(String[] args) {
        int rows = 3;
        int columns = 5;
//        INDArray allZeros = Nd4j.zeros(rows, columns);
//        System.out.println(allZeros);
//
//        INDArray allOnes = Nd4j.ones(rows, columns);
//        System.out.println(allOnes);
//
//        INDArray allTens = Nd4j.valueArrayOf(rows, columns, 10.0);
//        System.out.println(allTens);

//        double[] vectorDouble = new double[]{1,2,3};
//        INDArray rowVector = Nd4j.create(vectorDouble);
//        System.out.println(rowVector);
//        System.out.println(Arrays.toString(rowVector.shape()));
//
//        INDArray colVector = Nd4j.create(vectorDouble, 3, 1);
//        System.out.println(colVector);
//        System.out.println(Arrays.toString(colVector.shape()));

//        double[][] matrixDouble = new double[][]{
//                {1.0, 2.0, 3.0},
//                {4.0,5.0,6.0}
//        };
//        INDArray matrix = Nd4j.create(matrixDouble);
//        System.out.println(matrix);

//        int[] shape = new int[]{rows, columns};
//        INDArray rand = Nd4j.rand(shape);
//        System.out.println(rand);
//        System.out.println(rand.getDouble(0,1));
//        INDArray gaussian = Nd4j.randn(shape);
//        System.out.println(gaussian);

//        long[] shape_long = new long[]{rows, columns};
//        long rngseed = 12345;
//        INDArray randn1 = Nd4j.randn(rngseed, shape_long);
//        INDArray randn2 = Nd4j.randn(rngseed, shape_long);
//        System.out.println(randn1);
//        System.out.println();
//        System.out.println(randn2);

//        INDArray threeDimArr = Nd4j.ones(3, 4, 5);
//        System.out.println(threeDimArr);
//
//        INDArray fourDimArr = Nd4j.ones(3, 4, 5, 6);
//        System.out.println(fourDimArr);

//        INDArray rowVector1 = Nd4j.create(new double[]{1, 2, 3});
//        INDArray rowVector2 = Nd4j.create(new double[]{4, 5, 6});
//
//        // 垂直扩展
//        INDArray vstack = Nd4j.vstack(rowVector1, rowVector2);
//        System.out.println(vstack);
//        // 水平扩展
//        INDArray hstack = Nd4j.hstack(rowVector1, rowVector2);
//        System.out.println(hstack);

//        INDArray identityMatrix = Nd4j.eye(3);
//        System.out.println(identityMatrix);
        INDArray linspace = Nd4j.linspace(1,10,10);                 //Values 1 to 10, in 10 steps
        System.out.println("Nd4j.linspace(1,10,10):\n" + linspace);


    }

}
