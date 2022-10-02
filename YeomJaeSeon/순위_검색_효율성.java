package problems1;

import java.util.*;

public class 순위_검색_효율성 {
    static Map<String, List<Integer>> map = new HashMap<>();
    static int[] result = new int[4];

    public static void main(String[] args) {
        순위_검색_효율성 instance = new 순위_검색_효율성();
        System.out.println(Arrays.toString(instance.solution(
                new String[]{"java backend junior pizza 150","python frontend senior chicken 210","python frontend senior chicken 150","cpp backend senior pizza 260","java backend junior chicken 80","python backend senior chicken 50"},
                new String[]{"java and backend and junior and pizza 100","python and frontend and senior and chicken 200","cpp and - and senior and pizza 250","- and backend and senior and - 150","- and - and - and chicken 100","- and - and - and - 150"}
        )));
    }

    public int[] solution(String[] info, String[] query) {
        List<Integer> list = new ArrayList<>();

        // 모든 경우 구하기 Map이용
        for (String s : info) {
            String[] arr = s.split(" ");

            int grade = Integer.parseInt(arr[4]);
            // i : "-"의 개수
            for(int i = 0; i <= 4; i++){
                recursive(0, i, 0, arr, grade);
            }
        }

        // Map value 오름차순 정렬 - BS를 위함
        Set<String> keys = map.keySet();
        for (String key : keys) {
            map.get(key).sort(Comparator.comparingInt(o -> o));
        }

        // query의 key를 이용해 value에 접근하고 BS를 통해 개수 구함.
        for (String q : query) {
            String queryStr = q.replaceAll(" and ", "");
            String[] arr = queryStr.split(" ");
            int grade = Integer.parseInt(arr[1]);

            String key = arr[0];
            if(map.containsKey(key)){
                list.add(binarySearch(key, grade));
            }else{
                list.add(0);
            }
        }

        return Arrays.stream(list.toArray(Integer[]::new)).mapToInt(Integer::intValue).toArray();
    }
    public int binarySearch(String key, int score) {
        List<Integer> list = map.get(key);
        int start = 0, end = list.size() - 1;

        while (start <= end) {
            int mid = (start + end) / 2;
            if (list.get(mid) < score)
                start = mid + 1;
            else
                end = mid - 1;
        }

        return list.size() - start;
    }

    public void recursive(int m, int n, int next, String[] arr, int grade){
        if(n == m){
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < 4; i++){
                if(result[i] == 1){
                    sb.append("-");
                }else{
                    sb.append(arr[i]);
                }
            }
            if(map.containsKey(sb.toString())){
                map.get(sb.toString()).add(grade);
            }else{
                map.put(sb.toString(), new ArrayList<>());
                map.get(sb.toString()).add(grade);
            }
            return;
        }
        for(int i = next; i < 4; i++){
            result[i] = 1;
            recursive(m + 1, n, i + 1, arr, grade);
            result[i] = 0;
        }
    }
}
/**
 * 시간초과
 *  -> BS이용해야함
 *
 *  Map을 이용하여 탐색하려는 key에 바로 접근
 *  재귀함수를 이용하여 sentence만드는 로직
 *  해당 맵의 key가 있는지 확인해야 npe 런타임 에러가 나지 않는다. (이부분이 꽤 애를 먹음)
 */
