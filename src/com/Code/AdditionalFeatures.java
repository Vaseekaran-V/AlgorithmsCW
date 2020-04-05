package com.Code;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*an abstract class that contains all the methods that is going to be executed in order to obtain the maximum flow
* These methods are implemented by 2 main classes
*   Main.java - > finds the maximum flow of the CW graph and user input graph
*   Main_FromTextFiles.java - > finds the maximum flow of graphs specified by text files*/
public abstract class AdditionalFeatures {

    //displaying the menu for selection
    public void displayMenu(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Press 1 to solve the CW graph");
        System.out.println("Press 2 to create a new graph and find Maximum Flow");
        System.out.println("Enter your selection : ");
        while (!sc.hasNextInt()) {
            System.out.println("Incorrect data type... enter the correct data type...");
            sc.next();
        }
        int choice = sc.nextInt();
        selectChoice(choice);

    }

    public void selectChoice(int choice){
        Scanner sc = new Scanner(System.in);
        while(true) {
            switch (choice) {
                case 1:
                    System.out.println("You have selected to solve the CW graph");
                    CWGraphSolve();
                    break;

                case 2:
                    System.out.println("You have selected to find the Maximum Flow for a different graph");
                    solveNewGraph();
                    break;

                default:
                    System.out.println("Select a correct option");
                    break;
            }
            displayMenu();
            System.out.println("Enter the choice of selection");
            while (!sc.hasNextInt()) {
                System.out.println("Incorrect data type... enter the correct data type...");
                displayMenu();
                sc.next();
            }
            choice = sc.nextInt();
        }
    }

    //getting the capacity of the edge from the user
    public static Long getCapacityFromUser(){
        Scanner sc = new Scanner(System.in);
        while (!sc.hasNextLong()) {
            System.out.println("Incorrect data type... enter the correct data type...");
            sc.next();
        }
        Long capacity = sc.nextLong();
        return capacity;
    }

    //code to solve the max flow of the graph by getting inputs from the user
    private void solveNewGraph() {
        Scanner sc = new Scanner(System.in);

        //get the number of nodes
        System.out.println("How many nodes are present in the graph?");
        while (!sc.hasNextInt()) {
            System.out.println("Incorrect data type... enter the correct data type...");
            sc.next();
        }
        int nodes = sc.nextInt();
        System.out.println("What is the source node (enter the number of the source) : ");
        while (!sc.hasNextInt()) {
            System.out.println("Incorrect data type... enter the correct data type...");
            sc.next();
        }
        int source = sc.nextInt();
        System.out.println("Enter the sink node: ");
        while (!sc.hasNextInt()) {
            System.out.println("Incorrect data type... enter the correct data type...");
            sc.next();
        }
        int sink = sc.nextInt();

        solveNetworkFlow dinicsSolver = new DinicsAlgorithm(nodes, source, sink);

        //num of nodes between the source and sink
        System.out.println("Nodes between source and sink");
        for(int i = 0; i < nodes; i++){
            if(i != source && i != sink){
                System.out.println(i);
            }else{
                continue;
            }
        }

        //get the connections that start from the source nodes and end at other nodes
        System.out.println("Now, at the starting part of the graph (i.e. the source nodes)");
        System.out.println("Is there a connection between the source node and any other nodes");
        System.out.println("Answer \"y\" if yes or \"n\" for no");
        String answer = sc.next();
        while(answer.toLowerCase().contains("y")){
            System.out.println("Enter the node where the source node is connected to...");
            while (!sc.hasNextInt()) {
                System.out.println("Incorrect data type... enter the correct data type...");
                sc.next();
            }
            int end = sc.nextInt();
            System.out.println("What is the weight between the source node and node " + end + " :");

            Long weight = getCapacityFromUser();
            dinicsSolver.connectEdge(source, end, weight);
            System.out.println("Is there a connection between the source node and any other nodes");
            System.out.println("Answer \"y\" if yes or \"n\" for no");
            answer = sc.next();
        }

        //get the connections that start from the middle node and goes to other nodes (except the sink node)
        System.out.println("Now, the the middle part of the graph");
        for(int i = 0; i < nodes; i++){
            if(i != source && i != sink) {
                System.out.println("Is there a connection between the node " + i + " and any other nodes");
                System.out.println("Enter only the connections between the middle nodes... don't include the source nor sink");
                System.out.println("Answer \"y\" if yes or \"n\" for no");
                String answer_2 = sc.next();
                while (answer_2.toLowerCase().contains("y")) {
                    System.out.println("Enter the node where the node " + i + " is connected to...");
                    while (!sc.hasNextInt()) {
                        System.out.println("Incorrect data type.. enter the correct data type..");
                        sc.next();
                    }
                    int end = sc.nextInt();
                    System.out.println("What is the weight between the node " + i + " and node " + end + " :");

                    Long weight = getCapacityFromUser();
                    dinicsSolver.connectEdge(i, end, weight);
                    System.out.println("Is there a connection between the node " + i + " and any other nodes");
                    System.out.println("Answer \"y\" if yes or \"n\" for no");
                    answer_2 = sc.next();
                }
            }else{
                continue;
            }
        }

        System.out.println("Is there a connection between other nodes and the sink nodes");
        System.out.println("Answer \"y\" if yes or \"n\" for no");
        String answer_3 = sc.next();
        while(answer_3.toLowerCase().contains("y")){
            System.out.println("Enter the node which is connected to the sink node...");
            while (!sc.hasNextInt()) {
                System.out.println("Incorrect data type.. enter the correct data type...");
                sc.next();
            }
            int start = sc.nextInt();
            System.out.println("What is the weight between the sink node and node " + start + " :");

            Long weight = getCapacityFromUser();
            dinicsSolver.connectEdge(start, sink, weight);
            System.out.println("Is there a connection between any other nodes and sink node");
            System.out.println("Answer \"y\" if yes or \"n\" for no");
            answer_3 = sc.next();
        }

        getMaxFlowGraph(dinicsSolver);
        displayGraph(dinicsSolver, source, sink);
    }

