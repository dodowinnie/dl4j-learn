package com.brandon.dl4j.learn.demo1;

import org.datavec.api.records.reader.impl.csv.CSVRecordReader;
import org.datavec.api.split.FileSplit;
import org.datavec.api.transform.TransformProcess;
import org.datavec.api.transform.metadata.ColumnMetaData;
import org.datavec.api.transform.schema.InferredSchema;
import org.datavec.api.transform.schema.Schema;
import org.datavec.api.transform.transform.categorical.StringToCategoricalTransform;
import org.datavec.api.transform.transform.integer.ConvertToInteger;
import org.datavec.api.writable.Writable;
import org.datavec.local.transforms.LocalTransformExecutor;
import org.nd4j.shade.guava.io.ByteArrayDataOutput;
import org.nd4j.shade.guava.io.ByteStreams;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TransformDemo {

    public static void main(String[] args) throws Exception {

        int numLinesToSkip = 1;
        char delimiter = ' ';
        CSVRecordReader recordReader = new CSVRecordReader(numLinesToSkip, delimiter);
        FileSplit fileSplit = new FileSplit(new File("data/iris.txt"));
        recordReader.initialize(fileSplit);

        // the String to label conversion. Define schema and transform:
        Schema schema = new Schema.Builder()
                .addColumnsDouble("Sepal length", "Sepal width", "Petal length", "Petal width")
                .addColumnCategorical("Species", "setosa", "versicolor", "virginica")
                .build();

        TransformProcess tp = new TransformProcess.Builder(schema)
                .categoricalToInteger("Species")
                .build();




        ByteArrayDataOutput bos = ByteStreams.newDataOutput();
        List<List<Writable>> inputDatas = new ArrayList<List<Writable>>();
        while (recordReader.hasNext()) {
            List<Writable> records = recordReader.next();
            records.remove(0);
            List<Writable> execute = tp.execute(records);
            inputDatas.add(execute);
//            break;
        }
        BufferedWriter writer = null;
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter("data/iris-convert.txt");
            writer = new BufferedWriter(fileWriter);
            for(List<Writable> data : inputDatas){
                for (Writable d : data) {
                    String ds = d.toString();
                    writer.write(ds + " ");
                }
                writer.newLine();
            }
            writer.flush();
        }catch (Exception e) {
            e.printStackTrace();
            fileWriter.close();
            writer.close();
        }





//        byte[] bytes = bos.toByteArray();
//        String path = "data/iris-convert.txt";
//        Files.write(Paths.get(path), bytes);


    }
}
