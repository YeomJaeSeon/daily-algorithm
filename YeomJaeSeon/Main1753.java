package re_study_list;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Node implements Comparable<Node>{
    private int index;
    private int distance;

    public int getDistance(){
        return distance;
    }

    public int getIndex(){
        return index;
    }

    public Node(int index, int distance){
        this.index = index;
        this.distance = distance;
    }

    // Node의 정렬 기준 정하는 메서드
    // distance 오름차순 정렬
    @Override
    public int compareTo(Node o) {
        return distance - o.distance;
    }
}

public class Main1753 {
    static int V, E;
    static int start;
    static int[] d; // start에서 특정 idx까지의 가중치 저장할 배열
    static boolean[] visited;
    static List<List<Node>> graph = new ArrayList<>();
    static final int INF = (int)1e9;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        start = Integer.parseInt(br.readLine());

        d = new int[V + 1];
        visited = new boolean[V + 1];

        for(int i = 0; i < V + 1; i++){
            graph.add(new ArrayList<>());
        }

        for(int i = 0; i < E; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph.get(a).add(new Node(b, c));
        }

        Arrays.fill(d, INF);

        dijkstra(start);

        for(int i = 1; i <= V; i++){
            if(d[i] == INF){
                System.out.println("INF");
            }else{
                System.out.println(d[i]);
            }
    }
}

    private static void dijkstra(int start) {
        d[start] = 0; //자기 자신 0
        PriorityQueue<Node> pq = new PriorityQueue<>(); //현재 노드에서 가장 가까운걸 꺼냄

        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()){
            Node poll = pq.poll();
            int now = poll.getIndex();
            int dist = poll.getDistance();

            if(visited[now]){
                continue;
            }
            visited[now] = true;

            for (Node node : graph.get(now)) {
                int cost = dist + node.getDistance();
                if(cost < d[node.getIndex()]){
                    d[node.getIndex()] = cost;
                    pq.offer(new Node(node.getIndex(), cost));
                }
            }
        }
    }
}
