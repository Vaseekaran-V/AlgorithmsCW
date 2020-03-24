package com.Code;

import java.util.ArrayDeque;
import java.util.Queue;

import static java.lang.Math.min;

public class EdmondsKarpAlgorithm extends solveNetworkFlow {

    public EdmondsKarpAlgorithm(int nodes, int source, int sink) {
        super(nodes, source, sink);
    }

    @Override
    protected void solve() {
        long flow;

        do{
            markAllNodesAsUnvisited();
            flow = bfs();
            maxFlow += flow;

        }while(flow != 0);
    }

    private long bfs(){
        Queue<Integer> queue = new ArrayDeque<>(nodes);
        visit(source);
        queue.offer(source);

        Edge[] prev = new Edge[nodes];
        while(!queue.isEmpty()){
            int node = queue.poll();
            if(node == sink){
                break;
            }
            for (Edge edge: graph[node]){
                long cap = edge.remainingCapacity();

                if(cap > 0 && !visited(edge.end)){
                    visit(edge.end);
                    prev[edge.end] = edge;
                    queue.offer(edge.end);
                }
            }
        }
        if(prev[sink] == null){
            return 0;
        }

        long bottleNeck = Long.MAX_VALUE;

        for(Edge edge = prev[sink]; edge != null; edge = prev[edge.start]){
            bottleNeck = min(bottleNeck, edge.remainingCapacity());
        }

        for(Edge edge = prev[sink]; edge != null; edge = prev[edge.start]){
            edge.augment(bottleNeck);
        }

        return bottleNeck;

    }
}
