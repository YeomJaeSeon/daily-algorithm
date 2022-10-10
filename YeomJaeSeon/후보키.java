package problems1;


import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class 후보키 {
    // "12", "13", "4", 이런식으로 구성되어있음
    static Set<String> candidateKeys = new HashSet<>();
    static int columnCnt;
    static int[] result;
    static String[][] relations;

    public static void main(String[] args) {
        후보키 instance = new 후보키();
        System.out.println(instance.solution(
                new String[][]{ {"a","1","aaa","c","ng"},
                        {"a","1","bbb","e","g"},
                        {"c","1","aaa","d","ng"},
                        {"d","2","bbb","d","ng"}}));
    }

    public int solution(String[][] relation) {
        relations = relation;
        columnCnt = relation[0].length;

        // 전부 조사
        for(int i = 1; i <= columnCnt; i++){
            result = new int[i];
            recursive(i, 0, 0);
        }

        return candidateKeys.size();
    }
    private void recursive(int m, int n, int next){
        if(m == n){
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < m; i++){
                sb.append(result[i]);
            }
            if(isCandidateKey(sb.toString())){
                candidateKeys.add(sb.toString());
            }

            return;
        }
        for(int i = next; i < columnCnt; i++){
            result[n] = i;
            recursive(m, n + 1, i + 1);
        }

    }
    private boolean isCandidateKey(String columns){
        int[] tuple = Arrays.stream(columns.split("")).mapToInt(Integer::new).toArray();

        if(isUnique(tuple)){
            if(isMinimality(tuple)){
                return true;
            }
        }
        return false;
    }
    private boolean isUnique(int[] tuple){
        Set<String> subSet = new HashSet<>();
        for (String[] relation : relations) {
            StringBuilder sb = new StringBuilder();
            for (int i : tuple) {
                sb.append(relation[i]);
            }
            subSet.add(sb.toString());
        }
        if(subSet.size() == relations.length){
            // 유일성
            return true;
        }
        return false;
    }

    // 최소성 만족 여부
    private boolean isMinimality(int[] tuple){
        StringBuilder sb = new StringBuilder();
        for (int i : tuple) {
            sb.append(i);
        }
        String targetStr = sb.toString();
        // 012 (삭제 X) - targetStr
        // 023 (삭제) - targetStr

        for (String candidateKey : candidateKeys) {
            //02 - candidateKey
            if(isContainStr(targetStr, candidateKey)){
                // 문자열 포함하면 최소성 만족 X
                return false;
            }
        }
        return true;
    }
    private boolean isContainStr(String targetStr, String candidateKey){
        int candidateKeyPt = 0;
        for(int i = 0; i < targetStr.length(); i++){
            char targetChar = targetStr.charAt(i);
            if(targetChar == candidateKey.charAt(candidateKeyPt)){
                candidateKeyPt++;
                if(candidateKeyPt == candidateKey.length()){
                    return true;
                }
            }
        }

        return false;
    }
}

/**
 * 후보키: 아래 두 특징을 만족
 * 1. 유일성: 모든 튜플에 대해 유일하게 식별되어야함
 * 2. 최소성: 유일성을 만족하는 튜플이 최소함이어야함.
 *      - 문제에서 [이름, 전공]이 유일성, 최소성 만족하기에 후보키인데 [이름, 전공, 학년]은 후보키가 될수 없는 이유는 '학년'이 빠져도 유일성을 만족하기 때문에 최소성 만족하지 못하여 후보키 아니다.
 */

/**
 * 모든 칼럼 튜플 조합에 대한 전수조사를 해야함 - 재귀함수이용
 * String.contains를 이용하면 완벽히 최소성 검사를 할수 없음
 * 02 에 대하여 023은 최소성 검사 가능한데 012는 최소성 검사 불가능. -> 따로 로직 설계해야함
 */