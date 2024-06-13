package com.brandon.dl4j.learn.nd;

import org.nd4j.linalg.api.buffer.DataType;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.api.ops.impl.reduce.longer.MatchCondition;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.indexing.BooleanIndexing;
import org.nd4j.linalg.indexing.conditions.Condition;
import org.nd4j.linalg.indexing.conditions.Conditions;

import java.util.Arrays;

public class Nd4jEx6 {

    public static void main(String[] args) {
        long nRows = 3;
        long nCols = 5;
        long rngSeed = 12345;
        Nd4j.getRandom().setSeed(rngSeed);
        INDArray random = Nd4j.rand(DataType.FLOAT, nRows, nCols).muli(2).subi(1);
        System.out.println(random);

        INDArray randomCopy = random.dup();

//        BooleanIndexing.replaceWhere(randomCopy, 0.0, Conditions.lessThan(0));
//        System.out.println(randomCopy);
//        INDArray hasNaNs = Nd4j.create(new double[]{1.0, 1.0, Double.NaN, 1.0});
//        BooleanIndexing.replaceWhere(hasNaNs, 0.0, Conditions.isNan());
//        System.out.println(hasNaNs);

//        INDArray tens = Nd4j.valueArrayOf(nRows, nCols, 10.0);
//        System.out.println(tens);
//        BooleanIndexing.replaceWhere(randomCopy, tens, Conditions.lessThan(0));
//        System.out.println(randomCopy);

        MatchCondition op = new MatchCondition(random, Conditions.greaterThan(0));
        INDArray exec = Nd4j.getExecutioner().exec(op);
        System.out.println(Arrays.toString(exec.shape()));


    }

}
