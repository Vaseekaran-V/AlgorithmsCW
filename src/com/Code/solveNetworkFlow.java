package com.Code;

import java.util.ArrayList;
import java.util.List;

public abstract class solveNetworkFlow {
    //to avoid overflow
    public static final long CONSTANT_NUM = Long.MAX_VALUE / 2;

    //inputs :number of nodes, source and sink
    public int nodes, source, sink;

    //to check if the max flow has been found already
    protected boolean solved;

    protected long maxFlow;

    //adjacency list that stores the graph
    protected List<Edge>[] graph;

    public solveNetworkFlow(int nodes, int source, int sink) {
        this.nodes = nodes;
        this.source = source;
        this.sink = sink;
        initializeEmptyFlowGraph();
    }

    private void initializeEmptyFlowGraph() {
        graph = new List[nodes];
        for (int i = 0; i < nodes; i++){
            graph[i] = new ArrayList<>();
        }

    }

    //connecting 2 nodes
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

    //deleting an edge
    public void deleteEdge(int start, int end){
        updateCapacity(start, end, 0);

    }

    //updating the capacity on an edge
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
    }

    //returning the graph
    public List<Edge>[] getGraph(){
        execute();
        return graph;
    }

    //getting the max flow
    public long getMaxFlow(){
        execute();
        return maxFlow;
    }

    private void execute() {
        if(solved){
            return;
        }
        solved = true;
        solve();
    }

    //abstract method that solves the network flow | implemented in DinicsAlgorithm class
    protected abstract void solve();

}
