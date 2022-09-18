import java.util.*;
import java.util.stream.*;

public class 두큐_합_같게_만들기 {
    static long TARGET_VALUE;
    static List<LinkedData> linkedDatas = new ArrayList<>();
    public static void main(String[] args) {
        두큐_합_같게_만들기 instance = new 두큐_합_같게_만들기();
        System.out.println(instance.solution(new int[]{3, 2, 7, 2}, new int[]{4, 6, 5, 1}));
    }

    // length of queue1 and length of queue2 always same
    public int solution(int[] queue1, int[] queue2) {
        long sum = Arrays.stream(queue1).sum() + Arrays.stream(queue2).sum();
        if(sum % 2 != 0){
            // if odd number, early return
            return -1;
        }

        TARGET_VALUE = sum / 2;

        int[] concatArr = IntStream.concat(IntStream.of(queue1), IntStream.of(queue2)).toArray();

        // construct
        for(int value: concatArr){
            LinkedData linkedData = new LinkedData(value);
            linkedDatas.add(linkedData);
        }

        // set next node
        for(int i = 0; i < linkedDatas.size() - 1; i++){
            LinkedData currData = linkedDatas.get(i);
            LinkedData nextData = linkedDatas.get(i + 1);
            currData.linkNextNode(nextData);
        }
        // set next node of last node
        linkedDatas.get(linkedDatas.size() - 1).linkNextNode(linkedDatas.get(0));

        List<int[]> boundaryCountList = findBoundaryAndCount();

        return getMinJob(boundaryCountList, queue1, queue2);
    }

    List<int[]> findBoundaryAndCount(){
        List<int[]> boundaryCountList = new ArrayList<>();
        // 1개 ~ total size - 1개 까지
        for(int count = 1; count <= linkedDatas.size() - 1; count++){
            int pointer = 0;
            while(true){
                int currCnt = 0;
                long sum = 0;
                LinkedData currData = linkedDatas.get(pointer);

                // pointer에서 count만큼 노드의 value를 더하기
                while(currCnt < count){
                    sum += currData.value;
                    currData = currData.nextNode;
                    currCnt++;
                }

                // if sum == TARGET_VALUE
                if(sum == TARGET_VALUE){
                    // {boundary, count}
                    boundaryCountList.add(new int[]{pointer, count});
                }

                pointer++;

                if(pointer == linkedDatas.size()){
                    // if last, finish while loop
                    break;
                }
            }
            
        }

        return boundaryCountList;
    }
    // 3 2 7 2 4 6 5 1
    // pop -> push : 1 job 
    // how to make get min job count?
    int getMinJob(List<int[]> boundaryCountList, int[] queue1, int[] queue2){
        int jobCount = -1;
        int start1 = 0;
        int start2 = linkedDatas.size() / 2;
        for(int[] boundaryAndCount: boundaryCountList){
            int subJobCount = 0;
            int boundary = boundaryAndCount[0];
            int count = boundaryAndCount[1];

            
        }

    } 
}
class LinkedData{
    int value;
    LinkedData nextNode;

    LinkedData(int value){
        this.value = value;
    }

    void linkNextNode(LinkedData nextNode){
        this.nextNode = nextNode;
    }
}

/**
 * 결국 못품.
 * 
 * 시도 방법
 * - 큐1, 큐2를 붙이면 하나의 원이됨. count1 ~ 큐 두개 사이즈 합만큼 돌면서 TARGET_VALUE가 되는 boundary와 count를 찾음
 * 그다음에 최소 job count를 구하면 되는데 최소 job count를 어떻게 구해야할지 감이안잡힘...
 *  */