    //solving the Coursework graph by hardcoding the values
    public void CWGraphSolve() {
        //solving the graph mentioned in the CW Specification
        Scanner sc = new Scanner(System.in);

        int nodes = 6;
        int source = 0;
        int sink = 5;

        solveNetworkFlow dinicsAlgorithm = new DinicsAlgorithm(nodes, source, sink);

        System.out.println();

        //add from source to middle part
        dinicsAlgorithm.connectEdge(source, 1, 10);
        dinicsAlgorithm.connectEdge(source, 2, 8);

        //edges between the source and sink (excluding edges connected from source,
        //and edges connected to sink.
        dinicsAlgorithm.connectEdge(1, 3, 5);
        dinicsAlgorithm.connectEdge(1, 2, 5);
        dinicsAlgorithm.connectEdge(2, 1, 4);
        dinicsAlgorithm.connectEdge(2, 4, 10);
        dinicsAlgorithm.connectEdge(3, 2, 7);
        dinicsAlgorithm.connectEdge(3, 4, 6);
        dinicsAlgorithm.connectEdge(4, 3, 10);

        //edges connected to the sink
        dinicsAlgorithm.connectEdge(3, sink, 3);
        dinicsAlgorithm.connectEdge(4, sink, 14);


        System.out.println("Do you want to get the maximum flow, or make any pre-defined modifications...");
        System.out.println("Press 1 to get Maximum Flow for un-edited graph");
        System.out.println("Press 2 to get Maximum Flow for graph with deleted edges");
        System.out.println("Press 3 to get Maximum Flow for graph with edited capacities");

        while (!sc.hasNextInt()) {
            System.out.println("Incorrect data type... enter the correct data type...");
            sc.next();
        }
        int choice = sc.nextInt();

        switch(choice){
            case 1:
                System.out.println("\nDisplaying the Maximum Flow for unedited graph");
                getMaxFlowGraph(dinicsAlgorithm);
                System.out.println();
                displayGraph(dinicsAlgorithm, source, sink);
                System.out.println();
                break;

            case 2:
                System.out.println("\nDeleting edge...");
                CWGraphDeleteEdge(dinicsAlgorithm);
                System.out.println("Displaying Maximum Flow for the graph with deleted edge");
                getMaxFlowGraph(dinicsAlgorithm);
                System.out.println();
                displayGraph(dinicsAlgorithm, source, sink);
                System.out.println();
                break;

            case 3:
                System.out.println("\nEditing capacities of edges...");
                CWGraphEditCapacity(dinicsAlgorithm, source);
                System.out.println("Displaying Maximum Flow for the graph with edited capacities of edges");
                getMaxFlowGraph(dinicsAlgorithm);
                System.out.println();
                displayGraph(dinicsAlgorithm, source, sink);
                System.out.println();
                break;

            default:
                System.out.println("Incorrect Option...");
        }
    }

    //deleting an edge
    public void CWGraphDeleteEdge(solveNetworkFlow dinicsAlgorithm){
        dinicsAlgorithm.deleteEdge(1, 2);
        dinicsAlgorithm.deleteEdge(3, 4);
    }

    //editing the capacity of an edge
    public void CWGraphEditCapacity(solveNetworkFlow dinicsAlgorithm, int source){
        dinicsAlgorithm.updateCapacity(source, 1, 5);
        dinicsAlgorithm.updateCapacity(source, 2, 5);
    }

    //getting the max flow of the graph
    public void getMaxFlowGraph(solveNetworkFlow dinicsAlgorithm){
        System.out.println("Maximum Flow for the graph: " + dinicsAlgorithm.getMaxFlow());
    }

    //printing the graph (displaying each edge)
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

    //printing the graph (displaying each edge)
    public void displayGraph(solveNetworkFlow dinicsAlgorithm, int source, int sink){
        System.out.println("Displaying the graph...\n");
        List<Edge>[] graph = dinicsAlgorithm.getGraph();
        printGraph(graph, source, sink);
    }

    // -------------------------------------------------------------------------------------------------------
    /* code executed in Main_FromTextFiles class
    *  this gets the data from the text file included*/



    public void getOutput() throws FileNotFoundException {

        Scanner scanner;
        /* You can uncomment the required scanner and comment the not-required one
         * to find the Maximum Flow of Different graphs */

        scanner = new Scanner(new File("CW prob.txt"));
        //scanner = new Scanner(new File("6 nodes_10 edges graph.txt"));
        //scanner = new Scanner(new File("12 nodes_20 edges graph.txt"));
        //scanner = new Scanner(new File("24 nodes_40 edges graph.txt"));
        //scanner = new Scanner(new File("48 nodes_80 edges graph.txt"));


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

        System.out.println("No of nodes: " + numArray[0][0]);
        System.out.println("Source: " + numArray[0][1]);
        System.out.println("Sink: " + numArray[0][2]);
        System.out.println("No of Edges: " + (numArray.length - 1));


        //displaying the max flow and the connected graph
        System.out.println("\nMaximum Flow for the graph = " + dinicsAlgorithm.getMaxFlow());

        System.out.println();
        displayGraph(dinicsAlgorithm, source, sink);

    }
}
