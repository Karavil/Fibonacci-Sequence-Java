/**
 * Source Code: SequenceDemo.java
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
 * Language: Java
 * Compile/Run:
 * javac *.java
 * java SequenceDemo {input file name} {output file name}
 *
 * Example: java SequenceDemo input2 output3 <----- this will read the
 * arguments the file 'input2' in this directory, and print it to 'output3'.
 * It is not required for you to have your output file already created. If
 * you have already created it, you will be asked if you would like to
 * overwrite it.
 *
 * WARNING: Make sure the Sequence interface (Sequence.java) is in the same
 * directory as all other files (the source code).
 *
 * ------------------------------------------------------------------
 *
 * Description:
 *
 * This is a demo class for the 3 Fibonacci Sequence classe, which implements
 * the sequence interface, to provide the user with a sequence of fibonacci
 * values.
 *
 * The main point of this class is to compare the 3 fibonacci sequence
 * classes, which are coded/developed with 3 different programming ideas in
 * mind. Each one handles the generation of the fibonacci numbers
 * differently, with the first one doing it iteratively, the second it one
 * doing it recursively, and the third one doing it fast recursively.
 *
 * In this class, 3 different Fibonacci Sequence objects is created, then the
 * the specified amount of values starting from the start index by the user
 * is generated. These values are generated, and timed, and then outputted to
 * the user also while being written to the output file. The user can then
 * compare the timer values of all 3 sequences and decide which one is more
 * "efficient".
 *
 * User input for fibonacci generation is accepted through an input file, and
 * then the output is printed and also written to the specified output file.
 * The input and output files need to be specified in the command line, for
 * more information regarding user input please read the input section.
 * Example execution: java FibDemo inputfile outputfile
 *
 * If there are any errors (exceptions) with the users input, in the command
 * line and the file, the program will be gracefully terminated along with a
 * message that tells the user what they should fix. The exceptions utilized
 * in this class are EmptyFileException, FibonacciArgumentOutOfRange,
 * FibonacciIntegerOverflow, IncorrectArgumentFiles, IncorrectUserInput,
 * InputOutputFileOverwriteException, NonIntegerInput, and
 * OverwriteDeclinedException.
 *
 * NOTICE: This class has extra exceptions for EXTRA-CREDIT.
 *
 * Input:
 *
 * The name of the input file and the output file (no file extension is required)
 * needs to be specified on the command-line. This input will be validated,
 * and the exceptions will be handled.
 *
 * An example of execution of this program would be:
 * java FibDemo inputfile outputfile
 *
 * Please make sure that you don't have the output file created, but if you
 * do it is possible for you to overwrite it after the program is executed.
 *
 * In the input file, the user is required to input two integer values
 * separated by a newline characters. Example:
 * 1
 * 35
 *
 * The first integer in the input file is where the Fibonacci sequence will
 * start, beginning with that Fibonacci number. The second integer in the
 * input file will be the number of Fibonacci numbers to be displayed in the
 * square tables. The integer values in the input file are required to be
 * within [1-13] & [1-35], respectively, and inclusively. It is very
 * important to use a newline character (pressing enter after the first value
 * for most people) to separate the values.
 *
 * Output:
 *
 * As output, this class will print and write the same content to the
 * specified output file. The output file will contain one table of
 * recursively-computed Fibonacci integers; followed by a second table of
 * “expected” values; followed by a third table of ‘fast’ recursively-computed
 * Fibonacci integers. Please make sure to specify your output file in the
 * command-line while executing this class.
 *
 * Process:
 * 1. Validate user command-line arguments
 * 2. Validate and parse input file
 * 3. Generate 3 different Fibonacci sequence values while timing them
 * 4. Print the values and timers
 * 5. Write the printed output to the output file
 *
 * An algorithm to generate Fibonacci numbers is used, but it is a very
 * simple formula.
 * https://en.wikipedia.org/wiki/Fibonacci_number
 *
 * Known Bugs: None
 **/

//Used for IOException, FileNotFound, File objects

import java.io.*;
//Used to store sequence values
import java.util.ArrayList;
//Used to parse console input and read the input file
import java.util.Scanner;

