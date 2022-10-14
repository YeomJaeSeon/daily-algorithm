import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class MinRoute {
    static class Node implements Comparable<Node> {
        int to, weight;
        public Node(int to, int weight) {
            this.to = to;ㅛ
            this.weight = weight;
        }
        @Override
        public int compareTo(Node o) {
            return this.weight-o.weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        StringBuilder sb = new StringBuilder();

        int V = Integer.parseInt(st.nextToken()); // 정점의 개수
        int E = Integer.parseInt(st.nextToken()); // 간선의 개수
        int start = Integer.parseInt(in.readLine()); // 시작정점의 번호

        // 각 간선에 대한 정보
        PriorityQueue<Node>[] adj = new PriorityQueue[V+1];
        for (int i = 1; i <= V; i++) {
            adj[i] = new PriorityQueue<Node>();
        }
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(in.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            adj[u].add(new Node(v, w));
        }

        // 시작점에서 다른 정점으로의 최단 경로 구하기
        int[] distance = new int[V+1];
        Arrays.fill(distance, -1);
        distance[start] = 0; // 시작점 0 표시

        // 방문할 수 있는 곳 모두 가보기
        while (!adj[start].isEmpty()) {
            Node tmp = adj[start].poll();
            int to = tmp.to;
            int weight = tmp.weight;
            // 이미 방문한 곳은 지나침
            if (distance[to] != -1) continue;
            // 최단 경로 갱신 및 경유 했을 때 가중치 저장
            distance[to] = weight;
            for (Node n : adj[to]) {
                adj[start].add(new Node(n.to, n.weight+weight));
            }
        }
        for (int i = 1; i <= V; i++) {
            sb.append(distance[i] == -1 ? "INF" : distance[i]).append("\n");
        }
        System.out.println(sb.toString());
    }
}
