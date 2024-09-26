package com.enterprise.isodd.core;

import java.math.BigInteger;

@FunctionalInterface
public interface OddnessEvaluator {
    boolean evaluate(BigInteger number);
}
