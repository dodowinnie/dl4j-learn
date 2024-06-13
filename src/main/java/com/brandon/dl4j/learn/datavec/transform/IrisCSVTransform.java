package com.brandon.dl4j.learn.datavec.transform;

import org.apache.commons.io.FileUtils;
import org.datavec.api.records.reader.impl.csv.CSVRecordReader;
import org.datavec.api.records.writer.RecordWriter;
import org.datavec.api.records.writer.impl.csv.CSVRecordWriter;
import org.datavec.api.split.FileSplit;
import org.datavec.api.split.partition.NumberOfRecordsPartitioner;
import org.datavec.api.split.partition.Partitioner;
import org.datavec.api.transform.TransformProcess;
import org.datavec.api.transform.schema.Schema;
import org.datavec.api.writable.Writable;
import org.datavec.local.transforms.LocalTransformExecutor;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 下载的数据有2个问题，
 * 1.底部有空行
 * 2.标签是字符串，我们需要string
 */
public class IrisCSVTransform {

    public static void main(String[] args) throws Exception {
        String filename = "iris.data";
        URL url = new URL("https://archive.ics.uci.edu/ml/machine-learning-databases/iris/iris.data");
        File irisText = new File(filename);
        if (!irisText.exists()){
            FileUtils.copyURLToFile(url, irisText);
        }

        File outputFile = new File("iris-change.txt"); // 输出文件
        if(outputFile.exists()){
            outputFile.delete();
        }
        outputFile.createNewFile();
        RecordWriter rw = new CSVRecordWriter();
        Partitioner p = new NumberOfRecordsPartitioner();
        rw.initialize(new FileSplit(outputFile), p);
        //Process the data:
        List<List<Writable>> originalData = new ArrayList<>();
        CSVRecordReader rr = new CSVRecordReader(0,",");
        rr.initialize(new FileSplit(irisText));
        while(rr.hasNext()){
            originalData.add(rr.next());
        }

        // 定义schema
        Schema schema = new Schema.Builder()
                .addColumnsDouble("Sepal length", "Sepal width", "Petal length", "Petal width")
                .addColumnCategorical("Species", "Iris-setosa", "Iris-versicolor", "Iris-virginica")
                .build();
//        // 定义转换操作
        TransformProcess tp = new TransformProcess.Builder(schema)
                .categoricalToInteger("Species").build();
        List<List<Writable>> processData = LocalTransformExecutor.execute(originalData, tp);
        rw.writeBatch(processData);
        rw.close();


    }
}
