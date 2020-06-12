package com.bit.house.recommenderProcess;

import org.deeplearning4j.models.embeddings.loader.WordVectorSerializer;
import org.deeplearning4j.models.word2vec.Word2Vec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

public class RecommendProcess {

    private static Logger log = LoggerFactory.getLogger(RecommendProcess.class);

    public Collection<String> recommender(String productNo) {
        Word2Vec word2Vec = WordVectorSerializer.readWord2VecModel("src/main/webapp/trainingFile/trainingFile1.txt");

        /*PLEASE NOTE: after model is restored, it's still required to set SentenceIterator and TokenizerFactory, if you're going to train this model*/


        /*SentenceIterator uptrainIterator = new CollectionSentenceIterator(sentences);
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

        word2Vec.fit();*/

        Collection<String> recommendItems = word2Vec.wordsNearestSum(productNo, 4);
        log.info("Closest words : " + recommendItems);

        return recommendItems;
    }
}
