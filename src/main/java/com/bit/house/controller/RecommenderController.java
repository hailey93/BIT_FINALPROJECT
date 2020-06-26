package com.bit.house.controller;

import com.bit.house.recommenderProcess.TrainingProcess;
import com.bit.house.service.RecommenderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class RecommenderController {

    private static Logger log = LoggerFactory.getLogger(RecommenderController.class);

    @Autowired(required =false)
    RecommenderService recommenderService;

    @GetMapping("/training")
    public String training(){
        return "th/admin/recommender/trainingTestPage";
    }
    //추천아이템 트레이닝
    @GetMapping("/trainingStart")
    public void getTraining(){

        TrainingProcess training=new TrainingProcess();
        training.training(recommenderService.selectClickProductById());

    }
    /*@GetMapping("/training")
    public String getTraining(){

        TrainingProcess training=new TrainingProcess();
        training.training(recommenderService.selectClickProductById());

        return "th/admin/recommender/trainingTestPage";
    }*/

}
