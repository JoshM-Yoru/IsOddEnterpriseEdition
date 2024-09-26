package com.enterprise.isodd.core;

import java.math.BigInteger;
import java.util.function.Supplier;

import org.springframework.stereotype.Component;

@Component
public class OddnessEvaluators {
    public static final OddnessEvaluator BITWISE = number -> number.testBit(0);
    public static final OddnessEvaluator MODULO = number -> number.mod(BigInteger.TWO).equals(BigInteger.ONE);
    public static final OddnessEvaluator RECURSIVE = new OddnessEvaluator() {
        @Override
        public boolean evaluate(BigInteger number) {
            return trampoline(isOddTailRec(number.abs(), true));
        };

        private TailCall<Boolean> isOddTailRec(BigInteger n, boolean p) {
            if (n.equals(BigInteger.ZERO)) {
                return TailCall.complete(!p);
            } else if (n.equals(BigInteger.ONE)) {
                return TailCall.complete(p);
            } else {
                return TailCall.next(() -> isOddTailRec(n.subtract(BigInteger.ONE), !p));
            }
        }

        private <T> T trampoline(TailCall<T> tailRec) {
            while (tailRec.isNext()) {
                tailRec = tailRec.next().get();
            }
            return tailRec.result();
        }
    };

    private interface TailCall<T> {
        boolean isNext();

        Supplier<TailCall<T>> next();

        T result();

        static <T> TailCall<T> next(Supplier<TailCall<T>> nextCall) {
            return new TailCall<T>() {
                @Override
                public boolean isNext() {
                    return true;
                }

                @Override
                public Supplier<TailCall<T>> next() {
                    return nextCall;
                }

                @Override
                public T result() {
                    throw new IllegalStateException("Not Completed!");
                }
            };
        }

        static <T> TailCall<T> complete(T value) {
            return new TailCall<T>() {
                @Override
                public boolean isNext() {
                    return false;
                }

                @Override
                public Supplier<TailCall<T>> next() {
                    throw new IllegalStateException("Not Completed!");
                }

                @Override
                public T result() {
                    return value;
                }

            };
        }
    }
}
