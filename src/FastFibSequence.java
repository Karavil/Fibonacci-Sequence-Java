/**
 * Source code:  FastFibSequence.java
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
 * javac FastFibSequence.java
 *
 * Purpose:  This class implements the next() method from the Sequence class,
 * an fastRecursiveFibonacci(int fibonacciIndex) to provide the user with the
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
 * The fastRecursiveFibonacci() method simulates these steps, given on
 * https://en.wikipedia.org/wiki/Fibonacci_number
 *
 * 1) Accept index value
 *
 * 2) If index value is stored in the ArrayList fibonacci history, return
 * that value. If not, recursively call itself for the previous 2 values, and
 * add them to get the current value. These 2 values might repeat the process
 * if they are not within the known values.
 *
 * 3) After each recursion is complete, if the value was not calculated
 * before, store it in the fibonacci history for easier calculation/access in
 * the future.
 *
 * 4) Return final value after the stack (recursion) is complete.
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
 * private int fastRecursiveFibonacci(int fibonacciIndex) - returns the
 * fibonacci number at the requested index (recursively while storing
 * previous values for efficiency)
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
 * ArrayList<Integer> previousValues - stores the previous values of the
 * fibonacci sequence for efficiency, such that each value is not calculated
 * again.
 *
 * public int next() - Returns the next value, a fibonacci number, in the
 * sequence.
 * Arguments: No arguments
 * Output: int fibonacci number, an exception can be thrown
 */

//Used for storing previous fibonacci values

import java.util.ArrayList;

public class FastFibSequence implements Sequence
{
   private final int FIBONACCI_INDEX_START = 0;
   private final int FIRST_TWO_FIBONACCI_NUMBERS = 1;
   private int fibonacciIndexCounter = FIBONACCI_INDEX_START;
   private boolean intOverflowHappened = false;
   private ArrayList<Integer> previousValues = new ArrayList<Integer>();

   /**
    * Returns the next number in the fibonacci Sequence. This method utilizes
    * a fast recursive method as it stores the previous Fibonacci values at the
    * requested indexes.
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
         int thisFibonacci = fastRecursiveFibonacci(fibonacciIndexCounter);
         fibonacciIndexCounter++;
         previousValues.add(thisFibonacci);
         return thisFibonacci;
      }
      catch (FibonacciIntegerOverflow overflowException)
      {
         intOverflowHappened = true;
         throw overflowException;
      }
   }

   /**
    * Returns the Fibonacci value at the requested index at maximized speeds.
    * This speed is achieved by storing the previous Fibonacci numbers
    * calculated in an ArrayList, and accessing them instead of recalculating
    * them at each index.
    *
    * The next index value is calculated by adding the 2 previous values, and
    * the first values are known to be {1, 1}. As the first 2 values are
    * known, the next values can be calculated.
    *
    * This method was designed with the Fibonacci Algorithm in mind, which
    * requires adding the previous 2 numbers, to get the next Fibonacci number.
    * (The first 2 values of the sequence are {1, 1})
    *
    * @param fibonacciIndex The index of the requested Fibonacci number
    * @return int value of the Fibonacci at the requested index
    * @throws FibonacciIntegerOverflow Thrown when integer overflow occurs
    *                                  when calculating the next Fibonacci
    *                                  number.
    */
   private int fastRecursiveFibonacci(int fibonacciIndex)
           throws FibonacciIntegerOverflow
   {
      final int INDEX_IN_ARRAY_OFFSET = 1;
      final int FIBONACCI_KNOWN_INDEX_VALUES = 1;

      if (fibonacciIndex <= FIBONACCI_KNOWN_INDEX_VALUES)
      {
         return FIRST_TWO_FIBONACCI_NUMBERS;
      }
      //If fibonacci number is stored in the previous values, return it.
      else if (fibonacciIndex < (previousValues.size() - INDEX_IN_ARRAY_OFFSET))
      {
         return previousValues.get(fibonacciIndex);
      }
      else //If calculated the first time, do normal recursion.
      {
         final int ONE_INDEX_BACK = 1;
         final int TWO_INDEX_BACK = 2;

         int returnFibonacci =
                 fastRecursiveFibonacci(fibonacciIndex - ONE_INDEX_BACK) +
                 fastRecursiveFibonacci(fibonacciIndex - TWO_INDEX_BACK);

         //If the value is negative, int overflow happened
         final int ZERO_FOR_NEGATIVE_CHECK = 0;
         if (returnFibonacci < ZERO_FOR_NEGATIVE_CHECK)
         {
            throw new FibonacciIntegerOverflow();
         }

         return returnFibonacci;
      }
   }
}
