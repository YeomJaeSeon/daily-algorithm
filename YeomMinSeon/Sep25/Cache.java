import java.util.*;

    public class Cache {
        public int solution(int cacheSize, String[] cities) {
            int answer = 0;
            // LRU

            // 사용한지 가장 오래된 페이지를 대체
            LinkedList<String> list = new LinkedList<String>();
            if(cacheSize == 0){
                return 5*cities.length;
            }
            for(String city : cities){
                city = city.toUpperCase();

                if(list.contains(city)){
                    answer += 1;
                    list.remove(list.indexOf(city));
                    list.offer(city);
                } else {
                    if(list.size() == cacheSize){
                        list.poll();
                    }
                    answer += 5;
                    list.offer(city);
                }
            }


            return answer;
        }
    }


