package com.Code;

import java.util.ArrayList;
import java.util.List;

public abstract class  NetworkFlowSolverBase {
    //to avoid overflow
    public static final long INF = Long.MAX_VALUE / 2;

    //inputs :number of nodes, source and sink
    public final int nodes, source, sink;

    protected int visitedToken = 1;
    protected int[] visited;

    protected boolean solved;

    protected long maxFlow;

    protected List<Edge>[] graph;

    public NetworkFlowSolverBase(int nodes, int source, int sink) {
        this.nodes = nodes;
        this.source = source;
        this.sink = sink;
        initializeEmptyFlowGraph();
        visited = new int[nodes];
    }

    private void initializeEmptyFlowGraph() {
        graph = new List[nodes];
        for (int i = 0; i < nodes; i++){
            graph[i] = new ArrayList<Edge>();
        }
    }

    public void connectEdge(int start, int end, long capacity){
        if(capacity <= 0){
            throw new IllegalArgumentException("Forward edge capacity <= 0");
        }
        Edge e1 = new Edge(start, end, capacity);
        Edge e2 = new Edge(end, start, 0);
        e1.setResidual(e2);
        e2.setResidual(e1);
        graph[start].add(e1);
        graph[end].add(e2);
    }

    public List<Edge>[] getGraph(){
        execute();
        return graph;
    }

    public long getMaxFlow(){
        execute();
        return maxFlow;
    }

    public void visit(int i){
        visited[i] = visitedToken;
    }

    public boolean visited(int i){
        return visited[i] == visitedToken;
    }

    public void markAllNodesAsUnvisited(){
        visitedToken++;
    }

    private void execute() {
        if(solved){
            return;
        }
        solved = true;
        solve();
    }

    protected abstract void solve();


}
