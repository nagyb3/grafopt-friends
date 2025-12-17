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
        for (int elementNum : vertices) {
            if (elementNum > maxNumber) {
                maxNumber = elementNum;
            }
        }

        DSU disjointSet = new DSU(maxNumber);

        for (int elementNum : vertices) {
            disjointSet.makeSet(elementNum);
        }

        for (int[] edge: edges) {
            disjointSet.union(edge[0], edge[1]);
        }

        int[] results = new int[maxNumber + 1];
        for (int vertice: vertices) {
            results[disjointSet.find(vertice)]++;
        }

        int sizeOfLargestFriendGroup = 1;
        for (int i = 0; i < results.length; i++) {
            if (results[i] > sizeOfLargestFriendGroup) {
                sizeOfLargestFriendGroup = results[i];
            }
        }

        return sizeOfLargestFriendGroup;
    }
}