public class FibDemo
{
   private final static int GRACEFUL_EXIT_CODE = 1;

   public static void main(String[] args)
   {
      final int INPUT_FILE_INDEX = 0;
      final int OUTPUT_FILE_INDEX = 1;
      final int REQUIRED_ARGUMENT_COUNT = 2;

      validateArgumentFiles(args, INPUT_FILE_INDEX, OUTPUT_FILE_INDEX,
                            REQUIRED_ARGUMENT_COUNT);

      final int MIN_START_INDEX = 1;
      final int MAX_START_INDEX = 13;
      final int MIN_RUN_COUNT = 1;
      final int MAX_RUN_COUNT = 35;

      int[] parsedInputArguments = parseInputFile(args[INPUT_FILE_INDEX],
                                                  MIN_START_INDEX,
                                                  MAX_START_INDEX,
                                                  MIN_RUN_COUNT, MAX_RUN_COUNT);

      final int START_VALUE_INDEX = 0;
      final int RUN_COUNT_INDEX = 1;

      final int startIndex = parsedInputArguments[START_VALUE_INDEX];
      final int runCount = parsedInputArguments[RUN_COUNT_INDEX];

      String combinedOutput = generateAndPrintSequenceOutputs(runCount,
                                                              startIndex);
      writeToOutputFile(combinedOutput, args[OUTPUT_FILE_INDEX]);

   }

   /**
    * This method generates a String of all three sequence run outputs, which
    * include their names, the values calculated, and the time it took to
    * generate those values. The time it took to generate these values are
    * represented in nanoseconds, and also seconds in the output. Each output
    * is also printed to the console.
    *
    * These outputs are then combined and returned as a String. This String
    * contains the outputs of all 3 sequences generated.
    *
    * @param runCount   Amount of times the sequences will be run
    * @param startIndex Start index of the sequence
    * @return A String which holds the outputs of all 3 sequences ran.
    */
   private static String generateAndPrintSequenceOutputs(int runCount,
                                                         int startIndex)
   {

      System.out.println("\nWARNING: This might take up to a minute depending" +
                         "on system specifications... hold on tight!");

      Sequence iterativeFibonacci = new LoopFibSequence();
      Sequence slowRecursiveFibonacci = new FibSequence();
      Sequence fastRecursiveFibonacci = new FastFibSequence();


      String currentSequenceName = "Normal Recursive:";
      String slowRecursiveOutput = generateFibonacciOutput(
              slowRecursiveFibonacci, runCount, startIndex,
              currentSequenceName);
      System.out.print(slowRecursiveOutput);

      currentSequenceName = "Iterative:";
      String iterativeOutput = generateFibonacciOutput(iterativeFibonacci,
                                                       runCount, startIndex,
                                                       currentSequenceName);
      System.out.print(iterativeOutput);

      currentSequenceName = "Fast Recursive:";
      String fastRecursiveOutput = generateFibonacciOutput(
              fastRecursiveFibonacci, runCount, startIndex,
              currentSequenceName);
      System.out.print(fastRecursiveOutput);

      String combinedFibonacciOutput =
              slowRecursiveOutput + "\n" + iterativeOutput + "\n" +
              fastRecursiveOutput;

      return combinedFibonacciOutput;
   }

