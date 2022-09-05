import java.util.HashMap;
import java.util.Map;

public class Main_042888 {
	public static void main(String args[]) {
		
		Solution sol = new Solution();
		String[] record = {"Enter uid1234 Muzi", "Enter uid4567 Prodo", "Leave uid1234","Enter uid1234 Prodo", "Change uid4567 Ryan"};
		String[] answer = sol.solution(record);
		
		for(int i = 0; i < answer.length; i++) {
			System.out.println(answer[i]);
		}
	}
}

class Solution {
    public String[] solution(String[] record) {
        String[] answer = {};
        
        // enter, leave 의 개수를 더해줄 int형 변수 선언
        int count = 0;
        // key:uid value:name을 담아줄 map 선언
        Map<String, String> uidMap = new HashMap<String, String>();
        
        // record의 길이만큼 반복문 실행
        for(int i = 0; i < record.length; i++) {
        	// " "로 잘라서 info에 넣어줌 [0] = {Enter,Leave,Change}, [1] = uid, [2] = name
        	String[] info= record[i].split(" ");
        	// Enter인 경우 Map에 담고 카운트 증가
        	if("Enter".equals(info[0])) {
        		uidMap.put(info[1], info[2]);
        		count += 1;
        	// Change인 경우 Map에만 담음
        	} else if("Change".equals(info[0])) {
        		uidMap.put(info[1], info[2]);
        	// Leave인 경우 카운트만 증가
        	} else {
        		count += 1;
        	}
        }
        
        // 증가된 카운트만큼 answer 길이 선언
        answer = new String[count];
        // 인덱스에 빠르게 넣기 위해 선언
        int idx = 0;
        
        // record의 길이만큼 반복문 실행
        for(int i = 0; i < record.length; i++) {
        	// " "로 잘라서 info에 넣어줌
        	String[] info= record[i].split(" ");
        	// name은 uidMap에서 uid를 key로 하는 value값을 가져옴
        	String name = uidMap.get(info[1]);
        	// Enter인 경우 출력
        	if("Enter".equals(info[0])) {
        		answer[idx++] = name + "님이 들어왔습니다.";
        	// Leave인 경우 출력
        	} else if("Leave".equals(info[0])) {
        		answer[idx++] = name + "님이 나갔습니다.";
        	}
        }
        
        return answer;
    }
}
