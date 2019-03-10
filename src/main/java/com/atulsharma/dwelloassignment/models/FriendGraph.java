package com.atulsharma.dwelloassignment.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FriendGraph {
    int nodes;
    private Map<FriendNode, List<FriendEdge>> adjNodes;

    public FriendGraph(int nodes) {
        this.nodes = nodes;
        this.adjNodes = new HashMap<>();
    }

    public void addNode(int nodeId) {
        adjNodes.putIfAbsent(new FriendNode(nodeId), new ArrayList<>());
    }

    public void addEdge(int nodeId1, int nodeId2, int token, boolean update) {
        FriendNode n1 = new FriendNode(nodeId1);
        FriendNode n2 = new FriendNode(nodeId2);
        FriendEdge t1 = new FriendEdge(nodeId2, token);
        FriendEdge t2 = new FriendEdge(nodeId1, token);
        adjNodes.get(n1).add(t1);
        adjNodes.get(n2).add(t2);
        if(update) {
            updateIndirectEdges(nodeId1, nodeId2, token);
        }
    }

    public List<FriendEdge> getAdjNodes(int nodeId) {
        return adjNodes.get(new FriendNode(nodeId));
    }

    void updateIndirectEdges(int nodeId1, int nodeId2, int token) {
        List<FriendEdge> node1Edges = getAdjNodes(nodeId1);
        for(FriendEdge edge:node1Edges) {
            if(edge.token == token && edge.nodeId != nodeId2) {
                addEdge(edge.nodeId, nodeId2, token, false);
            }
        }
    }

    public int[] getNodeWithMaxEdges(int nodeId) {
        List<FriendEdge> nodeEdges = getAdjNodes(nodeId);
        Map<String,Integer> nodeCount = new HashMap<>();
        int max = 0;
        int maxNode = 0;
        for (FriendEdge edge:nodeEdges) {
            if(nodeCount.containsKey(Integer.toString(edge.nodeId))) {
                int value = nodeCount.get(Integer.toString(edge.nodeId))+1;
                nodeCount.replace(Integer.toString(edge.nodeId),value);
                if(value >= max) {
                    max = value;
                    if(maxNode < nodeId * edge.nodeId) {
                        maxNode = nodeId * edge.nodeId;
                    }
                }
            } else {
                nodeCount.putIfAbsent(Integer.toString(edge.nodeId),1);
                if(max == 0) {
                    max = 1;
                    maxNode = nodeId * edge.nodeId;
                }
            }
        }
        int[] result = {max,maxNode};
        return result;
    }

    public String printGraph() {
        StringBuffer sb = new StringBuffer();
        for(FriendNode v : adjNodes.keySet()) {
            sb.append(v);
            sb.append(adjNodes.get(v));
        }
        return sb.toString();
    }
}

class FriendNode {
    int nodeId;
    public FriendNode(int nodeId) {
        this.nodeId = nodeId;
    }
    @Override
    public boolean equals(Object obj) {
        FriendNode node = (FriendNode) obj;
        return node.nodeId == nodeId;
    }
    @Override
    public int hashCode() {
        return Integer.toString(nodeId).hashCode();
    }
    @Override
    public String toString() {
        return Integer.toString(nodeId);
    }
}

class FriendEdge {
    int nodeId;
    int token;
    public FriendEdge(int nodeId, int token) {
        this.nodeId = nodeId;
        this.token = token;
    }
    @Override
    public boolean equals(Object obj) {
        FriendEdge edge = (FriendEdge) obj;
        return (edge.nodeId == nodeId && edge.token == token);
    }
    @Override
    public String toString() {
        return nodeId+","+token;
    }
}