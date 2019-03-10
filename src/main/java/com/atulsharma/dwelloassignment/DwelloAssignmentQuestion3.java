package com.atulsharma.dwelloassignment;

import com.atulsharma.dwelloassignment.models.FriendGraph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class DwelloAssignmentQuestion3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String counts = br.readLine();
        int friends_nodes = Integer.parseInt(counts.split(" ")[0]);
        int friendEdgesCount = Integer.parseInt(counts.split(" ")[1]);
        int[] friends_from = new int[friendEdgesCount];
        int[] friends_to = new int[friendEdgesCount];
        int[] friends_weight = new int[friendEdgesCount];
        for(int i=0;i<friendEdgesCount;i++) {
            String[] inputs = br.readLine().split(" ");
            friends_from[i] = Integer.parseInt(inputs[0]);
            friends_to[i] = Integer.parseInt(inputs[1]);
            friends_weight[i] = Integer.parseInt(inputs[2]);
        }
        DwelloAssignmentQuestion3 application = new DwelloAssignmentQuestion3();
        int maxToken = application.maxTokens(friends_nodes, friends_from, friends_to, friends_weight);
        System.out.println(maxToken);
    }

    public int maxTokens(int friends_nodes, int[] friends_from, int[] friends_to, int[] friends_weight) {
        FriendGraph graph = new FriendGraph(friends_nodes);
        for(int i=1;i<=friends_nodes;i++) {
            graph.addNode(i);
        }
        for(int i=0;i<friends_from.length;i++) {
            graph.addEdge(friends_from[i], friends_to[i], friends_weight[i], true);
        }
        int maxEdges = 0;
        int maxToken = 0;
        for(int i=1;i<=friends_nodes;i++) {
            int[] edgeTuple = graph.getNodeWithMaxEdges(i);
            if(edgeTuple[0] >= maxEdges) {
                maxEdges = edgeTuple[0];
                if(edgeTuple[1] > maxToken) {
                    maxToken = edgeTuple[1];
                }
            }
        }
        return maxToken;
    }
}
