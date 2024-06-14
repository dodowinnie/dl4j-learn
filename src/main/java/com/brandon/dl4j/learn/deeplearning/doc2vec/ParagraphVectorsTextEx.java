package com.brandon.dl4j.learn.deeplearning.doc2vec;

import org.deeplearning4j.models.word2vec.VocabWord;
import org.deeplearning4j.models.word2vec.wordstore.inmemory.AbstractCache;
import org.deeplearning4j.text.sentenceiterator.BasicLineIterator;
import org.deeplearning4j.text.tokenization.tokenizer.preprocessor.CommonPreprocessor;
import org.deeplearning4j.text.tokenization.tokenizerfactory.DefaultTokenizerFactory;

import java.io.File;
import java.io.FileNotFoundException;

public class ParagraphVectorsTextEx {

    public static void main(String[] args) throws Exception {

        String filePath = "data/raw_sentences.txt";
        File file = new File(filePath);
        BasicLineIterator iter = new BasicLineIterator(file);
        AbstractCache<VocabWord> cache = new AbstractCache<>();
        DefaultTokenizerFactory t = new DefaultTokenizerFactory();
        t.setTokenPreProcessor(new CommonPreprocessor());


    }
}
