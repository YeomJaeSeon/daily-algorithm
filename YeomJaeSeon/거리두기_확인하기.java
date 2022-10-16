package problems1;

import java.util.*;

public class 거리두기_확인하기 {
    static char[][] board;
    static boolean[][] visited;

    static int[] xDir = new int[]{-1, 1, 0, 0};
    static int[] yDir = new int[]{0, 0, -1, 1};


    public static void main(String[] args) {
        거리두기_확인하기 instance = new 거리두기_확인하기();
        System.out.println(Arrays.toString(instance.solution(new String[][]{{"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"}, {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"}, {"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"}, {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"}, {"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}})));
    }
    // places.length -> 5
    // places[0].length -> 5
    public int[] solution(String[][] places) {
        List<Integer> listAnswer = new ArrayList<>();
        for (String[] place : places) {
            //board setting
            board = new char[5][5];
            for(int i = 0; i < 5; i++){
                String pos = place[i];
                for(int j = 0; j < 5; j++){
                    board[i][j] = pos.charAt(j);
                }
            }

            int isOk = 1;

            out:
            for(int i = 0; i < 5; i++){
                for(int j = 0; j < 5; j++){
                    if(board[i][j] == 'P'){
                        // visited 초기화
                        visited = new boolean[5][5];
                        // start graph 탐색


                        if(bfs(new int[]{i, j}) == 0){
                            isOk = 0;
                            break out;
                        }
                    }
                }
            }
            listAnswer.add(isOk);
        }

        return Arrays.stream(listAnswer.toArray(Integer[]::new)).mapToInt(Integer::intValue).toArray();
    }

    // return 0(거리두기 X) or 1(거리두기 O)
    private int bfs(int[] start){
        Queue<int[]> q = new LinkedList<>();

        q.offer(start);
        // 방문처리
        int startX = start[0];
        int startY = start[1];
        visited[startX][startY] = true;


        while (!q.isEmpty()){
            int[] curr = q.poll();
            int currX = curr[0];
            int currY = curr[1];


            for(int i = 0; i < 4; i++){
                int nextX = xDir[i] + currX;
                int nextY = yDir[i] + currY;

                // out of bound index
                if(nextX < 0 || nextX >= 5 || nextY < 0 || nextY >= 5){
                    continue;
                }

                if(visited[nextX][nextY]){
                    continue;
                }
                if(board[nextX][nextY] == 'X'){
                    continue;
                }

                if(board[nextX][nextY] == 'P' && !isDistanceGood(startX, startY, nextX, nextY) && getManhattan(startX, startY, nextX, nextY) <= 2){
                    // 맨허튼 거리가 2이하면 안돼!
                    return 0;
                }

                // 방문처리
                visited[nextX][nextY] = true;
                q.offer(new int[]{nextX, nextY});
            }
        }

        return 1;
    }
    private boolean isDistanceGood(int startX, int startY, int targetX, int targetY){
        // 세로
        if(startY == targetY && Math.abs(startX - targetX) == 2){
            if(board[(startX + targetX) / 2][startY] == 'X'){
                return true;
            }
        }

        // 가로
        if(startX == targetX && Math.abs(startY - targetY) == 2){
            if(board[startX][(startY + targetY) / 2] == 'X'){
                return true;
            }
        }

        if(Math.abs(startX - targetX) == 1 && Math.abs(startY - targetY) == 1){
            if(board[startX][targetY] == 'X' && board[targetX][startY] == 'X'){
                return true;
            }
        }

        return false;
    }
    private int getManhattan(int startX, int startY, int targetX, int targetY){
        return Math.abs(startX - targetX) + Math.abs(startY - targetY);
    }
}
