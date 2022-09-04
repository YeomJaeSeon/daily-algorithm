package problems1;

import java.util.*;

public class 메뉴_리뉴얼 {
    static Map<String, Integer> map;
    static int N;
    static String M;
    static int[] result;
    static List<String> answerList = new ArrayList<>();
    public static void main(String[] args) {
        메뉴_리뉴얼 instance = new 메뉴_리뉴얼();
        System.out.println(Arrays.toString(instance.solution(new String[]{"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"}, new int[]{2,3,4})));
    }
    
    public String[] solution(String[] orders, int[] course) {
        for(int num: course){
            map = new HashMap<>();

            for(String order: orders){
                // order sort
                char[] chars = order.toCharArray();
                Arrays.sort(chars);
                M = new String(chars); 
                N = num;

                result = new int[N];

                recursive(0, 0);
            }

            collectMaxOrder();
        }

        Collections.sort(answerList);

        return answerList.toArray(new String[answerList.size()]);
    }
    void recursive(int m, int start){
        if(m == N){
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < m; i++){
                sb.append(M.charAt(result[i]));
            }

            if(map.containsKey(sb.toString())){
                // if exists
                map.put(sb.toString(), map.get(sb.toString()) + 1);
            }else{
                // if not exists
                map.put(sb.toString(), 1);
            }

            return;
        }
        for(int i = start; i < M.length(); i++){
            result[m] = i;
            recursive(m + 1, i + 1);
        }
    }
    // count가 같은 합침 레시피중 가장 인기많은 녀서들 collect
    void collectMaxOrder(){
            int max = 2;
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                String key = entry.getKey();
                int value = entry.getValue();
                max = Math.max(max, value);
            }

            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                String key = entry.getKey();
                int value = entry.getValue();
                if(max == value){
                    answerList.add(key);
                }
            }
    }
}
