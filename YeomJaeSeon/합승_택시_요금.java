package problems1;

import java.util.*;

public class 합승_택시_요금 {
    static List<List<int[]>> feeInfo = new ArrayList<>();

    public static void main(String[] args) {
        합승_택시_요금 instance = new 합승_택시_요금();
        System.out.println(instance.solution(6, 4, 6, 2, new int[][]{{4, 1, 10}, {3, 5, 24}, {5, 6, 2}, {3, 1, 41}, {5, 1, 24}, {4, 6, 50}, {2, 4, 66}, {2, 3, 22}, {1, 6, 25}}));
    }

    /**
     *
     * @param n n은 지점 개수
     * @param s 출발 지점 번호
     * @param a A의 도착지점 번호
     * @param b B의 도착지점 번호
     * @param fares 요금정보 2차원 정수 배열
     * @return 합승을 하던 합승하지 않던 최소 요금
     */
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = 0;
        for(int i = 0; i <= n; i++){
            feeInfo.add(new ArrayList<>());
        }
        for (int[] fare : fares) {
            feeInfo.get(fare[0]).add(new int[]{fare[1], fare[2]});
            feeInfo.get(fare[1]).add(new int[]{fare[0], fare[2]});
        }


        // fee info print
        for (List<int[]> ints : feeInfo) {
            for (int[] anInt : ints) {
                System.out.print(Arrays.toString(anInt));
            }
            System.out.println();
        }


        return answer;
    }
    private void bfs(int[] start){
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(start);
    }
}
