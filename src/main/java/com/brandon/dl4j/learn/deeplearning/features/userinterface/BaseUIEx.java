package com.brandon.dl4j.learn.deeplearning.features.userinterface;

import com.brandon.dl4j.learn.util.UIExampleUtils;
import org.deeplearning4j.core.storage.StatsStorage;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.ui.api.UIServer;
import org.deeplearning4j.ui.model.stats.StatsListener;
import org.deeplearning4j.ui.model.storage.FileStatsStorage;
import org.nd4j.linalg.dataset.api.iterator.DataSetIterator;

import java.io.File;

public class BaseUIEx {

    public static void main(String[] args) {
        // 获取网络和训练数据
        MultiLayerNetwork net = UIExampleUtils.getMnistNetwork();
        DataSetIterator trainData = UIExampleUtils.getMnistData();
        // 初始化接口后端
        UIServer uiServer = UIServer.getInstance();
        StatsStorage statsStorage = new FileStatsStorage(new File(System.getProperty("java.io.tmpdir"), "ui-stats.dl4j"));
        int listenerFrequency = 1;
        net.setListeners(new StatsListener(statsStorage, listenerFrequency));
        uiServer.attach(statsStorage);
        net.fit(trainData);
        //Finally: open your browser and go to http://localhost:9000/train


    }
}
