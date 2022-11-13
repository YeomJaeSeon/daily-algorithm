package problems1;

import java.util.LinkedList;
import java.util.Queue;

public class 아이템_줍기 {
    static int[][] board = new int[101][101];
    static boolean[][] visited = new boolean[101][101];
    static int[][] writeBoard = new int[101][101];
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) {
        아이템_줍기 instance = new 아이템_줍기();

        System.out.println(instance.solution(
                new int[][]{{1,1,7,4},{3,2,5,5},{4,3,6,9},{2,6,8,8}},
                1,
                3,
                7,
                8
        ));
    }

    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        for (int[] rec : rectangle) {
            int x1 = rec[0] * 2;
            int y1 = rec[1] * 2;
            int x2 = rec[2] * 2;
            int y2 = rec[3] * 2;

            for (int j = x1; j <= x2; j++) {
                for (int k = y1; k <= y2; k++) {
                    board[j][k] = 1;
                }
            }
        }

        characterX *= 2;
        characterY *= 2;
        itemX *= 2;
        itemY *= 2;

        bfs(characterX, characterY);

        return writeBoard[itemX][itemY] / 2;
    }
    private void bfs(int startX, int startY){
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{startX, startY});
        visited[startX][startY] = true;

        while (!q.isEmpty()){
            int[] curr = q.poll();
            int currX = curr[0];
            int currY = curr[1];

            for(int i = 0; i < 4; i++){
                int nextX = dx[i] + currX;
                int nextY = dy[i] + currY;

                if(nextX < 0 || nextX >= 101 || nextY < 0 || nextY >= 101){
                    continue;
                }

                if(board[nextX][nextY] == 0){
                    continue;
                }

                if(!visited[nextX][nextY]){
                    visited[nextX][nextY] = true;
                    q.offer(new int[]{nextX, nextY});
                    writeBoard[nextX][nextY] = writeBoard[currX][currY] + 1;
                }
            }
        }
    }
}
