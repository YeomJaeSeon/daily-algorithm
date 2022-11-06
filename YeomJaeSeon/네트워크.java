package problems1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class 네트워크 {
    static int[][] info;
    static boolean[] visited;
    public static void main(String[] args) {
        네트워크 instance = new 네트워크();
        System.out.println(instance.solution(3, new int[][]{{1, 1, 0}, {1, 1, 1}, {0, 1, 1}}));

    }
    public int solution(int n, int[][] computers) {
        int answer = 0;
        visited = new boolean[n];
        info = computers;

        for(int i = 0; i < n; i++){
            boolean result = bfs(i, n);
            if(result){
                answer++;
            }
        }

        return answer;
    }
    public boolean bfs(int start, int n){
        if(visited[start]){
            return false;
        }

        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        visited[start] = true;

        while (!q.isEmpty()){
            Integer curr = q.poll();
            int[] currArr = info[curr];

            for(int i = 0; i < n; i++){
                if(i == curr) continue;

                if(currArr[i] == 1 && !visited[i]){
                    visited[i] = true;
                    q.offer(i);
                }
            }

        }

        return true;
    }
}
