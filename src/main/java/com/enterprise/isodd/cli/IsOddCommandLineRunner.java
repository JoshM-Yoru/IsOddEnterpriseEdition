package com.enterprise.isodd.cli;

import java.math.BigInteger;
import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.enterprise.isodd.facade.OddnessEvaluationFacade;

@Component
public class IsOddCommandLineRunner implements CommandLineRunner {
    private final OddnessEvaluationFacade facade;

    public IsOddCommandLineRunner(OddnessEvaluationFacade facade) {
        this.facade = facade;
    }

    @Override
    public void run(String... args) {
        var scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Enter a number to check if it is odd, 'change' to switch strategy, or 'exit' to quit:");
            var input = scanner.nextLine().trim();

            switch (input) {
                case "exit" -> {
                    System.out.println("Thank you for using IsOdd Enterprise Edition! Have a great day!");
                    scanner.close();
                    return;
                }
                case "change" -> {
                    System.out.println("Enter strategy (bitwise/modulo/recursive):");
                    var strategy = scanner.nextLine().trim();
                    facade.changeEvaluationStrategy(strategy);
                    System.out.println("Strategy changed to: " + strategy);
                }

                default -> {
                    try {
                        var number = new BigInteger(input);
                        var isOdd = facade.evaluateOddness(number);
                        String result = isOdd ? "odd." : "not odd.";
                        System.out.println(number + " is " + result);
                    } catch (NumberFormatException e) {
                        System.out.println(
                                "Invalid input. Please enter a valid integer so that we can complete your IsOdd Enterprise Experience.");
                    }
                }
            }
        }
    }
}