   /**
    * This method is used to validate that the command line arguments, which
    * represent the input and output file, point to valid input and output
    * files. This validation is done through making sure there are only 2
    * command-line arguments allowed, and then it is made sure that an input
    * file exists, and then it is made sure that an output file doesn't exist.
    *
    * In the case that an output file exists, the user will be asked if they
    * would like to overwrite the output file they provided. If the overwrite
    * is not accepted, the program will terminate.
    *
    * If there are any errors in how the user provides the 2 files, such as
    * providing a valid input file or making sure the input and output file
    * don't point to the same file, the program will print out the error, and
    * what the user should do to fix it, and then gracefully terminate.
    *
    * @param programArguments      command-line arguments
    * @param inputFileIndex        index of the input file in the command-line
    * @param outputFileIndex       index of the output file in the command-line
    * @param requiredArgumentCount how many arguments are required in the
    *                              command-line
    */
   private static void validateArgumentFiles(String[] programArguments,
                                             int inputFileIndex,
                                             int outputFileIndex,
                                             int requiredArgumentCount)
   {
      try
      {
         //Validate the amount of arguments
         validateCommandLineArguments(programArguments, requiredArgumentCount);

         String inputFileName = programArguments[inputFileIndex];
         String outputFileName = programArguments[outputFileIndex];
         boolean inputFound = false;
         boolean outputFound = false;
         File input = null;
         File output = null;


         //If there is no input, throw exception
         inputFound = searchForFile(inputFileName);
         if (!inputFound)
         {
            throw new FileNotFoundException(
                    "ERROR: Your input file was not found. Please" +
                    " make sure your input file is in the same " +
                    "directory as the FibDemo class file.");
         }
         else
         {
            try
            {
               input = generateFileObject(inputFileName);
            }
            catch (IOException exception)
            {
               System.out.println("ERROR: IOException occurred while creating" +
                                  " input file object.");
               exception.printStackTrace();
               System.exit(GRACEFUL_EXIT_CODE);
            }
         }

         //If there is already an output, ask for overwrite permission
         outputFound = searchForFile(outputFileName);
         if (outputFound)
         {
            System.out.println("\nAn output file already exists, would you " +
                               "like to override this file? [Enter Y for " +
                               "yes] [Enter N for no]\nEntering 'N' will " +
                               "terminate the program.");

            boolean overrideOutputFile = getUserOverrideDecision();
            if (!overrideOutputFile)
            {
               throw new OverwriteDeclinedException(
                       "Output override has been declined. Exiting program.");
            }
            else
            {
               System.out.println("Override allowed. Your file contents " +
                                  "will be overwritten.");
               output = generateFileObject(programArguments[outputFileIndex]);
            }
         }
         else
         {
            try
            {
               createFileInDirectory(outputFileName);
               output = generateFileObject(outputFileName);
            }
            catch (FileNotFoundException exception)
            {
               throw new FileNotFoundException("ERROR: Output file was not " +
                                               "found after creating it. " +
                                               "This should not happen... " +
                                               "Exiting program.");
            }
            catch (IOException exception)
            {
               System.out.println("ERROR: An IOException occurred while " +
                                  "creating output file. Exiting program.");
               exception.printStackTrace();
               System.exit(GRACEFUL_EXIT_CODE);
            }
         }

         //Compare paths to make sure they are not the same fil
         String inputPath = input.getAbsolutePath();
         String outputPath = output.getAbsolutePath();
         if (inputPath.equals(outputPath))
         {
            throw new InputOutputFileOverwriteException(
                    "\nERROR: Your input " + "and output files " +
                    "point to the same " + "file. Please make " +
                    "sure your input and " + "output files are not" +
                    " the same file.");
         }
      }
      catch (IncorrectArgumentFiles exception)
      {
         System.out.println(exception.getMessage());
         System.exit(GRACEFUL_EXIT_CODE);
      }
      catch (InputOutputFileOverwriteException exception)
      {
         System.out.println(exception.getMessage());
         System.exit(GRACEFUL_EXIT_CODE);
      }
      catch (OverwriteDeclinedException exception)
      {
         System.out.println(exception.getMessage());
         System.exit(GRACEFUL_EXIT_CODE);
      }
      catch (FileNotFoundException exception)
      {
         System.out.println(exception.getMessage());
         System.exit(GRACEFUL_EXIT_CODE);
      }
   }

   /**
    * This method validates the amount of arguments provided in the
    * command-line by the user. This method has 2 parameters required, with
    * one being an array of strings, which hold the arguments provided by the
    * users, and the required amount of arguments. If the arguments are not
    * equal to the required amount, an exception of IncorrectArgumentFiles is
    * thrown, which needs to be caught.
    *
    * @param programArguments      string array of command-line arguments
    * @param requiredArgumentCount required amount of arguments
    */
   private static void validateCommandLineArguments(String[] programArguments,
                                                    int requiredArgumentCount)
           throws IncorrectArgumentFiles
   {
      if (programArguments.length != requiredArgumentCount)
      {
         final int NO_ARGUMENTS = 0;
         if (programArguments.length == NO_ARGUMENTS)
         {
            throw new IncorrectArgumentFiles(
                    "\nERROR: No file arguments found." +
                    " Include your input and output file while executing this" +
                    " program. Example: java FibDemo infile outfile");
         }

         throw new IncorrectArgumentFiles(
                 "\nERROR: Please make sure you " + "only have 2 " +
                 "command-line arguments. Example: java FibDemo infile " +
                 "outfile");
      }
   }

