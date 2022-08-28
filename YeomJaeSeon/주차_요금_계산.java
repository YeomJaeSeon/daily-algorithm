package problems1;

import java.util.*;

public class 주차_요금_계산 {
    static int basicTime;
    static int basicFee;
    static int perTime;
    static int perFee;
    static Map<Integer, Record> map = new HashMap<>();

    public static void main(String[] args) {
        주차_요금_계산 instance = new 주차_요금_계산();
        System.out.println(Arrays.toString(instance.solution(
                new int[]{180, 5000, 10, 600},
                new String[]{"05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"}
        )));
    }

    public int[] solution(int[] fees, String[] records) {
        basicTime = fees[0];
        basicFee = fees[1];
        perTime = fees[2];
        perFee = fees[3];

        // cars setting
        for (String record : records) {
            StringTokenizer st = new StringTokenizer(record, " ");
            String time = st.nextToken();
            int carNumber = Integer.parseInt(st.nextToken());
            String inAndOutType = st.nextToken();

            if(!map.containsKey(carNumber)){
                Record newRecord = new Record();
                if(inAndOutType.equals("IN")){
                    newRecord.in.add(time);
                }else{
                    newRecord.out.add(time);
                }

                map.put(carNumber, newRecord);
            }else{
                Record foundRecord = map.get(carNumber);

                if(inAndOutType.equals("IN")){
                    foundRecord.in.add(time);
                }else{
                    foundRecord.out.add(time);
                }
            }
        }

        calculateTotalFee();

        ArrayList<Integer> keyList = new ArrayList<>(map.keySet());
        keyList.sort(Comparator.naturalOrder());

        List<Integer> result = new ArrayList<>();
        for (Integer carNumber : keyList) {
            result.add(map.get(carNumber).totalFee);
        }

        return result.stream().mapToInt(i -> i).toArray();
    }

    private void calculateTotalFee() {
        Iterator<Map.Entry<Integer, Record>> iterator = map.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry<Integer, Record> next = iterator.next();
            Integer carNumber = next.getKey();
            Record record = next.getValue();

            // 1. record totalTime 계산
            int totalTime = calculateTotalTime(record.in, record.out);

            System.out.println("totalTime = " + totalTime);

            // 2. record totalFee 계산
            if(totalTime <= basicTime){
                record.totalFee = basicFee;
            }else{
                record.totalFee = basicFee + (int)Math.ceil((totalTime - (double)basicTime) / perTime) * perFee;
            }
        }
    }

    private int calculateTotalTime(List<String> in, List<String> out) {
        int totalTime = 0;

        // 마지막 요소에는 접근 X
        for(int i = 0; i < in.size() - 1; i++){
            String vIn = in.get(i);
            String vOut = out.get(i);

            StringTokenizer inSt = new StringTokenizer(vIn, ":");
            int inHour = Integer.parseInt(inSt.nextToken());
            int inMin = Integer.parseInt(inSt.nextToken());
            int inTotalMin = inHour * 60 + inMin;

            StringTokenizer outSt = new StringTokenizer(vOut, ":");
            int outHour = Integer.parseInt(outSt.nextToken());
            int outMin = Integer.parseInt(outSt.nextToken());
            int outTotalMin = outHour * 60 + outMin;

            totalTime += (outTotalMin - inTotalMin);
        }

        if(in.size() == out.size()){
            String vIn = in.get(in.size() - 1);
            String vOut = out.get(out.size() - 1);

            StringTokenizer inSt = new StringTokenizer(vIn, ":");
            int inHour = Integer.parseInt(inSt.nextToken());
            int inMin = Integer.parseInt(inSt.nextToken());
            int inTotalMin = inHour * 60 + inMin;

            StringTokenizer outSt = new StringTokenizer(vOut, ":");
            int outHour = Integer.parseInt(outSt.nextToken());
            int outMin = Integer.parseInt(outSt.nextToken());
            int outTotalMin = outHour * 60 + outMin;

            totalTime += (outTotalMin - inTotalMin);
        }else{
            // in이 하나 더 많음
            String vIn = in.get(in.size() - 1);

            StringTokenizer inSt = new StringTokenizer(vIn, ":");
            int inHour = Integer.parseInt(inSt.nextToken());
            int inMin = Integer.parseInt(inSt.nextToken());
            int inTotalMin = inHour * 60 + inMin;

            int outTotalMin = 23 * 60 + 59;
            totalTime += (outTotalMin - inTotalMin);
        }

        return totalTime;
    }

}

class Record{
    List<String> in = new ArrayList<>();
    List<String> out = new ArrayList<>();
    int totalFee;

    @Override
    public String toString() {
        return "Record{" +
                "in=" + in +
                ", out=" + out +
                ", totalFee=" + totalFee +
                '}';
    }
}