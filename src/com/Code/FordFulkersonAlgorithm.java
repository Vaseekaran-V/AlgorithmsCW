package com.Code;

import java.util.List;

import static java.lang.Math.min;

public class FordFulkersonAlgorithm extends solveNetworkFlow {

    public FordFulkersonAlgorithm(int nodes, int source, int sink) {
        super(nodes, source, sink);
    }

    @Override
    protected void solve() {
        for(long f = dfs(source, CONSTANT_NUM); f != 0; f = dfs(source, CONSTANT_NUM)) {
            visitedToken++;
            maxFlow += f;
        }
    }

    public long dfs(int node, long flow){
        if(node == sink){
            return flow;
        }
        visited[node] = visitedToken;

        List<Edge> edges = graph[node];
        for (Edge edge : edges){
            if(edge.remainingCapacity() > 0 && visited[edge.end] != visitedToken){
                long bottleNeck = dfs(edge.end, min(flow, edge.remainingCapacity()));

                if(bottleNeck > 0){
                    edge.augment(bottleNeck);
                    return bottleNeck;
                }
            }
        }
        return 0;
    }


}
