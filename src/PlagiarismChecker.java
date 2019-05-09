/**
 * @author Scott O'Brien
 * due: 05092019
 *
 * PlagiarismChecker is a programm that compares two given programs and compares the code to see how similar they are.
 * It compares subsequences and keeps track of the longest subsequence and gives the two programs a "plagerism score".
 *
 * KNOWN BUGS: None.
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class PlagiarismChecker {

    public static ArrayList<PlagiarismChecker> programs = new ArrayList<PlagiarismChecker>();

    // create our private class variables that will be used when working with PlagiarismChecker objects.
    private String fileNamePair;
    private double plagiarismScore = 0.0;
    private boolean isPlagiarising = false;

    public void setPlagiarising(boolean plagiarising) {
        isPlagiarising = plagiarising;
    }

    // setter for the file names
    private void setFileNamePair(String fileNamePair) {
        this.fileNamePair = fileNamePair;
    }

    // setter for the plagiarism score
    private void setPlagiarismScore(double plagiarismScore) {
        this.plagiarismScore = plagiarismScore;
    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to PlagiarismChecker - Please enter the names of the files you want to compare." +
                "\n(include the file extension, i.e. \"player1.java\")");

        // get the first file name.
//        System.out.print("First Filename: ");
//        String file1 = input.nextLine();
        String file1 = ("Player1.txt");
        String file2 = ("Player2.txt");
        String file3 = ("Player1.txt");
        String file4 = ("Player2.txt");

        System.out.print("Please enter the threshold: ");
        double thresh = Double.parseDouble(input.nextLine());

        String [] fileNameList = new String[] {file1, file2, file3, file4};
        System.out.println(fileNameList.length);

        for (int i = 0; i <=fileNameList.length-1 ; i++) {
            if (i == fileNameList.length-1) {
                PlagiarismChecker p = new PlagiarismChecker();
                plagiarismScore(fileNameList[i], fileNameList[0], p);
            }
            else {
                PlagiarismChecker p = new PlagiarismChecker();
                plagiarismScore(fileNameList[i], fileNameList[i + 1], p);
            }
        }

        plagiarismChecker(thresh);

        // get second file name.
//        System.out.print("Second Filename: ");
//        String file2 = input.nextLine();

        // initialize an object to store data in and call methods on.
        //PlagiarismChecker p = new PlagiarismChecker();
       // System.out.println("Plagiarism score: " + plagiarismScore(file1, file2, p));
        // call plagiarism score, and call plagiarism checker on it.
        //plagiarismScore(file1, file2, p);
       // p.plagiarismChecker(thresh);

    }

    /**
     * Calculates the plagiarism score for two files.
     * @param filename1 the first file
     * @param filename2 the second file
     * @return returns a double amount, a "plagiarism" score.
     */
    private static double plagiarismScore(String filename1, String filename2, PlagiarismChecker pc) {

        // These two variables will hold both of the programs.
        String recordOne = "";
        String recordTwo = "";

        // read in the files data. put into char array's.
        try {
            // create random access files.
            RandomAccessFile fileOne = new RandomAccessFile(new File(filename1), "r");
            RandomAccessFile fileTwo = new RandomAccessFile(new File(filename2), "r");

            int a = fileOne.read();
            int b = fileTwo.read();
            while (a !=-1) {
                // read the first file.
                recordOne = recordOne + (char) a;
                a = fileOne.read();
            }
            while (b!=-1) {
                // read the second file.
                recordTwo = recordTwo + (char) b;
                b = fileTwo.read();
            }


            //close the files
            fileOne.close();
            fileTwo.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        // set this PlagiarismChecker's filename pair
        pc.setFileNamePair(filename1 + "            " + filename2);

        //set the plagiarism score
        pc.setPlagiarismScore(200 * pc.lcsLength(recordOne,recordTwo) / ((double) recordOne.length() + (double) recordTwo.length()));
        // add new plagiarism checker object to the arrayList
        programs.add(pc);
        return pc.plagiarismScore;
    }

    /**
     * Checks if a PlagiarismChecker object has a plagiarismScore exceeding the threshold provided by user.
     * If it does, sets boolean value.
     * @param threshold - threshold value passed, defined by user input
     */
    private static void plagiarismChecker(double threshold) {

        // create and print the header
        String header = "File 1                 File 2              Score                   THRESHOLD: " + threshold;
        System.out.println(header);

        for (int i = 0; i < programs.size() ; i++) {
            // if the pair is suspicious, that is, their score exceeds the threshold.
            if (programs.get(i).plagiarismScore > threshold) {
                programs.get(i).setPlagiarising(true); // set the ispPlagiarising boolean to true.
                //print the suspicious pair
                System.out.println(programs.get(i).fileNamePair + "         " + programs.get(i).plagiarismScore);
            }

        }

//        if (this.plagiarismScore > threshold) {
//            System.out.println("This pair exceeds the given threshold! You are plagiarising SCUM");
//            this.setPlagiarising(true);
//        }
//        else {
//            System.out.println("This score does not exceed the threshold! :)");
//        }
    }

    /**
     * Calculate the Longest common subsequence of two strings
     *
     * @param prog1 the first string to compare.
     * @param prog2 the second program to compare.
     * @return integer value, the LCS
     **/
    public int lcsLength(String prog1, String prog2) {

        // initialize two char arrays, one for each program.
        char []programOne = prog1.toCharArray();
        char []programTwo = prog2.toCharArray();

        // initialize two integer variables to hold the length of each program.
        int lengthOfP1 = programOne.length;
        int lengthOfP2 = programTwo.length;

        // create a new 2D array the size of both programs to be compared.
        int[][] X = new int[lengthOfP1 + 1][lengthOfP2 + 1];

        // find the lcs of the two programs.
        for (int i=0; i<=lengthOfP1; i++) {
            for (int j=0; j<=lengthOfP2; j++) {
                if (i == 0 || j == 0)
                    X[i][j] = 0;
                else if (programOne[i-1] == programTwo[j-1])
                    X[i][j] = X[i-1][j-1] + 1;
                else
                    X[i][j] = maxOfInt(X[i-1][j], X[i][j-1]);
            }
        }

        // this returns the LCS
        return X[lengthOfP1][lengthOfP2];
    }

    /**
     * Compares two integers and finds the max
     * @param a first int
     * @param b second int
     * @return returns the max of two characters.
     */
    int maxOfInt(int a, int b)
    {
        return (a > b)? a : b;
    }

}
