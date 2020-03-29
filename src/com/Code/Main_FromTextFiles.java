package com.Code;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
public class Main_FromTextFiles {

    public static void main(String[] args) throws FileNotFoundException {

        Main_FromTextFiles main = new Main_FromTextFiles();
        main.getOutput();
    }
    public void getOutput() throws FileNotFoundException {

        Scanner scanner;
        /* You can uncomment the required scanner and comment the not-required one
        * to find the Maximum Flow of Different graphs */

        //scanner = new Scanner(new File("CW prob.txt"));
        //scanner = new Scanner(new File("very small graph.txt"));
        //scanner = new Scanner(new File("small graph.txt"));
        //scanner = new Scanner(new File("medium graph.txt"));
        scanner = new Scanner(new File("large graph.txt"));

        //adding the integers in text to an array list
        ArrayList<Integer> intFromText = new ArrayList<>();
        int i = 0;
        while(scanner.hasNextInt())
        {
            intFromText.add(scanner.nextInt());
            i++;
        }

        //getting the size of the list of integers
        int len = intFromText.size();
        if(len%3 !=0){
            len = (len / 3) + 1;
        }
        else{
            len = len / 3;
        }

        /*creating a new 2-D array, to store the values in the text file
        * First 3 lines depict number of nodes, source and sink in that order
        * Then, after that each line is grouped as 3 numbers
        * first number depicts the starting node, 2nd number depicts the ending node
        * 3rd number depicts the capacity between the 2 nodes */
        int[][] numArray = new int[len][3];
        int m = 0;

        for(int j = 0; j < len ; j++){
            for(int k = 0; k < 3; k++){
                numArray[j][k] = intFromText.get(m);
                m++;
                if(m == intFromText.size() + 1){
                    break;
                }
            }
        }

        //setting the nodes, source and sink
        int nodes = numArray[0][0];
        int source = numArray[0][1];
        int sink = numArray[0][2];

        //code for creating edges in the graph
        solveNetworkFlow dinicsAlgorithm = new DinicsAlgorithm(nodes, source, sink);
        for(int j = 1; j < numArray.length; j++){
            int start = numArray[j][0];
            int end = numArray[j][1];
            int capacity = numArray[j][2];
            dinicsAlgorithm.connectEdge(start, end, capacity);
        }

        //initiating the start time of the program
        double startTime = System.nanoTime();
        //displaying the max flow and the connected graph
        System.out.println("Source: " + numArray[0][1]);
        System.out.println("Sink: " + numArray[0][2]);
        System.out.println("\nMaximum Flow for the graph = " + dinicsAlgorithm.getMaxFlow());
        double endTime = System.nanoTime();
        double elapsedTime = (endTime - startTime)/1000;
        System.out.println("\nTime taken: " + elapsedTime + " micro seconds");

        System.out.println();
        displayGraph(dinicsAlgorithm, source, sink);

    }

    public void printGraph(List<Edge>[] graph, int source, int sink){
        for(List<Edge> edges : graph){
            for (Edge e : edges){
                String flow = e.toString(source, sink);
                if(flow != null){
                    System.out.println(flow);
                }
            }
        }
    }

    public void displayGraph(solveNetworkFlow dinicsAlgorithm, int source, int sink){
        System.out.println("Displaying the graph...\n");
        List<Edge>[] graph = dinicsAlgorithm.getGraph();
        printGraph(graph, source, sink);
    }
}
