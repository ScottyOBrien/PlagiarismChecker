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
import java.util.Scanner;

public class PlagiarismChecker {

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

// get second file name.
//        System.out.print("Second Filename: ");
//        String file2 = input.nextLine();
        String file2 = ("Player2.txt");

        System.out.print("Please enter the threshold: ");
        double thresh = Double.parseDouble(input.nextLine());
        //System.out.println();

        //print file names.
        System.out.println("Filenames entered: " + file1 + ", " + file2);
        System.out.println("-------------------------------------------------");

        // initialize an object to store data in and call methods on.
        PlagiarismChecker p = new PlagiarismChecker();
        System.out.println("Plagiarism score: " + plagiarismScore(file1, file2, p));
        p.plagiarismChecker(thresh);


        // Test printing.
        //System.out.println("The LCS of " + file1 + " and " + file2 + " is: " + p.lcsLength(recordOne, recordTwo));

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
        pc.setFileNamePair(filename1 + " & " + filename2);
        //System.out.println("The LCS of " + filename1 + " and " + filename2 + " is: " + pc.lcsLength(recordOne, recordTwo));
        pc.setPlagiarismScore(200 * pc.lcsLength(recordOne,recordTwo) / ((double) recordOne.length() + (double) recordTwo.length()));
        //System.out.println("Pair: " + pc.fileNamePair);
        return pc.plagiarismScore;
    }

    /**
     * Checks if a PlagiarismChecker object has a plagiarismScore exceeding the threshold provided by user.
     * If it does, sets boolean value.
     * @param threshold
     */
    private void plagiarismChecker(double threshold) {

        if (this.plagiarismScore > threshold) {
            System.out.println("This pair exceeds the given threshold! You are plagiarising SCUM");
            this.setPlagiarising(true);
        }
        else {
            System.out.println("This score does not exceed the threshold! :)");
        }
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
        int X[][] = new int[lengthOfP1+1][lengthOfP2+1];

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
