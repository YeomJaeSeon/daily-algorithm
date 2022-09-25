class Solution {
    public int solution(int cacheSize, String[] cities) {
    	// 걸리는 전체 시간 선언
        int time = 0;
        // 큐 선언이유 (오래된 도시가 있으면 먼저 빠져야 하기에 큐)
        Queue<String> queue = new LinkedList<String>();
        
        // cities 배열만큼 반복문 실행
        for(int i = 0; i < cities.length; i++) {
        	// 대-소문자 구분을 없애기 위해 소문자로 통일
        	String city = cities[i].toLowerCase();
        	// 큐에 city가 없다면
        	if(!queue.contains(city)) {
        		// 큐에 city를 넣어주고
        		queue.offer(city);
        		// 시간을 5초 더해줌
        		time += 5;
        		// 큐의 사이즈가 캐시의 사이즈보다 커졌다면
        		if(queue.size() > cacheSize) {
        			// 가장 오랫동안 사용안된 데이터 제거
        			queue.poll();
        		}
        	// 큐에 city가 있다면 해당 city가 최근에 사용됐으므로 가장 뒤로 보내줘야함
        	} else {
        		// queue에서 도시를 삭제해주고
        		queue.remove(city);
        		// queue에 도시를 다시 추가해줌으로써 가장 뒤에 보내줌
        		queue.offer(city);
        		// 시간을 1초 더해줌
        		time += 1;
        	}
        	
        }
        // 결과값 반환
        return time;
    }
}
