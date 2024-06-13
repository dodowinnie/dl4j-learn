package com.brandon.dl4j.learn.datavec.transform;

import com.brandon.dl4j.learn.util.DownloaderUtility;
import org.datavec.api.records.reader.impl.csv.CSVRecordReader;
import org.datavec.api.records.writer.RecordWriter;
import org.datavec.api.records.writer.impl.csv.CSVRecordWriter;
import org.datavec.api.split.FileSplit;
import org.datavec.api.split.partition.NumberOfRecordsPartitioner;
import org.datavec.api.split.partition.Partitioner;
import org.datavec.api.transform.TransformProcess;
import org.datavec.api.transform.condition.ConditionOp;
import org.datavec.api.transform.condition.column.CategoricalColumnCondition;
import org.datavec.api.transform.condition.column.DoubleColumnCondition;
import org.datavec.api.transform.filter.ConditionFilter;
import org.datavec.api.transform.schema.Schema;
import org.datavec.api.transform.transform.time.DeriveColumnsFromTimeTransform;
import org.datavec.api.writable.DoubleWritable;
import org.datavec.api.writable.Writable;
import org.datavec.local.transforms.LocalTransformExecutor;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DateTimeZone;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * 1.移除一些不必要的列
 * 2.过滤样本，只保留“MerchantCountryCode”列是USA和CAN
 * 3.将"TransactionAmountUSD"列中的非法数值替换掉
 * 4.解析日期字符串，抽取小时病创建一个新的列HourOfDay
 */
public class CSVMixedDataTypes {

    public static String dataLocalPath;
    public static void main(String[] args) throws Exception {
        dataLocalPath = DownloaderUtility.BASICDATAVECEXAMPLE.Download();
        // 第一步定义输入schema
        Schema inputDataSchema = new Schema.Builder()
                .addColumnString("DateTimeString") // 定义一个列
                .addColumnsString("CustomerID", "MerchantID") // 相同类型的可以一起定义
                .addColumnInteger("NumItemsInTransaction")
                .addColumnCategorical("MerchantCountryCode", Arrays.asList("USA", "CAN", "FR", "MX"))
                // 最小的是0，没有最大限制，不允许NaN，不允许无限值
                .addColumnDouble("TransactionAmountUSD", 0.0, null, false, false)
                .addColumnCategorical("FraudLabel", Arrays.asList("Fraud", "Legit"))
                .build();
//        System.out.println(inputDataSchema);
//        System.out.println("\n\nOther information obtainable from schema:");
//        System.out.println("Number of columns: " + inputDataSchema.numColumns());
//        System.out.println("Column names: " + inputDataSchema.getColumnNames());
//        System.out.println("Column types: " + inputDataSchema.getColumnTypes());
        // 第二步，定义想在数据上做的操作
        TransformProcess tp = new TransformProcess.Builder(inputDataSchema)
                .removeColumns("CustomerID", "MerchantID") // 不要这两列
                // 过滤是指满足条件的移除掉
                .filter(new ConditionFilter(new CategoricalColumnCondition("MerchantCountryCode", ConditionOp.NotInSet, new HashSet<>(Arrays.asList("USA", "CAN")))))
                // 假设数据中的金额有负数的非法数值，负数替换为0
                .conditionalReplaceValueTransform("TransactionAmountUSD", new DoubleWritable(0), new DoubleColumnCondition("TransactionAmountUSD", ConditionOp.LessThan, 0.0))
                // 时间格式化
                .stringToTimeTransform("DateTimeString", "YYYY-MM-DD HH:mm:ss.SSS", DateTimeZone.UTC)
                // 重命名列
                .renameColumn("DateTimeString", "DateTime")
                // 根据现有的列添加一个新列
                .transform(new DeriveColumnsFromTimeTransform.Builder("DateTime").addIntegerDerivedColumn("HourOfDay", DateTimeFieldType.hourOfDay()).build())
                .removeColumns("DateTime")
                .build();

        // 得到新的schema
        Schema outputSchema = tp.getFinalSchema();
//        System.out.println(outputSchema);

        // 第三步，加载数据执行操作
        File inputFile = new File(dataLocalPath, "exampledata.csv");
        File outputFile = new File("BasicDataVecExampleLocalOut.csv"); // 输出文件
        if(outputFile.exists()){
            outputFile.delete();
        }
        outputFile.createNewFile();
        CSVRecordReader rr = new CSVRecordReader(0, ",");
        rr.initialize(new FileSplit(inputFile));
        RecordWriter rw = new CSVRecordWriter();
        Partitioner p = new NumberOfRecordsPartitioner();
        rw.initialize(new FileSplit(outputFile), p);
        //Process the data:
        List<List<Writable>> originalData = new ArrayList<>();
        while(rr.hasNext()){
            originalData.add(rr.next());
        }
        List<List<Writable>> processData = LocalTransformExecutor.execute(originalData, tp);
        rw.writeBatch(processData);
        rw.close();



    }
}
