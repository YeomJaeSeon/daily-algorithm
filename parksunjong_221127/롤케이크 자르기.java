import java.util.HashMap;
import java.util.Map;

class Solution {
    public int solution(int[] topping) {
        int answer = 0;

        // 토핑의 길이가 1일 경우 공평하게 자르지 못함
        if(topping.length == 1) {
            return 0;
        }

        for(int i = 0; i < topping.length; i++) {
            Map<Integer, Integer> oldTopping = new HashMap<Integer, Integer>();
            Map<Integer, Integer> youngTopping = new HashMap<Integer, Integer>();
            for(int j = 0; j < topping.length; j++) {
                if(j <= i) {
                    oldTopping.put(topping[j], topping[j]);
                } else {
                    youngTopping.put(topping[j], topping[j]);
                }
            }
            if(oldTopping.size() == youngTopping.size()) {
                answer++;
            }
        }

        return answer;
    }

  
  // 2개맞고 나머지 시간 초과
