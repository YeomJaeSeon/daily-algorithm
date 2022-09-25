package JoyStick.algoday1;
import java.util.*;

public class EliminationbyGrouping {

        public int solution(String s) {
            Stack<String> stack = new Stack<>();

            for(int i=0; i<s.length(); i++){
                if(stack.isEmpty()){
                    stack.push(String.valueOf(s.charAt(i)));
                }else{
                    String lastVal = stack.peek();
                    String currVal = String.valueOf(s.charAt(i));
                    if(!lastVal.equals(currVal)){
                        stack.push(currVal);
                    }else{
                        stack.pop();
                    }
                }
            }

            return stack.size() == 0 ? 1 : 0;
        }
    }


