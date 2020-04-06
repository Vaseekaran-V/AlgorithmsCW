package com.Code;

/* This part is for the CW specified question, and also this displays the functionalities such as delete and update edges
*  This class also has the feature to obtain the input from the user  */

import java.io.FileNotFoundException;

public class Main  {

    public static void main(String[] args) throws FileNotFoundException {
        ImplementMethods main = new ImplementMethods();
        /*uncomment this if you are going to use the user-input graph or solving CW graph
         * comment this if you are going to use to find max flow using text files*/

        //main.displayMenu();

        /*comment this if you are going to use the user-input graph or solving CW graph
        * uncomment this if you are going to use to find max flow using text files*/
        /* In order to run this method:
         *   1) Open a text document in the Max Flow folder
         *   2) In the first the lines, specify the number of nodes, the source and the sink, in the order
         *   3) Then, in the upcoming lines, specify the edges:
         *       First line specifies the starting node
         *       Second line specifies the ending node
         *       Third line specifies the capacity of the edge
         *   4) Save the text file
         *   5) Call the file using the Scanner class in the getFromTextFiles method in ImplementMethods class */
        main.getFromTextFiles();
    }

}