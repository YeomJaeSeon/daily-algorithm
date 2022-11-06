import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

class Solution {
    public int solution(int distance, int[][] scope, int[][] times) {
        ArrayList<Integer> answer = new ArrayList<Integer>();
        
        for(int i = 0; i < scope.length; i++) {
        	int allTimes = times[i][0] + times[i][1];
        	
        	Arrays.sort(scope[i]);
        	
        	for(int j = scope[i][0]; j <= scope[i][1]; j++) {
        		if(j%allTimes == 0) {
        			continue;
        		} else if(j%allTimes <= times[i][0]) {
        			answer.add(j);
        		}
        	}
        }
        Collections.sort(answer);
        
        if(answer.size() > 0) {
        	return answer.get(0);
        }
        
        return distance;
    }
}
