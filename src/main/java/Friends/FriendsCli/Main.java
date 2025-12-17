package Friends.FriendsCli;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

// https://onlinejudge.org/external/106/10608.pdf
class Main {
    static class DisjointSets {
        private int[] parent;

        public DisjointSets(int size) {
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
    }

    static class Solver {
        public int solve(ArrayList<int[]> edges) {
            HashSet<Integer> vertices = new HashSet<>();

            for (int[] edge : edges) {
                vertices.add(edge[0]);
                vertices.add(edge[1]);
            }

            int maxNumber = 0;
            for (int elementNum : vertices) {
                if (elementNum > maxNumber) {
                    maxNumber = elementNum;
                }
            }

            DisjointSets disjointSet = new DisjointSets(maxNumber);

            for (int elementNum : vertices) {
                disjointSet.makeSet(elementNum);
            }

            for (int[] element : edges) {
                disjointSet.union(element[0], element[1]);
            }

            int[] results = new int[maxNumber + 1];
            for (int vertice : vertices) {
                results[disjointSet.find(vertice)]++;
            }

            int sizeOfLargeFriendsConnection = 1;
            for (int i = 0; i < results.length; i++) {
                if (results[i] > sizeOfLargeFriendsConnection) {
                    sizeOfLargeFriendsConnection = results[i];
                }
            }

            return sizeOfLargeFriendsConnection;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null || line.trim().isEmpty()) {
            return;
        }

        int testCases = Integer.parseInt(line.trim());
        StringBuilder out = new StringBuilder();
        Solver solver = new Solver();

        for (int t = 0; t < testCases; t++) {
            do {
                line = br.readLine();
            } while (line != null && line.trim().isEmpty());
            if (line == null) break;

            StringTokenizer st = new StringTokenizer(line);
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            ArrayList<int[]> edges = new ArrayList<>(M);
            for (int i = 0; i < M; i++) {
                line = br.readLine();
                while (line != null && line.trim().isEmpty()) {
                    line = br.readLine();
                }
                st = new StringTokenizer(line);
                int A = Integer.parseInt(st.nextToken());
                int B = Integer.parseInt(st.nextToken());
                edges.add(new int[]{A, B});
            }

            int result;
            if (M == 0 && N > 0) {
                result = 1;
            } else {
                result = solver.solve(edges);
            }

            out.append(result).append('\n');
        }

        System.out.print(out.toString());
    }
}

