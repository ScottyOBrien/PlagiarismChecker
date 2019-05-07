/**
 * @author Scott O'Brien
 * due: 05092019
 *
 * PlagiarismChecker is a programm that compares two given programs and compares the code to see how similar they are.
 * It compares subsequences and keeps track of the longest subsequence and gives the two programs a "plagerism score".
 *
 * KNOWN BUGS: IDE says there are errors but it is in the test programs.
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

        System.out.println("Read Line Test: ");

        plagiarismScore(file1, file2);
    }

    public static double plagiarismScore(String filename1, String filename2) {
        String recordOne = null;
        String recordTwo = null;

        try {
            // create random access files.
            RandomAccessFile fileOne = new RandomAccessFile(filename1, "rw");
            RandomAccessFile fileTwo = new RandomAccessFile(filename2, "rw");

            // get byte position in file.
            fileOne.seek(5);
            fileTwo.seek(5);

            // store what is at that position in these variables
            recordOne = fileOne.readLine();
            recordTwo = fileTwo.readLine();

            //close the files
            fileOne.close();
            fileTwo.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        //print what is at that position
        System.out.println(recordOne);
        System.out.println(recordTwo);

        return 0.0;
    }

    public void plagiarismChecker (String[] filenames, double threshold) {

    }

}
