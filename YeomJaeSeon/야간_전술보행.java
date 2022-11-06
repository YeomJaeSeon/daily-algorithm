package problems1;

import java.util.Arrays;

public class 야간_전술보행 {
    public static void main(String[] args) {
        야간_전술보행 instance = new 야간_전술보행();
        System.out.println(instance.solution(12, new int[][]{{7, 8}, {6, 4}, {11, 10}} , new int[][]{{2, 2}, {2, 4}, {3, 3}}));

    }

    public int solution(int distance, int[][] scope, int[][] times) {
        int answer = 0;

        for (int[] ints : scope) {
            Arrays.sort(ints);
        }

        boolean isCapture = false;

        out:
        for(int i = 0; i < scope.length; i++){
            int[] ints = scope[i];
            int a = ints[0];
            int b = ints[1];

            int[] time = times[i];
            int workTime = time[0];
            int breakTime = time[1];


            for(int j = a; j <= b; j++){
                int mod = j % (workTime + breakTime);
                if(mod == 0){
                    continue ;
                }

                if(mod <= workTime){
                    //잡히면
                    answer = j;
                    isCapture = true;
                    break out;
                }
            }
        }

        if(!isCapture){
            return distance;
        }

        return answer;
    }
}
