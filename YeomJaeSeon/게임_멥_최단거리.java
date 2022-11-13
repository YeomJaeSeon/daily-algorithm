package problems1;

import java.util.LinkedList;
import java.util.Queue;

public class 게임_멥_최단거리 {
    static int n, m;
    static int[][] board;
    static int[][] writeBoard;
    static boolean[][] visited;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) {
        게임_멥_최단거리 instance = new 게임_멥_최단거리();
        System.out.println(instance.solution(new int[][]{{1,0,1,1,1},{1,0,1,0,1},{1,0,1,1,1},{1,1,1,0,1},{0,0,0,0,1}}));

        System.out.println(instance.solution(new int[][]{{1}, {1}})); // 2 x 1

    }

    public int solution(int[][] maps) {
        board = maps;
        n = maps.length;
        m = maps[0].length;

        writeBoard = new int[n][m];
        visited = new boolean[n][m];

        bfs();

        return writeBoard[n - 1][m - 1] == 0 ? -1 : writeBoard[n - 1][m - 1];
    }
    private void bfs(){
        int[] start = new int[]{0, 0};

        Queue<int[]> q = new LinkedList<>();
        q.offer(start);
        visited[0][0] = true;

        writeBoard[0][0] = 1; // 시작은 1

        while (!q.isEmpty()){
            int[] curr = q.poll();
            int currX = curr[0];
            int currY = curr[1];

            for(int i = 0; i < 4; i++){
                int nextX = currX + dx[i];
                int nextY = currY + dy[i];

                if(nextX < 0 || nextX >= n || nextY < 0 || nextY >= m){
                    continue;
                }
                if(board[nextX][nextY] == 0){
                    continue;
                }

                if(!visited[nextX][nextY]){
                    visited[nextX][nextY] = true;
                    writeBoard[nextX][nextY] = writeBoard[currX][currY] + 1;
                    q.offer(new int[]{nextX, nextY});
                }
            }
        }
    }
}
