package com.Code;
import java.io.FileNotFoundException;
/* In order to run this class:
*   1) Open a text document in the Max Flow folder
*   2) In the first the lines, specify the number of nodes, the source and the sink, in the order
*   3) Then, in the upcoming lines, specify the edges:
*       First line specifies the starting node
*       Second line specifies the ending node
*       Third line specifies the capacity of the edge
*   4) Save the text file
*   5) Call the file using the Scanner class*/
//this main class for obtaining the values from text files
public class Main_FromTextFiles extends AdditionalFeatures {

    public static void main(String[] args) throws FileNotFoundException {

        Main_FromTextFiles main = new Main_FromTextFiles();
        main.getOutput();
    }


}
