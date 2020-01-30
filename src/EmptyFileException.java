/**
 * Source code:  EmptyFileException.java
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
 * javac EmptyFileException.java
 *
 * Purpose:
 *
 * This is an exception that can be thrown in the case of trying to read an
 * empty file.
 *
 * Extends to: IOException
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
 * EmptyFileException(String errorMessage) - Default constructor, calls
 * IOException super with the error message.
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

//used to extend IOException
import java.io.IOException;

public class EmptyFileException extends IOException
{
   /**
    * Calls IOException, super class, with the exception message
    * @param errorMessage exception message
    */
   public EmptyFileException(String errorMessage)
   {
      super(errorMessage);
   }
}
