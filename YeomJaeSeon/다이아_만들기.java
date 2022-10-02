package problems1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * input: n (3 <= n <= 30, 홀수)
 */
public class 다이아_만들기 {
    static int n;
    static int[][] board;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        if(n % 2 == 0 || n < 3 || n > 30){
            throw new Exception("n은 홀수, 3 <= n <= 30");
        }

        solution(n);

        displayBoard();
    }

    static void solution(int n){
        board = new int[n][n];

        int count = -1;
        boolean isRight = true;
        int middlePosition = n / 2;

        for(int i = 0; i < n; i++){
            // get count
            if(isRight) {
                count += 2;
            }
            else {
                count -= 2;
            }

            if(count > n){
                count -= 4;
                isRight = false;
            }

            // draw
            int drawCount = count;
            board[i][middlePosition] = 1;
            drawCount--;

            int left = middlePosition;
            int right = middlePosition;

            while(drawCount > 0){
                left--;
                right++;

                board[i][left] = 1;
                board[i][right] = 1;

                drawCount -= 2;
            }
        }
    }
    static void displayBoard(){
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}

/**
 * 다이아(마름모) 만들기 풀이
 * - 각 행마다 그려야하는 count를 구한다 (1 -> 3 -> 5 -> 3 -> 1)
 * - 2씩 더해지다가 2씩 빼져야한다.
 * - 기준은 중앙값. n은 항상홀수이기에 n / 2가 중앙값
 * - count를 구하면 그리면 되는데, 중앙값 기준으로 왼쪽 오른쪽을 그린다. (count가 0이 될때까지)
 *
 */