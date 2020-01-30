import java.io.IOException;

/**
 * Source code:  OverwriteDeclinedException.java
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
 * javac OverwriteDeclinedException.java
 *
 * Purpose:
 *
 * This is an exception that can be thrown in the case of the overwriting
 * output file option by the user is declined.
 *
 * Extends to: UserException
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
 * public OverwriteDeclinedException(String errorMessage) calls IOException
 * super class with the error message.
 *
 * +-----------------------------------------------------------------------
 *
 * Class Methods:
 *
 * No methods.
 *
 * +-----------------------------------------------------------------------
 *
 * Instance Methods:
 *
 * No instance methods.
 *
 */
public class OverwriteDeclinedException extends IOException
{
   /**
    * Calls IOException, super class, with the exception message
    * @param message exception message
    */
   public OverwriteDeclinedException(String message)
   {
      super(message);
   }
}
