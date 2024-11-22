package org.example;

import java.math.BigInteger;

public class TaskThree {
    //Even long was useless here, so I used BigInteger
    public static void main(String[] args) {

        int number = 100;

        // Calculate factorial of 100 using a recursive method
        BigInteger factorialOfHundred = calculateFactorial(BigInteger.valueOf(number));

        //Calculate sum of the digits in the result
        int digitSum = calculateSumOfDigits(factorialOfHundred);

        //Finally, print the result to the console for review
        System.out.println("The sum of the digits in 100! is: " + digitSum);
    }

    private static BigInteger calculateFactorial(BigInteger num) {
        //Base case: if num equals 1, return 1
        if (num.equals(BigInteger.ONE)) {
            return BigInteger.ONE;
        }

        //Recursive step: num * factorial(num - 1)
        return num.multiply(calculateFactorial(num.subtract(BigInteger.ONE)));
    }

    private static int calculateSumOfDigits(BigInteger number) {
        //Convert the BigInteger to a string so we can loop through its digits
        String numberAsString = number.toString();

        int sum = 0; //Initialize a variable to store the sum of digits

        //Loop through each character in the string representation of the number
        for (char digitChar : numberAsString.toCharArray()) {
            //Convert the character back to an integer and add it to the sum
            sum += Character.getNumericValue(digitChar);
        }

        //Return the total sum of digits
        return sum;
    }
}