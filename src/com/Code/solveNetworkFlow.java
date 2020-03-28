package com.Code;

import java.util.ArrayList;
import java.util.List;

public abstract class solveNetworkFlow {
    //to avoid overflow
    public static final long CONSTANT_NUM = Long.MAX_VALUE / 2;

    //inputs :number of nodes, source and sink
    public int nodes, source, sink;

    protected int visitedToken = 1;
    protected int[] visited;
    protected int[] level;
    protected int[] next;

    protected boolean solved;

    protected long maxFlow;
    int counter = 0;

    protected List<Edge>[] graph;

    public solveNetworkFlow(int nodes, int source, int sink) {
        this.nodes = nodes;
        this.source = source;
        this.sink = sink;
        visited = new int[nodes];
        level = new int[nodes];
        next = new int[nodes];
        initializeEmptyFlowGraph();
    }

    private void initializeEmptyFlowGraph() {
        graph = new List[nodes];
        for (int i = 0; i < nodes; i++){
            graph[i] = new ArrayList<>();
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

    public void deleteEdge(int start, int end){
        updateCapacity(start, end, 0);

    }

    public void updateCapacity (int start, int end, long capacity){
        for(int i = 0; i < graph.length; i++){
            for(int j = 0; j < graph[i].size(); j++){
                Edge getEdge = graph[i].get(j);
                if((getEdge.getStart() == start && getEdge.getEnd() == end) || (getEdge.getStart() == end && getEdge.getEnd() == start)){
                    graph[i].get(j).setCapacity(capacity);
                    break;
                }
            }
        }

        //reset();

    }

    public List<Edge>[] getGraph(){
        execute();
        return graph;
    }

    public long getMaxFlow(){
        execute();
        return maxFlow;
    }

    public void reset(){
        maxFlow = 0;
        visitedToken = 1;
        visited = new int[nodes];
        level = new int[nodes];
        next = new int[nodes];
        execute();
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

    //method that solves the network flow
    protected abstract void solve();

}
