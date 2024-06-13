package com.brandon.dl4j.learn.nd;

import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.ops.transforms.Transforms;

public class Nd4jEx4 {

    public static void main(String[] args) {
        INDArray ones = Nd4j.ones(3, 5);
        INDArray mul = ones.mul(2);
//        INDArray tan = Transforms.tan(mul);
//        INDArray pow = Transforms.pow(mul, 2);
        INDArray max = Transforms.max(mul, 3);
        System.out.println(max);
//        INDArray originArr = Nd4j.linspace(1, 15, 15).reshape(3, 5);
//        System.out.println(originArr);
//        INDArray row = Nd4j.linspace(0, 4, 5);
//        System.out.println(row);
//        INDArray mulRowVector = originArr.mulRowVector(row);
//        System.out.println(mulRowVector);
//        INDArray first = Nd4j.rand(1, 2);
//        INDArray second = Nd4j.rand(2, 1);
//        INDArray mul = first.mmul(second);
//        System.out.println(first);
//        System.out.println(second);
//        System.out.println(mul);

//        INDArray originArr = Nd4j.linspace(1, 15, 15).reshape(3, 5);
//        INDArray random = Nd4j.rand(3, 5);
//        INDArray added = originArr.add(random);
//        System.out.println(added == originArr);
//        System.out.println(random);
//        System.out.println(added);

//        INDArray copyAdd = originArr.add(1);
//        System.out.println(originArr == copyAdd);
//        System.out.println(originArr);
//        System.out.println(copyAdd);

//        INDArray addi = originArr.addi(2);
//        System.out.println(originArr == addi);
//        System.out.println(originArr);
//        System.out.println(addi);
    }

}
