/**
 * Source code:  UserException.java
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
 * javac UserException.java
 *
 * Purpose:
 *
 * This is an exception that can be thrown in the case of any user error,
 * which can be then specified with a more customized error message.
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
 * public UserException(String errorMessage) calls UserException
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
public class UserException extends IllegalArgumentException
{
   /**
    * Calls IllegalArgumentException, super class, with the exception message
    * @param errorMessage exception message
    */
   public UserException(String errorMessage)
   {
      super(errorMessage);
   }
}

