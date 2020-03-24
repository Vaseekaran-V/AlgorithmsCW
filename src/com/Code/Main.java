package com.Code;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        //get the number of nodes
        int nodes = sc.nextInt();
        System.out.println("What is the source node (enter the number of the source) : ");
        //int source = nodes - 2;
        int source = sc.nextInt();
        System.out.println("Enter the sink node: ");
        //int sink = nodes - 1;
        int sink = sc.nextInt();

        //import each algorithm
        solveNetworkFlow fordFulkersonDfsSolver = new FordFulkersonAlgorithm(nodes, source, sink);
        solveNetworkFlow edmondsKarpSolver = new EdmondsKarpAlgorithm(nodes, source, sink);
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

        //getting connections between source node and other nodes



        //print the number of nodes in between the source and sink
        /*System.out.print("The nodes present between the source and sink: ");
        for(int i = 0; i < source; i++){
            System.out.print(i);
            if(i == source - 1){
                System.out.print("");
                System.out.println();
            }else{
                System.out.print(", ");
            }
        }*/

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
            fordFulkersonDfsSolver.connectEdge(source, end, weight);
            edmondsKarpSolver.connectEdge(source, end, weight);
            dinicsSolver.connectEdge(source, end, weight);
            System.out.println("Is there a connection between the source node and any other nodes");
            System.out.println("Answer \"y\" if yes or \"n\" for no");
            answer = sc.next();
        }

        //get the connections that start from the middle node and goes to other nodes (except the sink node)
        for(int i = 0; i < nodes; i++){
            if(i != source && i != sink) {
                System.out.println("Now, the the middle part of the graph");
                System.out.println("Is there a connection between the node " + i + " and any other nodes");
                System.out.println("Answer \"y\" if yes or \"n\" for no");
                String answer_2 = sc.next();
                while (answer_2.toLowerCase().contains("y")) {
                    System.out.println("Enter the node where the node " + i + " is connected to...");
                    int end = sc.nextInt();
                    System.out.println("What is the weight between the node " + i + " and node " + end + " :");
                    Long weight = getWeightFromUser();
                    fordFulkersonDfsSolver.connectEdge(i, end, weight);
                    edmondsKarpSolver.connectEdge(i, end, weight);
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
            fordFulkersonDfsSolver.connectEdge(start, sink, weight);
            edmondsKarpSolver.connectEdge(start, sink, weight);
            dinicsSolver.connectEdge(start, sink, weight);
            System.out.println("Is there a connection between any other nodes and sink node");
            System.out.println("Answer \"y\" if yes or \"n\" for no");
            answer_3 = sc.next();
        }

        System.out.println("Max Flow from Ford Fulkerson: " + fordFulkersonDfsSolver.getMaxFlow());
        System.out.println("Max Flow from Edmonds Karp: " + edmondsKarpSolver.getMaxFlow());
        System.out.println("Max Flow from Dinic's Algorithm: " + dinicsSolver.getMaxFlow());
    }
    private static Long getWeightFromUser(){
        Scanner sc = new Scanner(System.in);
        Long weight = sc.nextLong();
        return weight;
    }


}
