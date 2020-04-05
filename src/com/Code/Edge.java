package com.Code;

//class for edges in the graph
public class Edge {
    public int start;
    public int end;
    public Edge residual;
    public long flow;
    public long capacity;

    public Edge(int start, int end, long capacity) {
        this.start = start;
        this.end = end;
        this.capacity = capacity;
    }

    public void setResidual(Edge residual) {
        this.residual = residual;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public void setCapacity(long capacity) {
        this.capacity = capacity;
    }

    //checking if the capacity is positive in the edge
    public boolean isResidual(){
        return capacity == 0;
    }

    //returns the capacity of the edge after flow is calculated
    public long remainingCapacity(){
        return (capacity - flow);
    }

    //updates the flow along the edge
    public void augment(long bottleneck){
        flow = flow + bottleneck;
        residual.flow -= bottleneck;
    }

    //function to output each edge's source, sink, it's maximum capacity and the allowed flow through the edge
    public String toString(int source, int sink) {
        String u = (start == source) ? "source" : ((start == sink) ? "sink" : String.valueOf(start));
        String v = (end == source) ? "source" : ((end == sink) ? "sink" : String.valueOf(end));
        if(flow > 0 && capacity > 0) {
            return String.format(
                    "Edge %s -> %s | maximum capacity = %d | allowed flow = %d ",
                    u, v, capacity, flow, isResidual());
        }else{
            return null;
        }
    }

}
