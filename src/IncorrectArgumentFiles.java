/**
 * Source code:  IncorrectArgumentFiles.java
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
 * javac IncorrectArgumentFiles.java
 *
 * Purpose:
 *
 * This is an exception that can be thrown in the case of errors with the
 * specified files in the command line.
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
 * public IncorrectArgumentFiles(String errorMessage) calls UserException
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
public class IncorrectArgumentFiles extends UserException
{
   /**
    * Calls UserException, super class, with the exception message
    * @param errorMessage exception message
    */
   public IncorrectArgumentFiles(String errorMessage)
   {
      super(errorMessage);
   }
}
