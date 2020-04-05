package com.Code;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

import static java.lang.Math.min;

public class DinicsAlgorithm extends solveNetworkFlow {
    //keeping track of the levels of each nodes during the level graph construction
    private int[] level;
    public DinicsAlgorithm(int nodes, int source, int sink) {
        super(nodes, source, sink);
        level = new int[nodes];
    }

    @Override
    protected void solve() {
        //array which contains the nodes that can be traversed (by preventing the access of dead end nodes)
        int[] next = new int[nodes];

        //initiating the start time of the program
        double startTime = System.nanoTime();

        while(bfs()){
            //resetting the array "next"
            Arrays.fill(next, 0);
            //finding the blocking flow by repeatedly using depth first search from source to sink
            for(long f = dfs(source, next, CONSTANT_NUM); f != 0; f = dfs(source, next, CONSTANT_NUM)){
                maxFlow += f;
            }
        }

        //initiating the end time
        double endTime = System.nanoTime();
        //returning the time taken to find the maximum flow
        double elapsedTime = (endTime - startTime)/1000;
        System.out.println("\nTime taken: " + elapsedTime + " micro seconds");
    }

    private boolean bfs(){
        Arrays.fill(level, -1); //setting each nodes as unvisited
        Deque<Integer> deque = new ArrayDeque<>(nodes); //deque data structure to execute breadth-first search
        deque.offer(source);//adding the source node to the queue
        level[source] = 0; //marking the distance to the source node from the source node [as zero]

        while(!deque.isEmpty()){
            int node = deque.poll(); //removing the first node found in the deque
            for(Edge edge: graph[node]){ //iterating through all the adjacent edges
                long remainingCapacity = edge.remainingCapacity();
                if(remainingCapacity > 0 && level[edge.end] == -1){ //capacity of an edge must be greater than zero and the nodes must be unvisited
                    level[edge.end] = level[node] +1; //computing the level for that node that is going to be visited
                    deque.offer(edge.end); //adding the node to be visited to the deque
                }
            }
        }
        //checking whether the sink was reached
        return level[sink] != -1;
    }

    //using depth-first search to find the augmenting flow
    private long dfs(int current, int[] next, long flow){
        //search stops when the current node has reached the sink
        if(current == sink){
            return flow;
        }

        final int numOfEdges = graph[current].size();

        //loops through all the edges
        for(; next[current] < numOfEdges; next[current]++){
            //gets the edge which has the index from the next array
            Edge edge = graph[current].get(next[current]);
            //capacity of the edge mentioned above
            long cap = edge.remainingCapacity();

            //edge capacity must be greater than zero and at the next level
            if(cap > 0 && level[edge.end] == level[current] + 1){
                //calling the depth-first search recursively
                long bottleNeck = dfs(edge.end, next, min(flow, cap));
                if(bottleNeck > 0){
                    //augmenting the flow
                    edge.augment(bottleNeck);
                    return bottleNeck;
                }
            }
        }
        return 0;
    }
}
