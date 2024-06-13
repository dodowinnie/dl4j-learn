package com.brandon.dl4j.learn.nd;

import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.indexing.INDArrayIndex;
import org.nd4j.linalg.indexing.NDArrayIndex;

import java.util.Arrays;

public class Nd4jEx3 {

    public static void main(String[] args) {
        INDArray originalArr = Nd4j.linspace(1, 15, 15).reshape('c', 3, 5);
        INDArray firstRowDup = originalArr.getRow(0).dup();   //We now have a copy of the first row. i.e., firstRowDup is NOT a view of originalArray
        firstRowDup.addi(100);
        System.out.println("\n\n\n");
        System.out.println("firstRowDup, after .addi(100):\n" + firstRowDup);
        System.out.println("originalArray, after firstRowDup.addi(100): (note it is unmodified)\n" + originalArr);

    }

}