   /**
    * This method is used to parse the input file provided by the user in the
    * command-line. Normally, the input file should only have 1 line
    * containing 'x & y', where x represents the starting index and y
    * represents the amount of values to be printed, including the starting
    * index values. These values need to be separated by a new line, such
    * that they are both on different lines.
    *
    * This method makes sure that input file only has 1 line, and then makes
    * sure that this one line has 3 arguments. After this is validated, this
    * method makes sure the user inputs are appropriate, such that they are
    * two integer numbers with a newline character between them.
    *
    * After validating that the formatting is correct, this program will
    * validate that the start index and the run count is within the allowed
    * range, which is 1-13 for the start index and 1-35 for the run count.
    *
    * After it is made sure that they are within the specified range, a
    * String array containing the start index and run count is returned to
    * the method caller.
    *
    * If there are any exceptions (user errors) in the input file the problem
    * will be printed for the user to fix, and the program will exit.
    *
    * @param fileName name of the input file
    * @param minStart min start value allowed for index
    * @param maxStart max start value allowed for index
    * @param minRuns  min amount of runs allowed
    * @param maxRuns  max amount of runs allowed
    * @return
    */
   private static int[] parseInputFile(String fileName, int minStart,
                                       int maxStart, int minRuns, int maxRuns)
   {

      final int GRACEFUL_EXIT_CODE = 1;
      Scanner inputReader = null;

      try
      {
         File inputFile = null;
         try
         {
            inputFile = generateFileObject(fileName);
         }
         catch (IOException exception)
         {
            System.out.println("ERROR: An IOException occurred while reading " +
                               "input file.");
            exception.printStackTrace();
            System.exit(GRACEFUL_EXIT_CODE);
         }

         inputReader = new Scanner(inputFile);

         ArrayList<String> inputArguments = new ArrayList<String>();
         final int LAST_INPUT_OFFSET = 1;
         final int MAX_LINE_ARGUMENTS = 1;
         while (inputReader.hasNextLine())
         {
            String thisLine = inputReader.nextLine();
            inputArguments.add(thisLine);

            int lastInputIndex = inputArguments.size() - LAST_INPUT_OFFSET;
            String[] lineArguments = thisLine.split(" ");
            if (lineArguments.length != MAX_LINE_ARGUMENTS)
            {
               throw new IncorrectUserInput("ERROR: Please make sure that " +
                                            "your input file contains only 1 " +
                                            "argument per line, and only " +
                                            "contains 2 lines. The first line" +
                                            " is required to have the start " +
                                            "index, and then the second line " +
                                            "is required to have the run " +
                                            "count, which is the amount of " +
                                            "values that will be printed.");
            }
         }

         final int REQUIRED_ARGUMENT_LINES = 2;

         if (inputArguments.size() != REQUIRED_ARGUMENT_LINES)
         {
            if (inputArguments.isEmpty())
            {
               throw new EmptyFileException(
                       "\nERROR: Your input file is empty," +
                       " please make sure to include fibonacci program " +
                       "arguments  in your input file.");
            }

            throw new IncorrectUserInput(
                    "\nERROR: Please make sure to only use 2 lines to " +
                    "submit program arguments. Example: 2 & " +
                    "13 <--- this will print 13 fibonacci " +
                    "values after the the second fibonacci number, inclusively." +
                    "The first argument can range from [1-13] and the second " +
                    "argument can range from [1-35], both exclusively. " +
                    "(Blank lines are not allowed.)");
         }

         final int REQUIRED_ARGUMENTS = 2;
         if (inputArguments.size() != REQUIRED_ARGUMENTS)
         {
            throw new IncorrectUserInput(
                    "\nERROR: Please make sure to only have 2 arguments" +
                    " separated by a newline character in your input file. " +
                    "Example: 2 & 13 <--- this will print 13 fibonacci " +
                    "values after the the second fibonacci number, inclusively." +
                    "The first argument can range from [1-13] and the second " +
                    "argument can range from [1-35], both exclusively. " +
                    "(Blank lines are not allowed.)");
         }

         final int START_VALUE_INDEX = 0;
         final int RUN_COUNT_INDEX = 1;
         String[] arguments = new String[REQUIRED_ARGUMENTS];
         arguments[START_VALUE_INDEX] = inputArguments.get(START_VALUE_INDEX);
         arguments[RUN_COUNT_INDEX] = inputArguments.get(RUN_COUNT_INDEX);

         int[] parsedArguments = parseFibonacciArguments(arguments, minStart,
                                                         maxStart, minRuns,
                                                         maxRuns);
         return parsedArguments;
      }
      catch (FileNotFoundException exception)
      {
         System.out.println("Unable to find file '" + fileName + "'. Please " +
                            "make sure the file you are referencing is in the" +
                            " same directory as the FibDemo class.");
         System.exit(GRACEFUL_EXIT_CODE);
      }
      catch (IncorrectUserInput exception)
      {
         System.out.println(exception.getMessage());
         System.exit(GRACEFUL_EXIT_CODE);
      }
      catch (FibonacciArgumentOutOfRange exception)
      {
         System.out.println(exception.getMessage());
         System.exit(GRACEFUL_EXIT_CODE);
      }
      catch (EmptyFileException exception)
      {
         System.out.println(exception.getMessage());
         System.exit(GRACEFUL_EXIT_CODE);
      }
      catch (NonIntegerInput exception)
      {
         System.out.println(exception.getMessage());
         System.exit(GRACEFUL_EXIT_CODE);
      }
      finally
      {
         if (inputReader != null)
         {
            inputReader.close();
         }
      }
      return null;
   }

