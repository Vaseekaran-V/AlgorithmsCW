package com.Code;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        main.displayMenu();
    }

    private static Long getWeightFromUser(){
        Scanner sc = new Scanner(System.in);
        Long weight = sc.nextLong();
        return weight;
    }

    public void selectChoice(int choice){
        Scanner sc = new Scanner(System.in);
        while(true) {
            switch (choice) {
                case 1:
                    System.out.println("You have selected to solve the CW graph");
                    CWGraphAddEdge();
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
            choice = sc.nextInt();
        }
    }

    public void displayMenu(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Press 1 to solve the CW graph");
        System.out.println("Press 2 to create a new graph and find Maximum Flow");
        System.out.println("Enter your selection : ");
        int choice = sc.nextInt();
        selectChoice(choice);

    }

    private void solveNewGraph() {
        Scanner sc = new Scanner(System.in);

        //get the number of nodes
        System.out.println("How many nodes are present in the graph?");
        int nodes = sc.nextInt();
        System.out.println("What is the source node (enter the number of the source) : ");
        //int source = nodes - 2;
        int source = sc.nextInt();
        System.out.println("Enter the sink node: ");
        //int sink = nodes - 1;
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
            int end = sc.nextInt();
            System.out.println("What is the weight between the source node and node " + end + " :");
            Long weight = getWeightFromUser();
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
                    int end = sc.nextInt();
                    System.out.println("What is the weight between the node " + i + " and node " + end + " :");
                    Long weight = getWeightFromUser();
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
            int start = sc.nextInt();
            System.out.println("What is the weight between the sink node and node " + start + " :");
            Long weight = getWeightFromUser();
            dinicsSolver.connectEdge(start, sink, weight);
            System.out.println("Is there a connection between any other nodes and sink node");
            System.out.println("Answer \"y\" if yes or \"n\" for no");
            answer_3 = sc.next();
        }

        getMaxFlowGraph(dinicsSolver);
        displayGraph(dinicsSolver, source, sink);
    }

    //code for the CW specification
    private void CWGraphAddEdge() {
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

        dinicsAlgorithm.connectEdge(1, 3, 5);
        dinicsAlgorithm.connectEdge(1, 2, 5);
        dinicsAlgorithm.connectEdge(2, 1, 4);
        dinicsAlgorithm.connectEdge(2, 4, 10);
        dinicsAlgorithm.connectEdge(3, 2, 7);
        dinicsAlgorithm.connectEdge(3, 4, 6);
        dinicsAlgorithm.connectEdge(4, 3, 10);

        dinicsAlgorithm.connectEdge(3, sink, 3);
        dinicsAlgorithm.connectEdge(4, sink, 14);


        System.out.println("Do you want to get the maximum flow, or make any pre-defined modifications...");
        System.out.println("Press 1 to get Maximum Flow for un-edited graph");
        System.out.println("Press 2 to get Maximum Flow for graph with deleted edges");
        System.out.println("Press 3 to get Maximum Flow for graph with edited capacities");

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

    public void CWGraphDeleteEdge(solveNetworkFlow dinicsAlgorithm){
        dinicsAlgorithm.deleteEdge(1, 2);
        dinicsAlgorithm.deleteEdge(3, 4);
    }

    public void CWGraphEditCapacity(solveNetworkFlow dinicsAlgorithm, int source){
        dinicsAlgorithm.updateCapacity(source, 1, 5);
        dinicsAlgorithm.updateCapacity(source, 2, 5);
    }

    public void getMaxFlowGraph(solveNetworkFlow dinicsAlgorithm){
        System.out.println("Maximum Flow for the graph: " + dinicsAlgorithm.getMaxFlow());
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