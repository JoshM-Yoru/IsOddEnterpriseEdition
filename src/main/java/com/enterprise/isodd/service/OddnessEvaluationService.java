package com.enterprise.isodd.service;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enterprise.isodd.core.OddnessEvaluator;
import com.enterprise.isodd.factory.OddnessEvaluationFactory;

@Service
public class OddnessEvaluationService {
    private final OddnessEvaluationFactory factory;
    private OddnessEvaluator currentEvaluator;

    @Autowired
    public OddnessEvaluationService(OddnessEvaluationFactory factory) {
        this.factory = factory;
        this.currentEvaluator = factory.createEvaluator("BITWISE");
    }

    public boolean isOdd(BigInteger number) {
        return currentEvaluator.evaluate(number);
    }

    public void setEvaluatorStrategy(String strategy) {
        this.currentEvaluator = factory.createEvaluator(strategy);
    }

}
