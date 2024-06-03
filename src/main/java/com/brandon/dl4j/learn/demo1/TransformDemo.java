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

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
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

        while (recordReader.hasNext()) {
            List<Writable> records = recordReader.next();
            records.remove(0);
            for (Writable writable : tp.execute(records)) {
                System.out.println(writable.toString());
            }
            System.out.println("===================================");
            break;
        }


    }
}
