import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class Main {

	public static void main(String[] args) {
		
		Solution sol = new Solution();
		int[] fees = {180, 5000, 10, 600};
		String[] records = {"05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"};
		
		int[] answer = sol.solution(fees, records);
		for(int i = 0; i < answer.length; i++) {
			System.out.println(answer[i]);
		}
	}

}

class Solution {
	// 23:59분을 분으로 변경
	final static int LASTTIME = 1439;
    public int[] solution(int[] fees, String[] records) {
        int[] answer = {};
        
        // 차량 스케쥴을 저장하고 key값 정렬을 위해 TreeMap 
        Comparator<String> comparator = (s1, s2) -> s1.compareTo(s2);
        Map<String, String> carMap = new TreeMap<>(comparator);
        
        // 기본 시간(분)
        int defaultTime = fees[0];
        // 기본 요금(원)
        int defaultFee = fees[1];
        // 단위 시간(분)
        int usingTime = fees[2];
        // 단위 요금(원)
        int usingFee = fees[3];
        
        // KEY : 자동차 번호... VALUE : 시간
        for(int i = 0; i < records.length; i++) {
        	String time = records[i].split(" ")[0];
        	String carNumber = records[i].split(" ")[1];
        	// 자동차 번호 키가 없을 경우 새로 등록
        	if(!carMap.containsKey(carNumber)) {
        		carMap.put(carNumber, time);
        	// 자동차 번호 키가 있을 경우 기존 값 업데이트
        	} else {
        		carMap.put(carNumber, carMap.get(carNumber) + "/" + time);
        	}	
        }
        
        // return 값에 carMap size 만큼 크기 선언
        answer = new int[carMap.size()];
        int count = 0;
        
        Iterator<Map.Entry<String, String>> itr = carMap.entrySet().iterator();
        // 순회해서 없을때까지 반복
        while(itr.hasNext()) {
        	Map.Entry<String, String> entry = itr.next();
        	int sum = 0;
        	// value의 길이가 짝수인 경우 == in, out 시간이 다 등록되어있음
        	if(entry.getValue().split("/").length % 2 == 0) {
        		String[] time = entry.getValue().split("/");
        		for(int i = 0; i < time.length; i+=2) {
        			int inTime = (Integer.parseInt(time[i].split(":")[0]) * 60) + Integer.parseInt(time[i].split(":")[1]);
        			int outTime = (Integer.parseInt(time[i+1].split(":")[0]) * 60) + Integer.parseInt(time[i+1].split(":")[1]);
        			sum += outTime - inTime;
        		}
        	// value의 길이가 홀수인 경우 == 마지막 출차 시간이 없음
        	} else {
        		String[] time = entry.getValue().split("/");
        		for(int i = 0; i < time.length-1; i+=2) {
        			int inTime = (Integer.parseInt(time[i].split(":")[0]) * 60) + Integer.parseInt(time[i].split(":")[1]);
        			int outTime = (Integer.parseInt(time[i+1].split(":")[0]) * 60) + Integer.parseInt(time[i+1].split(":")[1]);
        			sum += outTime - inTime;
        		}
        		sum += LASTTIME - ((Integer.parseInt(time[time.length-1].split(":")[0]) * 60) + Integer.parseInt(time[time.length-1].split(":")[1]));
        	}
        	
        	// return 값에 요금 등록
        	if(sum <= defaultTime) {
        		answer[count] = defaultFee;
        	} else {
        		if((sum - defaultTime) % usingTime == 0) {
        			answer[count] = defaultFee + ((sum-defaultTime) / usingTime) * usingFee;
        		} else {
        			answer[count] = defaultFee + ((sum-defaultTime) / usingTime + 1) * usingFee;
        		}
        	}
        	count++;
        }
        
        return answer;
    }
}
