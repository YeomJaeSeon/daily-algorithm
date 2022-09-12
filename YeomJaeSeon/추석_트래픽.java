package problems1;

import java.util.*;

// 초당 최대 처리량은 요청의 응답 완료 여부에 관계없이 임의 시간부터 1초(=1,000밀리초)간 처리하는 요청의 최대 개수를 의미한다
public class 추석_트래픽 {
    static final int ARRAY_LENGTH = 86400000;
    static List<boolean[]> list = new ArrayList<>();
    public static void main(String[] args) {
        추석_트래픽 instance = new 추석_트래픽();
        System.out.println(instance.solution(
            new String[]{
                "2016-09-15 01:00:04.001 2.0s",
                "2016-09-15 01:00:07.000 2s"
                }
        ));
    }

    /**
     * 
     * @param lines ["응답-완료된-시간 처리-시간", ..], 처리-시간: 0.001 <= <= 3.000
     * @return
     */
    public int solution(String[] lines) {
        for(String line: lines){
            String[] splitData = line.split(" ");
            String finishTime = splitData[1];
            int jobTime = partIntStringTime(splitData[2]);

            boolean[] boolArr = new boolean[ARRAY_LENGTH];
            int finishDateTime = getTime(finishTime);
            int startDateTime = finishDateTime - jobTime +1;

            for(int i = startDateTime; i <= finishDateTime; i++){
                boolArr[i] = true;
            }

            list.add(boolArr);
        }

        return getMax();
    }
    int getMax(){
        int max = 0;
        for(int i = 0; i < ARRAY_LENGTH - 1000; i++){
            int count = 0;
            for(boolean[] arr: list){
                if(arr[i]){
                    count++;
                }
            }
            max = Math.max(max, count);
        }
        return max;
    }
    // "0.123s" (String) -> 123 (int)
    int partIntStringTime(String jobTime){
        return (int)(Double.parseDouble(jobTime.substring(0, jobTime.length() - 1)) * 1000);
    }

    // hh:mm:ss.sss -> Int
    int getTime(String date){
        int result = 0;

        String[] splitData = date.split(":");
        String hour = splitData[0];
        result += (Integer.parseInt(hour) * 60 * 60 * 1000);

        String min = splitData[1];
        result += (Integer.parseInt(min) * 60 * 1000);

        String sec = splitData[2];
        StringTokenizer st = new StringTokenizer(sec, ".");
        String firstSec = st.nextToken();
        result += (Integer.parseInt(firstSec) * 1000);

        String secondSec = st.nextToken();
        result += (Integer.parseInt(secondSec));

        return result;
    }

}

// case 1
//1. 01:00:02.001 ~ 01:00:04.001 (2.0s)
//2. 01:00:05.000 ~ 01:00:07.000 (2s)

// 01:00:04.001 ~ 01:00:05.000 -> 01:00:05.000 겹치지않는다고 판단

// -> 초당 최대 처리량: 1초에 최대 한개

// case 2
//1. 01:00:02.002 ~ 01:00:04.002 (2.0s)
//2. 01:00:05.000 ~ 01:00:07.000 (2s)

// 01:00:04.002 ~ 01:00:05.001 -> 01:00:05.000 ~ 최대 두번

/**
 * 뽀인트: 처리시간이 시작시간과 끝 시간을 포함한다..?
 */

// 0 ~ 86399999
