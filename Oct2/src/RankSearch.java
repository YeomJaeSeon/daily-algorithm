import java.util.*;


public class RankSearch {
    public int[] solution(String[] info, String[] query) {
        Map<String, List<Integer>> infos = new HashMap<>();
        for (String in : info) {
            String[] split = in.split(" ");
            int score = Integer.parseInt(split[4]);

            for (int i = 0; i < (1 << 4); i++) {
                StringBuilder key = new StringBuilder();
                for (int j = 0; j < 4; j++) {
                    if ((i & (1 << j)) > 0) key.append(split[j]);
                }
                infos.computeIfAbsent(key.toString(), s -> new ArrayList<>()).add(score);
            }
        }

        List<Integer> empty = new ArrayList<>();
        for (Map.Entry<String, List<Integer>> entry : infos.entrySet())
            entry.getValue().sort(null);

        int[] answer = new int[query.length];
        for (int i = 0; i < query.length; i++) {
            String[] split = query[i].replaceAll("-", "").split(" and | ");
            String key = String.join("", split[0], split[1], split[2], split[3]);
            int score = Integer.parseInt(split[4]);

            List<Integer> list = infos.getOrDefault(key, empty);

            int s = 0, e = list.size();

            while (s < e) {
                int mid = (s + e) / 2;

                if (list.get(mid) < score) s = mid + 1;
                else e = mid;
            }

            answer[i] = list.size() - s;
        }

        return answer;
    }
}