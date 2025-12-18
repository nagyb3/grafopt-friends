package Friends.FriendsBrokenUp;

import java.util.ArrayList;
import java.util.HashSet;

public class Solver {
    public int solve(ArrayList<int[]> edges) {
        HashSet<Integer> vertices = new HashSet<>();

        for (int[] edge : edges) {
            vertices.add(edge[0]);
            vertices.add(edge[1]);
        }

        // calculate the biggest number for an edge for the size of the disjoint sets' array
        int maxNumber = 0;
        for (int vertex : vertices) {
            if (vertex > maxNumber) {
                maxNumber = vertex;
            }
        }

        DisjointSetUnion disjointSet = new DisjointSetUnion(maxNumber);

        for (int vertex : vertices) {
            disjointSet.makeSet(vertex);
        }

        for (int[] edge : edges) {
            disjointSet.union(edge[0], edge[1]);
        }

        // assign to each vertex, how many other vertices is this one the representative of:
        int[] setSizes = new int[maxNumber + 1];
        for (int vertex : vertices) {
            setSizes[disjointSet.find(vertex)]++;
        }

        int sizeOfLargestFriendGroup = 1;
        for (int i = 0; i < setSizes.length; i++) {
            if (setSizes[i] > sizeOfLargestFriendGroup) {
                sizeOfLargestFriendGroup = setSizes[i];
            }
        }

        return sizeOfLargestFriendGroup;
    }
}
