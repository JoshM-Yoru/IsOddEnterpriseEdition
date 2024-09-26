package com.enterprise.isodd.factory;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.enterprise.isodd.core.OddnessEvaluator;
import com.enterprise.isodd.core.OddnessEvaluators;

@Component
public class OddnessEvaluationFactory {
    private static final Map<String, OddnessEvaluator> EVALUATOR_MAP = Map.of(
            "BITWISE", OddnessEvaluators.BITWISE,
            "MODULO", OddnessEvaluators.MODULO,
            "RECURSIVE", OddnessEvaluators.RECURSIVE);

    public OddnessEvaluator createEvaluator(String type) {
        return EVALUATOR_MAP.getOrDefault(type.toUpperCase(), OddnessEvaluators.BITWISE);
    }
}
