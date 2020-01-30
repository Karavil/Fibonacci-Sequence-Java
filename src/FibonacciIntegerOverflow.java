/**
 * Source code:  FibonacciIntegerOverflow.java
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
 * javac FibonacciIntegerOverflow.java
 *
 * Purpose:
 *
 * This is an exception that can be thrown in the case of integer overflow in
 * a Fibonacci sequence.
 *
 * Extends to: Arithmetic Exception
 *
 * +-----------------------------------------------------------------------
 *
 * Constants:
 *
 * This class has no constants
 *
 * +-----------------------------------------------------------------------
 *
 * Constructors:
 *
 * FibonacciIntegerOverflow(), calls UserException super class with the error
 * message saying that fibonacci integer overflow has happened.
 *
 * +-----------------------------------------------------------------------
 *
 * Class Methods:
 *
 * String getOverflowAsAsterisk() - returns a String of 10 asterisks which
 * represents the overflowed value.
 *
 * +-----------------------------------------------------------------------
 *
 * Instance Methods:
 *
 * No instance methods.
 *
 */

public class FibonacciIntegerOverflow extends ArithmeticException
{
   /**
    * Calls UserException, super class, with a default exception message
    */
   public FibonacciIntegerOverflow()
   {
      super("ERROR: Fibonacci calculation has overflowed its int value.");
   }

   /**
    * Returns a string containing 10 asterisks which represent the overflown
    * int value.
    * @return  a String of 10 asterisks
    */
   public String getOverflowAsAsterisk()
   {
      return "**********";
   }
}
