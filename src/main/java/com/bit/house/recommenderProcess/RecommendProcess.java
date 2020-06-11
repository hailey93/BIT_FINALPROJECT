package com.bit.house.recommenderProcess;

import org.deeplearning4j.models.embeddings.loader.WordVectorSerializer;
import org.deeplearning4j.models.word2vec.Word2Vec;
import org.deeplearning4j.text.sentenceiterator.CollectionSentenceIterator;
import org.deeplearning4j.text.sentenceiterator.SentenceIterator;
import org.deeplearning4j.text.tokenization.tokenizer.preprocessor.CommonPreprocessor;
import org.deeplearning4j.text.tokenization.tokenizerfactory.DefaultTokenizerFactory;
import org.deeplearning4j.text.tokenization.tokenizerfactory.TokenizerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.List;

public class RecommendProcess {

    private static Logger log = LoggerFactory.getLogger(RecommendProcess.class);

    public void recommender(List<String> sentences) {
        Word2Vec word2Vec = WordVectorSerializer.readWord2VecModel("src/main/webapp/trainingFile/trainingFile1.txt");

        /*PLEASE NOTE: after model is restored, it's still required to set SentenceIterator and TokenizerFactory, if you're going to train this model*/


        SentenceIterator uptrainIterator = new CollectionSentenceIterator(sentences);
        TokenizerFactory uptrainTokenizer = new DefaultTokenizerFactory();

        uptrainTokenizer.setTokenPreProcessor(new CommonPreprocessor() {
            @Override
            public String preProcess(String token) {
                return StringCleaning.stripPunct(token).toUpperCase();
            }


        });

        word2Vec.setTokenizerFactory(uptrainTokenizer);
        word2Vec.setSentenceIterator(uptrainIterator);


        log.info("Word2vec uptraining...");

        word2Vec.fit();

        Collection<String> uptrainWords = word2Vec.wordsNearestSum("ZUTTSOFAS-100100001", 5);
        log.info("Closest words : " + uptrainWords);
    }
}
