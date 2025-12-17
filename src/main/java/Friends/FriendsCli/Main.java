package Friends.FriendsCli;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

// https://onlinejudge.org/external/106/10608.pdf
class Main {
    public static class DisjointSetUnion {
        private int[] parent;

        public DisjointSetUnion(int size) {
            this.parent = new int[size + 1];
        }

        public void makeSet(int v) {
            parent[v] = v;
        }

        public int find(int v) {
            if (v == parent[v]) {
                return v;
            }

            return find(parent[v]);
        }

        public void union(int u, int v) {
            int uSet = find(u);
            int vSet = find(v);

            if (uSet != vSet) {
                parent[uSet] = vSet;
            }
        }

        @Override
        public String toString() {
            return "DSU{" +
                    "parent=" + Arrays.toString(parent) +
                    '}';
        }
    }

    static class Solver {
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

            DisjointSetUnion disjointSet = new DisjointSetUnion(maxNumber);

            for (int elementNum : vertices) {
                disjointSet.makeSet(elementNum);
            }

            for (int[] edge : edges) {
                disjointSet.union(edge[0], edge[1]);
            }

            // assign to each vertix, how many other vertixes is this one the representative of:
            int[] results = new int[maxNumber + 1];
            for (int vertice : vertices) {
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

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        if (!scanner.hasNextLine()) {
            return;
        }

        int numberOfTestCases = scanner.nextInt();

        StringBuilder outputSb = new StringBuilder();
        Solver solver = new Solver();

        for (int t = 0; t < numberOfTestCases; t++) {
            // N: number of citizens in town (vertices), M: number of pairs/friendships (edges)
            int N = scanner.nextInt();
            int M = scanner.nextInt();

            ArrayList<int[]> edges = new ArrayList<>(M);
            for (int i = 0; i < M; i++) {

                edges.add(new int[]{scanner.nextInt(), scanner.nextInt()});
            }

            int result = solver.solve(edges);

            outputSb.append(result).append('\n');
        }

        System.out.print(outputSb);
    }
}

