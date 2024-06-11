package com.brandon.dl4j.learn.nd;

import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

import java.util.Arrays;

public class Nd4jEx1 {

    public static void main(String[] args) {
        int rows = 3;
        int cols = 5;
        INDArray myArray = Nd4j.zeros(rows, cols);
//        System.out.println("Basic INDArray information:");
//        System.out.println("Num. Rows:          " + myArray.rows());
//        System.out.println("Num. Columns:       " + myArray.columns());
//        System.out.println("Num. Dimensions:    " + myArray.rank());
//        System.out.println("Shape:              " + Arrays.toString(myArray.shape()));
//        System.out.println("Length:             " + myArray.length());
//        System.out.println("Array Contents:     " + myArray);
//        System.out.println("Is a vector:        " + myArray.isVector());
//        System.out.println("Is a scalar:        " + myArray.isScalar());
//        System.out.println("Is a matrix:        " + myArray.isMatrix());
//        System.out.println("Is a square matrix: " + myArray.isSquare());
        myArray.putScalar(0,1,2.0);
        System.out.println("Array Contents:     " + myArray);




    }

}
