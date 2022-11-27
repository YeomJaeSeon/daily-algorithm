import java.util.ArrayList;
import java.util.Collections;

class Solution {
    public int solution(int[] arrayA, int[] arrayB) {
        boolean std = false;
        // arrayA를 모두 나눌 수 있는 가장 큰 수
        int cb = getFactor(arrayA);
        if (cb != 1) {
            std = isDivide(arrayB, cb);
        }

        if (std == true) {
            return cb;
        } else {
            cb = getFactor(arrayB);
            if (cb != 1) {
                std = isDivide(arrayA, cb);
            }
            if (std == true) {
                return cb;
            } else {
                return 0;
            }
        }

    }

    // 해당 array에서 나눌 수 있는 가장 큰 수 구하기
    public int getFactor(int[] array) {
        int bigNumber = array[0];

        // 제곱근 구하기
        int sqrt = (int) Math.sqrt(bigNumber);
        // 약수 받을 ArrayList
        ArrayList<Integer> list = new ArrayList<>();

        // list에 약수 저장
        for(int i = 1; i <= sqrt; i++) {
            if(bigNumber % i == 0) {
                // 약수 중 작은 수 저장
                list.add(i);
                if(bigNumber / i != i) {
                    // 약수 중 큰 수 저장
                    list.add(bigNumber / i);
                }
            }
        }
        // 약수 list 정렬
        Collections.sort(list, Collections.reverseOrder());

        // 모든 수를 나눌 수 있는 만족하는 수
        int allDivide = 0;

        for(int i = 0; i < list.size(); i++) {
            int count = 0;
            for(int j = 0; j < array.length; j++) {
                if(array[j] % list.get(i) != 0) {
                    continue;
                }
                count++;
            }
            if(count == array.length) {
                allDivide = list.get(i);
                break;
            }
        }

        return allDivide;
    }

    // 해당 수가 나눌 수 있는 지 없는 지 확인하기
    public boolean isDivide(int[] array, int num) {
        boolean std = true;

        for(int i = 0; i < array.length; i++) {
            if(array[i] % num == 0) {
                std = false;
                break;
            }
        }

        return std;
    }
}

// 정확성 77.8
