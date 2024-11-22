package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskOne {

    public static void main(String[] args) {

        // In this part, I called sout with a scanner to create user input according to the task.
        System.out.println("Enter number of brackets: ");

        Scanner myScanner = new Scanner(System.in);

        // Created variable which will be used as Catalan number.
        int N = myScanner.nextInt();

        // Added functionality to calculate and display the number of correct bracket sequences.
        System.out.println("Number of correct bracket sequences: " + calculateCatalan(N));

        // Calling brackets as per the existing logic, with clear output for its role.
        System.out.println("Brackets calculation result: " + brackets(N));

        // Generating and displaying all valid bracket sequences for the given number of bracket pairs.
        System.out.println("Valid bracket sequences: ");
        List<String> sequences = generateBracketSequences(N);
        for (String sequence : sequences) {
            System.out.println(sequence);
        }
    }

    /* Used long in both methods, because in some cases, a huge amount of calculations can occur.
     * Long ensures we won't face issues when big numbers are used as input.
     */
    private static long factorial(int num) {
        // Base case for factorial, using a ternary for simplicity.
        return (num == 1 || num == 0) ? 1 : num * factorial(num - 1);
    }

    // Existing brackets method is preserved for other uses or outputs.
    private static long brackets(int N) {
        return factorial(2 * N) / (factorial(N) * factorial(N + 1));
    }

    /* Added a new method, calculateCatalan, specifically for Catalan numbers.
     * It uses the same formula as brackets but emphasizes its purpose in calculating correct bracket sequences.
     */
    private static long calculateCatalan(int N) {
        // Using the factorial-based formula for Catalan numbers.
        return factorial(2 * N) / (factorial(N) * factorial(N + 1));
    }

    /* Added a method to generate all valid bracket sequences.
     * This uses recursion to explore all combinations of '(' and ')' that maintain balance.
     */
    private static List<String> generateBracketSequences(int N) {
        List<String> sequences = new ArrayList<>();
        generateHelper(sequences, "", 0, 0, N);
        return sequences;
    }

    /* Helper method for generateBracketSequences:
     * - Keeps track of the current sequence, number of open and close brackets, and the target number of pairs.
     */
    private static void generateHelper(List<String> sequences, String current, int open, int close, int max) {
        // If the sequence reaches the maximum length, add it to the list.
        if (current.length() == max * 2) {
            sequences.add(current);
            return;
        }

        // Add an open bracket if we haven't reached the max number of opens.
        if (open < max) {
            generateHelper(sequences, current + "(", open + 1, close, max);
        }

        // Add a close bracket if it won't unbalance the sequence.
        if (close < open) {
            generateHelper(sequences, current + ")", open, close + 1, max);
        }
    }

}
