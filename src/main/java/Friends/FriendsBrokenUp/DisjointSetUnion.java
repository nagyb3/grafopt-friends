package Friends.FriendsBrokenUp;

import java.util.Arrays;

public class DisjointSetUnion {
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
