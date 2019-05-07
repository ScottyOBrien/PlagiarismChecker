/**
 * @author Scott O'Brien
 * due: 05092019
 *
 * PlagiarismChecker is a programm that compares two given programs and compares the code to see how similar they are.
 * It compares subsequences and keeps track of the longest subsequence and gives the two programs a "plagerism score".
 *
 * KNOWN BUGS: IDE says there are errors but it is in the test programs (fixed by changing to .txt extension)
 */

import java.io.*;
import java.util.Scanner;

public class PlagiarismChecker {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to PlagiarismChecker - Please enter the names of the files you want to compare." +
                "\n(include the file extension, i.e. \"player1.java\")");

        // get the first file name.
        //System.out.print("First Filename: ");
        //String file1 = input.nextLine();
        String file1 = ("Player1.txt");
        // get second file name.
        //System.out.print("Second Filename: ");
        String file2 = ("Player2.txt");
        //String file2 = input.nextLine();

        //print file names.
        System.out.println("Filenames entered: " + file1 + ", " + file2);
        System.out.println("-------------------------------------------------");

        // These two variables will hold both of the programs.
        String recordOne = null;
        String recordTwo = null;

        try {
            // create random access files.
            RandomAccessFile fileOne = new RandomAccessFile(new File(file1), "r");
            RandomAccessFile fileTwo = new RandomAccessFile(new File(file2), "r");

            int a = fileOne.read();
            int b = fileTwo.read();
            while (a !=-1 && b !=-1) {
                // read the first file.
                recordOne = recordOne + (char) a;
                a = fileOne.read();
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

        PlagiarismChecker p = new PlagiarismChecker();
        // Test printing.
        System.out.println("The LCS of " + file1 + " and " + file2 + " is: " + p.lcsLength(file1, file2));

    }

    /**
     * Calculates the plagiarism score for two files.
     * @param filename1 the first file
     * @param filename2 the second file
     * @return returns a double amount, a "plagiarism" score.
     */
    private static double plagiarismScore(String filename1, String filename2) {


        return 0.0;
    }

    public void plagiarismChecker (String[] filenames, double threshold) {

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
