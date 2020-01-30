/**
 * Source code:  FibSequence.java
 *
 * Author:  Alp Karavil
 * Student ID:  5827197
 * Assignment:  Program #6 - Fibonacci Sequence
 *
 * Course:  COP 3337 (Intermediate Programming)
 * Section:  U09
 * Instructor:  William Feild
 * Due Date:  29 November 2018, by the beginning of class
 *
 * I hereby certify that this collective work is my own
 * and none of it is the work of any other person or entity.
 * ______________________________________ [Signature]
 *
 * Language:  Java
 * Compile/Run:
 * javac FibSequence.java
 *
 * Purpose:  This class implements the next() method from the Sequence class,
 * an getFibonacci(int fibonacciIndex) to provide the user with the
 * fibonacci number at the requested index.
 *
 * The user is only required, and allowed to use the next() method, inherited
 * from the Sequence interface, to request fibonacci numbers.
 *
 * The next() method will return fibonacci numbers starting with the first
 * index, which is number 1.
 *
 * If the current prime number reaches any value over the max integer allowed
 * (integer overflow), a FibonacciIntegerOverflow exception is thrown, which
 * can then be accessed to request a String representation of the overflow.
 *
 * The getFibonacci() method simulates these steps, given on
 * https://en.wikipedia.org/wiki/Fibonacci_number
 *
 * 1) Accept index value
 *
 * 2) Create variables storing the newest value, the old value, and the
 * oldest value. At the start, the old and oldest value are 1.
 *
 * 3) Run the for loop until the requested index is reached, while updating
 * the newest value to be the oldest + old, then updating the oldest and old
 * values respectively.
 *
 * 4) Return the final newest value after the for loop is complete
 *
 *
 * Inherits From:  None
 *
 * Interfaces: Sequence.java
 *
 * +-----------------------------------------------------------------------
 *
 * Constants:
 *
 * FIBONACCI_INDEX_START - start index value of first fibonacci
 * FIRST_TWO_FIBONACCI_NUMBERS - int value of first two fibonacci numbers
 *
 * +-----------------------------------------------------------------------
 *
 * Constructors:  This class has no constructors.
 *
 * +-----------------------------------------------------------------------
 *
 * Class Methods:
 *
 * private int getFibonacci(int fibonacciIndex) - returns the
 * fibonacci number at the requested index (recursively)
 *
 * +-----------------------------------------------------------------------
 *
 * Instance Methods:
 *
 * int fibonacciIndexCounter - counter for the index which is used by the
 * next() method
 *
 * boolean intOverflowHappened - turns true when int overflow happens, thus
 * stopping the calculation of future fibonacci numbers
 *
 * public int next() - Returns the next value, a fibonacci number, in the
 * sequence.
 * Arguments: No arguments
 * Output: int fibonacci number, an exception can be thrown
 */

public class LoopFibSequence implements Sequence
{
   private final int FIBONACCI_INDEX_START = 0;
   private int fibonacciIndexCounter = FIBONACCI_INDEX_START;
   private final int FIRST_TWO_FIBONACCI_NUMBERS = 1;
   private boolean intOverflowHappened = false;

   /**
    * Returns the next number in the fibonacci Sequence. This method utilizes
    * an iterative method to calculate the next number, which is faster than
    * both recursive methods implemented in the other Fibonacci Sequence
    * calculators.
    *
    * This method will return Fibonacci numbers starting with 1. If the
    * Fibonacci number is greater than the maximum integer, thus resulting in
    * integer overflow, a FibonacciIntegerOverflow exception will be thrown.
    *
    * @return Next integer Fibonacci number
    * @throws FibonacciIntegerOverflow Thrown when integer overflow occurs
    *                                  when calculating the next Fibonacci
    *                                  number. The exception object holds
    *                                  the value of the integer overflow as a
    *                                  String of 10 asterisks.
    **/
   public int next() throws FibonacciIntegerOverflow
   {
      if (intOverflowHappened)
      {
         throw new FibonacciIntegerOverflow();
      }

      try
      {
         int thisFibonacci = getFibonacci(fibonacciIndexCounter);
         fibonacciIndexCounter++;
         return thisFibonacci;
      }
      catch (FibonacciIntegerOverflow overflowException)
      {
         intOverflowHappened = true;
         throw overflowException;
      }

   }

   /**
    * Returns the Fibonacci value at the requested index at speeds faster
    * than the efficient recursive methods. This speed is achieved by
    * iteratively calculating them, instead of recursion, thus using less
    * system resources, resulting in a more efficient and faster calculation.
    *
    * The next index value is calculated by adding the 2 previous values, and
    * the first values are known to be {1, 1}. As the first 2 values are
    * known, the next values can be calculated.
    *
    * This method was designed with the Fibonacci Algorithm in mind, which
    * requires adding the previous 2 numbers, to get the next Fibonacci number.
    * (The first 2 values of the sequence are {1, 1})
    *
    * @param fibonacciIndex
    * @return int value of the fibonacci at the requested index
    * @throws FibonacciIntegerOverflow Thrown when integer overflow occurs
    *                                  when calculating the next Fibonacci
    *                                  number.
    */
   private int getFibonacci(int fibonacciIndex) throws FibonacciIntegerOverflow
   {
      final int FIBONACCI_KNOWN_INDEX_VALUES = 1;
      if (fibonacciIndex <= FIBONACCI_KNOWN_INDEX_VALUES)
      {
         return FIRST_TWO_FIBONACCI_NUMBERS;
      }

      int olderFibonacci = FIRST_TWO_FIBONACCI_NUMBERS;
      int oldFibonacci = FIRST_TWO_FIBONACCI_NUMBERS;
      int newFibonacci = 0;

      final int INDEX_AFTER_FIRST_TWO_FIBONACCI_NUMBERS = 2;
      final int ZERO_FOR_NEGATIVE_CHECK = 0;

      //Fibonacci loop
      for (int fibLoop = INDEX_AFTER_FIRST_TWO_FIBONACCI_NUMBERS;
           fibLoop <= fibonacciIndex; fibLoop++)
      {
         newFibonacci = oldFibonacci + olderFibonacci;
         if (newFibonacci < ZERO_FOR_NEGATIVE_CHECK)
         {
            throw new FibonacciIntegerOverflow();
         }
         olderFibonacci = oldFibonacci;
         oldFibonacci = newFibonacci;
      }

      return newFibonacci;

   }
}
