package com.Code;

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

    public Edge getResidual() {
        return residual;
    }

    public void setResidual(Edge residual) {
        this.residual = residual;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public void setCapacity(long capacity) {
        this.capacity = capacity;
    }

    public long getFlow() {
        return flow;
    }

    public void setFlow(long flow) {
        this.flow = flow;
    }

    public long getCapacity() {
        return capacity;
    }

    public boolean isResidual(){
        return capacity == 0;
    }

    public long remainingCapacity(){
        return (capacity - flow);
    }

    public void augment(long bottleneck){
        flow = flow + bottleneck;
        residual.flow -= bottleneck;
    }

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