   /**
    * This method is used to validate the integer input in the input file
    * provided by the user. The user is required to submit the ranges allowed
    * for the index and run count, which then the input values are validated
    * to make sure they are within that range.
    *
    * In the case that the fibonacci input file arguments are not properly
    * formatted, exceptions will be thrown and it is required to catch them.
    *
    * These exceptions at the time include FibonacciArgumentOutOfRange,
    * IncorrectUserInput, and NonIntegerInput. All of these exceptions have
    * messages appointed to them which can be printed to help user fix the
    * problem.
    *
    * @param arguments input file arguments
    * @param minStart  min start index allowed
    * @param maxStart  max start index allowed
    * @param minRuns   min run count allowed
    * @param maxRuns   max run count allowed
    * @return an array containing the parsed start index and run count
    * @throws FibonacciArgumentOutOfRange thrown when one of the arguments
    *                                     are out of the specified range
    * @throws NonIntegerInput             thrown when one of the values inputted is not
    *                                     an integer
    */
   private static int[] parseFibonacciArguments(String[] arguments,
                                                int minStart, int maxStart,
                                                int minRuns, int maxRuns)
           throws FibonacciArgumentOutOfRange, IncorrectUserInput,
                  NonIntegerInput
   {

      final int START_VALUE_INDEX = 0;
      final int RUN_COUNT_INDEX = 1;

      try
      {
         int startValue = Integer.parseInt(arguments[START_VALUE_INDEX]);
         int valueCount = Integer.parseInt(arguments[RUN_COUNT_INDEX]);
         if (startValue < minStart || startValue > maxStart)
         {
            throw new FibonacciArgumentOutOfRange(
                    "\nERROR: Your first argument, which declares the " +
                    "start index is out of range. Please input a value " +
                    "between 1 and 13, inclusive.\n");
         }

         if (valueCount < minRuns || valueCount > maxRuns)
         {
            throw new FibonacciArgumentOutOfRange(
                    "\nERROR: Your second argument, which declares the " +
                    "amount of values needed after the start value, " +
                    "inclusively, is out of range. Please input a value " +
                    "between 1 and 35, inclusive.\n");
         }

         int[] fibonacciArguments = {startValue, valueCount};
         return fibonacciArguments;
      }
      catch (NumberFormatException exception)
      {
         throw new NonIntegerInput(
                 "\nERROR: Please make sure that your 2 arguments" +
                 " in your input file are integers. Example: 2 & " +
                 "13 <--- this will print 13 fibonacci " +
                 "values after the the second fibonacci number, inclusively." +
                 "The first argument can range from [1-13] and the second " +
                 "argument can range from [1-35], both exclusively. " +
                 "(Blank lines are not allowed.)\n");
      }
   }


