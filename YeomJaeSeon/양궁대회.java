import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 양궁대회 {
    static int[] result;
    static int[] appeachInfo;
    static int[] lionInfo;
    static int max = -1;
    static List<int[]> resultLionInfoList = new ArrayList<>();
    public static void main(String[] args) {
        양궁대회 instance = new 양궁대회();
        System.out.println(Arrays.toString(instance.solution(9, new int[]{0,0,1,2,0,1,1,1,1,1,1})));
    }

    /**
     * 
     * @param n 화살 수
     * @param info 어피치가 쏜 점수들
     * @return 라이언이 가장 큰 점수차로 이길수있는 11길이의 정수형 배열 (단, 점수차가 같을 경우 가장 낮은 점수를 더 많이 맞춘 경우를 return)
     */
    public int[] solution(int n, int[] info) {
        appeachInfo = info;

        result = new int[n];

        recursive(0, n);


        if(resultLionInfoList.size() == 0){
            return new int[]{-1};
        }
        
        return getAnswer();
    }
    int[] getAnswer(){
        if(resultLionInfoList.size() == 1){
            return resultLionInfoList.get(0);
        }

        int[] result = resultLionInfoList.get(0);
        int lastIdx = 0;

        List<Integer> arr = new ArrayList<>();
        for(int i = 10; i >= 0; i--){
            for(int j = 0; j < resultLionInfoList.size(); j++){
                int[] resultCandidate = resultLionInfoList.get(j);
                if(resultCandidate[i] > 0){
                    arr.add(j);
                }
            }

            if(arr.size() > 0){
                lastIdx = i;
                break;
            }
        }

        int maxCount = -1;
        int maxJ = -1;

        for(int i = 0; i < arr.size(); i++){
            int j = arr.get(i);
            int subMaxCount = Math.max(maxCount, resultLionInfoList.get(j)[lastIdx]);
            if(subMaxCount > maxCount){
                maxCount = subMaxCount;
                maxJ = j;
            }
        }

        return resultLionInfoList.get(maxJ);
    }
    void recursive(int m, int n){
        if(m == n){
            lionInfo = new int[11]; 
            for(int i = 0; i < m; i++){
                lionInfo[result[i]]++;
            }
            int lionScore = 0;
            int appeachScore = 0;
            for(int i = 0; i < 11; i++){
                if(lionInfo[i] == 0 && appeachInfo[i] == 0){
                    // 둘다 못맞췄으면 점수 X
                    continue;
                }
                if(appeachInfo[i] < lionInfo[i]){
                    lionScore += (10 - i);
                }else{
                    // 어피치가 더 많이 맞췄거나 같으면
                    appeachScore += (10 - i);
                }
            }
            if(lionScore > appeachScore){
                int scoreDiff = lionScore - appeachScore;

                int subMax = Math.max(max, scoreDiff);
                if(subMax > max){
                    // 기존 max보다 더 크면 max값 초기화 및 resultLionInfoList 초기화
                    max = subMax;
                    resultLionInfoList.clear();
                    resultLionInfoList.add(lionInfo);

                }else{
                    if(max == scoreDiff){
                        // 동일한 max값이면
                        resultLionInfoList.add(lionInfo);
                    }
                }
            }

            return;
        }
        for(int i = (m == 0) ? 0 : result[m - 1]; i < 11; i++){
            result[m] = i;
            recursive(m + 1, n);
        }
    }
}

/*
 * 백트래킹문제임.
 * 
 * 재귀함수로 모든 경우를 다돈다음 라이언이 이기는 경우를 뽑으면됨.
 * 
 * 백준의 N, M문제를 많이 풀어봐야할듯
 */