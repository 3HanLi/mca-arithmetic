package com.wy.mca.test;

import lombok.Data;

import java.util.List;

public class TestTreeSum {

    private static void processLearningCount(LearningCount learningCount){
    }

}


@Data
class LearningCount{

    private Integer count;

    private List<LearningCount> learningCountList;

}