   /**
    * This method is used to get the output file overwrite decision by the
    * user. The user will be asked to type 'Y' or 'N' if they want to
    * overwrite their current output file.
    *
    * If they type 'Y' a true value will be returned, if they type 'N' a
    * false value will be returned.
    *
    * If the user does not type 'Y' or 'N', the method will keep looping
    * until a 'Y' or 'N' value is entered.
    *
    * @return true if Y is entered, false if N is entered
    */
   private static boolean getUserOverrideDecision()
   {
      Scanner userInput = new Scanner(System.in);
      final int ARGUMENT_LENGTH = 1;
      String yesDecision = "Y";
      String noDecision = "N";

      String userDecision = "";
      while (userInput.hasNextLine())
      {
         userDecision = userInput.nextLine();
         if (userDecision.length() == ARGUMENT_LENGTH)
         {
            if (userDecision.equals(yesDecision))
            {
               return true;
            }
            else if (userDecision.equals(noDecision))
            {
               return false;
            }
         }
         System.out.println(
                 "Please make sure input is either Y or N, try again: ");
      }

      return false;
   }

   /**
    * This method is a shortcut for generating a File object of a file in the
    * current directory. If a file is not found at the specified location, a
    * FileNotFoundException is thrown and it is required to catch it. If
    * there is a file in the specified location, a File object for
    * that location is returned.
    *
    * @param fileName name of the file in the current directory
    * @return File object pointing to the inputted parameter
    * @throws FileNotFoundException thrown when there is no file at the
    *                               specified location
    */
   private static File generateFileObject(String fileName)
           throws FileNotFoundException
   {
      File returnFile = new File(fileName);

      if (!(returnFile.exists()))
      {
         throw new FileNotFoundException();
      }

      return returnFile;
   }

   /**
    * This method is used to create a file (the extension doesn't matter) in
    * the current directory. If the file already exists, no file is created.
    * In the case that an IOException occurs while creating the file, the
    * exception is thrown and is required to be caught by the user.
    *
    * @param fileName Name of the file being created
    * @throws IOException thrown when an exception occurs when creating the file
    */
   private static void createFileInDirectory(String fileName) throws IOException
   {
      File returnFile = new File(fileName);
      if (!(returnFile.exists()))
      {
         try
         {
            returnFile.createNewFile();
         }
         catch (IOException exception)
         {
            throw exception;
         }
      }
   }

   /**
    * This method is used to search for a file in the current directory. A
    * true value is returned if a value with the specified name can be found,
    * if it is not found, a false value is returned.
    *
    * @param fileName name of the file being searched
    * @return true if found, false otherwise
    */
   private static boolean searchForFile(String fileName)
   {
      File returnFile = new File(fileName);

      if (!(returnFile.exists()))
      {
         return false;
      }

      return true;
   }

   /**
    * This is a method intended for writing a String of data to an output
    * file in the current directory. This method requires the output file to
    * be already created. If the output file is not found, or an IOException
    * occurs an error will be printed an the program will be terminated.
    *
    * @param data       String of data that will be written to output file
    * @param outputFile name of the output file in current directory
    */
   private static void writeToOutputFile(String data, String outputFile)
   {
      FileWriter outputWriter = null;
      try
      {
         File output = generateFileObject(outputFile);
         outputWriter = new FileWriter(output);
         outputWriter.write(data);
      }
      catch (FileNotFoundException exception)
      {
         System.out.println("\nERROR: Output file not found. Cannot write to " +
                            "output file");
         exception.printStackTrace();
         System.exit(GRACEFUL_EXIT_CODE);
      }
      catch (IOException exception)
      {
         System.out.println(
                 "\nERROR: Encountered an IOException while writing to " +
                 "output file. Exiting program");
         exception.printStackTrace();
         System.exit(GRACEFUL_EXIT_CODE);

      }
      finally
      {
         try
         {
            if (outputWriter != null)
            {
               outputWriter.close();
            }
         }
         catch (IOException exception)
         {
            System.out.println(
                    "\nERROR: Exception occurred while closing " + "file" +
                    " " + "writer.");
            exception.printStackTrace();
            System.exit(GRACEFUL_EXIT_CODE);
         }
      }

   }

