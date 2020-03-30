package com.Code;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

import static java.lang.Math.min;

public class DinicsAlgorithm extends solveNetworkFlow {
    private int[] level;
    public DinicsAlgorithm(int nodes, int source, int sink) {
        super(nodes, source, sink);
        level = new int[nodes];
    }

    @Override
    protected void solve() {

        int[] next = new int[nodes];

        //initiating the start time of the program
        double startTime = System.nanoTime();

        while(bfs()){
            Arrays.fill(next, 0);

            for(long f = dfs(source, next, CONSTANT_NUM); f != 0; f = dfs(source, next, CONSTANT_NUM)){
                maxFlow += f;
            }
        }

        double endTime = System.nanoTime();
        double elapsedTime = (endTime - startTime)/1000;
        System.out.println("\nTime taken: " + elapsedTime + " micro seconds");
    }

    private boolean bfs(){
        Arrays.fill(level, -1);
        Deque<Integer> deque = new ArrayDeque<>(nodes);
        deque.offer(source);
        level[source] = 0;

        while(!deque.isEmpty()){
            int node = deque.poll();
            for(Edge edge: graph[node]){
                long cap = edge.remainingCapacity();
                if(cap > 0 && level[edge.end] == -1){
                    level[edge.end] = level[node] +1;
                    deque.offer(edge.end);
                }
            }
        }
        return level[sink] != -1;
    }

    private long dfs(int current, int[] next, long flow){
        if(current == sink){
            return flow;
        }
        final int numOfEdges = graph[current].size();

        for(; next[current] < numOfEdges; next[current]++){
            Edge edge = graph[current].get(next[current]);
            long cap = edge.remainingCapacity();

            if(cap > 0 && level[edge.end] == level[current] + 1){
                long bottleNeck = dfs(edge.end, next, min(flow, cap));
                if(bottleNeck > 0){
                    edge.augment(bottleNeck);
                    return bottleNeck;
                }
            }
        }
        return 0;
    }
}
