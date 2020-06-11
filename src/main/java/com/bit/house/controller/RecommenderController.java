package com.bit.house.controller;

import com.bit.house.recommenderProcess.RecommendProcess;
import com.bit.house.recommenderProcess.TrainingProcess;
import com.bit.house.service.RecommenderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RecommenderController {

    private static Logger log = LoggerFactory.getLogger(RecommenderController.class);

    @Autowired(required =false)
    RecommenderService recommenderService;

    @GetMapping("/training")
    public String getTraining(){

        TrainingProcess training=new TrainingProcess();
        training.training(recommenderService.selectClickProductById());

        return "th/admin/recommender/trainingTestPage";
    }

    @GetMapping("/recommenderItems")
    public String getRecommender(){ //추후 추천아이템이 보이는 페이지와 연결해야한다.

        RecommendProcess recommendItems=new RecommendProcess();
        recommendItems.recommender(recommenderService.selectClickProductById());

        return "test2";
    }
}
