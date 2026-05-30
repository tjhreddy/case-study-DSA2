import java.util.*;

public class CO3_TataSteel_MaxFlow {

    static final int V = 6;

    boolean bfs(int[][] rGraph, int s, int t, int[] parent) {

        boolean[] visited = new boolean[V];
        Queue<Integer> q = new LinkedList<>();

        q.add(s);
        visited[s] = true;
        parent[s] = -1;

        while (!q.isEmpty()) {

            int u = q.poll();

            for (int v = 0; v < V; v++) {

                if (!visited[v] && rGraph[u][v] > 0) {
                    q.add(v);
                    parent[v] = u;
                    visited[v] = true;
                }
            }
        }
        return visited[t];
    }

    int edmondsKarp(int[][] graph, int s, int t) {

        int[][] rGraph = new int[V][V];

        for (int i = 0; i < V; i++)
            for (int j = 0; j < V; j++)
                rGraph[i][j] = graph[i][j];

        int[] parent = new int[V];
        int maxFlow = 0;

        while (bfs(rGraph, s, t, parent)) {

            int pathFlow = Integer.MAX_VALUE;

            for (int v = t; v != s; v = parent[v]) {
                int u = parent[v];
                pathFlow = Math.min(pathFlow, rGraph[u][v]);
            }

            for (int v = t; v != s; v = parent[v]) {
                int u = parent[v];
                rGraph[u][v] -= pathFlow;
                rGraph[v][u] += pathFlow;
            }

            maxFlow += pathFlow;
        }

        return maxFlow;
    }

    public static void main(String[] args) {

        int[][] graph = {
            {0,10,10,0,0,0},
            {0,0,0,4,8,0},
            {0,0,0,9,0,0},
            {0,0,0,0,6,10},
            {0,0,0,0,0,10},
            {0,0,0,0,0,0}
        };

        CO3_TataSteel_MaxFlow m = new CO3_TataSteel_MaxFlow();

        System.out.println("Maximum Flow = "
                + m.edmondsKarp(graph,0,5)
                + " KL/hour");
    }
}