   /**
    * This method is used to generate a String containing the name of the
    * sequence, the fibonacci values generated, and the time it took to
    * generate the Fibonacci numbers of the current sequence.
    *
    * This method stores the sequence run values in an ArrayList, and in the
    * case that integer overflow of the sequence occurs, stores integer
    * overflow values as 10-digit asterisks in an ArrayList of Strings.
    *
    * These values are then handed off to the square generator method, which
    * then properly formats them in a square formation, and returns them to
    * this method.
    *
    * While this method is generating the fibonacci values, a timer is
    * running which records how many nanoseconds/seconds it takes for the
    * fibonacci sequence generation to complete.
    *
    * @param sequenceInput the Sequence object that will be used to generate
    *                      the values
    * @param runCount      number of fibonacci values to be generated
    * @param startIndex    index of the first fibonacci number
    * @param sequenceName  name of the sequence
    * @return a String containing name of the sequence, the fibonacci values
    * generated, and the time it took to generate the values
    */
   private static String generateFibonacciOutput(Sequence sequenceInput,
                                                 int runCount, int startIndex,
                                                 String sequenceName)
   {

      ArrayList<Integer> sequenceHistory = new ArrayList<Integer>();
      ArrayList<String> overflowHistory = new ArrayList<String>();
      final int SEQUENCE_RUNNER_START = 1;
      final int SEQUENCE_COUNTER_START = 0;
      int integerOverflowCounter = 0;


      int sequenceRunner = SEQUENCE_RUNNER_START;
      int sequenceCounter = SEQUENCE_COUNTER_START;
      long startTime = System.nanoTime(); //Start timer
      while (sequenceCounter < runCount)
      {
         try
         {
            int sequenceValue = sequenceInput.next();
            if (sequenceRunner >= startIndex)
            {
               sequenceHistory.add(sequenceValue);
               sequenceCounter++; //Start increasing counter after index reached
            }
            //Keep increasing index until start index is reached
            sequenceRunner++;
         }
         catch (FibonacciIntegerOverflow exception) //If overflow occurs
         {
            if (sequenceRunner >= startIndex)
            {
               String overflowString = exception.getOverflowAsAsterisk();
               overflowHistory.add(overflowString);
               integerOverflowCounter++;
               sequenceCounter++;
            }
            sequenceRunner++;
         }
      }

      long endTime = System.nanoTime(); //Stop timer
      long runtimeNanoseconds = (endTime - startTime);

      String output = "";
      output += "\n" + sequenceName + "\n" +
                generateFibonacciSquare(sequenceHistory, overflowHistory,
                                        runtimeNanoseconds);

      final int OVERFLOW_ZERO_CHECK = 0;
      if (integerOverflowCounter > OVERFLOW_ZERO_CHECK)
      {
         output += "ALERT: " + integerOverflowCounter + " integer " +
                   "overflow value(s) were generated in this fibonacci " +
                   "sequence. These values have their digits replaced with " +
                   "asterisks '*' in the output square.\n";
      }

      return output;
   }

