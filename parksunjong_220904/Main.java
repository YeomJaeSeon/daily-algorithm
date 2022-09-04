package programmers_012973;

import java.util.Stack;

public class Main {
	public static void main(String args[]) {
		
		Solution sol = new Solution();
		int answer = sol.solution("baabaa");
		
		System.out.println(answer);
		
	}
}

// stack을 이용하기
class Solution
{
    public int solution(String s)
    {
        int answer = -1;
        
        // 처음 받은 문자열을 문자배열로 저장
        char[] charArr = s.toCharArray();
        // 스택을 선언
        Stack<Character> stack = new Stack<Character>();
        
        // 문자열 길이만큼 실행
        for(int i = 0; i < charArr.length; i++) {
        	// 스택의 사이즈가 0이 아니고 stack의 가장 위에 있는 문자가 스택에 들어가려는 문자와 같으면
        	if(stack.size() != 0 && stack.peek() == charArr[i]) {
        		// 맨 위에 문자를 pop(뽑는거) + 들어가려는 문자는 저장 X
        		stack.pop();
        	} else {
        		// 그렇지 않으면 문자열을 저장
        		stack.push(charArr[i]);
        	}
        }
        
        // 스택이 비워져있으면 -1을 리턴
        if (stack.isEmpty()) {
        	answer = 1;
        // 스택에 존재하면 0을 리턴
        } else {
        	answer = 0;
        }
        
        return answer;
    }
}
