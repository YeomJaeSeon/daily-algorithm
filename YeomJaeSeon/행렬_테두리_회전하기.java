package problems1;

import java.util.*;

public class 행렬_테두리_회전하기 {
    static int[][] board;

    public static void main(String[] args) {
        행렬_테두리_회전하기 instance = new 행렬_테두리_회전하기();
        System.out.println(Arrays.toString(instance.solution(6, 6, new int[][]{{2, 2, 5, 4}, {3, 3, 6, 6}, {5, 1, 6, 3}})));
    }
    public int[] solution(int rows, int columns, int[][] queries) {
        List<Integer> list = new ArrayList<>();

        initBoard(rows, columns);

        for (int[] query : queries) {
            list.add(rotate(query));
        }

        return Arrays.stream(list.toArray(Integer[]::new)).mapToInt(i->i).toArray();
    }

    private int rotate(int[] query) {
        int x1 = query[0] - 1;
        int y1 = query[1] - 1;
        int x2 = query[2] - 1;
        int y2 = query[3] - 1;

        Map<Point, Integer> map = new HashMap<>();

        //가로
        for(int i = y1; i <= y2; i++){
            // 위
            map.put(new Point(x1, i), board[x1][i]);
            // 아래
            map.put(new Point(x2, i), board[x2][i]);
        }

        //세로
        for(int i = x1 + 1; i < x2; i++){
            //왼
            map.put(new Point(i, y1), board[i][y1]);
            //우
            map.put(new Point(i, y2), board[i][y2]);
        }

        // rotate
        //x1 y1        x1 y2
        //x2 y1         x2 y2

        //위
        for(int i = y1 + 1; i <= y2; i++){
            board[x1][i] = map.get(new Point(x1, i - 1));
        }
        //오른
        for(int i = x1 + 1; i <= x2; i++){
            board[i][y2] = map.get(new Point(i - 1, y2));
        }
        //아래
        for(int i = y2 - 1; i >= y1; i--){
            board[x2][i] = map.get(new Point(x2, i + 1));
        }
        //왼
        for(int i = x2 - 1; i >= x1; i--){
            board[i][y1] = map.get(new Point(i + 1, y1));
        }

        // get min
        return map.values().stream().mapToInt(i -> i).min().orElseThrow();
    }


    public void initBoard(int rows, int columns){
        board = new int[rows][columns];

        int v = 1;
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                board[i][j] = v++;
            }
        }
    }

    public void displayBoard(){
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                System.out.print(board[i][j] + "  ");
            }
            System.out.println();
        }
    }
}
class Point{
    int x;
    int y;

    Point(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x && y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