   /**
    * This method is used to generate a "square" output if Fibonacci values.
    * To generate this "square" an ArrayList of the sequence values, an
    * ArrayList of the overflow values in asterisk form and the time it took to
    * generate these values is needed.
    *
    * The square is dynamically spaced depending on the length of the
    * fibonacci numbers such that it looks more like a "square".
    *
    * Example: If there were 3 values inputted the square would look like;
    *
    * 1  1
    * 2
    *
    * @param sequenceArray        ArrayList holding fibonacci sequence values
    * @param overflowHistory      ArrayList holding the overflow asterisk values
    * @param runLengthNanoseconds the time (in nanoseconds) it took to
    *                             generate these values.
    * @return a String formatted in "square" form which has all the values
    * provided, and the run length in nanoseconds and seconds.
    */
   private static String generateFibonacciSquare(
           ArrayList<Integer> sequenceArray, ArrayList<String> overflowHistory,
           long runLengthNanoseconds)
   {
      int rows = 0;
      int columns = 0;
      int valueCount = sequenceArray.size() + overflowHistory.size();


      rows = (int) Math.ceil(Math.sqrt(sequenceArray.size()));
      columns = (int) Math.ceil(valueCount / (double) rows);

      final int EXTRA_SPACES = 3;
      final int MAX_FIBONACCI_LENGTH = getMaxFibonacciLength(sequenceArray,
                                                             overflowHistory);
      int dynamicSquareSpacing = MAX_FIBONACCI_LENGTH + EXTRA_SPACES;

      final int NEW_ROW_CHECK = 0;
      final int FIRST_VALUE_CHECK = 0;

      String formattedSquare = "";
      for (int valueCounter = 0; valueCounter < valueCount; valueCounter++)
      {
         if (valueCounter >= sequenceArray.size())
         {
            int updatedCounter = valueCounter - sequenceArray.size();
            //If current row is filled up, move onto second row
            if (valueCounter % columns == NEW_ROW_CHECK &&
                valueCounter != FIRST_VALUE_CHECK)
            {
               formattedSquare += String
                       .format("\n%" + dynamicSquareSpacing + "s",
                               overflowHistory.get(updatedCounter));
            }
            else //If current row is not full, keep printing on same row
            {
               formattedSquare += String
                       .format("%" + dynamicSquareSpacing + "s",
                               overflowHistory.get(updatedCounter));
            }
         }
         else
         {
            //If current row is filled up, move onto second row
            if (valueCounter % columns == NEW_ROW_CHECK &&
                valueCounter != FIRST_VALUE_CHECK)
            {
               formattedSquare += String
                       .format("\n%" + dynamicSquareSpacing + "d",
                               sequenceArray.get(valueCounter));
            }
            else //If current row is not full, keep printing on same row
            {
               formattedSquare += String
                       .format("%" + dynamicSquareSpacing + "d",
                               sequenceArray.get(valueCounter));
            }
         }
      }

      final int NANO_SECOND_TO_SECOND_CONVERSION = 1000000000;
      double runLengthSeconds =
              runLengthNanoseconds / (double) NANO_SECOND_TO_SECOND_CONVERSION;

      formattedSquare += String
              .format("\n\nTime to compute: %,15d " + "nanoseconds," + " " +
                      "%6" + ".4f" + " " + "seconds" + ".\n",
                      runLengthNanoseconds, runLengthSeconds);

      return formattedSquare;

   }

   /**
    * This method is used to dynamically space the fibonacci square generator
    * This method will find the longest/max value in the sequenceArray, and if
    * the overflowHistory array exists, in the overflowHistory array and
    * return the length as an int value.
    *
    * This method is written in mind with overflow values being able to reach
    * asterisks digits higher than the default 10 in future versions of the
    * program.
    *
    * @param sequenceArray   Array holding all the fibonacci sequence values
    * @param overflowHistory Array holding the overflow history (asterisk)
    *                        values
    * @return int length of the max value
    */
   private static int getMaxFibonacciLength(ArrayList<Integer> sequenceArray,
                                            ArrayList<String> overflowHistory)
   {
      final int NO_VALUES = 0;
      int maxValueLength = 0;
      if (overflowHistory.size() > NO_VALUES)
      {
         for (String thisOverflow : overflowHistory)
         {
            if (thisOverflow.length() > maxValueLength)
            {
               maxValueLength = thisOverflow.length();
            }
         }
      }
      else if (sequenceArray.size() > NO_VALUES)
      {
         for (int sequenceValue : sequenceArray)
         {
            if (Integer.toString(sequenceValue).length() > maxValueLength)
            {
               maxValueLength = Integer.toString(sequenceValue).length();
            }
         }
      }
      return maxValueLength;
   }
}



