package com.enterprise.isodd.facade;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.enterprise.isodd.service.OddnessEvaluationService;

@Component
public record OddnessEvaluationFacade(OddnessEvaluationService service) {

    @Autowired
    public OddnessEvaluationFacade {
    }

    public boolean evaluateOddness(BigInteger number) {
        return service().isOdd(number);
    }

    public void changeEvaluationStrategy(String strategyName) {
        service().setEvaluatorStrategy(strategyName);
    }
}
