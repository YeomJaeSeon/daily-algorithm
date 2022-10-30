package problems1;

import java.util.Stack;

public class K진수에서_소수_개수_구하기 {
    public static void main(String[] args) {
        K진수에서_소수_개수_구하기 instance = new K진수에서_소수_개수_구하기();
        System.out.println(instance.solution(437674, 3));
    }
    public int solution(int n, int k) {
        int answer = 0;
        String convertedValue = convertToK(n, k);

        StringBuilder sb = new StringBuilder();
        int start = 0;
        for(int i = 0; i < convertedValue.length(); i++){
            if(convertedValue.charAt(i) == '0'){
                sb = new StringBuilder();
                start = i + 1;
                continue;
            }
            sb.append(convertedValue.charAt(i));

            if(isResultPrimeNumber(convertedValue, sb.toString(), start, i)){
                answer++;
            }
        }

        return answer;
    }
    // int -> String
    private String convertToK(int n, int k){
        Stack<Character> stack = new Stack<>();

        while (n > 0){
            int value = n % k;
            stack.push(String.valueOf(value).charAt(0));
            n /= k;
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()){
            sb.append(stack.pop());
        }
        return sb.toString();
    }
    // check is result prime number
    private boolean isResultPrimeNumber(String totalStr, String subStr, int startIdx, int lastIdx){
        long number = Long.parseLong(subStr);

        // if not prime number
        if(!isPrimeNumber(number)){
            return false;
        }

        //P0 check
        if(startIdx == 0 && lastIdx != totalStr.length() - 1){
            if(totalStr.charAt(lastIdx + 1) == '0'){
                return true;
            }
        }

        //0P check
        if(lastIdx == totalStr.length() - 1 && startIdx != 0){
            if(totalStr.charAt(startIdx - 1) == '0'){
                return true;
            }
        }

        //P check
        if(startIdx == 0 && lastIdx == totalStr.length() - 1){
            return true;
        }

        //0P0 check
        if(startIdx > 0 && lastIdx < totalStr.length() - 1 && totalStr.charAt(startIdx - 1) == '0' && totalStr.charAt(lastIdx + 1) == '0'){
            return true;
        }

        return false;
    }
    // check is normal prime number
    private boolean isPrimeNumber(long number){
        if(number < 2){
            return false;
        }

        for(int i = 2; i <= Math.sqrt(number); i++){
            if(number % i == 0){
                return false;
            }
        }

        return true;
    }
}

/**
 * 걍 풀면됨
 *
 * 1번 테케가 계쏙 시간초과
 * -> isPrimeNumber에서 시간초과가 난것.
 *
 * 소수인지 구하는 범위를 제곱근을 이용하면 해결됨(참고함)
 * 나는 절반값까지 범위로 하였는데 제곱근으로 하여도 소수임을 확신할수있나봄.
 *
 * 정확한 증명은 찾지않음.
 */
