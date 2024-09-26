package com.enterprise.isodd.core;

import java.math.BigInteger;

import org.springframework.stereotype.Component;

@Component
public class OddnessEvaluators {
    public static final OddnessEvaluator BITWISE = number -> number.testBit(0);
    public static final OddnessEvaluator MODULO = number -> number.mod(BigInteger.TWO).equals(BigInteger.ONE);
    public static final OddnessEvaluator RECURSIVE = new OddnessEvaluator() {
        @Override
        public boolean evaluate(BigInteger number) {
            return switch (number.abs().compareTo(BigInteger.TWO)) {
                case 0 -> false;
                case 1 -> true;
                default -> evaluate(number.abs().subtract(BigInteger.TWO));
            };
        }
    };
}
