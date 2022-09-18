import java.util.LinkedList;
import java.util.Queue;

public class Main {
	public static void main(String args[]) {
		
		Solution sol = new Solution();
		int[] queue1 = {3, 2, 7, 2};
		int[] queue2 = {4, 6, 5, 1};
		int answer = sol.solution(queue1, queue2);
		
		System.out.println(answer);
	}
}

class Solution {
    public int solution(int[] queue1, int[] queue2) {
    	// queue1, queue2 배열을 담을 QUEUE 선언
    	Queue<Integer> que1 = new LinkedList<Integer>();
    	Queue<Integer> que2 = new LinkedList<Integer>();
    	
    	// 각각 queue의 값을 더해줄 long 변수 선언
    	long sum1 = 0;
    	long sum2 = 0;
    	
    	// queue1 배열을 돌면서 합계를 구하고 queue1에 담기
    	for(int i = 0; i < queue1.length; i++) {
    		sum1 += queue1[i];
    		que1.add(queue1[i]);
    	}
    	// queue2 배열을 돌면서 합계를 구하고 queue2에 담기
    	for(int i = 0; i < queue2.length; i++) {
    		sum2 += queue2[i];
    		que2.add(queue2[i]);
    	}
    	
    	// 횟수를 구할 count 변수 선언
    	int count = 0;
    	// sum1 == sum2가 될때까지 반복
    	while(sum1 != sum2) {
    		// 횟수 증가
    		count++;
    		
    		// sum1이 작은 경우 => queue2에서 하나 빼서 queue1에 넣어줌
    		if(sum1 < sum2) {
    			int value = que2.poll();
    			sum1 += value;
    			sum2 -= value;
    			que1.offer(value);
    		// sum2가 작은 경우 => queue1에서 하나 빼서 queue2에 넣어줌
    		} else {
    			int value = que1.poll();
    			sum1 -= value;
    			sum2 += value;
    			que2.offer(value);
    		}
    		
    		// 전체 코드를 싹 바꿔봐도 같아지지 않을 때 -1 return
    		if (count > (queue1.length + queue2.length) * 2) {
    			return -1;
    		}
    	}
    	
        return count;
    }
}
